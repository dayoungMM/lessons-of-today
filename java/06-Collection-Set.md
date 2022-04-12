## 1. Set

- 데이터 순서와 상관없이 저장
- 주 사용 목적
    - 데이터 중복 방지
    - 원하는 값이 포함되어 있는지 확인하기 위해

### 1.1 HashSet

- 순서가 전혀 필요 없는 데이터를 해시 테이블에 저장.
- Set중에 성능이 가장 좋다. (정렬작업이 필요 없어서)
- 주요 메소드
    - add(E e): 데이터 추가
    - clear(): 모든 데이터 삭제
    - clone(): 객체 복제(데이터 복제 X)
    - contains(Object o): 해당 객체가 있는지 확인
    - isEmpty(): 데이터 유무 확인
    - iterator(): 데이터 꺼내기 위해 Iterator 객체 리턴

        ```java
        Iterator<String> iterator = carSet.iterator();
        while(iterator.hasNext()) {
        	System.out.print(iterator.next());
        }
        
        ```

    - remove(Object o): 매개변수 삭제
    - size(): 데이터 개수

### 1.2 TreeSet

- 저장된 데이터의 값에 따라서 정렬되는 셋.
- red-black이라는 트리 타입으로 값이 저장됨
- HashSet보다 성능이 약간 느림

### 1.3 LinkedHashSet

- 연결된(Linked) 목록 타입으로 구현된 해시 테이블에 데이터 저장
- 저장된 순서에 따라 값이 정렬됨.
- 성능이 Set중에 가장 나쁨