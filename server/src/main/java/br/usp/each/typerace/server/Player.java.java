
package br.usp.each.typerace.server;
import java.util.Set;
public class java {
    private int listaPalavras;
    private String playerID;
    private int correto,errado;
    
    public Player(String playerID, int errado, int correto){
        this.playerID = playerID;
        this.errado = errado;
        this.correto = correto;
        this.listaPalavras = 0;
    }
    //Geters e seters
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
    public int getPalavras(){
        return this.listaPalavras;
    }
    public void incrementarListaPalavras(){
        this.listaPalavras++;
    }
    
    //Métodos de comparação
    
    //verificar se a resposta está correta ou errada
    public boolean compararPalavras(String entrada, String respostaCorreta){
        if(correto.equals(entrada)){
            setRespostaCorreta();
        }else{
            setRespostaErrada();
        }
        incrementarListaPalavras();
    }
    
}
