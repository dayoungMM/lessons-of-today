# 쿼리에 유효한 인덱스 사용 조건이 있음에도 인덱스 안타는 케이스

### index

- Index를 테이블 특정 컬럼에 한개 이상을 주게 되면 Index 테이블이 따로 만들어지는데, 이 index 테이블에는 인덱스 컬럼의 로우값과 row id값이 저장되게 되며, 로우의 값은 정렬된 B-TREE 구조로 저장시켜 두어 더 빠르게 해당 데이터를 찾느데 도움을 줌
- 하지만 UPDATE, INSERT, DELETE 시에는 속도가 느려진다는 단점이 있음(원본테이블과 Index 테이블 모두 업데이트 시켜야하기 때문)

### Query Plan때문에 발생

- QueryPlan은 데이터가 적당하면 index를 선택해서 scan하지만
- 데이터 조회 범위가 많은경우 full scan하는게 하게됨 → index scan이 더 빠른 상황이여도 QueryPlan이 알아서 판단해서 scan하고, 그로인해 지연이 생겨 Dead Lock 처럼 되어버린 상황이 발생할 수 있다.
- 언제 자주 발생할까?
    - 삽입, 삭제 빈번하게 일어남 → 테이블에 빈공간이 생김 → scan 어려움 + Queryplan 혼란
- 시사점
    - QueryPlan은 완벽하지 않다
    - 한번에 많은 데이터를 요청하는것을 지양하자

### Query Plan이 Full Scan으로 판단하는 경우 예시

1. 인덱스 컬럼의 변형
    1. 예:

    ```java
    select * from table where LOWER(name) ='word';
    ```

2. NOT 또는 IN 연산자 사용
    1. NOT일 경우에도 인덱스를 타긴 타지만, 일반적으로, NOT에 사용된 값이 아닌 데이터의 비율이 높은 경우가 많기 때문에 인덱스를 타지 않는 경우가 많다
    2. N일 경우에도, IN에 포함된 데이터들의 비율이 매우 높다면 FULL SCAN을 하는 것이 낫다고 DBMS가 판단하면 Full Scan
3. 와일드 카드 LIKE문장에서 범위를 전체를 지정시
4. 복합 컬럼 index에서 조건이 잘못되여 index 가 적용 되지 못하는경우