package br.usp.each.typerace.server;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.util.Map;

public class Server extends WebSocketServer {

    private final Map<String, WebSocket> connections;

    public Server(int port, Map<String, WebSocket> connections) {
        super(new InetSocketAddress(port));
        this.connections = connections;
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        System.out.println("Iniciando conexão");
        novaconexao(conn);
        
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        if(!reason.equalsIgnoreCase("invalidName")) {
            connections.remove(getPlayerId(conn));
            broadcast(getPlayerId(conn.getResourceDescriptor()) + " saiu da sala!\n" + line);
            System.out.println("Conexao perdida: " + getPlayerId(conn.getResourceDescriptor()) + " [" + conn.getRemoteSocketAddress().getAddress().getHostAddress() + "]");
        }
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        System.out.println(message);
        // Essa mensagem eh apenas a mensagem de retorno de conexao com o cliente
        // Nao deve ser considerada como input do usuario
        if(message.equalsIgnoreCase("###Nova conexao feita com sucesso###")) {
            System.out.println("Nova conexao feita com sucesso");
            return;
        }

        // Comandos de iniciar e finalizar o jogo so podem ser feitos enquanto nao tem nenhuma partida executando
        // Ou seja, enquanto o jogo eh null
        if(game == null) {

            String[] inputArray = message.split(" ");

            if (inputArray[0].equalsIgnoreCase("Iniciar")) {
                try {
                    int nPalavras = Integer.parseInt(inputArray[1]);
                    int placarMax = Integer.parseInt(inputArray[2]);

                    // Chama o metodo que cria uma partida e retorna uma string com as palavras do jogo para serem trasmitidas
                    String listaPalavras = startGame(nPalavras, placarMax);

                    if(listaPalavras.equals("")) {
                        conn.send("A pontuacao maxima nao pode ser maior do que a quantidade de palavras no jogo!\nPor favor, tente novamente!\n" + line);
                    }

                    else {
                        broadcast("Jogo iniciado! Lista de palavras:\n" + wordList + "\n" + line);
                    }
                }
                // Se a pessoa digitou "iniciar" e nao colocou os complementos de forma devida, ele entra nesse catch e envia essa mensagem
                catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                    conn.send("Comando invalido!\nPor favor, tente novamente!\nLembre-se de digitar: Iniciar [quantidade de palavras] [pontuacao maxima]\n" + line);
                }
            }
            else if (message.equalsIgnoreCase("Sair")) {
                conn.close();
            }
            else {
                conn.send("Comando invalido!\n" + line + "\n");
            }
        }
        // Nesse else, o game nao eh null, ou seja, a partida esta rodando
        // Nesse caso, todo input de usario eh considerado uma tentativa de resposta
        else {
            String playerId = getPlayerId(conn.getResourceDescriptor());

            if(game.checkAnswer(playerId, message)) {
                conn.send("Resposta correta!\n" + line);
            }
            else {
                conn.send("Resposta incorreta :(\n" + line);
            }

            // Se chegou na pontuacao maxima, vai transmitir a mensagem de resultado do jogo para todos os jogadores
            if(game.isGameFinished()) {
                resultadoGames();
                game = null;
            }
            // Se o jogo nao acabou, manda para o jogador atual quais palavras faltam para ele acertar
          
                String remainingWords = game.getWordsOfPlayerAsString(playerId) + "\n" + line;
                conn.send(remainingWords);  // (acertando a tentativa atual ou nao)
            else {
                String remainingWords = game.getWordsOfPlayerAsString(playerId) + "\n" + line;
                conn.send(remainingWords);
            }
        }
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        if(conn != null){
            System.out.println("Problema de conexão");
        }
    }

    @Override
    public void onStart() {
        System.out.println("Servidor está iniciando");
    }
     public int nConexoes() {
        return connections.size();
    }
     private String startGame(int nPalavras, int placar) {
        if(placar > nPalavras) {
            return "";
        }
        String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\listaDePalavras.txt";
        this.game = new typerace(connections.keySet(), nPalavras, placar, new WordListFromFile(filePath));

        return game.getListaString();
    }
     private void resultadoGames() {

        String result = "Jogo encerrado!\nPlacar [Nome - Acertos - Erros]:\n";
        int count = 0;
        for(Player player : game.getPlacar()) {
            result += (++count) + "- " + player + "\n";
        }

        result += "\nDuracao do jogo: " + game.duracaoJogo() + " s\n" + line;
        broadcast(result);
    }
     private void novaconexao(WebSocket conn) {

        String playerId = getPlayerId(conn);
        this.connections.put(playerId, conn);

        broadcast("\n" + line + "\n" + playerId + " entrou na sala!" + "\nNumero de jogadores: " + nConexoes() + "\n" + line);

        conn.send("Regras: Quem conseguir a maior pontuação é o vencedor" +
                "\n Envie uma palavra por vez\n" +
                "\n Comandos:" +
                "\n Para iniciar um jogo digite: \"Iniciar [quantidade de palavras] [pontuacao maxima]\"" +
                "\n Para sair de um jogo digite: \"Sair\""+
                "\n + line);

        System.out.println("Conexão récem criada: " + playerId + " [" + conn.getRemoteSocketAddress().getAddress().getHostAddress() + "]");
    }
     
    private String getPlayerId(WebSocket conn) {
        return getPlayerId(conn.getResourceDescriptor());
    }

    private boolean playerIdExists(WebSocket conn) {
        if(connections.containsKey(getPlayerId(conn))) {
            conn.send("Nome de jogador já existênte!\n" + line);
            conn.close(1000, "NomeInvalido");
            return true;
        }

        return false;
    }
}
