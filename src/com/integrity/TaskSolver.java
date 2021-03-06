package com.integrity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by Michael Rudyy on 06-Sep-17.
 */

public class TaskSolver {

    private static List<String> wordsList;
    private static HashMap<String, Cell<Integer, Integer>> dictionaryMap;
    private static int wordsMinLenght;
    public static int levelOfRecursion = 0;

    public static void init(String path) throws IOException {
        SetWordsLists(path);
    }

    public static List getLongestConcatenatedWords(int amount) {
        List<String> listOfLongestConcatenatedWords = new ArrayList<>();
        if (checkWordListIsNull()) return listOfLongestConcatenatedWords;

        List<String> sortedByLenghtWordList = new ArrayList<>(wordsList);
        sortedByLenghtWordList.sort((w1, w2) -> w2.length() - w1.length());

        for (int i = 0; i < sortedByLenghtWordList.size(); i++) {
            if (amount != 0) {
                if (checkConcatenation(sortedByLenghtWordList.get(i))) {
                    listOfLongestConcatenatedWords.add(sortedByLenghtWordList.get(i));
                    amount--;
                }
            } else {
                break;
            }
        }
        return listOfLongestConcatenatedWords;
    }

    public static int getAmountOf() {
        int amount = 0;
        if (checkWordListIsNull()) return amount;
        for (String word : wordsList) {
            if (checkConcatenation(word)) amount++;
        }
        return amount;
    }

    private static boolean checkWordListIsNull() {
        if (wordsList == null) {
            return true;
        }
        return false;
    }

    public static boolean checkConcatenation(String word) {
        Cell<Integer, Integer> cellOfWord;

        if ((levelOfRecursion != 0) && (word.length() == 0)) return true;

        // Проверка на валидность Cell
        if (word.length() >= wordsMinLenght) {
            cellOfWord = dictionaryMap.get(word.substring(0, wordsMinLenght));
            if (cellOfWord == null) return false;
        } else {
            return false;
        }

        for (int i = cellOfWord.getValue1(); i < cellOfWord.getValue2(); i++) {

            if (word.startsWith(wordsList.get(i)) &&
                    ((!word.equals(wordsList.get(i))) || (levelOfRecursion != 0))) {
                levelOfRecursion++;
                if ((wordsList.get(i).length() <= word.length()) &&
                        checkConcatenation(word.substring(wordsList.get(i).length()))) {
                    levelOfRecursion--;
                    return true;
                }
                levelOfRecursion--;
            }
        }
        return false;
    }

    private static void SetWordsLists(String path) throws IOException {
        wordsMinLenght = Integer.MAX_VALUE;
        String tmpWord;
        wordsList = new ArrayList<>();

        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));

        while (((tmpWord = bufferedReader.readLine()) != null) && (!tmpWord.isEmpty())) {
            wordsList.add(tmpWord);
            if (tmpWord.length() < wordsMinLenght) wordsMinLenght = tmpWord.length();
        }

        bufferedReader.close();

        dictionaryMap = new HashMap<>();
        tmpWord = "*";
        int firstLocate = 0;

        for (int i = 0; i < wordsList.size(); i++) {
            if (!wordsList.get(i).startsWith(tmpWord)) {
                dictionaryMap.put(tmpWord, new Cell<>(firstLocate, i));
                firstLocate = i;
                tmpWord = wordsList.get(i).substring(0, wordsMinLenght);
            }
            if (i == wordsList.size()-1) {
               // System.out.println(tmpWord + " : " + firstLocate + " " + wordsList.size());
                dictionaryMap.put(tmpWord, new Cell<>(firstLocate, wordsList.size()));
            }
        }
        dictionaryMap.remove("*");
    }
}