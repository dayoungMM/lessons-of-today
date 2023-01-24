package java8;

import java.util.function.Predicate;

public class PredictExample {
    public static void main(String[] args) {
        PredictExample sample = new PredictExample();

        Predicate<String> predicateLength5 = (a) -> a.length()>5;
        Predicate<String> predicateContains = (a) -> a.contains("God");

        String godOfJava = "GodOfJava";
        sample.predicateTest(predicateLength5, godOfJava);
        sample.predicateAnd(predicateContains,predicateLength5, godOfJava);

    }

    private void predicateTest(Predicate<String> p, String data) {
        System.out.println(p.test(data));
    }

    private void predicateAnd(Predicate<String> p1, Predicate<String> p2, String data) {
        System.out.println(p1.and(p2).test(data));
    }
}
