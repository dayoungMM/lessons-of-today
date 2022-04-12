## 개념

- JVM 시작 → 자바 Process 시작 → 프로세스 안에서 Thread 수행
- 작업을 동시에 해주려면 여러 Thread 생성해서 수행
- 각 Thread마다 메모리를 할당해줌
- Process
    - 메모리에 올라와 실행되고 있는 프로그램의 인스턴스(독립적인 개체)
    - 운영체제로부터 시스템 지원을 할당받는 작업의 단위
    - 프로세스는 각각 독립된 메모리 영역(Code, Data, Stack, Heap 구조)를 할당받음
    - 프로세스당 최소 1개의 thread(main thread)를 가지고 있다.
    - 각 프로세스는 별도의 주소공간에서 실행. 변수, 자료구조 등에 접근 X (통신해야함)

  ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/3c116a30-a750-4cb7-85c6-7fe4de36291b/Untitled.png)

- Thread
    - 프로세스 내에서 실행되는 여러 흐름의 단위. 프로세스가 할당받은 자원을 이용하는 실행의 단위
    - thread는 프로세스 내에서 각각 stack 만 따로 할당받고 Code, Data, Heap 영역은 공유한다
    - 같은 프로세스 안에 있는 thread끼리 heap 공간 공유

  ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/81259223-4750-4048-97ca-639d9d02f76e/Untitled.png)


## 멀티 프로세스 / 멀티 스레드

- 멀티 프로세스
    - 개념
        - 하나의 응용프로그램을 여러개의 프로세스로 구성.
    - 장점
        - 한 프로세스가 장애 발생해도 다른 프로세스에 영향 없음
    - 단점
        - Context Switching 에서의 오버헤드: 프로세스는 각각 독립된 메모리 영역 할당받아 (공유하는 메모리 x), Context Switching이 발생하면 캐쉬에 있는 모든 데이터 리셋하고 다시 캐시 정보 받아야함
            - context switching: CPU에서 여러 프로세스 돌아가면서 작업하면서 처리할 때 동작중인 프로세스가 대기하면서 해당 프로세스의 상태(Context)를 보관
        - 프로세스 사이에 어렵고 복잡한 통신기법
- 멀티 스레드
    - 개념
        - 하나의 응용프로그램을 여러개의 스레드로 구성하고, 각 스레드가 하나의 테스트 수행
        - 웹서버는 대표적인 멀티 스레드 응용 프로그램
    - 장점
        - 시스템 자원 효율성 증대: 프로세스를 생성하여 자원을 할당하는 시스템 콜이 줄어듦.
        - 시스템 처리 비용 감소: 시스템 자원 소모 감소(Stack 외 메모리 공유). 스레드 사이의 작업량이 작아(stack만 처리) Context Switching이 빠르다
        - 간단한 통신방법: 프로세스 내 스레드들은 stack 제외하고 모든 메모리 공유하기 때문에 통신 부담 적음
    - 단점
        - 디버깅 까다로움
        - 멀티스레드 일 경우 동기화 문제 발생가능
        - 하나의 스레드에 문제생기면 전체 프로세스 영향받음

## Thread 생성방법

1. Runnable 인터페이스
    - run() 메소드만 있음. 쓰레드가 시작되면 수행되는 메소드
2. Thread 클래스 사용
    - Runnable 인터페이스를 구현한 클래스
    - 생성자가 여러개 있음
        - 특정 이름의 쓰레드를 만들수도, ThreadGroup으로 여러 쓰레드를 묶어놓을수도 있음
        - 쓰레드를 생성할 때 매개변수를 받는 생성자를 만들고 싶다면 Override 해서 쓰자

## Thread 확장

- 다른 클래스를 extends 하면서 쓰레드로 구현해야하는 경우 → 다중상속 불가 → 다중 구현이 가능한 Runnable 인터페이스를 구현해서 사용
- 그 외에는 Thread 가 편리

## 주요 메소드

