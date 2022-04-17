# Connection Pool

# DBCP (DataBase Connection Pool)

## 개념

- 웹 컨테이너 실행되면서 DB와 미리 Connection을 해놓은 객체들을 pool에 저장해두었다가
- 클라이언트 요청이 오면 Connection을 빌려주고, 처리가 끝나면 Connection을 반납받아 pool에 저장하는 방식

## 왜 필요한가

- 자바에서 DB에 직접 연결해서 처리하는 경우(JDBC 사용) 드라이버를 로드→ 커넥션 객체를 생성→ 연결→ 다 이용하면 종료
- 사용자가 요청할 때 마다 일련의 과정을 반복하면 비효율적
- Pool에 미리 Connection 을 해놓은 객체들을 저장해서 불러오는 Object Pool 방식
- 장점
    - 커넥션 객체 생성, 종료 작업을 반복하지 않아도 된다
    - connection 수를 제한할 수 있음 → 서버 자원 고갈 방지 , 동시 접속자 수가 많아도 어플리케이션이 쉽게 다운되지 않음
    - DB 접속 모듈을 공통화 하여 DB서버의 환경이 바뀔 경우 쉬윤 유지보수 가능
    - Connection을 재사용하기 때문에 생성되는 커넥션 수가 많지 않아 메모리를 효율적으로 사용

## 특징

- 웹 컨테이너(WAS)가 실행되면서 connection 객체를 미리 pool에 생성해둠
- connection을 계속 재사용하기 때문에 생성되는 커넥션 수를 제한적으로 설정해둠
    - 동시접속자가 많아서 pool에 connection이 없는경우, connection이 반환될 때까지 번호순대로 대기상태로 기다린다
    - 즉, connection pool을 크게 설정하면 메로리 소모 많지만, client의 요청 대기시간이 줄어듦