
# Observable vs Flowable

[Example Code](../src/main/java/me/zeroest/rxjava/observ_flow)

| Observable                                              | Flowable                                             |
| ------------------------------------------------------- | ---------------------------------------------------- |
| Reactive Streams 인터페이스를 구현함                    | Reactive Streams 인터페이스 구현하지 않음            |
| Subscriber에서 데이터를 처리                            | Observer에서 데이터를 처리                           |
| 데이터 개수를 제어하는 **배압 기능이 있음**             | 데이터 개수를 제어하는 **배압 기능이 없음**          |
| Subscription으로 전달 받는 데이터 개수를 제어할 수 있다 | 배압 기능이 없기 때문에 데이터 개수를 제어할 수 없다 |
| Subscription으로 구독을 해지                            | Disposable로 구독을 해지                             |


## Back Pressure

Flowable에서 데이터를 통지하는 속도가 Subscriber에서 통지된 데이터를 전달받아 처리하는 속도 보다 빠를때  
밸런스를 맞추기 위해 데이터 통지량을 제어하는 기능을 말한다.

### Backpressure Strategy

Rxjava에서는 BackpressureStrategy를 통해 Flowable이 통지 대기 중인 데이터를 어떻게 다룰지에 대한 배압 전략을 제공한다.

- MISSING 전략 ([Example Code](../src/main/java/me/zeroest/rxjava/backpressure/BackpressureMissing.java))

배압을 적용하지 않는다.  
나중에 onBackpressureXXX()로 배압 적용을 할 수 있다.

- ERROR 전략 ([Example Code](../src/main/java/me/zeroest/rxjava/backpressure/BackpressureError.java))

통지된 데이터가 버퍼의 크기를 초과하면 MissingBackpressureException 에러를 통지한다.  
즉, 소비자가 생산자의 통지 속도를 따라 잡지 못할 때 발생한다.  

- BUFFER 전략 : DROP_LATEST ([Example Code](../src/main/java/me/zeroest/rxjava/backpressure/BackpressureBufferDropLatest.java))

버퍼가 가득 찬 시점에 버퍼내에서 가장 최근에 버퍼로 들어온 데이터를 DROP한다.  
DROP 된 빈 자리에 버퍼 밖에서 대기하던 데이터를 채운다.

- BUFFER 전략 : DROP_OLDEST ([Example Code](../src/main/java/me/zeroest/rxjava/backpressure/BackpressureBufferDropOldest.java))

버퍼가 가득 찬 시점에 버퍼내에서 가장 오래전에(먼저) 버퍼로 들어온 데이터를 DROP  
DROP 된 빈 자리에는 버퍼 밖에서 대기하던 데이터를 채운다.

- DROP 전략 ([Example Code](../src/main/java/me/zeroest/rxjava/backpressure/BackpressureDrop.java))

버퍼에 데이터가 모두 채워진 상태가 되면 이후에 생성되는 데이터를 버리고(DROP),  
버퍼가 비워지는 시점에 DROP되지 않은 데이터부터 다시 버퍼에 담는다

- LATEST 전략 ([Example Code](../src/main/java/me/zeroest/rxjava/backpressure/BackpressureLatest.java))

버퍼에 데이터가 모두 채워진 상태가 되면 버퍼가 비워지리 때까지 통지된 데이터는 버퍼 밖에서 대기하며  
버퍼가 비워지는 시점에 가장 나중에(최근에) 통지된 데이터부터 버퍼에 담는다.
