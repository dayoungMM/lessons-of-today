package di;

public class BeanFactory {
    public void store() {
        Product pencil = new Pencil();
        Store store = new Store(pencil);
    }
}





