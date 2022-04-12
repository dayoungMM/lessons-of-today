## 네트워킹

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/62ac3011-0644-4416-a981-c1b1b5057f6f/Untitled.png)

- TCP (Transmission Control Protocol)
    - Transport Layer 레이어 통신 프로토콜
    - 연결기반 프로토콜
    - 상대방이 데이터를 받았는지 확실히 보장
    - Java에서 Socket 클래스 사용
- UDP (User Datagram Protocol)
    - 상대방이 데이터를 받았는지 보장 못함
    - TCP보다 싸고 빠르며 가벼움

## Socket

- for TCP
- [클라이언트] Socket 생성(ip, port) - OutputStream 객체 생성 - 데이터 전달후 socket close
- [서버] ServerSocket 생성(port) - 수신연결 대기 - InputStream 객체로 데이터 받아 - client 수신연결 종료 - server 소켓 종료

## DatagramSocket

- for UDP
- [서버] DatagramSocket 생성(port) - DatagramPacket 생성 - socket 대기하고 있다가 데이터 오면 packet에 담음 - byte를 String으로 변경 - socket 닫기
- [클라이언트] - DatagramSocket생성 - InetAddress로 도착지 ip 설정 - DatagramPacket 생성 - packet 전송 - soxket 종료

## HTTP 통신

- 인터넷을 통하여 웹페이지 요청하는 등 HTTP 통신할 때
- CORE JAVA
    - HttpURLConnection
    - HttpClient (Java 11 이상)
- 3rd Party
    - ApacheHttpClient
    - OkHttp
    - Retrofit