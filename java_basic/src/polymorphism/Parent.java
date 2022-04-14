package polymorphism;

public class Parent {
    public Parent() {
        System.out.println("Parent Constructor");
    }

    public void printName() {
        System.out.println("I am Parent");
    }

    public void printName(String name) {
        System.out.println("I am " + name);
    }
}
