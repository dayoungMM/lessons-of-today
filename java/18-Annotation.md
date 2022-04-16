# Annotiation

## 개념

- annotation의 사전적 의미: 주석
- 자바에서 annotion의 기능
    - 메타데이터 정보를 제공해줌. 즉, 데이터에 대한 설명을 의미
    - 데이터의 유효성 검사 등을 쉽게할 수 있어 코드가 깔끔해질 수 있음

## 구성

```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public  CustomAnnotation {
	boolean isCheck() default true;
}
```

- @Target
    - 어노테이션이 적용할 위치
    - ElementType.METHOD: 메서드 선언
- @Retention
    - 자바 컴파일러가 어노테이션을 다루는 방법 기술. 어느 시점까지 영향을 미치는지 결정
    - 컴파일 시기에 처리될수도 있고, 런타임에 처리될 수도 있음
    - RetentionPolicy.RUNTIME: 컴파일 이후에도 JVM에 의해 계속 참조가 가능 (리플렉션 사용)
    - 리플렉션이란?

      [Reflection](https://www.notion.so/Reflection-009ebb99b8bc4aa1948844387b05fd92)


## 활용

- 비즈니스 로직과는 별도로 시스템 설정과 관련된 부가적인 사항들은 @에게 위임하고 개발자는 비즈니스 로직 구현에 집중할 수 있음
    - 즉, AOP(Aspect Oriented Programming)을 편리하게 구성할 수 있음