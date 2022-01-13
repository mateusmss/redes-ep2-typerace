
package br.usp.each.typerace.server;

public class java {
    private Set<String> 
    private String playerID;
    private int correto,errado;
    
    public Player(String playerID, int errado, int correto){
        this.playerID = playerID;
        this.errado = errado;
        this.correto = correto;
    }
    
    public String getPlayerID(){
        return this.playerID;
    }
    public void setRespostaErrada(){
        this.errado++;
    }
    public void setRespostaCorreta(){
        this.correto++;
    }
    public int getRespostaCorreta(){
        return this.correto;
    }
    public int getRespostaErrada(){
        return this.errado;
    }
    
}
