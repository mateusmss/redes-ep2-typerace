
package br.usp.each.typerace.server;

public class typerace {
    private int maxPlacar, tamanhoLista, quantPlayer;
    private double timeInicial, timeFinal;
    private boolean rodando;
    private String[] listaPalavras;
    private Map<String, Player> players;
    
    typerace(){
        this.timeInicial = 0;
        this.quantPlayer = 0;
        this.listaPalavras = 100;
        this.players = new HashMap<>();
        this.maxPlacar = 10;
        
        private Map<String, Player> placar;
        private Set<String> listaPlavras;
        private int maxPlacar;
        private boolean jogoFinalizado;
        private long tempoInicio;
        private long tempoFinal;
    }
        
    public TypeRacer(Set<String> players, int nPalavras, ListaPalavras lista) {
        this.placar = new HashMap<>();
        this.maxPlacar = 10;
        this.initialTime = 0;
        this.jogoFinalizado = false;
        this.listaPlavras = new HashSet<>();
        

        for (String playerID : players) {
            placar.put(playerId, new Player(playerID, 0, 0, new HashSet<>(ListaPalavras)));
        }
    }
    public boolean verificarResposta(String playerID, String resposta){
        Player player = placar.get(playerID);

        if(player.verificarResposta(resposta)){
            if(player.getRespostaCorreta() == maxPlacar) {
                jogoFinalizado = true;
                timeFinal = new Date().getTime();
            }

            return true;
        }
        return false;
    }
        
    public void setTime() {
        this.time = System.nanoTime();
    }
    public Player getPlayer(String nome) {
        return players.get(nome);
    }
    public Set<String> getListaPalavras() {
        return listaPalavras;
    }
    
    public int getMaxPlacar() {
        return this.maxPlacar;
    }

    public String getListaString() {
        return setListaPalavras(getListaPalavras());
    }

    public boolean jogoFinalizado() {
        return this.jogoFinalizado;
    }

    public long duracaoJogo() {
        return (this.finalTime - this.initialTime) / 1000;
    }
    public String getListaPalavras() {
        return setToWordList(getListaPalavras());
    }
    public String setListaPalavras(Set<String> lista) {
        String novaLista = "| ";
        for (String s : lista) {
            novaLista += s + " | ";
        }
        return novaLista;
    }
    public List<Player> getPlacar() {
        List<Player> placar = new LinkedList<>(Placar.values());
        sortedScore.sort((a,b) -> b.compareTo(a));

        return placar;
    }

    public Set<String> getPlayerPalavras(String playerID) {
        return placar.get(playerID).getPalavras();
    }

    public String getWordsOfPlayerAsString(String playerID) {
        return setToWordList(getPlayerPalavras(playerID));
    }
}   
