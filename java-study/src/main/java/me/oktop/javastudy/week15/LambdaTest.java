package me.oktop.javastudy.week15;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Supplier;

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

    public static String processFile(String filePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            return br.readLine();
        }
    }

    public static void main(String[] args) {
        BufferedReaderProcessor bufferedReaderProcessor = br -> br.readLine() + br.readLine();
        processFile(bufferedReaderProcessor, "aaa.txt");
    }
}
