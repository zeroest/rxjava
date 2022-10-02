
# Assert

### assertEmpty

[Example Code](../src/test/java/me/zeroest/rxjava/test/assertxxx/AssertEmptyTest.java)

- 테스트 시점까지 통지받은 데이터가 없다면 테스트에 성공한다.
- Observable.empty()로 생성시, 완료 통지를 하기 때문에 테스트가 실패한다.
- 즉, 통지 이벤트 자체가 없는지를 테스트할 수 있다.

### assertValue

[Example Code](../src/test/java/me/zeroest/rxjava/test/assertxxx/AssertValueTest.java)

- 통지된 데이터가 한개인 경우에 사용한다.
- 즉, 통지된 데이터가 한개뿐이므로 파라미터로 입력된 값과 같다면 테스트에 성공한다.

### assertValues

[Example Code](../src/test/java/me/zeroest/rxjava/test/assertxxx/AssertValuesTest.java)

- 통지된 데이터가 한개 이상인 경우에 사용한다.
- 즉, 통지된 데이터의 값과 순서가 파라미터로 입력된 데이터의 값과 순서가 일치하면 테스트에 성공한다.

### assertNoValues

[Example Code](../src/test/java/me/zeroest/rxjava/test/assertxxx/AssertNoValuesTest.java)

- 해당 시점까지 통지된 데이터가 없으면 테스트에 성공한다.
- 완료 통지와 에러 통지는 테스트 대상에서 제외된다.

### assertResult

[Example Code](../src/test/java/me/zeroest/rxjava/test/assertxxx/AssertResultTest.java)

- 해당 시점까지 통지를 완료했고, 통지된 데이터와 파라미터로 입력된 데이터의 값과 순서가 같으면 테스트에 성공한다.
- assertValues와의 차이점은 해당 시점까지 완료 통지를 받았느냐 받지 않았느냐이다.

### assertError

[Example Code](../src/test/java/me/zeroest/rxjava/test/assertxxx/AssertErrorTest.java)

- 해당 시점까지 에러 통지가 있으면 테스트에 성공한다.
- 단순히 에러 통지가 있었는지의 여부와 구체적으로 발생한 에러가 맞는지를 테스트할 수 있다.

### assertComplete

[Example Code](../src/test/java/me/zeroest/rxjava/test/assertxxx/AssertCompleteTest.java)

- 해당 시점까지 완료 통지가 있으면 테스트에 성공한다.

### assertNotComplete

[Example Code](../src/test/java/me/zeroest/rxjava/test/assertxxx/AssertNotCompleteTest.java)

- 해당 시점까지 완료 통지가 없으면 테스트에 성공한다.
