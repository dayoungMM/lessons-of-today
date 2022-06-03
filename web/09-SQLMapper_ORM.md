# SQL Mapper vs ORM

## 등장 배경

- 영속성(Persistence): 프로그램이 실행되는 동안 데이터가 사용되고 생성되는데, DB에 저장되어 프로그램이 종료되어도 사라지지 않게 하는 성질
- 객체정보를 DB에 저장하고, DB의 정보를 담아서 사용해야함
- 도메인 모델과 데이터 베이스 사이에 존재하여 둘 사이를 매핑해주는 것이 필요해짐
- 개발자가 직접 코딩하지 않아도 구현 가능하도록 Persistence Framework 가 등장한 것이 SQL Mapper, ORM이다

## SQL Mapper

- 개념
    - Object 와 SQL의 필드를 매핑하여 데이터를 객체화 하는 기술
    - 객체와 테이블 간의 관계를 매핑하는 것이 아니라, SQL문을 직접 작성하고 쿼리 수행 결과를 어떤한 객체에 매핑해 줄 지 바인딩하는 방법
    - SQL 의존적
- 대표
    - JdbcTemplate
    - Mybatis
- 장점
    - SQL을 개발자가 직접 작성하여 동적 쿼리를 지원하기 때문에 다이나믹하게 변경되는 쿼리 작성 가능
    - JOIN, 튜닝 등을 좀더 수월하게 작성 가능
- 단점
    - SQL을 개발자가 직접 작성해야한다는 번거로움
    - DBMS에 종속적임
    - 비슷한 쿼리를 반복적으로 작성해야함
    - 객체를 단순히 데이터 전달 목적으로 사용할 뿐, 객체지향적이지 못함 (패러다임 불일치)

## ORM (Object Relation Mapping)

- 개념
    - Object 와 DB 테이블을 매핑하여 데이터를 객체화 하는 기술
    - Java ORM기술에 대한 인터페이스 표준을 JPA라고 하고, 이를 구현한 것 중 가장 대표적인 기술이 Hibernate
    - 관계형 데이터베이스의 Relation을 Object에 반영하자는 목적으로 등장
    - 객체간의 관계를 바탕으로 SQL 자동 생성
- 대표
    - Hibernate, EclipseLink, Datanucleus
- 장점
    - CRUD관련 메소드를 사용하면 자동으로 SQL이 만들어져 개발자가 반복적으로 SQL 직접 작성하지 않아도 됨
    - DBMS에 종속적이지 않음으로써 도메인과 비스니스 로직 설계에 더 집중 가능 (여러 DB 벤더 호환)
    - 유지보수 편리
        - 테이블 칼럼 하나 변경되었을 경우 DAO의 파라미터, 결과, SQL 수정 필요 없음
    - 요구사항 변화에 빠르게 대처 가능
- 단점
    - 복잡한 쿼리는 사용하기 어려움. (복잡한 통계성 쿼리보다는 실시간 처리용 쿼리에 적합)


## N+1 Problem

- ORM에서 발생하는 성능 이슈 중 가장 흔한 원인
- 쿼리 1번으로 N건의 데이터를 가져왔는데, 원하는 데이터를 얻기 위해 이 N건의 데이터를 데이터 수만큼 반복해서 2차적으로(+1) 쿼리를 수행하는 문제
- 하위 엔티티들을 첫 쿼리 실행시 가져오지 않고 Lazy Loading으로 필요한 곳에서 사용되어 쿼리가 실행될 때 발생하는 문제
- 해결방안
    - Join Fetch
        - 조회시 바로 가져오고 싶은 Entity 필드를 지정 (@Query 로 쿼리 직접 작성하는 방법)
    - @EntityGraph
        - Annotation으로 Eager 조회 지정(attributePaths 매개변수로 가져올 필드명 지정해야함)
    - 주의사항
        - 1:N 구조인 경우 1에 해당하는 Entity에서 N에 해당하는 필드 타입을 Set으로 선언하자

- 참고

  [https://velog.io/@mu1616/ORM-SQL-Mapper-차이](https://velog.io/@mu1616/ORM-SQL-Mapper-%EC%B0%A8%EC%9D%B4)[https://deveun.tistory.com/entry/SQL-Mapper와-ORM-차이](https://deveun.tistory.com/entry/SQL-Mapper%EC%99%80-ORM-%EC%B0%A8%EC%9D%B4)

  [https://baek-kim-dev.site/35](https://baek-kim-dev.site/35)

  [https://jojoldu.tistory.com/165](https://jojoldu.tistory.com/165)