- sleep(long millis): 시간(1/1000초 단위) 만큼 대기
- getPriority(): 쓰레드의 우선순위 확인
- setPriority(int newPriority) : 쓰레드의 우선순위 지정 (장애일어날 수 있으니 되도록 직접 설정하지 말자)
    - MAX_PRIORITY : 10
    - NORM_PRIORITY: 5(일반 순위)
    - MIN_PRIORITY: 1
- setDemon(true)
    - 해당 쓰레드를 데몬 쓰레드로 지정
    - JVM은 사용자 쓰레드가 끝날때까지 기다리지만, 데몬 쓰레드로 지정하면 그 쓰레드가 수행되고 있는지 여부를 떠나 JVM이 끝날 수 있다.


## synchronized

- 쓰레드에 안전(Thread safe)하기 위해서 사용필수
- 여러 쓰레드가 한 객체에 동시 접근하여 메소드를 처리할 시.
- 사용 방법
    1. 메소드 자체를 synchronized 로 선언(synchronized methods)

       동일한 객체를 참조하는 다른 쓰레드에서 plus메소드로 amount 변경하려고 하면 먼저 들어온 쓰레드가 종료될 때까지 기다림


    ```
    public synchronized void plus(int value) {
    	amount += value;
    };
    ```
    
    1. 메소드 내의 특정 문장만 synchronized로 감싸는 방법(synchronized statements) 
        
        중괄호 안에 있는 연산만 동시에 여러 쓰레드에서 병렬 처리하지 않겠다는 의미
        
    
    ```
    public synchronized void plus(int value) {
    	synchronized(this) {
    		amount += value;
    	}
    };
    ```

- 결론
    - 여러 쓰레드에서 하나의 객체에 있는 인스턴스 변수를 동시에 처리하고 싶을 때 발생하는 문제를 해결할때만 사용
    - 만약 변수가 선언되어있는 객체를 다른 쓰레드에서 공유할 일이 전혀 없으면 synchronized 사용할 이유 없음


## Thread 통제 메소드

- getState() : 쓰레드 상태 확인
- join(): 수행중인 쓰레드가 중지할 때까지 대기
- join(long milis) : 매개변수에 지정된 시간만큼 대기
- join(long millis, int nanos) : 첫번째 매개변수 시간(1/1000초 단위) + 두번째 매개변수 시간(1/10억)만큼 대기
- interrupt(): 수행중인 쓰레드에 중지 요청

## Thread 상태확인 메소드

- checkAccess(): 현재 수행중인 쓰레드 수정권한 확인
- isAlive(): 살아있는지 확인
- isInterrupted(): 정상종료되지 않고 interrupted 되었는지
- interrupted(): 현재 쓰레드가 중지되었는지

- activeCount: 현재 쓰레드가 속한 쓰레드 그룹 중 살아있는 쓰레드 개수
- currentThread(): 현재 수행중인 쓰레드의 객체 리턴
- dumpStack: 콘솔창에 쓰레드 스택 정보 풀력

## Object 클래스에 선성된 Thread 관련 메소드

- wait(), wait(long timeout): 쓰레드 대기
- notify(), notifyAll(): Ojbect 객체의 모니터에 대기중인 쓰레드 깨우기

## ThreadGroup

- 쓰레드의 관리를 용이하게 하기 위한 클래스
- enumerate(Thread[] list) ⇒ 현재 쓰레드 그룹에 있는 모든 쓰레드를 매개변수로 넘어온 쓰레드 배열에 담음
- enumerate(ThreadGroup[] list) ⇒ int 리턴 : 쓰레드 그룹에 있는 모든 쓰레드를 매개변수로 넘어온 쓰레드 그룹 배열에 담음
- 쓰레드 그룹의 모든 쓰레드를 출력하고 싶을 때
    - activeCount()로 쓰래드 개수 확인
    - list()로 콘솔에 출력하거나
    - 쓰레드 개수만큼 배열을 생성한 후 enumerate()로 쓰레드 데이터를 저장하고 for 문으로 출력