# 격리 레벨과 동시성 이슈

## 트랜잭션 격리 수준(isolation level)

- 동시에 여러 트랜잭션이 처리될 때 트랜잭션끼리 얼마나 서로 얼마나 고립되어 있는지 나타내는 수준
- 특정 트랜잭션이 다른 트랜잭션에 변경한 데이터를 볼 수 있도록 허용할지 말지 결정

## 왜 중요?

- 데이터베이스는 ACID 특징을 지키기 위해 트랜잭션이 DB를 다루는 동안 (주로) Locking을 통해 다른 트랜잭션이 관여하지 못하게 된다
- 그러나 Locking으로 동시에 수행되는 수많은 트랜잭션들을 순서대로 처리하는 방식으로 구현하게 되면 데이터 베이스 성능이 떨어짐
- 한편 성능을 높이기 위해 Locking 범위를 줄인다면 정합성이 깨질 수 있다.
- 따라서 성능과 동시성 제어를 고려해 격리 수준을 정하는 것이 중요하다

## 용어설명

- 공유(shared) lock
    - 데이터 Read 할 때 사용되어지는 Lock
    - 공유 락끼리 동시에 접근 가능
- 배타(exclusive) lock
    - 데이터를 write 할 때 사용됨. 트랜잭션 완료시까지 유지
    - lock이 해제될 때까지 다른 트랜잭션(read 포함)은 해당 리소스에 접근 불가능

## 단계

- READ UNCOMMITTED
- READ COMMITTED
- REPEATABLE READ
- SERIALIZABABLE

아래로 내려갈수록 트랜잭션간 고립 정도가 높아지며, 성능이 떨어짐

### 0. READ UNCOMMITTED

- 트랜잭션이 처리중이거나, 아직 commit 되지 않은 데이터를 다른 트랜잭션이 읽는것을 허용
- 데이터의 일관성을 유지하는 것이 불가능함 → RDBMS 표준에서 격리수준으로 인정하지 않음
- `Dirty Read,`  `Non-Repeatable Read`, `Pantom Read` 발생 가능

### 1. READ COMMITTED

- 트랜잭션이 수행되는 동안 다른 트랜잭션이 접근할 수 없어 대기
- commit 이 이루어진 트랜잭션만 조회 가능
- `Non-Repeatable Read` ,  `Pantom Read`발생 가능

### 2. REPEATABLE READ

- 트랜잭션이 시작되기 전에 커밋된 내용에 대해서만 조회할 수 있음
- Non-Repeatable Read 발생하지 않음
- 자신의 트랜잭션 번호보다 낮은 트랜잭션 번호에서 commit 된 결과만 보게됨
- `Pantom Read` 발생 가능

### 3. SERIALIZABABLE

- 가장 단순하고 엄격한 격리수준
- 읽기 작업에도 공유 잠금을 설정하고, 동시에 다른 트랜잭션에서 이 레코드를 변경하지 못함
- 동시처리 능력이 다른 격리 수준보다 떨어지고, 성능 저하 발생 가능

## 낮은 트랜잭션 격리 수준에서 발생하는 현상 (동시성 이슈)

### `Dirty Read`

- 유효하지 않은 데이터를 읽어들임
- Commit 되지 않은 수정중인 데이터를 다른 트랜잭션에서 읽을 수 있도록 허용할 때 발생

```
1. A 트랜잭션에서 10번 사원의 나이를 27살에서 28살로 바꿈
2. 아직 커밋하지 않음
3. B 트랜잭션에서 10번 사원의 나이를 조회함
4. 28살이 조회됨
    → 이를 더티 리드(Dirty Read)라고 한다
5. A 트랜잭션에서 문제가 발생해 ROLLBACK함
6. B 트랜잭션은 10번 사원이 여전히 28살이라고 생각하고 로직을 수행함
```

### `Non-Repeatable Read`

- 한 트랜잭션에서 같은 Query를 두번 수행할 때 두 Query의 결과가 상이하게 나타나는 현상
- 특정 트랜잭션이 접근하고 있는 데이터를 다른 트랜잭션이 수정 또는 삭제하므로써 발생
- 하나의 트랜잭션 내에서 똑같은 SELECT를 수행했을 경우 항상 같은 결과를 반환해야한다는 Repeatable Read 정합성에 어긋나는 경우

```
1. B 트랜잭션에서 10번 사원의 나이를 조회
2. 27살이 조회됨
3. A 트랜잭션에서 10번 사원의 나이를 27살에서 28살로 바꾸고 커밋
4. B 트랜잭션에서 10번 사원의 나이를 다시 조회(변경되지 않은 이름이 조회됨)
5. 28살이 조회됨 
		-> Non-Repeatable Read
```

### `Pantom Read`

- 한 트랜잭션 안에서 일정 범위의 레코드를 두번 이상 읽었을 때 첫번째 쿼리에서 없던 레코드가 두번째 쿼리에서 나타나는 현상(유령 레코드)
- 트랜잭션 도중 새로운 레코드 삽입을 허용하기 때문에 나타남

| Isolation Level | Dirty Read | Non-Reapeatable Read | Phantom Read |
| --- | --- | --- | --- |
| Read Uncommitted | O | O | O |
| Read committed | - | O | O |
| Repeatable Read | - | - | O |
| Serializable | - | - | - |

- 참고

  [https://joont92.github.io/db/트랜잭션-격리-수준-isolation-level/](https://joont92.github.io/db/%ED%8A%B8%EB%9E%9C%EC%9E%AD%EC%85%98-%EA%B2%A9%EB%A6%AC-%EC%88%98%EC%A4%80-isolation-level/)

  [https://dar0m.tistory.com/225](https://dar0m.tistory.com/225)

  [https://untitledtblog.tistory.com/123](https://untitledtblog.tistory.com/123)