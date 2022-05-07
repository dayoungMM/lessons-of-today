package ch7.item46;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;

public class Freq {

    public static void main(String[] args) {
        File file = new File(args[0]);
        Map<String, Long> freq;
        try (Stream<String> words = new Scanner(file).tokens()) {
            freq = words
                       .collect(groupingBy(String::toLowerCase, counting()));

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println(freq);

        List<String> topTen = freq.keySet().stream()
                                  .sorted(comparing(freq::get).reversed())
                                  .limit(10)
                                  .collect(toList());

        System.out.println(topTen);
    }

}
