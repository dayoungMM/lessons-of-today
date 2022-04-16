## 개념

- 멀티 쓰레드 프로그래밍에서 동기화를 통해 락을 획득하여 자원을 여러 곳에서 함부로 사용하지 못하게 하는데
- 두개의 쓰레드에서 서로가 가지고 있는 락이 해제되기를 기다리는 상태가 되는것을 Deadlock(교착상태)라고 한다
- 서로의 작업이 끝날때(락이 풀리길)까지  무한정 대기하는 상태
- 다음과 같이 Shell에 뜬다

```java
Thread 1  locks A, waits for B
Thread 2  locks B, waits for A
```

## DeadLock 이 걸리는 경우 예제

```java
package deadLockExample;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadlockExample {

    private Lock lock1 = new ReentrantLock(true);
    private Lock lock2 = new ReentrantLock(true);

    public static void main(String[] args) {
        DeadlockExample deadlock = new DeadlockExample();
        new Thread(deadlock::operation1, "T1").start();
        new Thread(deadlock::operation2, "T2").start();
    }

    public void operation1() {
        lock1.lock();
        System.out.println("lock1 acquired, waiting to acquire lock2.");
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        lock2.lock();
        System.out.println("lock2 acquired");;

        System.out.println("executing first operation.");

        lock2.unlock();
        lock1.unlock();
    }

    public void operation2() {
        lock2.lock();
        System.out.println("lock2 acquired, waiting to acquire lock1.");
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        lock1.lock();
        System.out.println("lock1 acquired");

        System.out.println("executing second operation.");

        lock1.unlock();
        lock2.unlock();
    }

    // helper methods

}
```

- ReentrantLock: 공유자원 접근에 대해 단 하나의 쓰레드만 접근하도록 한다
- 순환대기 상태이기 때문에 DeadLock 발생
    - operation1은 lock1을 가지고 있고 lock2를 가져올려고 대기하고 있는데,
    - operation2는 lock2를 가지고 있고 lock1을 가져올려고 대기하고 있는 상태

## 해결

- 순환대기 상태 방지
    - 모든 락이 항상 같은 순서로 획득된다면 데드락 발생하지 않음
    - 위의 예제에서 operation1, operation2 모두 lock1 → lock2로 점유하게 하면 순환대기가 발생하지 않는다
    - 획득하려는 락의 순서를 알고 있는 상태에서만 사용될 수 있다는 한계
- Lock 타임아웃
    - 스레드가 락을 획득하기 위해 기다리는 시간을 정해놓는것.
    - 타임아웃 시간이 다 되도록 락을 획득하지 못하면 이 스레드는 락을 포기한다
    - 작업 처리하기 위해 락을 오래 잡고 있어도 Lock 타임아웃에 걸려서 포기할 수도 있다는 것에 유의
    - 동기화 블록에의 진입에 대해서는 타임아웃을 설정할 수 없다는 한계. 이를 위해서는 커스텀 락클래스를 만들거나 java.util.concurrency를 사용해야 함(Thread 안전한 패키지)