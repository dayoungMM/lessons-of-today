# 웹 브라우저에서 웹서버 호출시 응답까지의 네트워크를 포함한 과정

## 1. 브라우저의 URL 파싱

- 브라우저에서 어떤 프로토콜, URL, 포트로 요청할 것인지 해석 및 분석
- 명시적으로 포트 선언하지 않았다면 포트 요청
    - HTTP : 80
    - HTTPS: 443

## 2. HSTS (HTTP Strict transport security) 목록 조회

- HTTP를 허용하지 않고 HTTPS를 사용하는 연결만 허용하는 기능
- HSTS 목록에 URL이 존재한다면 HTTP 요청이 들어와도 브라우저가 HTTPS로 Redirect 해서 요청

## 3. URL을 IP 주소로 변환

- 자신의 로컬 host파일과 브라우저 캐시에 해당 URL의 존재 여부 확인
- 존재하지 않는 다면 DNS 서버에 요청하여 해당 URL을 IP로 변환

## 4. 라우터를 통해 해당 서버의 게이트 웨이까지 이동

- DNS 서버에게 IP 주소를 받았으니 서버로 요청
- 해당 IP 주소를 어떻게 가는지 경로를 알기 위해서는 라우터가 필요
- 라우터가 라우팅을 해주면 해당 IP 주소로 요청 도달

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/26706690-302f-4784-bf1f-f82394036b60/Untitled.png)

## 5. ARP를 통해 IP 주소를 MAC 주소로 변환

- 실질적 통신을 하기 위해서 논리 주소인 IP를 물리 주소인 MAC으로 변환
- 이를 위해 해당 네트워크 내에서 ARP를 브로드 캐스팅 → IP 주소를 가진  노드는 자신의 MAC 주소 응답

## 6. 대상 서버와 TCP 소켓 연결

- 대상 서버와 통신을 하기 위해 TCP 소켓 연결 (3-way-handshake)

  

    - 클라이언트에서 서버에 연결 요청 (SYN)
        - 이 때 클라이언트는 closed, 서버는 listen 상태
    - 서버가 SYN을 받으면 ACK데이터와 함께 클라이언트쪽에서도 포트를 열어달라는 SYN 전송
        - 이 때 서버는 SYN_RCV 상태가 됨
    - 클라이언트에서 서버로부터 ACK+SYN을 받으면 포트를 열고 이에 대한 응답으로 서버에 ACK 전송
        - 이 때 클라이언트는 Established 상태
    - ACK를 받은 서버도 Established 상태가 되면서 클라이언트 - 서버 연결 성공
- HTTPS 요청일때는 암호화통신을 위한 TLS 핸드 셰이킹이 추가됨

  


## 7. HTTP 프로토콜로 요청

- 연결이 되었으니 요청하였던 페이지를 달라고 서버에게 요청
- 서버에서는 해당 요청을 받고 수락할 수 있는 요청인지 검사

## 8. WAS (Web Application Server)


> 💡 동적 컨텐츠를 제공할 때에만 WAS를 거친다



- 잠깐! WAS를 거치는 경우는? (동적컨텐츠란?
    - 개념
        - 정적 컨텐츠 제공 : WAS를 거치지 않고 바로 자원을 제공
        - 동적 컨텐츠 제공 : 클라이언트의 request를 WAS에 보내기
    - 정적, 동적 컨텐츠란?
        - 정적 컨텐츠(static) : 변화가 없는 컨텐츠, 어느 사용자에게나 동일한 결과값을 보여줌 ( ex. html, jpg, css 등..)
        - 동적 컨텐츠(dynamic) : 배너 광고나 카테고리 등 모두가 볼 수 있어야 하는 컨텐츠는 정적으로두고 , 마이페이지나 장바구니 등 사용자 맞춤형 정보를 제공해야 할때에는 동적 컨텐츠를 사용 ( DB, 비즈니스 로직 )


### 개념

- DB 조회나 로직 처리를 요구하는 동적 컨텐츠를 제공하기 위해 만들어진 Application Server
- **Web container** 혹은 **Servlet Container**라고도 불린다.
- Container란 jsp, Servlet을 실행시킬 수 있는 소프트웨어를 말한다.

### 기능

- 프로그램 실행 환경과 DB 접속 기능 제공
- 여러 개의 트랜잭션 관리기능
- 업무 처리하는 비즈니스 로직수행
- ex) Tomcat, Websphere, Weblogic, Jeus, JBoss, Resin 등..

### 동작

사용자 요청을 받으면 서블릿으로 바꾸어 실행

웹 서버에서 요청한 동적 페이지를 읽어 프로그램을 실행

그 결과를 다시 HTML로 재구성하여 WebServer에 전달



- Tomcat
    - 웹 애플리케이션 서버(WAS) 중 하나로 Servlet Container, Servlet Engine이라고 표현할 수 있음
    - 자바 웹 프로그래머가 작성한 Servlet을 관리 (request 왔을 때 어떤 servlet을 실행할 것인가)

## 9.Dispatcher Servlet

### 개념

- 클라이언트의 요청을 처리하고, 그 결과를 반환하는 Servlet 클래스의 구현 규칙을 지킨 자바 웹 프로그래밍 기술
- 자바를 사용하여 웹을 만들기 위해 필요한 기술
- 클라이언트가 어떠한 요청을 하면 그에 대한 결과를 다시 전송 해주는 역할을 하는 자바 프로그램

### 특징

- 클라이언트의 요청에 대해 동적으로 작동하는 웹 어플리케이션 컴포넌트
- html을 사용하여 요청에 응답한다.
- Java Thread를 이용하여 동작한다.
- MVC 패턴에서 Controller로 이용된다.
- HTTP 프로토콜 서비스를 지원하는 javax.servlet.http.HttpServlet 클래스를 상속받는다.

### 동작

- 자바클래스로 웹 애플리케이션 작성 → 웹 서버 안에 있는 웹 컨테이너에서 실행 → 웹 컨테이너에서 서블릿 인스턴스 생성 → 서블릿 인스턴스 서버에서 실행 → 웹 브라우저에서 Request 있을 때 Response 해줌
- 서블릿 컨테이너에서 서블릿을 관리, 실행함 (생명주기 등)





- 참고

  [https://velog.io/@pilyeooong/웹-브라우저에서-서버까지의-흐름](https://velog.io/@pilyeooong/%EC%9B%B9-%EB%B8%8C%EB%9D%BC%EC%9A%B0%EC%A0%80%EC%97%90%EC%84%9C-%EC%84%9C%EB%B2%84%EA%B9%8C%EC%A7%80%EC%9D%98-%ED%9D%90%EB%A6%84)

  [https://www.baeldung.com/spring-web-contexts](https://www.baeldung.com/spring-web-contexts)

  [https://velog.io/@developerjun0615/WEB-WAS-란-무엇일까](https://velog.io/@developerjun0615/WEB-WAS-%EB%9E%80-%EB%AC%B4%EC%97%87%EC%9D%BC%EA%B9%8C)