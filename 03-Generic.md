## 개념

- 클래스 선언할 때 <> 로 타입을 적어주는 것
- 형 변환에서 발생할 수 있는 실수를 컴파일 할 때 점검할 수 있도록 하기 위해 사용

## 예시

- WildGeneric

    ```java
    public class WildGeneric<W> {
        W wildcard;
        public void setWildcard(W wildcard) {
            this.wildcard=wildcard;
        }

        public W getWildcard(){
            return wildcard;
        }
    }
    ```

- WildCardExample

```java
public class WildCardExample {
    public static void main(String[] args) {
        WildCardExample sample = new WildCardExample();
        sample.callWildCardMethod();
    }

    public void callWildCardMethod() {
        WildGeneric<String> wildcard = new WildGeneric<String>();
        wildcard.setWildcard("A");
        wildcardStringMethod(wildcard);
    }

    public void wildcardStringMethod(WildGeneric<String> c) {
        String value = c.getWildcard();
        System.out.println(value);
    }
}
```

- callWildCardMethod는 WildGeneric 클래스에 String 을사용하는 제네릭한 객체 생성
- 생성한 객체는 wildcardStringMethod 를 호출할 때 매개변수로 쓰임
- 이렇게 함으로써 setWildcard 할 때 String 이 매개변수로 들어오도록 컴파일 단계에서 점검할 수 있다.

## 다양한 제네릭 타입 지정

- ?: 알수없는 타입
- <? extends Car> : Car를 상속받은 모든 클래스