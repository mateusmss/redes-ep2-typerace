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
        
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        System.out.println(message);
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
}
