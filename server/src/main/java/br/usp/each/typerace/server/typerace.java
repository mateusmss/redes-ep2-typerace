
package br.usp.each.typerace.server;

public class typerace {
    private int maxPlacar, tamanhoLista, quantPlayer;
    private double time;
    private boolean rodando;
    private String[] listaPalavras;
    private Map<String, Player> players;
    
    typerace(){
        this.time = 0;
        this.quantPlayer = 0;
        this.listaPalavras = 100;
        this.listaPalavras = 200;
        this.players = new HashMap<>();
        this.maxPlacar = 10;
    }
    
    

    
}
