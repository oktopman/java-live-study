package me.oktop.javastudy.week9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TryWithResourceExample {

//    public static void main(String[] args) {
//        Scanner sc = null;
//        try {
//            sc = new Scanner(new File("test.txt"));
//            while (sc.hasNext()) {
//                System.out.println(sc.nextLine());
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } finally {
//            if (sc != null) {
//                sc.close();
//            }
//        }
//    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(new File("test.txt"))) {
            System.out.println("asdasd");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
