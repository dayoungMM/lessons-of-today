package polymorphism;

public class ExePoly {
    public static void main(String[] args) {
        Parent parA = new Parent();
        Parent childA = new Child();
        parA.printName();
        childA.printName();
    }
}
