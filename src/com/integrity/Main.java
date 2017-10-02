package com.integrity;

import java.io.IOException;

/**
 * Created by Michael Rudyy on 06-Sep-17.
 */
public class Main {

    private final static String TEXT_FILE_PATH = "resources/words.txt";

    public static void main(String[] args) {
        try {
            TaskSolver.init(TEXT_FILE_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(TaskSolver.getLongestConcatenatedWords(3));
        System.out.println(TaskSolver.getAmountOf());
    }
}
