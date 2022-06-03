# Context, Servlet

## Spring에서 Context란?

- Bean의 확장버전으로 Spring Bean을 다루기 위한 기능 + 기타 추가적인 기능을 수행하는 공간이다

## 구조

- Root Context(공통부분)
    - 모든 서블릿이 공유할 수 있는 Bean들이 모인 공간.
    - DB와 관련된 Repository, Service 등이 있음
- Servlet Context (개별 부분)
    - 서블릿 각자의 Bean들이 모인 공간
    - 웹, 앱마다 한개씩 존재하므로 웹 앱 그 자체를 의미하기도 함
    - 이 Context 내에서 Bean들은 서로 공유될 수 없음


## Web Application 용 Context 종류

- ServletContext
    - 자바 자체의 Context를 말함
    - 스프링도 자바로 만들어졌으므로 모든 스프링 Context는 ServletContext라고 할 수 있음
- WebApplicationContext
    - 웹 애플리케이션에 특화된 Context
    - 앞서 설명한 Root Context, ServletContext로 사용됨
- ConfigurationWebApplicationContext
    - WebApplicationContext를 설정하는데 쓰이는 context
    - WebContext를 설정할때에는 Configurable 클래스로 바꿔서 설정함


## Servelet이란?

- Dynamic Web Page를 만들 때 사용되는 자바 기반의 웹 애플리케이션 프로그래밍 기술
- 웹 요청과 응답의 흐름을 간단한 메서드 호출 만으로 체계적으로 다룰 수 있게 해주는 기술
- 자바클래스로 웹 애플리케이션 작성 → 웹 서버 안에 있는 웹 컨테이너에서 실행 → 웹 컨테이너에서 서블릿 인스턴스 생성 → 서블릿 인스턴스 서버에서 실행 → 웹 브라우저에서 Request 있을 때 Response 해줌
- 서블릿 컨테이너에서 서블릿을 관리, 실행함 (생명주기 등)


## Tomcat

- 웹 애플리케이션 서버(WAS) 중 하나로 Servlet Container, Servlet Engine이라고 표현할 수 있음
- 자바 웹 프로그래머가 작성한 Servlet을 관리 (request 왔을 때 어떤 servlet을 실행할 것인가)

## Spring과 Spring boot에서의 Servlet 설정

- Spring
    - Spring은 기본 Servlet인 Dispatcher Servlet을 사용함
    - web.xml에서 일일이 Servlet(Dispatcher Servlet외에 다른것을 사용할 수도) 설정을 web.xml에 등록해서 사용해야함
- Spring boot
    - java config를 사용해 더 쉽게 설정 가능.
    - Dispatcher Servlet은 기본으로 설정되어 있어 따로 설정하지 않아도 된다

- 참고

  [https://coding-factory.tistory.com/742](https://coding-factory.tistory.com/742)

  [https://jeong-pro.tistory.com/222](https://jeong-pro.tistory.com/222)