package ch9.item65;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Set;

public class ReflectionInstantiation {

    public static void main(String[] args) {
        Class<? extends Set<String>> cl = null;
        try {
            // 비검사 형변환
            cl = (Class<? extends Set<String>>) Class.forName(args[0]);
        } catch (ClassNotFoundException e) {
            fatalError("클래스를 찾을 수 없습니다.");
        }

        //생성자를 얻는다
        Constructor<? extends Set<String>> cons = null;
        try {
            cons = cl.getDeclaredConstructor();
        } catch (NoSuchMethodException e) {
            fatalError("매개변수 없는 생성자를 찾을 수 없습니다.");
        }

        //Set의 인스턴스를 만들기
        Set<String> s = null;
        try {
            s = cons.newInstance();
        } catch (InvocationTargetException e) {
            fatalError("생성자가 예외를 던졌습니다: " + e.getCause());
        } catch (InstantiationException e) {
            fatalError("클래스를 인스턴스화할 수 없습니다.");
        } catch (IllegalAccessException e) {
            fatalError("생성자에 접근할 수 없습니다.");
        } catch (ClassCastException e) {
            fatalError("Set을 구현하지 않은 클래스 입니다.");
        }

        //생성한 Set 사용
        s.addAll(Arrays.asList(args).subList(1, args.length));
        System.out.println(s);
    }



    private static void fatalError(String msg) {
        System.err.println(msg);
        System.exit(1);
    }
}
