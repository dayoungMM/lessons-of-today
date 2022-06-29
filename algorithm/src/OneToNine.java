public class OneToNine {

    public static boolean test(String target) {
        String[] chars = target.split("");
        int[] arr = {1,1,1,1,1,1,1,1,1};
        for (int i=0; i<chars.length; i++) {
            // index는 0부터 시작하고, target은 1~9 숫자라서 minus 1
            int charInt = Integer.parseInt(chars[i]) - 1;
            if (arr[charInt] != 1) {
                //arr[charInt] == 1이라면 이미 해당 숫자 나왔었던것(중복)
                return false;
            }
            arr[charInt] = 0;
        }
        return true;
    }
    public static void main(String[] args) {
        String target = "123456789";
        System.out.println("result: " + test(target));
    }
}
