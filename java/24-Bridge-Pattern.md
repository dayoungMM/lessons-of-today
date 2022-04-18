## 개념

- 구현(implementation)부에서 추상(abstraction)층을 분리하여 각자 독립적으로 변형이 가능하고 확장이 가능하도록 설계
- 기능과 구현에 대해서 두개의 별도 클래스로 구현
- 커플링(결합)은 약화시키며 협력은 가능하도록 하는 패턴

## 다이어 그램으로 파악

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/891dd707-d51e-468e-bb1b-14b291e32de2/Untitled.png)

- 클래스
    - Abstraction: Implementor를 인스턴스 변후로 합성한 상태. function이 추상화 된 상태
    - Implementor : 추상화된 인터페이스
    - ConcreteImplementor: 인터페이스(Implementor)를 구현한 객체(상속)
    - RefinedAbstraction: 추상화 클래스(Abstraction)을 구체화한 객체
- Abstraction 클래스는 Implementor에 의존하는 상태
- Abstraction - Implementor는 추상화 되어있고, 실제 구현은 자식 클래스인 RefinedAbstraction - CeoncreteImplementor에 맡기는 구조

## 구현 예시

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/c2a80479-91d5-4db5-b8fe-6e4029a29926/Untitled.png)

- 사냥법에 대한 인터페이스는 HuntingHandler에서 정의
- 사냥법에 대한 실제 출력 기능은 HuntingMethod1, HuntingMethod2에서 구현
- 객체 생성과 기능에 대한 매서드 정의는 Animal(abstract class)에서 추상적으로 정의
- 실제 동물 객체를 구체화 하는것은 Tiner와 Bird

## 장점

- 추상화된 부분과 실제 구현 부분을 독립적으로 확장할 수 있음
- 동물 객체 생성하는 객체와 사냥법을 정의하는 객체를 분리함으로써 도메인을 나눴는데, 각 도메인 로직에 집중할 수 있음
- 런타임 환경에서 유연하게 동작할 수 있음(컴파일 시점에는 어떤 구현체를 사용할지 알 수 없고 런타임에서 결정되기 때문)
- 일반적인 상속을 하면 수직적으로 객체들이 증가하는 구조지만, 브릿지 패턴을 사용한다면 아래와 같이 수평적으로(직교하여) 문제를 해결할 수 있다.

  ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/3cd44e2f-5829-4936-b234-135e39eb9ead/Untitled.png)

-

## 단점

- 디자인이 복잡해진다