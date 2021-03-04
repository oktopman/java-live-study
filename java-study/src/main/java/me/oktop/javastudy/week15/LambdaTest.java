package me.oktop.javastudy.week15;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LambdaTest {

    public static String processFile(BufferedReaderProcessor brp, String filePath) {
        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            line = brp.process(br);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }

    public static void main(String[] args) {
        BufferedReaderProcessor bufferedReaderProcessor = br -> br.readLine() + br.readLine();
        processFile(bufferedReaderProcessor, "aaa.txt");

    }
}
