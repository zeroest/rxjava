
# Blocking (Unit Test)

[[Docs] Utility Operator Do](https://reactivex.io/documentation/operators/do.html)

## 문제점 및 대안

- 비동기 처리 결과를 테스트 하려면 현재 쓰레드에서 호출 대상 쓰레드의 실행 결과를 반환 받을때까지 대기할 수 있어야 한다.
- RxJava에서는 현재 쓰레드에서 호출 대상 쓰레드의 처리 결과를 받을 수 있는 blockingXXX 함수를 제공한다.
- Observable에서 통지되고 가공 처리된 결과 데이터를 현재 쓰레드에 반환하므로, 반환된 결과 값과 예상되는 기대값을 비교해서 단위 테스트를 수행할 수 있다.


### BlockingFirst

[Example Code](../src/test/java/me/zeroest/rxjava/test/blockingxxx/BlockingFirstTest.java)

- 생산자가 통지한 첫번째 데이터를 반환한다.
- 통지된 데이터가 없을 경우 NoSuchElementException을 발생시킨다.

### BlockingLast

[Example Code](../src/test/java/me/zeroest/rxjava/test/blockingxxx/BlockingLastTest.java)

- 생산자가 통지한 마지막 데이터를 반환한다.
- 통지된 데이터가 없을 경우 NoSuchElementException을 발생시킨다.
- 결과를 반환하는 시점이 완료를 통지하는 시점이므로 완료 통지가 없는 데이터 통지일 경우 사용할 수 없다.

### BlockingSingle

[Example Code](../src/test/java/me/zeroest/rxjava/test/blockingxxx/BlockingSingleTest.java)

- 생산자가 한개의 데이터를 통지하고 완료되면 해당 데이터를 반환한다.
- 2개 이상의 데이터를 통지할 경우에는 IllegalArgumentException을 발생시킨다.

### BlockingGet

[Example Code](../src/test/java/me/zeroest/rxjava/test/blockingxxx/BlockingGetTest.java)

- 생산자가 0개 또는 1개의 데이터를 통지하고 완료되면 해당 데이터를 반환한다.
- 즉, 생산자가 Maybe일 경우 사용할 수 있다.

### BlockingIterable

[Example Code](../src/test/java/me/zeroest/rxjava/test/blockingxxx/BlockingIterableTest.java)

- 생산자가 통지한 모든 데이터를 받을 수 있는 Iterable을 얻게 한다.
- 구독 후, Iterator의 next() 메서드를 호출하는 시점부터 처리한다.

### BlockingForEach

[Example Code](../src/test/java/me/zeroest/rxjava/test/blockingxxx/BlockingForEachTest.java)

- 생산자가 통지한 데이터를 순차적으로 모두 통지한다.
- 통지된 각각의 데이터가 모두 조건에 맞아야 true를 반환한다.

### BlockingSubscribe

[Example Code](../src/test/java/me/zeroest/rxjava/test/blockingxxx/BlockingSubscribeTest.java)

- 통지된 원본 데이터를 호출한 원본 쓰레드에서 부수적인 처리를 할 수 있도록 해준다.
- 소비자가 전달 받은 데이터로 어떤 부수적인 처리를 할때 이 처리 결과를 테스트 할 수 있다.
