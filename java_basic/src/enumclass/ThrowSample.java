package enumclass;

public class ThrowSample {
    public static void main(String[] args) throws Exception {
        ThrowSample throwSample = new ThrowSample();
        throwSample.throwException(13);
    }

    private void throwException(int num) {
        try {
            if(num>12) {
                throw new Exception("number is over 12");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
