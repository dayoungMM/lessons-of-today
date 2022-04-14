package enumclass;

public class multiCatch {
    public static void main(String[] args) {
        int[] intArray = new int[5];
        try {

        } catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("ArrayIndexOutOfBoundsException");
        } catch (Exception e) {
            System.out.println("Exception");
        }
    }
}
