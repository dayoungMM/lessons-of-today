package java8;

public class LabmdaExample {
    public static void main(String[] args) {

    }

    private void calculateLambda() {
        Calculate calcAdd = (a,b) -> a+b;
        System.out.println(calcAdd.operation(1,2));
        Calculate calcSub = (a,b) -> a-b;
        System.out.println(calcSub.operation(4,3));
    }
}
