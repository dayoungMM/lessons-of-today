# 상속과 조합(Inheritance vs Composition)

## 상속보다는 컴포지션을 사용하라

### 1. 상속 장점

- 코드 재사용 가능
- 확장성
- 유연성

### 2. 상속 단점 (클래스- 클래스)

- 캡슐화를 깨트림
    - 설계자가 확장 고려 안했거나, API 엉망일 경우) → 상위 수정되면 하위도 계속 수정
- 상위클래스 생성자 각각에 대응하는 생성자 별도로 정의해줘야함
- 내부 구현을 불필요하게 오픈 (잘못하면 외부 API가 내부 구현에 묶일수도)

### 3. 컴포지션 Composition

- 새로운 클래스를 만들고, private 필드로 기존 클래스의 인스턴스 참조
- 기존 클래스가 새로운 클래스의 구성요소로 쓰임

### 4. 컴포지션 장점

- 새 클래스는 기존 클래스의 내부 구현 방식의 영향에서 벗어남
- 기존 클래스 메서드 추가되어도 상관 없음
- 한번만 구현해두면 어떤 상위 구현체도 계측할 수 있으며, 기존 생성자와 함께 사용가능

```java
// 코드 18-2 래퍼 클래스 - 상속 대신 컴포지션을 사용했다. (117-118쪽)
public class InstrumentedSet<E> extends ForwardingSet<E> {
    private int addCount = 0;

    public InstrumentedSet(Set<E> s) {
        super(s);
    }

    @Override public boolean add(E e) {
        addCount++;
        return super.add(e);
    }
    @Override public boolean addAll(Collection<? extends E> c) {
        addCount += c.size();
        return super.addAll(c);
    }
    public int getAddCount() {
        return addCount;
    }

    public static void main(String[] args) {
        InstrumentedSet<String> s = new InstrumentedSet<>(new HashSet<>());
        s.addAll(List.of("틱", "탁탁", "펑"));
        System.out.println(s.getAddCount());
    }
}
```

### 5. 컴포지션 용어

- 래퍼 클래스: 다른 인스턴스를 wrap 하고 있음
- Decorator Pattern : Wrapper클래스로 계속 인스턴스 위에 계측기능 덧씌우는 패턴

### 6. 컴포지션 단점

- 래퍼클래스가 callback 프레임 워크 적용시 SELF 문제 발생
    - 자기 자신(this)의 참조를 다음 객체에 넘겨 콜백 때 사용해야하는데, 내부객체는 wrapper를 모르니 this를 넘기고, 콜백함수는 wrapper가 아닌 this(내부)를 호출


### 결론

- 상위 클래스가 진짜 하위 타입인 경우 (is-a 관계) → 상속 쓰자
- 그 외 → 컴포지션
- 래퍼클래스로 구현할 인터페이스가 있다면 → 더더욱 컴포지션
