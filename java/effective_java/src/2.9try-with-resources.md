# try-finally 보다 try-with-resources를 사용하라

## try-finally의 한계

- 자원이 둘 이상이면 finally 두번 써야함 → 코드 지저분

```java
public class Copy {
    private static final intBUFFER_SIZE= 8 * 1024;

    // 코드 9-2 자원이 둘 이상이면 try-finally 방식은 너무 지저분하다! (47쪽)
    static void copy(String src, String dst) throws IOException {
        InputStream in = new FileInputStream(src);
        try {
            OutputStream out = new FileOutputStream(dst);
            try {
                byte[] buf = new byte[BUFFER_SIZE];
                int n;
                while ((n = in.read(buf)) >= 0)
                    out.write(buf, 0, n);
            } finally {
                out.close();
            }
        } finally {
            in.close();
        }
    }

    public static void main(String[] args) throws IOException {
        String src = args[0];
        String dst = args[1];
copy(src, dst);
    }
}
```

## try-with-resources 구현

- AutoCloseable 인터페이스 구현
```java
public class Copy {
    private static final int BUFFER_SIZE = 8*1024;

    // 복수의 자원을 처리하는 try-with-resources
    static void copy(String src, String dst) throws IOException {
        try (InputStream in = new FileInputStream(src);
            OutputStream out = new FileOutputStream(dst)) {
            byte[] buf = new byte[BUFFER_SIZE];
            int n;
            while ((n = in.read(buf)) >= 0)
                out.write(buf, 0, n);
        }
    }

    public static void main(String[] args) throws IOException{
        String src = args[0];
        String dst = args[1];
        copy(src, dst);
    }
}
```
