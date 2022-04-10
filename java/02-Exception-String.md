## Try- Catch

- try - catch -(finally)

```java
public class multiCatch {
    public static void main(String[] args) {
        int[] intArray = new int[5];
        try {
            
        } catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("ArrayIndexOutOfBoundsException");
        } catch (Exception e) {
            System.out.println("Exception");
        }
    }
}
```

- 하나의 try 블록에서 예외가 발생하면 그 예외와 관련이 있는 catch 블록을 찾아서 실행됨
- 먼저 선언한 catch 블록의 예외 클래스가 다음에 선언한 catch 블록의 부모에 속하면, 자식에 속하는 catch 블록은 절대 실행될 일이 없으므로 컴파일 되지 않는다
    - example
        - Exception catch 블록이 먼저 선언되고, ArrayIndexOutOfBoundsException 나중에 선언될 경우
        - Exception은 모든 예외의 부모 클래스이기 때문에 모두 이 블록에 걸릴것이다
        - 따라서 ArrayIndexOutOfBoundsException 블록은 실행될 일이 없다
- catch 블록 중 발생한 예외와 관련있는 블록이 없으면 예외가 발생되면서 해당 쓰레드는 끝난다
    - 따라서 catch 마지막에는 Exception 클래스로 묶어주자


## Throwable

- 모든 Exception 과 Error 클래스의 부모 클래스
- 대표 메서드
    - getMessage(): 예외메세지를 String 형태로 제공받음. 이 메세지를 활용하여 별도의 예외 메세지 사용자에게 보려주려고 할 때 사용
    - toString(): getMessage 보다 자세하게 예외메세지를 String 형태로 제공받음


## 예외 발생(throw, throws)

- try 블록 내에서 예외 발생시키는 경우 ⇒ throw 예약어 뒤에 예외 객체 생성하거나 생성되어있는 객체 명시

```java
public class ThrowSample {
    public static void main(String[] args) {
        ThrowSample throwSample = new ThrowSample();
        throwSample.throwException(13);
    }

    private void throwException(int num) {
        try {
            if(num>12) {
                throw new Exception("number is over 12");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
```

```
[결과]
number is over 12
```

- try- catch 로 예외 발생 케이스에 대해 대응 가능
- 메소드 선언할 때 매개변수 소괄호 뒤에 throws 라는 예약어를 적어둔 뒤 예외를 선언 ⇒ 선언한 예외가 발생했을 때 호출한 메소드로 예외 전달

```java
public static void main(String[] args) throws Exception {
        ThrowSample throwSample = new ThrowSample();
        throwSample.throwException(13);
    }
```

- Throwable 클래스를 이용해 Customize 한 예외 클래스를 만들 수 있다.

---

## String

<aside>
💡 메소드가 너무 많으므로 대표적인 메서드의 대분류 정도만 적고, 자세한건 검색하도록 하자

</aside>

- 문자열이 같은지 비교
    - equals
    - equalsIgnoreCase
- 특정 조건에 맞는 문자열이 있는지 확인
    - startsWith
    - endsWith
    - contains
    - matches(String regex)
    - regionMatches
- Index 찾기
    - indexOf
    - lastIndexOf
- String 값의 일부 추출
    - charAt
    - copyValueOf
    - toCharArray
- 문자열 일부 값 잘라내기
    - substring
    - subSequence
- 문자열을 여러개의 String 배열로 나누기
    - split
- String 값 바꾸기
    - concat
    - trim: 맨 앞과 맨 뒤의 공백 제거한 문자열 객체 만들어 리턴
    - replace
    - replaceAll
    - replaceFirst
- 치환
    - format
        - %s: String
        - %d: 정수형
        - %f: 소수점이 있는 숫자
        - %%: %
- 대소문자 치환
    - toLowerCase
    - toUpperCase
- String 타입으로 변환
    - valueOf