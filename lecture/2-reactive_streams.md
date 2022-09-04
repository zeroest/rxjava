
# Reactive Streams

- 리액티브 프로그래밍 라이브러리의 표준 사양
    - [Reactive Streams](https://github.com/reactive-streams/reactive-streams-jvm)
- 리액티브 프로그래밍에 대한 인터페이스만 제공
- RxJava는 Reactive Streams 인터페이스를 구현한 구현체
- Publisher, Subscriber, Subscription, Processor 4개의 인터페이스 제공
    - [Specification](https://github.com/reactive-streams/reactive-streams-jvm#specification)
    - Publisher: 데이터를 생성하고 통지한다
    - Subscriber: 통지된 데이터를 전달받아서 처리한다
    - Subscription: 전달 받을 데이터의 갯수를 요청 또는 구독을 해지한다
    - Processor: Publisher와 Subscriber의 기능이 모두 있음

```text
Publisher(생산자)                                                  Subscriber(소비자)
     |                                                                     |
     |  데이터를 구독한다(subscribe)                                        |
     | <----------------------------------------------------------------   |
     |                  데이터를 통지할 준비가 되었음을 알린다(onSubscribe)  |
     |   ----------------------------------------------------------------> |
     |  전달 받을 통지 데이터 개수를 요청한다(Subscription.request)          |
     | <----------------------------------------------------------------   |
     |                                                                     |
     |   ------+                                                           |
     |         | 데이터를 생성한다                                          |
     | <-------+                                                           |
     |                         요청 받은 개수만큼 데이터를 통지한다(onNext)  |
     |   ----------------------------------------------------------------> |
     |                                                                     |
     |   ------+                                                           |
     |         | 데이터를 생성한다                                          |
     | <-------+                                                           |
     |   ------+                                                           |
     |         | 데이터를 생성한다                                          |
     | <-------+                                                           |
     |                                                                     |
     |  전달 받을 통지 데이터 개수를 요청한다(Subscription.request)          |
     | <----------------------------------------------------------------   |
     |                         요청 받은 개수만큼 데이터를 통지한다(onNext)  |
     |   ----------------------------------------------------------------> |
     |                                                                     |
                      완료 또는 에러(onError)가 발생할 때까지
                      데이터 생성, 통지, 요청을 계속한다
                     
     |                       데이터 통지가 완료 되었음을 알린다(onComplete)  |
     |   ----------------------------------------------------------------> |
```

## Cold & Hot Publisher

- Cold Publisher ([Example Code](../src/main/java/me/zeroest/rxjava/coldhot/ColdPublisherExample.java))
    - 생산자는 소비자가 구독 할때마다 데이터를 처음부터 새로 통지한다
    - 데이터를 통지하는 새로운 타임 라인이 생성된다
    - 소비자는 구독 시점과 상관없이 통지된 데이터를 처음부터 전달 받을 수 있다

- Hot Publisher ([Example Code](../src/main/java/me/zeroest/rxjava/coldhot/HotPublisherExample.java))
    - 생산자는 소비자 수와 상관없이 데이터를 한번만 통지한다
    - 즉, 데이터를 통지하는 타임라인은 하나이다
    - 소비자는 발행된 데이터를 처음부터 전달 받는게 아니라  
      구독한 시점에서 통지된 데이터들만 전달 받을 수 있다.
