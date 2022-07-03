# 단일 장애 지점(SPOF)이란?

## SPOF (Single Point of Failure)

- 시스템 구성 요소 중에서 하나가 동작하지 않으면 전체 시스템이 중단되는 요소
- 이중화가 되어있지 않은 요소라면 SPOF일 가능성이 높다
- SPOF가 없애는 것이 장애회복성을 위해 필요하다

## 해결

- 이중화
  - 고가용성(High Availiability)를 확보
  - 시스템의 장애를 대비하는 등 안정성을 강화하기 위해 같은 시스템을 두 벌 또는 그 이상으로 만들어 두고 하나의 시스템에 장애가 생겼을 때 즉시 다른 시스템으로 전환되도록 만들어진 구조
  - 대표적인 방식 두가지
    - Active-Active
      - 동일한 두벌의 시스템 같이 운영(두개 다 Active)
      - 장점: 처리가능한 전체 용량 증가
      - 단점: 장애 발생시 역량 절반으로 줄어들어 서비스 정상 작동하지 않을 수도 있음
    - Active-Standby
      - 두벌의 시스템을 만들어 두되, 하나만 active, 나머지 하나는 standby로 대기
      - 장애 발생시 standby 시스템을 활성화함
      - 장점: 일정한 성능 유지
      - 단점: 자원의 비효율성(standby 서버 성능 낭비)

- 참고자료

  [https://blog.seongjun.kr/single-point-of-failure/](https://blog.seongjun.kr/single-point-of-failure/)

  [https://haeunyah.tistory.com/122](https://haeunyah.tistory.com/122)