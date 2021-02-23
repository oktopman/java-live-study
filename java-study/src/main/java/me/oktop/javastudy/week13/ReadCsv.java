package me.oktop.javastudy.week13;

import me.oktop.javastudy.week9.Ex;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ReadCsv {

    public static void main(String[] args) {
        try (BufferedReader br = Files.newBufferedReader(Paths.get("/Users/hayun/Desktop/test.csv"))) {
            String line;
            List<Vo> vos = new ArrayList<>();

            while ((line = br.readLine()) != null) {
                String[] array = line.split(",", -1);
                Vo vo = new Vo();
                vo.setName(StringUtils.isEmpty(array[0]) ? null : array[0]);
                vo.setAmount(value(() -> new BigDecimal(array[1]), null));
                vos.add(vo);
            }
            vos.stream()
                    .forEach(System.out::println);

        } catch (IOException e) {
            // todo 로그 찍고 throw exception
//            logger.error("message............. {}", e);
        }
    }

    static <T> T value(Supplier<T> supplier, T defaultValue) {
        try {
            T t = supplier.get();
            return t;
        } catch (Exception e) {
            return defaultValue;
        }
    }

}
