## Aspect-Oriented Programming

- 관점 지향 프로그래밍
- 객체 지향 프로그래밍의 단점을 해소하기 위해 등장
    - Object Oriented Programming
    - 모든 변수 선언시 new를 통해 객체를 선언
    - 객체를 재사용 한다는 측면에서 효율적이었으나, 공통된 부가기능에 대한 코드가 중복,반복된다는 단점

```java
class A {
	method a() {
		AAAA
		method a 가 하는 일
		BBBB
	}

	method a() {
		AAAA
		method b 가 하는 일
		BBBB
	}
}

class B {
	method c() {
		AAAA
		method c 가 하는일
		BBBB
	}
}
```

- AAAA,BBBB는 여러군데에서 중복적으로 사용하는 코드
    - 중복된 코드 → 비효율
    - AAAA, BBBB 수정시 여러 군데에 있는 코드 수정해줘야함 → 비효율
- AOP는 흩어진 관심사를 모아서 모듈화 시킴
    - 여기저기에 흩어져서 반복되는 코드를 흩어진 관심사라고 한다 → 관심사 관점으로 생각하는게 관점지향 프로그래밍
    - 중복되는 코드를 모듈화 하고 핵심적인 비즈니스 로직에서 분리하여 재사용

  ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/bc2e1d8e-e82d-4038-8c89-4e11b5982dae/Untitled.png)


## 주요 개념, 어노테이션

- Spring
    - Aspect: 흩어진 관심사를 묶어서 모듈화 한 것. 하나의 모듈. Advice와 Point Cut이 들어간다
    - Advice: 해야할 일들에 대한 정보
    - Joint Point: Advice 가 적용될 위치, 끼어들 수 있는 지점, 메서드 진입 지점 등 다양한 시점 등 어디에 적용해야하는지에 대한 정보
    - Point Cut: JointPoint의 상세한 스펙을 정의한 것. 구체적으로 Advice가 실행될 시점
    - Target: Advice가 적용되는 대상(클래스, 메서드 등)
- Spring boot
    - @Aspect : AOP를 정의하는 클래스에 할당
    - @Pointcut : AOP를 적용 시킬 지점 설정
    - @Before : 메소드 실행하기 이전
    - @After : 메소드가 성공적으로 실행 후 예외가 발생되더라도 실행
    - @AfterReturing : 메소드 호출 성공 실행 시
    - @AfterThrowing : 메소드 호출 실패 예외 발생
    - @Around : Before/After 모두 제어

## 스프링 AOP 특징

- 프록시 패턴을 이용해 AOP 효과를 낸다. (접근 제어 및 부가기능을 추가하기 위해)
- 스프링 빈에만 AOP를 적용할 수 있다.
- 모든 AOP 기능을 제공하는 것이 목적이 아니라, 스프링 IoC와 연동하여 애플리케이션에서 가장 흔히 발생하는 문제(중복코드, 프록시 클래스 작성의 번거로움, 객체 간 관계 복잡도 등)를 해결하기 위한 솔루션을 제공하는것이 목적

## 프록시 패턴

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/3fc347a1-c61d-490d-b04a-95465c4cda5a/Untitled.png)

- interface가 존재하고, client는 interface 타입으로 proxy 객체를 사용하게 된다
- Real Subject는 Subject라는 interface를 구현한 객체. 원래 해야할 일을 가지고 있다
- Proxy는 Subject를 구현하면서, 원래해야할 일을 가지고 있는 Real Subject를 의존(의존성 주입)해서 생성된 객체. Client의 요청을 처리한다
- 사용하는 이유
    - 원래의 Client와 Real Subject의 코드를 건드리지 않고 부가기능을 추가할 수 있다
- Spring AOP는 동적으로 Proxy 생성
    - 정적으로 Proxy를 만들었을 때의 단점
        - Proxy를 만드는데 생기는 비용과 수고가 발생.
        - Proxy를 여러 클래스, 메소드에 적용시켜야 한다면 매번 프록시 클래스 작성해야함
    - 런타임, 즉 애플리케이션이 동작하는 중에 proxy 객체 생성함으로써 해결