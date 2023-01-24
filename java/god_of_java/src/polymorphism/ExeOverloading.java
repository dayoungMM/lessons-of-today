package polymorphism;

public class ExeOverloading {
    public static void main(String[] args) {
        Animal animal = new Animal();
        animal.printInfo();
        animal.printInfo("Jack");
    }
}
