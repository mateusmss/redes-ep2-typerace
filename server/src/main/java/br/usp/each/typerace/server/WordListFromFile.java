package br.usp.each.typerace.server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

// Essa classe le e seleciona palavras de um arquivo

public class WordListFromFile implements WordListMaker {

    private static String filePath = "";
    private static List<String> allWords;

    public WordListFromFile(String filePath) {

        // Checa se esse arquivo ja foi utilizado anteriormente para selecionar palavras
        // Isso poupa tempo de processamento desnecessario, caso a lista carregada seja
        // a mesma
        if (!filePath.equals(WordListFromFile.filePath)) {
            WordListFromFile.filePath = filePath;
            allWords = new ArrayList<>();

            try {
                BufferedReader input = new BufferedReader(new FileReader(filePath));

                String line;
                while ((line = input.readLine()) != null) {
                    allWords.add(line);
                }
            } catch (Exception e) {
                System.out.println("Erro ao ler arquivo");
                e.printStackTrace();
            }
        }
    }

    // Vai selecionar uma quantidade numberOfWords de palavras aleatorias e
    // adicionar num set
    // Essas palavras serao utilizadas para o jogo
    public Set<String> selectWords(int numberOfWords) {

        Set<String> selectedWords = new HashSet<>();
        Random rand = new Random();

        for (int i = 0; i < numberOfWords;) {
            int randNum = rand.nextInt(allWords.size());
            String selectedWord = allWords.get(randNum);

            // Esse if impede que palavras repetidas sejam selecionadas
            if (!selectedWords.contains(selectedWord)) {
                selectedWords.add(selectedWord);
                i++;
            }
        }

        return selectedWords;
    }
}