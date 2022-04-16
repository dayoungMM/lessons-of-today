# Reflection
## 개념

- 구체적인 클래스 타입을 알지 못해도 클래스의 정보(메서드, 타입, 변수 등등)에 접근할 수 있게 해주는 자바 API

```java
public class Car {
    private final String name;
    private int position;

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
```

위와 같이 Car 클래스가 있다고 하자

```java
public static void main(String[] args) {
    Object obj = new Car("foo", 0);
    obj.move();    // 컴파일 에러 발생 java: cannot find symbol
}
```

- obj.move();에서 컴파일 에러가 난다
- obj는 Object 클래스라는 타입만 알 뿐 Car클래스의 구체적인 타입을 몰라 move메소드를 모르기 때문

```java
public static void main(String[] args) throws Exception {
    Object obj = new Car("foo", 0);
    Class carClass = Car.class;
    Method move = carClass.getMethod("move"); //try-catch 해주는것이 좋다(가독성 위해 생략)

    // move 메서드 실행, invoke(메서드를 실행시킬 객체, 해당 메서드에 넘길 인자)
    move.invoke(obj, null); //try-catch 해주는것이 좋다 (가독성 위해 생략)
}
```

- Reflection API 사용
- 자바에서는 JVM실행 → 자바코드 컴파일 → 바이트 코드로 변환됨 → static 영역에 저장
- Reflection API는 클래스 이름만 알고 있다면 static 영역을 뒤져 정보를 가져옴

## 활용

- 실제 Car 객체를 굳이 Object 타입으로 생성해서 쓰는 일이 거의 없기 때문에 우리가 직접 코드를 작성할 때 Reflection을 쓸 일은 거의 없다.
- 나중에 추가될 클래스에 대한 기능을 미리 구현해야할 경우 Reflection을 써야함 (ex. annotation)
- 단점
    - 성능 오버헤드
        - 이미 인스턴스를 만들었음에도 불구하고 굳이 필드와 리플렉션을 이용해서 접근하거나 사용할 경우 메모리 낭비
        - 컴파일 타임에 확인되지 않고 런타임 시에 문제가 확인된느 경우가 있음
        - 이걸 방지하기 위해 try-catch 구문으로 예외처리 해줘야하므로 가독성 저하
- 주로 활용되는 곳
    - 프레임 워크. 라이브러리
        - 프레임워크나  라이브러리는 사용자가 어떤 클래스를 만들지 예측할 수 없기 때문에 동적으로 해결해줌
    - Spring Container의 BeanFactory
        - Bean - 애플리케이션 실행 후 런타임에 객체 호출될 때 동적으로 객체의 인스턴스 생성
        - 이 때 Spring Container의 BeanFactory에서 리플렉션 사용
    - JPA
        - JPA Entity → Reflection API를 이용해 해당 클래스의 메서드, 멤버변수, 변수타입 등을 알 수 있다.
        - 하지만 기본 생성자가 없으면 객체를 생성할 수 없기 때문에 JPA Entity는 기본생성자가 필요
        - 자바에서는 Lombok을 사용해주면 @NoArgsConstructor 어노테이션만으로 기본 생성자 쉽게 만들 수 있음
        - 코틀린에서는 kotlin-jpa 라이브러리가 기본 생성자 만들어줌 (data class를 이용하자)