# Overloading / Overriding

## Overloading

### 개념

- 하나의 클래스 안에서 같은 이름의 매서드를 여러개 정의
- 매개변수의 개수가 다르거나, 매개변수의 타입이 달라야한다

```java
public class Animal {
    public void printInfo() {
        System.out.println("This is Animal");
    }
    public void printInfo(String name) {
        System.out.println("This is Animal whose name is " + name);
    }
}
```

```java
public class ExeOverloading {
    public static void main(String[] args) {
        Animal animal = new Animal();
        animal.printInfo();
        animal.printInfo("Jack");
    }
}

```

결과

```java
This is Animal
This is Animal whose name is Jack
```

### 오버로딩 사용하는 이유

- 다양한 매개변수를 받는 매서드를 만들어 유연한 코드를 작성할 수 있다.
- 생성자에 사용하는 경우 객체를 생성할 때 상황에 따라 다른 매개변수를 받아 생성할 수 있다.

## Overriding

### 개념

- 상속이 일어날 때 상위 클래스가 가지고 있는 메서드를 하위 클래스가 재정의해서 사용하는 것
- 상위 클래스의 메서드를 하위 클래스의 메서드로 덮어씌우는 것

```java
public class Cat extends Animal{
    @Override
    public void printInfo() {
        System.out.println("This is Cat");
    }
}
```

```java
public class ExeOverriding {
    public static void main(String[] args) {
        Animal animal = new Animal();
        Cat cat = new Cat();
        animal.printInfo();
        cat.printInfo();
    }
}
```

결과

```java
This is Animal
This is Cat
```

### Overriding 쓰는 이유

- 상위 클래스의 메서드를 대부분을 쓰기 위해 상속을 했지만, 하위 클래스에서 특정 메서드를 상위 클래스와 다르게 쓰고 싶을 때 사용
- 유연하면서 확장성 있는 코딩이 가능해짐
- Overriding을 할 때 리턴타입은 상위 클래스와 같아야하므로 일관성은 지키면서 유연한 상속이 가능

## 비교

| 항목 | Overloading | Overriding |
| --- | --- | --- |
| 메서드명 | 같다 | 같다 |
| 매개변수, 타입 | 다르다 | 같다 |
| 리턴타입 | 상관없음 | 같다 |