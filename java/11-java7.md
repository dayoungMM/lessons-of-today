## Java 7 에서 달라진 것

### 숫자 표현법

- 10진수: 숫자 앞에 아무런 접두사 없음
- 8진수: 0으로 시작하는 숫자
- 16진수: 0x 로 시작하는 숫자
- 2진수; 0b로 시작하는 숫자

```java
int decVal = 1106; //1106
int octVal = 02112; //1106
int hexVal = 0x452; //1106
int vinaryVal = 0b10001010010; //1106

int million = 1_000_000; //1000000
```

### Switch 문장

- case 조건문에 int, String 모두 사용 가능

```java
public class SwitchExample {
    public static void main(String[] args) {
        SwitchExample switchExample = new SwitchExample();
        System.out.println(switchExample.salaryIncreaseAmount("Engineer"));
    }

    public double salaryIncreaseAmount(String employeeLevel) {
        switch(employeeLevel) {
            case "CEO":
                return 10.0;
            case "Manager":
                return 15.0;
            case "Engineer":
            case "Developer":
                return 100.0;
        }
        return 0.0;
    }
}
```

### 제네릭

- 생성자에는 일일이 타입 명시할 필요 없음. 변수 선언시 제네릭 타입 지정했기 때문

```java
public class TypeInference {
    public static void main(String[] args) {
        TypeInference type = new TypeInference();
        type.makeObjects1();
        type.makeObjects2();
    }

    public void makeObjects1() {
        GenericClass<Integer> generic1 = new GenericClass<Integer>("String");
        generic1.setValue(999);
    }

    public void makeObjects2() {
        GenericClass<Integer> generic1 = new GenericClass("String");
        generic1.setValue(999);
    }
}
```

### Folk, Join

- folk: 여러개로 나누어 작업
- Join :결과를 모음
- CPU를 더 쉽게, 효율적으로 사용하기 위해
- 쓰레드 객체 만들거나 쓰레드 작업을 할당하지 않아도 JVM에서 알아서 수행

## NIO2

- 기존 File 클래스의 단점들을 보안한 클래스
    - 단점
        - 심볼릭 링크, 속성파일의 권한등에 기능이 없음
        - delete()는 실패시 예외 발생시키지 않고 Boolean만 리턴
        - 파일이 변경되었는 확인하는 방법은 lastModified()의 결과와 이전 시간 비교하는 방법뿐 → 성능 문제

## JDBC 4.1

- RowSet (인터페이스): Connection 및 Statement 객체를 생성할 필요 없이 SQL Query 수행 가능
- RowSetFactory와 RowSetProvicer를 사용하면 쉽게 RowSet을 생성 가능