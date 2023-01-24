package overLoadingExample;

public class MethodVarags {
    public static void main(String[] args) {
        MethodVarags vargs = new MethodVarags();
        vargs.calculateNumWithArray(new int [] {1,2,3,4});
        vargs.calculateNum(1,2,3,4,5);
        vargs.boxing();
    }

    public void calculateNumWithArray(int [] args) {
        System.out.println("calculateNumWithArray");
        for (int i : args) {
            System.out.println(i);
        }
        System.out.println("end");
    }

    public void calculateNum(int...numbers) {
        System.out.println("calculateNum");
        for (int i : numbers) {
            System.out.println(i);
        }
        System.out.println("calculateNum");
    }

    public void boxing() {
        int a = 3;
        Integer wInt1 = a;
        Integer wInt2 = a;
        System.out.println(wInt1 == wInt2);

    }
}
