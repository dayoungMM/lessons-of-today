package enumclass;

public class OvertimeManager {
    public static void main(String[] args) {
        OvertimeValues values = OvertimeValues.FIVE_HOUR;
        System.out.println(values);
        System.out.println(values.getAmount());
    }
}
