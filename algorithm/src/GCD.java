

public class GCD {
    private static int test(int big, int small) {
        if (small == 0) {
            return big;
        }
        return test(small, big%small);
    }

    public static void main(String[] args) {
        int a = 24;
        int b = 16;
        int result = (a > b) ? test(a,b) : test(b,a);
        System.out.println(result);
    }
}
