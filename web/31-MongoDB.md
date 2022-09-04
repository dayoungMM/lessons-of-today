# MongoDB

## NoSQL

- 전통적인 관계형 데이터베이스 보다 덜 제한적인 일관성 모델을 이용
    - 테이블간의 조인 기능 없음
    - Transaction ACID 미지원
- 빅데이터와 실시간 웹 애플리케이션과 궁합이 잘 맞음
- SQL 계열 쿼리 언어를 사용할 수 있음
- 대부분 여러 대의 데이터베이스 서버를 묶어서 (클러스터링) 하나의 데이터 베이스 구성
- 분류
    - Wide Columnar Store : 카산드라
    - Document Store : MongoDB
    - Key-Value Store : 다이나모, 레디스
    - Graph Store: Neo4j
- 비교 (벤 스코필드 평가)


    |  | 성능 | 확장성 | 유연성 | 복잡성 | 복잡도 |
    | --- | --- | --- | --- | --- | --- |
    | Key-Value Store | 높음 | 높음 | 높음 | 없음 | 가변적(없음) |
    | Wide Columnar Store | 높음 | 높음 | 준수 | 낮음 | 최소 |
    | Document Store | 높음 | 가변적(높은편) | 높음 | 낮음 | 가변적 (낮음) |
    | Graph Store | 가변적 | 가변적 | 높음 | 높음 | 그래프 이론 |
    | RDBMS | 가변적 | 가변적 | 낮음 | 준수 | 관계 대수(relational algebra) |
- RDBMS와 비교
    - 성능과 확장면에서 NoSQL이 우수. 유연하고 복잡성이 낮음. 따라서 빅데이터, 속도가 중요한 어플리케이션에서는 NoSQL을 쓰자
    - 반면 ACID 트랜잭션이 보장받기 위해서는 RDBMS를 쓰자


## MongoDB

- 개념
    - C++로 작성된 문서지향(Document-Oriented) 크로스 플랫폼 데이터 베이스
- 구성
    - Document
        - RDBMS에서의 Row(tuple)과 같은 개념
        - Key-Value쌍으로 이루어진 데이터 구조 (JSON이랑 비슷)
        - 각 Document는 _id를 가지고 있는데 이 값은 유일함. primary key와 동일
        - RDBMS처럼 스키마로 정해진 것이 없고 동적 정의 (schema-less)
            - example

                ```json
                {
                    "_id": "5f2ad6b54866e5109dd2367b"
                    "username": "홍길동",
                    "hashedPassword": "비밀번호",
                }
                ```

                - hashedPassword 밑에다가 email을 추가한 Document를 새로 생성해도 문제 없이 동작함.
    - Collection
        - Document의 그룹.
        - RDBMS의 Table과 비슷한 기능이지만 스키마는 없다(동적 정의)
    - Database
        - Collection의 물리적인 컨테이너이자 가장 상위 개념
        - RDBMS에서 Database과 동일
- Join은 어떻게 처리할까?
    - 테이블끼리 Join하는 기능은 없지만, schema-less한 특징을 이용해 document안에 document를 넣는 방식으로 처리할 수 있다.
    - 예를들어 Post : Comment = 1 : N 관계라면

    ```json
    {
       _id: POST_ID,
       title: POST_TITLE,
       content: POST_CONTENT,
       username: POST_WRITER,
       tags: [ TAG1, TAG2, TAG3 ],
       comments: [
         { 
            username: COMMENT_WRITER1,
            mesage: COMMENT_MESSAGE1,
            time: COMMENT_TIME1
         },
    	  { 
            username: COMMENT_WRITER2,
            mesage: COMMENT_MESSAGE2,
            time: COMMENT_TIME2
         },
       ]
    }
    ```

- 참고

  [https://velog.io/@ckstn0777/MongoDB란](https://velog.io/@ckstn0777/MongoDB%EB%9E%80)

  [https://www.samsungsds.com/kr/insights/1232564_4627.html](https://www.samsungsds.com/kr/insights/1232564_4627.html)