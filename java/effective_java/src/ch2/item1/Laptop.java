package ch2.item1;

public class Laptop {
    private String model;
    private String company;

    public static Laptop ofModelNameAndCompany(
            String modelName, String company) {
        Laptop laptop = new Laptop();
        laptop.company = company;
        laptop.model = modelName;
        return laptop;
    }
}
