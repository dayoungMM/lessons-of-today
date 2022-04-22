# finalizer와 cleaner 사용을 피하라

## 객체소멸자 in Java

1. finalizer
    - 예측할 수 없고, 상황에 따라 위험할 수 있어 일반적으로 불필요
    - java9 에서 deprecated 되고, cleaner 등장
2. cleaner
    - finalizer보다 덜 위험하지만 여전히 예측할 수 없고, 느리고, 불필요

결론: 되도록 사용하지 말자

## finalizer, cleaner 사용하지 말아야하는 이유 (#불확실성 #성능저하)

- 즉시 수행된다는 보장 없음 (얼마나 신속히 수행될지는 GC에 달렸음)→ 제때 사용해야하는 작업 사용금지
- 수행 여부를 보장하지 않는다 → 상태를 영구적으로 수정하는 작업에서 사용금지
- finalizer 동작 중 발생한 예외는 무시됨 (경고도 출력안됨)
- GC의 효율을 떨어트려 성능 저하
- finalizer 공격에 노출되어 보안 문제 초래

## 대신 뭘 써야할까

- AutoCloseable을 구현 → try-with-resourcess 로 close를 꼭 하는것
    - + close 했다는 것을 기록하고, 다른 메서드에서 이 객체 닫혔는지 검사→ (false)→ throw IllegalStateException


## finalizer, cleaner 써도 될만한 케이스

- close 메서드 호출하지 않는것에 대한 안전망 필요시 (ex: FileInputStream)
- native peer와 연결된 객체 (GC가 존재를 몰라서)