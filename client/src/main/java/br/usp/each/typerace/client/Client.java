package br.usp.each.typerace.client;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

public class Client extends WebSocketClient {

    public Client(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
         System.out.println("Sala aberta!");
    }

    @Override
    public void onMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        if(!reason.equalsIgnoreCase("invalidName")) {
            System.out.println("Voc� est� desconectado!");
            System.exit(0);
        }
    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
    }
}
