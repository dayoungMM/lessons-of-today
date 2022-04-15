# Java 8에서 달라지거나 추가된 것
## Optional

- null 처리를 보다 간편하게 처리하기 위해 만들어짐
- final로 선언되어있어 추가 확장 불가

```java
public class OptionalExample {
    public static void main(String[] args) {
        Optional<String> emptyString = Optional.empty();
        System.out.println(emptyString.isPresent());

        String common = null;
        Optional<String> nullableString = Optional.ofNullable(common);
        System.out.println(nullableString.isPresent());

        common = "common";
        Optional<String> commonString = Optional.of(common);
        System.out.println(commonString.isPresent());
    }
}
```

## Labmda

```java
@FunctionalInterface
public interface Calculate {
    int operation(int a, int b);
}
```

```
public class LabmdaExample {
    public static void main(String[] args) {

    }

    private void calculateLambda() {
        Calculate calcAdd = (a,b) -> a+b;
        System.out.println(calcAdd.operation(1,2));
        Calculate calcSub = (a,b) -> a-b;
        System.out.println(calcSub.operation(4,3));
    }
}

```

- @FunctionalInterface : 하나의 메소드만 선언되는 Functional 인터페이스라고 표시하는 어노테이션
- 메소드가 하나만 있어야 람다표현식 쓸 때 매칭이 될 수 있다.
- Funcional Interface만 람다 표현식으로 처리할 수 있다.
- (매개변수 목록) → 처리식

## Predicate

```
public class PredictExample {
    public static void main(String[] args) {
        PredictExample sample = new PredictExample();

        Predicate<String> predicateLength5 = (a) -> a.length()>5;
        Predicate<String> predicateContains = (a) -> a.contains("God");

        String godOfJava = "GodOfJava";
        sample.predicateTest(predicateLength5, godOfJava);
        sample.predicateAnd(predicateContains,predicateLength5, godOfJava);

    }

    private void predicateTest(Predicate<String> p, String data) {
        System.out.println(p.test(data));
    }

    private void predicateAnd(Predicate<String> p1, Predicate<String> p2, String data) {
        System.out.println(p1.and(p2).test(data));
    }
}
```

```java
true
true
```

## Stream

- Collection 과 같이 목록을 처리할 때 사용
- 스트림 생성 - 중간연산 -종단연산
    - 생성: stream()
    - 중간 연산: 데이터 가공 후 Stream 타입 리턴
    - 종단: 스트림 처리 후 숫자, 목록형 데이터 리턴

```java
public class StudentForEachSample {
    public static void main(String[] args) {
        StudentForEachSample sample = new StudentForEachSample();
        List<StudentDTO> studentList = new ArrayList<>();
        studentList.add(new StudentDTO("요다",43,99,10));
        studentList.add(new StudentDTO("만두",30,71,20));
        studentList.add(new StudentDTO("건빵",32,64,40));
        sample.printStudentNames(studentList);
    }

    private void printStudentNames(List<StudentDTO> students) {
        students.stream().forEach(student -> System.out.println(student.getName()));
    }
}
```

```java
요다
만두
건빵
```

## stream map()

- 해당 스트림의 내용을 바꾸어 리턴

```java
private void printNim(List<StudentDTO> students) {
        students.stream().map(x -> x.getName()+"님").forEach(System.out::println);
    }
```

요다님
만두님
건빵님

## stream collect()

```java
private void collectName(List<StudentDTO> students) {
        List<String> nameList = students.stream().map(x -> x.getName()).collect(Collectors.toList());
        System.out.println(nameList);
    }
```

[요다, 만두, 건빵]

## stream filter()

```java
private void filterAgradeMath(List<StudentDTO> students, int scoreCutline) {
        students.stream()
            .filter(student -> student.getScoreMath() > scoreCutline)
            .forEach(student -> System.out.println(student.getName()));
    }
```

요다