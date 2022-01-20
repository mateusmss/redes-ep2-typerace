package br.usp.each.typerace.client;

import org.java_websocket.client.WebSocketClient;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

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
        Scanner ler = new Scanner(System.in);
        WebSocketClient client;

        System.out.println("Servidor localhost:8080 ");
        String server = entrada.readLine();

        if (server.isEmpty())
            server = "ws://localhost:8080";
        boolean condi��o = true;
        
        while(condi��o) {
            System.out.println("Digite seu nome de usu�rio: ");
            String nome = ler.nextLine();
            String conexao = server;

            if (nome.isEmpty()) {
                System.out.println("Digite um nome v�lido!");
                continue;
            }

            conexao += "/playerId=" + nome;

            client = new Client(new URI(conexao));
            ClientMain main = new ClientMain(client);

            main.init(nome);    
        }
        while(true) {
            String tentativa = ler.nextLine();
            client.send(tentativa);
        }
    
    
}
