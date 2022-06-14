# Shorten URL 작동 원리 유추

## Shorten URL이란?

- 긴 URL 주소를 짧게 줄여주는 서비스
- Google URL Shortener([https://goo.gl/](https://goo.gl/)) 또는 Bitly ([https://bitly.com/](https://bitly.com/))

## DB를 이용한 방법 원리

- 긴 URL 주소를 입력받아 이 URL에 대응되는 키값을 부여하여 DB에 저장
- 키 값을 서버에 요청하면 서버는 DB에서 URL을 찾아 페이지 이동
- 키 값 생성
    - 키값을 일련번호 형식으로 만들면 URL이 급격하게 많아졌을 때 자리수가 빠르게 늘어나는 단점
    - 따라서 간단한 알고리즘을 구현한 함수를 통해 자리수가 최대한 천천히 늘어나도록 함
    - ex) Bijection 함수

## DB를 사용하지 않는 방법 원리

- 해싱
    - 일정한 input을 넣으면 더 짧은 문자열의 output이 나오는(문자열 압축) 해싱 알고리즘을 사용하여 변환
- 참고

  [https://john-kim.gitbooks.io/training-nodejs-2/content/chapter1.html](https://john-kim.gitbooks.io/training-nodejs-2/content/chapter1.html)