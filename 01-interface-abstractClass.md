## 인터페이스와 추상클래스 사용 이유

- 설계시 선언해두면 개발할 때 기능을 구현하는 데에만 집중할 수 있다.
- 개발자의 역량에 따른 메소드의 이름과 매개변수 선언의 격차를 줄일 수 있다.
- 공통적인 인터페이스와 abstract 클래스를 선언해놓으면 선언과 구현을 구분할 수 있다.

## 인터페이스

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
public class MemberManagerImpl2 implements MemberManager{
    @Override
    public boolean addMember(MemberDTO member) {
				System.out.println("add Member");
        return false;
    }
}
```

- interface 내부에 선언된 메소스들은 몸통이 있으면 안된다
- interface를 구현하는것은 상속이 아니므로 여러개를 implements 할 수 있다.

객체 생성해서 사용

```java
public class ExeMember {
    public static void main(String[] args) {
        MemberManager memberManager1 = new MemberManagerImpl1();
				MemberManager memberManager2 = new MemberManagerImpl2();

    }
}
```

- MemberManager라는 interface를 구현한 클래스는 MemberManager로 형변환을 할 수 있다.
- memberManager1, memberManager2 모두 MemberManager 타입이지만 실제 실행되는 메서드는 각 구현체에서 Overriding 한 메서드가 실행된다
- 즉 interface 를 이용하면 같은 MemberManager타입이라고 하더라도 다른 메서드가 실행될 수 있는 다형성을 구현할 수 있다.

## abstract 클래스

- abstract 으로 선언한 메소드가 하나라도 있을 때 abstract 클래스로 선언한다
- interface와 달리 구현되어있는 메소드가 있어도 상관 없다(단, abstract 으로 메소드를 선언하면 안된다)
- 구현이 안된(몸통이 없는) 메소드 선언문에는 abstract 라는 예약어로 명시

Abstract 클래스

```java
public abstract class MemberManagerAbstract {
    public abstract boolean addMember(MemberDTO member);
    public abstract boolean removeMember(MemberDTO member);
}
```

상속 클래스

```java
public class MemberManagerImpl2 extends MemberManagerAbstract{
    @Override
    public boolean addMember(MemberDTO member) {
        return false;
    }

    @Override
    public boolean removeMember(MemberDTO member) {
        return false;
    }
}
```

## 비교하기

|  | Interface | abstract 클래스 | 클래스 |
| --- | --- | --- | --- |
| 선언시 예약어 | interface | abstract class | class |
| 구현안된 메서드 포함 가능 여부 | 가능(필수) | 가능 | 불가 |
| 구현된 메서드 포함 가능 여부 | 불가 | 가능 | 가능(필수) |
| static 메소드 선언 가능 여부 | 불가 | 가능 | 가능 |
| final 메소드 선언 가능 여부 | 불가 | 가능 | 가능 |
| 상속(extends) 가능 | 불가 | 가능 | 가능 |
| 구현(implementes) 가능 | 가능 | 불가 | 불가 |

## final

- final 클래스
    - 더이상 클래스를 상속할 수 없게 만들기 위해 사용
    - 더이상 변경이 필요하지 않고, 확장 불가하게 만들 때 사용
- final 메소드
    - 상속해도 이 메소드는 변경을 원치 않을 때
    - abstract 클래스 → 구현한 메소드를 자식 클래스에서 override 하길 원치 않을 때 final 메소드로 선언 가능
- final 변수
    - 변수 더이상 변경 불가
    - 변수 생성과 동시에 초기화를 해야한다.


## Enum 클래스

- 상수의 집합 클래스
- enum클래스를 extends 할 수 없다
- 열거되어있는 데이터나 상수를 처리할 때 사용
- 고정되어 있는 값을 처리할 때 사용하면 편리 (오타 나지 않아야하는 상수값이라던지)

enum 클래스

```java
public enum OvertimeValues {
    THREE_HOUR(18000),
    FIVE_HOUR(30000),
    WEEKEND_FOUR_HOUR(40000),
    WEEKEND_EIGHT_HOUR(60000);
    private final int amount;

    OvertimeValues(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}
```

호출

```java
public class OvertimeManager {
    public static void main(String[] args) {
        OvertimeValues values = OvertimeValues.FIVE_HOUR;
        System.out.println(values);
        System.out.println(values.getAmount());
    }
}
```