
# subscribeOn vs observeOn

[[Refer] subscribeOn과 observeOn의 차이](https://4z7l.github.io/2020/12/18/rxjava-6.html)

## subscribeOn

Observable이 데이터 흐름을 발생시키고 연산하는 스레드를 지정할 수 있다.

- 여러번 호출 되더라도 맨 처음의 호출만 영향을 주며 어디에 위치하든 상관없다.

## observeOn

Observable이 Observer에게 알림을 보내는 스레드를 지정할 수 있다.

- 여러번 호출될 수 있으며 이후에 실행되는 연산에 영향을 주므로 위치가 중요하다.
