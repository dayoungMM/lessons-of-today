# JWT란 무엇이고 어떻게 써야 되는가? (쿠키, 세션 또는 기타 저장방식 이용)

## 개념

- Json Web Token
- 사용자 인증을 위해 사용하는 암호화된 토큰

## 구성

3파트로 나누어지고, 각 파트는.(점)으로 구분

- header
    - 토큰 타입과 해시 알고리즘
- payload
    - 토큰에 담을 정보
    - 각 정보의 단위는 claim
    - claim은 key-value의 한쌍으로 이루어짐
- signiture
    - 서명은 [헤더 base64 + 페이로드 base64 + SECRET_KEY ] 를 사용하여 JWT 백엔드에서 발행
    - 각 요청시 서명이 확인
    - 헤더 또는 페이로드의 정보가 클라이언트에 의해 변경된 경우 서명이 무효화

## 세션과 차이점

- 세션
    - 서버측에서 유저들의 정보를 세션에 기억하고 있어야함

- 토큰 기반(JWT)
    - 유저의 인증 정보를 서버나 세션에 담아두지 않아도 됨. 토큰 저장소가 필요 없음
    - 서버에서 토큰을 생성하고 클라이언트에 보내주면 →  클라이언트에서 토큰을 저장하고 있다가 요청할 때 토큰정보를 함께 담아 요청 → 서버는 JWT 디코딩 하여 유저 인증

## 장점

- stateless 하기 때문에 확장성이 있다.
    - 세션은 서버에 저장해야하기 때문에 서버 여러대를 사용한다면 처음 로그인한 서버에만 요청해야함.
    - 하지만 JWT는 서버 저장소가 필요 없기 때문에 어떤 서버에 요청하든 상관 없다.
- 보안성 쿠키를 전달하지 않아도 되므로 쿠키를 사용함으로서 발생하는 취약점이 사라짐
- 여러 플랫폼 및 도메인 어플리케이션 규모가 커져서 여러 디바이스를 호환시키더라도, 토큰을 사용한다면 어떤 디바이스에서라도 요청이 정상 처리됨

## 단점

- claim에 넣는 데이터가 많아질 수록 JWT토큰이 길어짐 → API 호출 시 네트워크 대역폭 낭비
- Payload에 대한 정보를 암호화 하지 않음 → 단순히 BASE64로 인코딩만 하기 때문에 중간에 패킷을 가로채거나 기타 방법으로 토큰을 취득했으면 디코딩을 통해 데이터를 볼 수 있음
    - 따라서 JWE(JSON Web Encryption)를 통해 암호화 하거나 중요데이터를 Payload에 넣지 말아야 함
- 발급된 JWT는 삭제가 불가능하다. 따라서 토큰을 탈취당하면 유효시간이 종료되기 전까지 악의적으로 사용될 수 있다.
    - 이 경우 Refresh Token이라는 것을 이용해 피해를 조금이라도 줄일 수 있다.


## 어떻게 써야하는가?

- 세션처럼 서버가 별도의 저장소를 가질 필요 없다.
- 클라이언트가 저장하고 있다가 요청시마다 JWT를 담아 요청
- 동작방식
    - 클라이언트가 로그인 요청
    - 서버에서 유저의 고유한 ID와 다른 인증 정보들과 함께 payload에 담는다
    - JWT의 유효기한 설정 및 옵션을 설정
    - secret key를 이용해 토큰 발급
    - 발급된 토큰은 클라이언트에 쿠키 혹은 로컬 스토리지 등에 저장하여 요청시마다 같이 보낸다
    - 서버는 토큰을 secret key로 복호화 하여 검증하는 과정을 거친다
    - 검증이 완료되면 대응하는 데이터를 보내준다

## 언제 유용할까?

- 회원 인증
    - 세션처럼 별도의 저장소 관리를 서버에서 하고싶지 않을 때
- 조작되지 않은 정보인지 검증할 때