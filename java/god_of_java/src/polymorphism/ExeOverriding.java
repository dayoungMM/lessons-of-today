package polymorphism;

public class ExeOverriding {
    public static void main(String[] args) {
        Animal animal = new Animal();
        Cat cat = new Cat();
        animal.printInfo();
        cat.printInfo();
    }
}
