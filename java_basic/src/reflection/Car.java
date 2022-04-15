package reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Car {
    private final String name;
    private int position;

    public static void main(String[] args) {
        Object obj = new Car("foo", 0);
        Class carClass = Car.class;
        Method move = null;
        try {
            move = carClass.getMethod("move");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        try {
            move.invoke(obj, null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        Method position = null;
        try {
            position = carClass.getMethod("getPosition");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        try {
            System.out.println(position.invoke(obj, null));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        ;
    }
    public Car(String name, int position) {
        this.name = name;
        this.position = position;
    }

    public void move() {
        this.position++;
    }

    public int getPosition() {
        return position;
    }
}