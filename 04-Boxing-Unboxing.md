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