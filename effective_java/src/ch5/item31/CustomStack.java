package ch5.item31;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.EmptyStackException;

public class CustomStack<E> {
    private E[] elements;
    private int size=0;
    private static final int DEFAULT_INITIAL_CAPACITY=16;

    // 배열 elements는 push(E)로 넘어온 E 인스턴스만 담음.
    // 타입 안정성을 보장하지만 이 배열의 런타임 타입은 E[]가 아닌 Object[] 다
    @SuppressWarnings("unchecked")
    public CustomStack() {
        elements = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(E e) {
        ensureCapacity();
        elements[size++] = e;
    }

    private void ensureCapacity() {
        if (elements.length == size)
            elements = Arrays.copyOf(elements, 2*size + 1);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E pop() {
        if (size==0)
            throw new EmptyStackException();
        E result = elements[--size];
        elements[size] = null; //다쓴 참조 해제
        return result;
    }

    //producer 매개변수에 와일드 카드 타입 적용
    // E의 하위타입의 Iterable 타입을 매개변수로
    public void pushAll(Iterable<? extends  E> src) {
        for (E e: src)
            push(e);
    }

    //consumer 매개변수에 와일드 카드 타입 적용
    // E의 상위 타입의 Collection이어야 한다
    public void popAll(Collection<? super E> dst) {
        while (!isEmpty()) {
            dst.add(pop());
        }
    }

    public static void main(String[] args) {
        CustomStack<Number> numberStack = new CustomStack<>();
        Iterable<Integer> integers = Arrays.asList(3, 1, 4, 1, 5, 9);
        numberStack.pushAll(integers);

        Collection<Object> objects = new ArrayList<>();
        numberStack.popAll(objects);

        System.out.println(objects);
    }

}
