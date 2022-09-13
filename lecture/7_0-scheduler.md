
# Scheduler

[[Docs] ReactiveX Scheduler](https://reactivex.io/documentation/scheduler.html)

- RxJava에서 스케줄러는 비동기 프로그래밍을 위한 쓰레드(Thread) 관리자이다.
- 즉, 스케줄러를 이용해서 어떤 쓰레드에서 무엇을 처리할 지에 대해서 제어할 수 있다.
- 스케줄러를 이용해서 데이터를 통지하는 쪽과 데이터를 처리하는 쪽 쓰레드를 별도로 지정해서 분리할 수 있다.


- 생산자쪽의 데이터 흐름을 제어하기 위해서 subscribeOn() 연산자를 사용한다.
- 소비자쪽에서 전달받은 데이터 처리를 제어하기 위해서는 observeOn() 연산자를 사용한다.
- subscribeOn(), observeOn() 연산자는 각각 파라미터로 Scheduler를 지정해야 한다.
  - [subscribeOn() vs observeOn()](7_1-subscribeon_observeon.md)

[Example Code](../src/main/java/me/zeroest/rxjava/scheduler)

| 스케줄러                  | 설명                                                         |
| ------------------------- | ------------------------------------------------------------ |
| Schedulers.io()           | - I/O 처리 작업을 할 때 사용하는 스케줄러<br />- 네트워크 요청 처리, 각종 입/출력 작업, 데이터 베이스 쿼리 등에 사용<br />- 쓰레드 풀에서 쓰레드를 가져오거나 가져올 쓰레드가 없으면 새로운 쓰레드를 생성한다. |
| Schedulers.computation()  | - 논리적인 연산 처리 시, 사용하는 스케줄러<br />- CPU 코어의 물리적 쓰레드 수를 넘지 않는 범위에서 쓰레드를 생성한다.<br />- 대기 시간 없이 빠르게 계산 작업을 수행하기 위해 사용한다. |
| Schedulers.newThread()    | - 요청시마다 매번 새로운 쓰레드를 생성한다.<br />- 매번 생성되면 쓰레드 비용도 많이 들고, 재사용도 되지 않는다. |
| Schedulers.trampoline()   | - 현재 실행되고 있는 쓰레드에 큐(Queue)를 생성하여 처리할 작업들을 큐에 넣고 순서대로 처리한다. |
| Schedulers.single()       | - 단일 쓰레드를 생성하여 처리 작업을 진행한다.<br />- 여러번 구독해도 공통으로 사용한다. |
| Schedulers.from(executor) | - Executor를 사용해서 생성한 쓰레드를 사용한다.<br />- RxJava의 Scheduler와 Executor의 동작 방식이 다르므로 자주 사용되지 않음. |
