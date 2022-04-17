# Object Pool

## 개념

- 많은 수의 오브젝트가 생성되는 것을 방지하고자 Pool에 오브젝트들을 담아두고 사용 및 반납을 하는 방식
- 매번 new 로 인스턴스를 생성하는 것이 아니라, Pool에 있는 오브젝트를 빌려오고 반납하는 방식
- 장점
    - 잦은 오브젝트 할당으로 인한 메모리 부하 방지

## 예시

```java
@Test
    public void test() throws Exception {

        GenericObjectPool genericObjectPool = new GenericObjectPool(new PersonPool());

        for (int i = 0; i < 1000; i++) {
            Person person = (Person) genericObjectPool.borrowObject();
            logger.info("{} {}", person, person.getCount());
            genericObjectPool.returnObject(person);
        }

        logger.info("Tested");
    }
```

- person을 생성할 때 new로 객체를 생성하는것이 아니라
- GenericObjectPool에서 borrowObject 해서 사용하고
- 마지막에 returnObject로 반환한다