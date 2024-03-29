# API Gateway

## 필요성

- 각각의 서비스마다 인증/인가 등 공통된 로직을 구현해야하는 번거로움이 있음
- 수많은 API 호출을 기록하고 관리하기 어려움
- 클라이언트에서 여러 마이크로 서비스에 대해 번거로운 호출을 해야함(서비스 주소가 달라지면 클라이언트도 알고있어야 함)
- 내부의 비즈니스 로직이 클아이언트에게 공개되어 보안에 취약해짐

## API Gateway

- API 서버 앞단에서 모든 API 서버들의 엔드포인트를 단일화 해주는 서버
- API에 대한 인증과 인가 기능을 가지고 있음
- 메세지의 내용에 따라 어플리케이션 내부에 있는 마이크로 서비스로 라우팅 하는 역할 수행

### 주요 기능

1. 인증 및 인가 (Authentication and Authorization)
    - 인증 (Authentication): 유저가 누구인지 확인
    - 인가 (Authorization) : 유저가 특정 자원에 접근하려고 할 때 그에 대한 접근 권한이 있는지 확인
2. 요청 절차의 단순화
    - 여러 마이크로 서비스를 대상으로 기능을 이용하려고 할 때 클라이언트가 일일이 요청하지 않아도 됨
    - 여러 클라이언트의 요청을 단일 클라이언트의 요청으로 대체 가능하도록 함
    - 클라이언트와 백엔드 간의 API 통신량 줄여줌 → 대기시간 감소, 효율성 증가
3. 라우팅 및 로드밸런싱
    - API 호출에 대해 적절한 서비스에 라우팅
    - 서비스 인스턴스들에 대한 부하 분산
4. 서비스 디스커버리
    - 클라이언트가 서비스의 IP와 포트번호 몰라도 Gateway에 요청하면 알아서 서비스로 포워딩함
5. Mediation
    - 메세지 포맷 변환 : 요청메세지를 변환해서 API 서버로 보내야하는 경우 Gateway에서 처리하여 보낼 수 있음
    - 프로토콜 변환

### 고려할 점

- API Gateway를 내부 마이크로 서비스와 결합함. SOA에서 발생한 문제점이 다시 발생할 수 있음
- Scale-out이 유연하게 일어나지 않을 경우 병목지점이 될 수 있음
- Gateway라는 추가적인 계층이 만들어지는 것이기 때문에 그만큼 네트워크 latency가 증가
- spof

### 해결

- L4, L7을 앞에 둬서 SPOF 문제 완화
- 헤비한 로직을 gateway에서 실행하지 말자

- 참고

  [https://velog.io/@tedigom/MSA-제대로-이해하기-3API-Gateway-nvk2kf0zbj](https://velog.io/@tedigom/MSA-%EC%A0%9C%EB%8C%80%EB%A1%9C-%EC%9D%B4%ED%95%B4%ED%95%98%EA%B8%B0-3API-Gateway-nvk2kf0zbj)

  [https://bcho.tistory.com/1005](https://bcho.tistory.com/1005)