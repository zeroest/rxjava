
# TestSubscriber / TestObserver

TestSubscriber: Flowable에서 사용되는 소비자 클래스  
TestObserver: Observable에서 사용되는 소비자 클래스

- 테스트 용도로 사용되는 소비자 클래스 
- assertXXX 함수를 이용해 통지된 데이터를 검증할 수 있따.
- awaitXXX 함수를 이용해서 지정된 시간 동안 대기하거나 완료 또는 에러 이벤트가 발생할 때까지 대기 할 수 있다.
- 완료, 에러, 구독 해지 등의 이벤트 발생 결과 값을 이용해서 데이터를 검증할 수 있다.

### [Assert](10_1-assert_xxx.md)
### [Await](10_2-await_xxx.md)
