package br.usp.each.typerace.client;

import org.java_websocket.client.WebSocketClient;

import java.net.URI;
import java.net.URISyntaxException;

public class ClientMain {

    private WebSocketClient client;

    public ClientMain(WebSocketClient client) {
        this.client = client;
    }

    public void init(String idCliente) {
        System.out.println("Iniciando cliente: " + idCliente);
        client.connect();
    }

    public static void main(String[] args) {
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
        WebSocketClient client;

        System.out.println("Servidor localhost:8080 ");
        String server = entrada.readLine();

        if (server.isEmpty())
            server = "ws://localhost:8080";
        boolean condição = true;
        while(condição) {
            System.out.println("Digite seu nome de usuárioo: ");
            String nome = input.readLine();
            String conexao = server;

            if (nome.isEmpty()) {
                System.out.println("Digite um nome válido!");
                continue;
            }

            conexao += "/playerId=" + nome;

            client = new Client(new URI(conexao));
            ClientMain main = new ClientMain(client);

            main.init(nome);

    }
    
    
}
