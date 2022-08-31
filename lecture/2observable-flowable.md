
# Observable vs Flowable

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


