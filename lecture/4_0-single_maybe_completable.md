
# Single [Example Code](../src/main/java/me/zeroest/rxjava/single_maybe_completable/single)

- 데이터를 1건만 통지하거나 에러를 통지한다.  
- 데이터 통지 자체가 완료를 의미하기 때문에 완료 통지는 하지 않는다. 
- 데이터를 1건만 통지하므로 데이터 개수를 요청할 필요가 없다. 
- onNext(), onComplete()가 없으며 이 둘을 합한 onSuccess()를 제공한다. 
- Single의 대표적인 소비자는 SingleObserver이다.
- 클라이언트의 요청에 대응하는 서버의 응답이 Single을 사용하기 좋은 대표적인 예다.

# Maybe [Example Code](../src/main/java/me/zeroest/rxjava/single_maybe_completable/maybe)

- 데이터를 1건만 통지하거나 1건도 통지하지 않고 완료 또는 에러를 통지한다.
- 데이터 통지 자체가 완료를 의미하기 때문에 완료 통지는 하지 않는다.
- 단, 데이터를 1건도 통지하지 않고 처리가 종료될 경우에는 완료 통지를 한다.
- Maybe의 대표적인 소비자는 MaybeObserver이다.

# Completable [Example Code](../src/main/java/me/zeroest/rxjava/single_maybe_completable/completable)

- 데이터 생산자이지만 데이터를 1건도 통지하지 않고 완료 또는 에러를 통지한다.
- 데이터 통지의 역할 대신에 Completable 내에서 특정 작업을 수행한 후, 해당 처리가 끝났음을 통지하는 역할을 한다.
- Completable의 대표적인 소비자는 CompletableObserver이다.
