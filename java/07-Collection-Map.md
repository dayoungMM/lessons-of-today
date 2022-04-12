## 1. Map

- Key - value 값으로 이루어진 구조
    - Key는 해당 Map에서 고유해야함
- 주요메소드
    - put(K key, V value): 데이터 저장
    - get(Object key): 데이터 확인
    - remove(Object Key): 데이터 삭제
    - keySet(): 키 목록을 Set 타입으로 리턴
    - values(): 값의 목록을 Collection 타입으로 리턴
    - entrySet(): Map안의 Entry라는 타입의 Set 리턴 (Map은 Entry의 집합)
- 여러 쓰레드 동기화
    - Hashtable이 아닌 Map은 쓰레드 안정성 X
    - 다음과 같이 동기화 하거나

    ```java
    Map m = Collections.synchronizedMap( new HashMap(...));
    ```

    - ConcurrentHashMap, CopyOnWriteArrayList 등 쓰레드 안전성이 보장된 클래스 사용하기


### 1.1 HashMap

- HashMap의 Key는 primary type, reference type모두 가능. 그러나 그 Object의 hashCode(), equals() 잘 구현되어있어야함
- Key는 순서없이 Set 형태로 읽어옴

### 1.2 TreeMap

- 키를 정렬해서 저장
    - 숫자 > 알파벳 대문자 > 알파벳 소문자> 한글
- 많은 데이터를 보관하여 처리할 때에는 HashMap보다 느림(정렬화해서 저장하기 때문)
- 적은 데이터를 정렬 처리해서 자주 읽어와야 한다면 HashMap보다 유리

### 1.3 LinkedHashMap

- 데이터가 입력된 순서대로 Key가 보장됨
- 그 외의 사용방법은 HashMap과 동일

### 1.4 HashTable

- Map 인터페이스를 구현하긴 했지만 일반적인 클래스들과 다르다
- Map은 Collection View를 사용하지만 Hashtable은 Enumeration 객체를 통해 데이터 처리
- Map은 k, v, k-v 쌍으로 데이터 순환해서 처리가능. Hashtable은 k-v 쌍으로 데이터 순환 처리 불가
- Map → 이터레이션 처리하는 도중에 데이터 삭제하는 안전한 방법 제공, Hashtable→ 없음