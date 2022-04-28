package ch4.item20;

import java.util.*;

// skeletal implementation을  구현한 구체 클래스
public class IntArrays {
    static List<Integer> intArrayAsList(int[] a) {
        Objects.requireNonNull(a);

        return new AbstractList<>() {
            @Override
            public Integer get(int i) {
                return a[i]; //오토 박싱
            }

            @Override
            public Integer set(int i, Integer val) {
                int oldVal = a[i];
                a[i] = val; //오토 언박싱
                return oldVal;
            }

            @Override
            public int size() {
                return a.length;
            }
        };
    }

    public static void main(String[] args) {
        int[] a = new int[10];
        for (int i = 0; i < a.length; i++) {
            a[i] = i;
        }
        List<Integer> list = intArrayAsList(a);
        Collections.shuffle(list);
        System.out.println(list);
    }
}
