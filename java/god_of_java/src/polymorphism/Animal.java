package polymorphism;

public class Animal {
    public void printInfo() {
        System.out.println("This is Animal");
    }
    public void printInfo(String name) {
        System.out.println("This is Animal whose name is " + name);
    }
}
