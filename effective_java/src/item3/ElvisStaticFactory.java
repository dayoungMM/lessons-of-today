package item3;

public class ElvisStaticFactory {
    public static final ElvisStaticFactory INSTANCE = new ElvisStaticFactory();
    private ElvisStaticFactory() {
        System.out.println("private Elvis");
    }
    public static ElvisStaticFactory getInstance() {return INSTANCE;}
    public void leaveTheBuilding() {
        System.out.println("leaveTheBuilding");
    }
    private Object readResolve() {
        return INSTANCE;
    }
}
