# 스프링 컨테이너, 스프링 빈

## 스프링 컨테이너

- 개념
    - 스프링에서 사용할 객체를 담고있는 컨테이너
    - 등록된 스프링 빈을 생성하고 의존 관계를 주입하고 생명주기를 관리해준다
    - 스프링 컨테이너로 인해 spring의 IoC(제어의 역전), DI 특징을 띌 수 있다.
        - IoC + 싱글톤 컨테이너까지 개념을 알고싶다면 아래의 페이지를 참고

      [IoC(제어의 역전)](https://www.notion.so/IoC-c63b2b9902044faeb071f528092f309b)


- Spring 컨테이너 종류
    - Bean Factory
        - Bean의 라이프 사이클 관리
        - 처음으로 getBean()이 호출된 시점에서야 빈을 생성(Lazy Loading)
    - Application Context
        - Bean을 등록, 생성, 조회, 반환 관리하는 기능은 BeanFactory와 같음
        - BeanFactory 인터페이스를 상속받아 추가 기능을 제공함
        - 추가 기능
            - 국제화가 지원되는 텍스트 메세지를 관리
            - 이미지 같은 파일 자원을 로드할 수 있는 포괄적인 방법 제공
            - 리스너로 등록된 빈에게 이벤트 발생을 알려줌
        - 컨텍스트 초기화 시점에 모든 싱글톤 빈을 미리 로드한 후 애플리케이션 가동 후에는 Bean을 지연없이 얻을 수 있음 (미리 생성해놔 즉시 사용 가능)
        - Bean Factory와 비슷하지만 더 기능이 많기 때문에 더 많이 사용

## 스프링 빈

- 개념
    - 스프링 컨테이너가 관리하는 자바 객체
    - 스프링 컨네이너에 의해 생성부터 소멸까지 관리됨