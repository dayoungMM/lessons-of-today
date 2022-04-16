# 다형성(Polymorpism)

## 개념

- 하나의 메소드나 클래스가 있을 때 이것들이 다양한 방법으로 동작하는 것
- 하나의 코드가 여려 자료형으로 구현되어 실행됨

## 방식

1. Overloading

<aside>
💡 overloading이 다형성인지 아닌지 이견이 존재하나 이름은 같지만 다른 parameter를 받아 다른 동작을 할 수 있는 매서드들을 만들 수 있다는 점에서 다형성 부분에 넣었음

</aside>

- Parent.java

    ```java
    
    public class Parent {
        public Parent() {
            System.out.println("Parent Constructor");
        }
    
        public void printName() {
            System.out.println("I am Parent");
        }
    
        public void printName(String name) {
            System.out.println("I am " + name);
        }
    }
    ```

- [ExePoly.java](http://ExePoly.java)

    ```java
    public class ExePoly {
        public static void main(String[] args) {
            Parent parA = new Parent();
            parA.printName();
            parA.printName("Jane");
        }
    }
    ```

    - 결과

    ```java
    Parent Constructor
    I am Parent
    I am Jane
    ```

- 같은 이름의 printName 매서드이지만, parameter를 안받을수도, String 타입의 parameterfmf 를 받을수도 있게 할 수 있으며, 각각 다른 동작을 할 수 있다는 점에서 다형성을 구현했다.

1. 상속- (Overriding)
- Child.java

    ```java
    public class Child extends Parent {
        public Child() {
            System.out.println("Child Constructor");
        }
    
        public void printName() {
            System.out.println("I am Child");
        }
    }
    ```

- [ExePoly.java](http://ExePoly.java) 수정

    ```java
    public class ExePoly {
        public static void main(String[] args) {
            Parent parA = new Parent();
            Parent childA = new Child();
            parA.printName();
            childA.printName();
        }
    }
    ```

    - 결과

    ```java
    Parent Constructor
    Parent Constructor
    Child Constructor
    I am Parent
    I am Child
    ```

  parA와 childA 모두 Parent 타입으로 선언되어있으나 각각의 printName이라는 매서드의 결과는 다르다.

  즉, 메서드 이름은 같지만 각 객체의 실제 타입은 다르며, 생성자를 사용한 클래스에 있는 메서드를 호출한다.


1. 구현 (Interface)

interface 클래스

```java
public interface MemberManager {
    public boolean addMember(MemberDTO member);
}
```

구현 클래스

```java
public class MemberManagerImpl1 implements MemberManager{
    @Override
    public boolean addMember(MemberDTO member) {
        return false;
    }
}
```

```java
public class MemberManagerImpl1 implements MemberManager{
    @Override
    public boolean addMember(MemberDTO member) {
        return false;
    }
	- 즉 interface 를 이용하면 같은 MemberManager타입이라고 하더라도 다른 메서드가 실행될 수 있는 다형성을 구현할 수 있다.
```

객체 생성해서 사용

```java
public class ExeMember {
    public static void main(String[] args) {
        MemberManager memberManager1 = new MemberManagerImpl1();
				MemberManager memberManager2 = new MemberManagerImpl2();

    }
}
```

- interface를 구현하는것은 상속이 아니므로 여러개를 implements 할 수 있다.
- MemberManager라는 interface를 구현한 클래스는 MemberManager로 형변환을 할 수 있다.
- memberManager1, memberManager2 모두 MemberManager 타입이지만 실제 실행되는 메서드는 각 구현체에서 Overriding 한 메서드가 실행된다
- 즉 interface 를 이용하면 같은 MemberManager타입이라고 하더라도 다른 메서드가 실행될 수 있는 다형성을 구현할 수 있다.

1. 상속 (abstract 클래스)
- abstract 클래스도 상속을 할 수 있다.
- 2번과 동일한 방법으로 Override해서 다형성을 구현할 수 있다.
- 다만 상속 클래스는 그 자체로 사용하기 보다는 interface처럼 구현체를 만들어 사용해야한다.

- 자세한 내용은 ⇒

[인터페이스와 추상 클래스](https://www.notion.so/e567710b18c8497cad7f6e8390512fcf)

## 필요한 이유

- 하나의 매서드 명으로 여러 타입의 파라미터를 받아 동작하게 할 수 있어 유연하다.
- 공통적으로 지켜야 하는 부모 클래스를 만들고, 추가적인 기능이 있는 자식 클래스를 만드는 방식으로 유연하고 확장성있고, 유지보수가 편리한 프로그램을 만들 수 있음