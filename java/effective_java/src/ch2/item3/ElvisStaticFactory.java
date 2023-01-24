package ch2.item3;

public class ElvisStaticFactory {
    //static 멤버 private으로 막고
    private static final ElvisStaticFactory INSTANCE = new ElvisStaticFactory();
    private ElvisStaticFactory() {
        System.out.println("private Elvis");
    }

    //메서드로 인스턴스를 가져옴
    public static ElvisStaticFactory getInstance() {return INSTANCE;}
    public void leaveTheBuilding() {
        System.out.println("leaveTheBuilding");
    }
    private Object readResolve() {
        return INSTANCE;
    }
}
