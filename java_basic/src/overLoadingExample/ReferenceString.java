package overLoadingExample;

public class ReferenceString {
    public ReferenceString() {}
    public ReferenceString(String arg) {}

    public static void main(String[] args) {
        ReferenceString refStr = new ReferenceString();
        ReferenceString refStr2 = new ReferenceString("hello");
    }
}
