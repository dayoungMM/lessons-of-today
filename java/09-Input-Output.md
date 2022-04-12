## 개념

- input/output
- [java.io](http://java.io) 패키지
    - 바이트 파일 기반의 데이터: Stream 클래스 (InputStream, OutputStream)
        - Stream: 끊기지 않고 연속적인 데이터
    - char 기반의 문자열 데이터: Reader, Writer 클래스
- NIO(New I/O)
    - JDK 1.4부터 빠른 I/O처리하기 위해 등장
    - Stream 기반 X
    - Buffer 와 Channel 기반으로 처리

## 메소드

- exists(): 파일이 해당 경로에 존재하는지 여부 리턴
- mkdir(): 디렉토리 생성
- isDirectory(), isFile() : 디렉토리/파일 여부 확인
- canRead(), canWrite(), canExecute(): 권한 확인
- list(), listFiles(): 디렉토리 하위에 있는 목록 리턴(리턴 타입에 따라 구분)

## FileFilter

- FileFilter(또는 FileNameFilter)를 상속해서 필터클래스를 만들고, listFiles(FileFilter filter), listFiles(FilenameFilter filter)를 이용하면 **내가 원하는 특정 타입의 파일만 골라서 처리**하게 할 수 있다.
- 이 때 accept() 부분을 overriding해서 원하는 형식 조건을 추가하자

## InputStream, OutputStream

- abstract 클래스
- 바이트 파일 기반의 데이터
- InputStream
    - 확장클래스 (FileInputStream, ObjectInputStream 등)의 객체를 생성해서 사용
    - 데이터 읽을때 read()로 읽고, 해당 리소스를 닫을때에는 close() 메소드로 닫아줘야한다.
- OutputStream
    - write()로 쓰고 close()로 닫아줘야함
    - flush()로 버퍼에 있는 데이터를 쌓아두지 말고 바로 저장하라고 할수도 있다

## Reader, Writer

- char 기반의 문자열 데이터
- 마지막에 close()로 리소스 닫아줘야함
- **Scanner**
  - 텍스트 기반의 기본자료형이나 문데열 데이터를 처리하기 위한 클래스


## Serializable

- 생성한 객체를 파일로 저장하거나, 다를 서버로 보내려면 Serializable interface를 구현해야만 한다.
- 즉 파일로 저장하려는 DTO(Data Transfer Object)는 implements Serializable 해줘야함.
  - 파일 읽을때에는 Object로 파일 읽어오고 DTO로 Casting
- Serializable 인터페이스 구현 후 serialVersionUID 값 지정해주기
  - 클래스 이름이 같더라도 serialVersionUID가 다르면 다른 클래스라고 인식하기 때문에
  - 데이터가 바뀌면(변수명이 바뀌는 등) serialVersionUID의 값을 변경하자
- transient라는 예약어
  - Serializable의 대상에서 제외
  - 패스워드 등 보안상 문제있는 경우 사용


## NIO

- JDK 1.4에서 부터 추가됨
- 스트림 사용 X
- Channel 과 Buffer 사용
  - channel: 도매상 여러 바이트 배열을 담음
  - buffer: 소매상 (바이트 데이터 감싸기)

- Write
  - 데이터 Bytes로 변환 → ByteBuffer로 byte 감싸기 → channel에 write → channel close
- Read
  - ByteBuffer 만들기 → channel 데이터를 만든 buffer에 담음 → flip → get으로 한바이트씩 호출 → close