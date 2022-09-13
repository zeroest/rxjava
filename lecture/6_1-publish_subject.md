
# PublishSubject

![publish subject](img/subject/publish_subject.png)

[Example Code](../src/main/java/me/zeroest/rxjava/subject/Publish.java)

- 구독 전에 통지된 데이터는 받을 수 없고, 구독한 이후에 통지 된 데이터만 받을 수 있다.
- 데이터 통지가 완료 된 이후에 소비자가 구독하면 완료 또는 에러 통지를 받는다.
