
package br.usp.each.typerace.server;
import java.util.Set;
public class java {
    private Set<String> palavras;
    private String playerID;
    private int correto,errado;
    
    public Player(String playerID, int errado, int correto, Set<String> listaPalavras){
        this.playerID = playerID;
        this.errado = errado;
        this.correto = correto;
        this.palavras = listaPalavras;
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
    public Set<String> getPalavras() {
        return this.listaPalavras;
    }

    public boolean checkAnswer(String palavraResposta) {

        if(getPalavras().contains(palavraResposta.toUpperCase())){
            setRespostaCorreta();
            getPalavras().remove(palavraResposta.toUpperCase());
            return true;
        }else{
            setRespostaErrada();
        }
        return false;
    }

    public boolean checkAnswer(String resposta) {

        if(getPalavras().contains(resposta.toUpperCase())){
            setRespostaCorreta();
            getPalavras().remove(resposta.toUpperCase());
            return true;
        }else{
            setRespostaErrada();
            return false;
        }
    }
    public int compareTo(Player x) {
        if(this.correto != x.correto){
            return this.correto - x.correto;
        }else{
            return x.errado - this.errado;
    }
        
    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        } 
        if (o == null || getClass() != o.getClass()){
            return false;
        } 
        
        Player player = (Player) o;
        return playerId.equals(player.playerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerId, correto, errado);
    }
    
    public String toString() {
        return this.playerId + " - " + this.correto + " - " + this.errado;
    }

        
    }
    
    
}
