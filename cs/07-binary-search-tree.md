# 이진 검색 트리의 시간 복잡도

## 이진 검색(탐색) 트리

- 용어
    - 노드
    - 간선: 노드와 노드를 이어주는 선
    - 트리의 높이: 로트 노드부터 말단 노드까지의 가장 긴 경로의 간선 수
    - 레벨: 트리의 특정 깊이를 가지는 노드의 집합
    - 이진트리: 모든 노드와 자식 노드가 최대 2개의 노드만을 가진 트리
- 개념
    - 원소가 정렬되어 있다는 조건 아래 정렬된 특징을 이용해 반씩 쪼개가며 검색하는 방법
    - 트리구조
        - 루트 노드가 비었다면 삽입 노드를 루트 노드로 채움
        - 루트노드가 존재한다고 가정할 때 삽입노드의 값과 비교하여 삽입 노드의 값이 작으면 루트노드의 왼쪽, 크면 오른쪽으로 이동
        - 이동시 노드가 비었다면 삽입 노드로 채움

- 장점 (이진 탐색을 사용하는 배열 자료구조보다 나은 점)
    - 원소 삽입시 배열은 삽입/삭제될 index를 찾고, 그 뒤에 위치한 원소들은 한칸씩 뒤로 이동하는 작업을 하기 위해 새 배열을 만들지만, 트리는 참조 (Pointer)만 변경해주면 된다
        - 원소의 대량 이동 필요 없음
        - 공간 복잡도를 신경을 덜 씀

## 이진 검색 트리의 시간복잡도

- 검색/삽입
    - 높이가 H라고 표시한다면 O(H)
        - 모든 원소를 탐색하지 않고, 최악의 경우가 트리 높이 만큼 탐색하는 경우이기 때문
    - 원소의 개수를 N이라고 표시한다면 $O(log_2N)$
        - H를 원소의 개수 N으로 표현
        - 자세한 수학식은 참고자료 첫번째 블로그 참고
- 삭제
    - $O(log_2N)$ == O(H)
        - 삭제할 대상이 1일때 검색과 같이 O(H) 만큼 소요
        - 삭제할 대상이 2개일 때
            - 검색 후 대체할 노드만 찾는다면 대체 노드의 참조값을 원래 노드에 붙여줌
            - 삭제 노드 탐색: O(H)
            - 대체할 노드를 찾는 탐색에 걸리는 시간: O(H-K)
                - K: 탐색을 시작할 높이 값
            - 참조값 변경: O(1)
            - 모두 더하면 O(2H-K-1)인데, 상수 및 계수는 생략 가능하므로 간단히 표현하면 ⇒ O(H)

- 참고자료

  [https://velog.io/@xdfc1745/이진트리-시간복잡도](https://velog.io/@xdfc1745/%EC%9D%B4%EC%A7%84%ED%8A%B8%EB%A6%AC-%EC%8B%9C%EA%B0%84%EB%B3%B5%EC%9E%A1%EB%8F%84)

  [https://mommoo.tistory.com/101](https://mommoo.tistory.com/101)