# 스프링 vs 스프링 부트

## 스프링

- 자바 플랫폼을 위한 오픈소스 애플리케이션 프레임 워크
- 동적 웹사이트를 개발하기 위한 서비스 제공.
- 웹 개발에 필요한 기본 공통 기능은 프레임워크가 제공할테니, 개발자는 비즈니스 로직에만 집중해서 코드 작성할 수 있음

## 스프링 부트

- 스프링은 환경설정이 복잡함
- 설정을 자동화해서 사용자가 편하게 스프링을 활용할 수 있게 하기위해 등장
- 디펜던시만 추가해주면 바로 API를 정의하고 WAS로 서버 실행할 수 있음
- 스프링 이니셜라이저를 사용하면 바로 실행가능한 코드 만들어줌

## 차이점

- Spring boot는 Embed Tomcat을 사용하기 때문에 따로 설치할 필요 없다
- starter를 통한 dependency 자동화 지원. 라이브러리 버전 관리 편리
- xml 설정을 하지 않아도 됨
- jar file을 이용해 자바 옵션만으로 손쉽게 배포 가능