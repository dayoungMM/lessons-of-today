## 개념

- Boxing: 기본 자료형(Primitive Type)을 Wrapper Class로 객체화하는것

```java
int pInt = 3; 
Integer wInt1 = new Integer(pInt); //명시적 박싱
Integer wInt2 = pInt //묵시적 박싱 (오토 박싱)
```

- Unboxing:  Wrapper Class 를 Primitive Type으로 바꾸는것

```java
Integer wInt = new Integer(3);
int pInt1 = wInt.intValue(); //명시적 언박싱
int pInt2 = wInt; //묵시적 언박싱 (오토 언박싱)
```

## 하는 이유

- 제네릭(<>)을 사용할 때 제네릭 타입에는 Primitive Type을 쓰지 못하기 때문에 Wrapper Class로 Boxing 해야한다.
- Primitive Type은 Stack에 저장되고, Wrapper Class(참조타입)는 Heap에 저장됨. 참조타입인 Wrapper는 stack에 주소를 저장하고 heap에서 값을 참조해오기 때문에 값을 불러올 때 Stack 에서 값을 불러오는 것보다 더 느리다. 따라서 Primitive Type으로 표현해도 되는 단순값은 Unboxing 한다. 또한 Primitive Type은 null값을 가질 수 없지만 Wrapper Class는 가질 수 있다.

## 단점


- 박싱과 언박싱을 하면 힙과 스택을 오가며 메모리 복사가 이루어지는데, 너무 빈번하게 이루어지면 이것 또한 성능 부하가 될 수 있다

## 활용 방안

- 컬렉션을 써야하는 경우나, 전역변수로 써야하거나, 힙/스택 오버플로우가 나서 메모리 관리가 필요한 경우에는 적극사용하고, 그 외에는 최소화 하는게 좋을 것 같음

---
# 박싱된 타입보다 기본 타입이 좋은 이유

## 박싱된 기본타입을 쓰는게 유리한 경우

- 식별성이 중요한 경우. 두 인스턴스 값이 같아도 서로 다르다고 식별하고 싶을 때
- null 값을 가질 수 있어야 할 때
- 컬렉션의 원소, 키 값으로 쓸 때 (제네릭에는 박싱된 기본타입만 사용 가능)
- 리플렉션을 통해 메서드를 호출할 때

## 기본 타입을 쓰는게 유리한 경우

- 시간과 메모리가 중요한 경우
- 값이 같으면 같다고 비교하고 싶을 때 (==를 사용해서)
    - 박싱된 기본타입은 값이 같다고 하더라도 다른 인스턴스라면 다르게 인식한다
    - 그래도 박싱된 기본타입 값을 비교하고 싶다면 compare 메서드 사용
    - 또는 기본타입 변수로 Unboxing 해서 비교

## 박싱된 기본타입, 언박싱된 기본타입 혼용시 유의점

- 혼용된 연산에서는 박싱된 기본타입이 자동으로 언박싱 된다.
- 따라서 NullPointerException이 발생하는 것을 막으려면 int로 선언해줘서 null 이 없게 하자

## 결론

- 기본타입과 박싱된 기본타입 중 하나를 선택해야 한다면 가급적 기본타입을 사용하자 (간단하고 빨라서)
- 오토박싱이 Boxing된 기본타입이 사용할 때의 번거로움을 줄여주지만, 그 위험까지 없애주지 않는다(비교할 때 등)
- 언박싱 과정에서 NullPointerException이 발생할 수 있다.
- 기본타입을 박싱하는 작업이 너무 많은 경우 불필요하게 Instance를 생성해 성능 저하가 발생할 수 있다.