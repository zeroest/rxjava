
# Await

### awaitDone

[Example Code](../src/test/java/me/zeroest/rxjava/test/awaitxxx/AwaitDoneTest.java)

- 파라미터로 지정된 시간 동안 대기시키거나 지정된 시간 전에 완료 통지나 에러가 있다면 통지가 있을때까지만 대기시킨다.

### await

[Example Code](../src/test/java/me/zeroest/rxjava/test/awaitxxx/AwaitTest.java)

- 생산자 쪽에서 완료 통지나 에러 통지가 있을때까지 쓰레드르 대기시킨다.
- 파라미터로 지정된 시간동안 대기하며, 대기 시간내에 완료 통지가 있었는지 여부를 검증한다.

### awaitCount

[Example Code](../src/test/java/me/zeroest/rxjava/test/awaitxxx/AwaitCountTest.java)

- 파라미터로 지정된 개수만큼 통지될 때까지 쓰레드를 대기시킨다.
