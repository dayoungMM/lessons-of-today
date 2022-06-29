import java.util.ArrayDeque;
import java.util.Deque;

public class Palindrome {
    private static boolean test(String str) {
        String target = str.toLowerCase();
        Deque<String> stack = new ArrayDeque<>();
        for (int i=0; i<target.length(); i++) {
            stack.push(String.valueOf(target.charAt(i)));
        }

        System.out.println("stack: " + stack);
        for (int i=0; i<target.length(); i++) {
            String p = stack.pop();
            String t = String.valueOf(target.charAt(i));
            if (!p.equals(t)) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        String str = "abcba";
        System.out.println("answer: " + test(str));
    }
}
