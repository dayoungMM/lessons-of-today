# ELK (Elastic Search, Log Stash, Kibana+ File Beat)

## 로그 분석의 어려움

- 기본적으로는 .log 파일로 로그를 남기는 방식을 사용한다
- 단점
    - 로그 레벨 파악 어려움
        - INFO, WARNING 과 같은 log lovel 일일이 찾아야함
    - 검색어 분석 어려움
    - 날짜 및 시간에 따라 로그 분석 어려움
    - 디스크 공간을 너무 많이 차지한다
- 단순히 로그를 쌓는것만 중요한게 아니라 로그를 관리하고 분석을 용이하게 하기 위해 ELK 등장

## ELK stack

- 구성
    - Elastic Search: 로그 저장 및 검색
    - Logstash: 로그 수집 엔진
    - Kibana: 로그 시각화 및 관리
    - (Beats: 최근에 함께 쓰는 추세가 있음. 서버에 설치하는 에이전트. 다양한 유형의 데이터를 ElasticSearch/Logstash에 전송할 수 있음)

<aside>
💡 세 모듈은 각자 독립된 기술이기 때문에 필요에 따라 일부만 사용해도 된다. 하지만 서로 호환이  잘 되고 합쳤을 때 시너지가 좋기 때문에 일반적으로 같이쓰기 때문에 stack이라고 부른다.

</aside>

- 역할
    - Data Processing (Logstash)
        - 서버 내의 로그, 웹, 메트릭 등 다양한 소스에서 데이터 수집
            - JRuby로 되어있어 Java Runtime 가상머신 위에서 돌아간다
        - 데이터 변환 및 구조 구축
        - 데이터 출력 및 송신
            - 수집할 로그를 선정하여 지정된 대상 서버에 인덱싱(정규화 하여) ElasticSearch 등의 목적지로 전송
    - Storage (ElasticSearch)
        - 데이터 저장
            - 정형, 비정형, 위치정보, 매트릭 등 원하는 방법으로 다양한 유형의 검색 수행 및 결합이 가능
        - 데이터 분석
            - 실시간 분석 가능
            - 클러스터가 실행되고 있는 동안 계속 데이터 입력
            - 동시에 real-time에 가까운 속도로 검색 및 집계 수행
            - 역인덱스(Inverted File Index)데이터 구조를 사용하여 풀 텍스트 검색이 가능
        - 데이터 관리
            - 분산형, 개방형 검색 엔진
    - Visualize (Kibana)
        - Dashboard를 통한 데이터 탐색
            - 시각화 도구(차트, 그래프, 매트릭, 지도 등) 을 단일 페이지에 모아놓을 수 있음
        - 팀원들과 공유 및 협업하는데 용이
        - 엑세스 제어 가능
- 장점
    - open source여서 가격적인 메리트가 있다
    - 오픈소스 내려받아 설치하기만 하면 되기 때문에 접근성과 사용성이 뛰어나다
    - 속도가 빠르다 (Elasticsearch는 거의 real-time에 가까움)
    - 자유 스키마
        - Json 방식의 Key-Value 형식으로 데이터 사용 형식이 자유로움
        - 인덱스 와일드 카드를 지원해서 인덱스의 조인 가능
    - 클러스터 방식이라 확장 가능
    - 사전에 준비된 시각화 도구(Kibana)
    - 실시간 데이터 처리 가능
- 단점
    - 초기 데이터 구성 및 이관문제
        - 기존에 보유한 데이터를 ElasticSearch로 이관하는 경우 심각한 성능 저하 문제 발생. 많은 양의 데이터 이관 용도로는 사용이 불가능
        - 커널 변수의 불필요한 사용
            - 커널의 cgroup에 해당하는 변수를 참조하는 내용 때문에 해당 변수가 없는 커널일 경우 오류가 발생할 수도 있따. 즉, 커널 컴파일 설정환경에 따라 프로그램이 중단되는 문제 발생할 수 있다.
        - 시간대의 일관성이 깨짐
            - 3개의 stack으로 데이터가 넘어가는 과정에서 시간대(time zone)의 일관성이 깨진다
            - ‘Asia/Seoul’ 같이 명시를 해도 시간을 두번 바꿔야 하는 번거러움이 발생

- 참고

  [https://pearlluck.tistory.com/583](https://pearlluck.tistory.com/583)

  [https://velog.io/@holidenty/ELK-ELK-Stack-이란-무엇일까](https://velog.io/@holidenty/ELK-ELK-Stack-%EC%9D%B4%EB%9E%80-%EB%AC%B4%EC%97%87%EC%9D%BC%EA%B9%8C)