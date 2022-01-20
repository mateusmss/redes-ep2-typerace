package br.usp.each.typerace.server;

import org.java_websocket.server.WebSocketServer;
import java.util.Scanner;
import java.util.HashMap;

public class ServerMain {

    private WebSocketServer server;

    public ServerMain(WebSocketServer server) {
        this.server = server;
    }

    public void init() {
        System.out.println("Iniciando servidor...");
        server.start();
    }

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int entradaPortaServidor;
        System.out.println("Servidor por padrão 8080 ");
        System.out.println("Aperte 0 para não escolher nenhuma porta ou digite uma porta de preferencia");
        entradaPortaServidor = entrada.nextInt();
        if(entradaPortaServidor == 0){
            entradaPortaServidor = 8080;
        }
        WebSocketServer server = new Server(entradaPortaServidor, new HashMap<>());
        ServerMain main = new ServerMain(server);
        main.init();
        System.out.println("\nTudo pronto.");
        
        
        
    }

}
