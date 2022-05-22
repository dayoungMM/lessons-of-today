# DAO vs DTO

## DAO (Data Access Object)

- 데이터 베이스의 data에 접근하기 위한 객체
- 데이터 베이스에 접근하기 위한 로직이나 비즈니스 로직을 분리하기 위해 사용

## DTO (Data Transfer Object)

- 계층간 데이터 교환을 하기 위해 사용하는 객체
- 로직을 가지지 않는 순수한 데이터 객체 (getter, setter만 가지고 있음)

## VO (Value Object)

- 값 오브젝트로써 값을 위해 쓰임
- read-only
- DTO랑 유사하지만 VO는 setter가 없음