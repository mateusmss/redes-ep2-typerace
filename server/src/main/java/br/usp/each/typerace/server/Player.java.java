
package br.usp.each.typerace.server;
import java.util.Set;
public class java {
    private Set<String> palavras;
    private String playerID;
    private int correto,errado;
    
    public Player(String playerID, int errado, int correto, Set<String>palavras){
        this.playerID = playerID;
        this.errado = errado;
        this.correto = correto;
        this.palavras = palavras;
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
    public Set<String> getPalavras(){
        return this.palavras;
    }
    //Métodos de comparação
    
    //verificar se a resposta está correta ou errada
    public boolean compararPalavras(String entrada, String respostaCorreta){
        if(getPalavras().contains(respostaCorreta){
            setRespostaCorreta();
            return true;
        }else{
            setRespostaErrada();
            return false;
        }
    }
    
}
