package ch2.item3;

public class Elvis {
    public static final Elvis INSTANCE = new Elvis();
    private Elvis() {
        System.out.println("private Elvis");
    }
    public void leaveTheBuilding() {
        System.out.println("leaveTheBuilding");
    }
}
