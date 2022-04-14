package java8;

import java.util.Optional;

public class OptionalExample {
    public static void main(String[] args) {
        Optional<String> emptyString = Optional.empty();
        System.out.println(emptyString.isPresent());

        String common = null;
        Optional<String> nullableString = Optional.ofNullable(common);
        System.out.println(nullableString.isPresent());

        common = "common";
        Optional<String> commonString = Optional.of(common);
        System.out.println(commonString.isPresent());
    }
}
