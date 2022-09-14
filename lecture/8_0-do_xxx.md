
# Do (Debugging)

[[Docs] Utility Operator Do](https://reactivex.io/documentation/operators/do.html)

## 문제점 및 대안

- RxJava 프로그래밍은 데이터를 생성 및 통지하고 이를 구독하여 처리하는 과정이 하나의 문장으로 되어 있다.
- 즉, RxJava 프로그래밍은 선언적 프로그래밍 방식이기 때문에 데이터의 상태 변화를 확인하기 위한 디버깅이 쉽지 않다.
- RxJava 프로그래밍은 여러 쓰레드가 동시에 실행되는 비동기 프로그래밍이기 때문에 실행 시, 항상 같은 결과가 나온다는 보장을 할 수가 없다.
- 이러한 문제점을 해결하기 위해 RxJava에서는 doXXX로 시작하는 함수를 통해 생산자나 소비자쪽에서 이벤트 발생 시, 로그를 기록할 수 있는 방법을 제공한다.
- 함수형 프로그래밍의 특성상 부수 효과는 소비자쪽에서 처리하는것이 맞지만 doXXX 함수는 예외이다.
- 따라서 소비자가 전달 받은 데이터를 처리하기 전 원본 데이터의 상태나 변환 및 필터링 등으로 가공되는 시점의 데이터 상태를 doXXX 함수를 통해 쉽게 파악할 수 있다.

### [doOnSubscribe](8_1-do_on_subscribe.md)

### [doOnNext](8_2-do_on_next.md)

### [doOnComplete](8_3-do_on_complete.md)

### [doOnError](8_4-do_on_error.md)

### [doOnEach](8_5-do_on_each.md)

### [doOnDispose](8_6-do_on_dispose.md)


### Other

#### doAfterNext

- 생산자가 통지한 데이터가 소비자에 전달된 직후 호출되는 함수

#### doOnTerminate

- 완료 또는 에러가 통지될 때 호출 되는 함수
- doOnComplete + doOnError

#### doAfterTerminate

- 완료 또는 에러가 통지된 후 호출 되는 함수
- (after) doOnComplete + doOnError

#### doFinally

- 구독이 취소된 후, 완료 또는 에러가 통지된 후 호출되는 함수
- doOnDispose/doOnCancel + doOnComplete + doOnError

#### doOnLifecycle

- 소비자가 구독할 때 또는 구독 해지할 때 호출되는 함수
- doOnSubscribe + doOnDispose/doOnCancel
