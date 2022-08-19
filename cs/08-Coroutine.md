# Coroutine

## 개념

- 코틀린에서의 동시성 프로그래밍 개념
- 코루틴: 시작된 스레드를 중단하지 않으면서 비동기적으로 실행되는 코드
- 기존의 복잡한 AsyncTask 또는 다수 스레드 관리 직접 해주지 않아도 되며, 기존의 다중 스레드보다 훨씬 효율적으로 동작

## 동작

- 코루틴은 스레드 위에서 실행되는데, 여러가지 코루틴이 존재한다고 할 때 (코루틴 1,2,3)
- 코루틴 1을 실행하던 중 2가 실행되어도 실행중인 스레드를 정지시키지 않고 기존 스레드를 유지하며 코루틴 2를 실행시킴
- 이후 코루틴1을 다시 실행할 때 저장해준 코루틴 1 상태 불러와 다시 실행

![coroutine](img/coroutine.png)

## 장점

- 스레드 멈춤 없이 루틴을 돌릴 수 있게 되어 성능 향상.
- 여러 스레드를 사용하는것보다 훨씬 적은 자원 소모
- depth가 깊은 코드인 경우 지저분하고 가독성이 떨어지는데, coroutine을 사용하면 깔끔한 코드를 작성할 수 있다.
    - 기존 방식: flatMap을 여러번 사용해야한다
        - flatMap이란? → [블로그 참고](https://kotlinworld.com/13)

    ```kotlin
    private fun addExtra(coffee: CoffeeRecord): Mono<CoffeeModel> {
            val coffeeId = coffee.id
    
            return commentRepository.findAllByCoffeeId(coffeeId)
                .collectList()
                .flatMap { comments ->
                    val rating = comments.map { it.rating }.average().let {
                        if (it.isNaN()) {
                            0.0
                        } else it
                    }
    
                    val commentUserIds = comments.map { it.userId }
                    val req = listCafeUsersReq {
                        userIds.addAll(commentUserIds)
                    }
                    reactorCafeUserService.listCafeUsers(req)
                        .flatMap { res ->
                            val users = res.usersList.associateBy { it.id }
    
                            val commentModels = comments.mapNotNull {
                                users[it.userId]?.let { cafeUser ->
                                    CoffeeCommentModel(
                                        nickname = it.nickname,
                                        content = it.content,
                                        rating = it.rating,
                                        avatarUrl = cafeUser.avatarUrl,
                                    )
                                }
                            }
    
                            redisTemplate.opsForValue()["coffee:${coffeeId}:stock"]
                                .defaultIfEmpty("0")
                                .map { stock ->
                                    CoffeeMapper.INSTANCE.toCoffeeModel2(
                                        coffee,
                                        rating,
                                        commentModels,
                                        comments.size,
                                        stock.toLong()
                                    )
                                }
                        }
                }
        }
    ```

    - 코루틴 사용: 깔끔

    ```kotlin
    @Suppress("UnusedPrivateMember")
        internal suspend fun addExtraWithAsync(coffee: CoffeeRecord): CoffeeModel {
            val coffeeId = coffee.id!!
    
            val (comments, stock) = withContext(Dispatchers.IO) {
                coroutineScope {
                    val commentsDeferred = async {
                        commentRepository.findAllByCoffeeId(coffeeId).toList()
                    }
    
                    val stockDeferred = async {
                        redisTemplate.opsForValue()["coffee:${coffeeId}:stock"]
                            .defaultIfEmpty("0")
                            .awaitSingle()
                            .toLong()
                    }
    
                    commentsDeferred.await() to stockDeferred.await()
                }
            }
    ... 이후 생략
    ```

    - coroutineScope안에서 job 별로 동시성 처리를 할 수 있다.
    - async, await를 이용해 결과 값을 기다렸다가 결과값이 나오면 다음이 작동하도록 할 수 있다.
    - 그래서 depth 있게 코드를 짜지 않아도 된다.

## 특징

- suspend 키워드
    - 코루틴 안에서 사용되며, suspend 함수가 호출될 경우 이전까지의 코드 실행이 멈추며, suspend 함수가 처리가 완료된 후 멈춰 있던 원래의 스코프의 다음 코드가 실행됨

    ```kotlin
    suspend fun subRoutine() {
    	for(i in 0..10){
    		Log.d("subRoutine", "$i")
    	}
    }
    
    CoroutineScope(Dispatchers.Main).launch {
    	//선 처리 코드
    	subRoutine()
    	//후 처리 코드
    }
    ```

    - suspend 함수인 subRoutine이 완료되어야 후처리 코드가 동작
- suspendCancellableCoroutine - Callback을 coroutine으로 변경
    - 내용은 블로그를 참고하자
        - [https://medium.com/박상권의-삽질블로그/callback지옥으로부터-coroutine까지의-긴-여정-88d38d06449a](https://medium.com/%EB%B0%95%EC%83%81%EA%B6%8C%EC%9D%98-%EC%82%BD%EC%A7%88%EB%B8%94%EB%A1%9C%EA%B7%B8/callback%EC%A7%80%EC%98%A5%EC%9C%BC%EB%A1%9C%EB%B6%80%ED%84%B0-coroutine%EA%B9%8C%EC%A7%80%EC%9D%98-%EA%B8%B4-%EC%97%AC%EC%A0%95-88d38d06449a)
        - [https://tourspace.tistory.com/442](https://tourspace.tistory.com/442)
- CoroutineRepository를 이용하면 DB도 nonBlocking하게 가져올 수 있다
    - [https://docs.spring.io/spring-data/r2dbc/docs/current/reference/html/#kotlin.coroutines.repositories](https://docs.spring.io/spring-data/r2dbc/docs/current/reference/html/#kotlin.coroutines.repositories)


## 내의견

- 통신에서도 WebFlux와 같이 비동기 통신이 인기가 많아지더니, MSA 내부 동작 (특히 Thread 동작)도 비동기로 하는게 인기가 많아지고 있는 것 같다.
- 코루틴은 하나의 스레드 안에서 동시성을 효율적으로 관리해 멀티스레드 같은 효과를 내는 것 같다. 트래픽이 많고 빠른 처리가 필요한 서비스에 어울리는 것 같다
- 다만 결과값을 return받아서 그 값으로 그 다음 코드에서 활용해야하는 경우 async + await 와 같은 처리를 해줘야하기 때문에 유의해서 써야하는것 같다. 정확히 개념을 알고 써야할 것 같다

- 참고자료

  [https://whyprogrammer.tistory.com/596](https://whyprogrammer.tistory.com/596)

  [https://underdog11.tistory.com/entry/Kotlin-코루틴-Coroutine-async과-await-LifecycleScope과-ViewModelScope-3편](https://underdog11.tistory.com/entry/Kotlin-%EC%BD%94%EB%A3%A8%ED%8B%B4-Coroutine-async%EA%B3%BC-await-LifecycleScope%EA%B3%BC-ViewModelScope-3%ED%8E%B8)