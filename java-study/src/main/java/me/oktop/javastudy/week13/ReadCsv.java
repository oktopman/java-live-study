package me.oktop.javastudy.week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ReadCsv {

    public static void main(String[] args) {
        try (BufferedReader br = Files.newBufferedReader(Paths.get("/Users/hayun/Desktop/test.csv"))) {
            String line;
            List<Vo> vos = new ArrayList<>();

            while ((line = br.readLine()) != null) {
                String[] array = line.split(",");
                Vo vo = new Vo();
                vo.setName(array[0]);
                vo.setAmount(new BigDecimal(array[1]));
                vos.add(vo);
            }
            vos.stream()
                    .forEach(System.out::println);

        } catch (IOException e) {
            // todo 로그 찍고 throw exception
//            logger.error("message............. {}", e);
        }
    }
}
