package genericExample;

public class WildCardExample {
    public static void main(String[] args) {
        WildCardExample sample = new WildCardExample();
        sample.callWildCardMethod();
    }

    public void callWildCardMethod() {
        WildGeneric<String> wildcard = new WildGeneric<String>();
        wildcard.setWildcard("A");
        wildcardStringMethod(wildcard);
    }

    public void wildcardStringMethod(WildGeneric<String> c) {
        String value = c.getWildcard();
        System.out.println(value);
    }
}
