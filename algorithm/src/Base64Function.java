import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64Function {
    private  static String originalBase64(String str) {
        byte[] message = str.getBytes(StandardCharsets.UTF_8);
        String encoded = Base64.getEncoder().encodeToString(message);
        return encoded;
    }
    private static String test(String str) {
        String result = "";
        String[] baseChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".split("");
        //ASCII 코드
        byte[] bytes = str.getBytes(StandardCharsets.US_ASCII);
        //이진수로 변환
        String binary = "";
        for (byte b : bytes) {
            binary +=  String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');;
        }
        //padding
        int paddingCnt = 6 - (binary.length() % 6);
        int lastPadding = binary.length() % 3;
        for (int i=0; i<paddingCnt; i++) {
            binary += "0";
        }
        //문자 매칭
        for (int i=0; i< binary.length()/6 ; i++){
            String b = binary.substring(i*6, (i+1)*6);
            int index = Integer.parseInt(b, 2);
            result += baseChars[index];
        }
        //padding
        for (int i=0; i<lastPadding; i++) {
            result += "=";
        }

        return result;
    }

    public static void main(String[] args) {
        String target = "klmn";
        System.out.println(test(target));
        System.out.println(originalBase64(target));
    }
}
