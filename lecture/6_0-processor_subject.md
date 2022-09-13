
# Processor Subject

[[Docs] ReactiveX Subject](https://reactivex.io/documentation/subject.html)

## Processor

```java
package org.reactivestreams;

/**
 * A Processor represents a processing stage—which is both a Subscriber
 * and a Publisher and obeys the contracts of both.
 *
 * @param <T> the type of element signaled to the Subscriber
 * @param <R> the type of element signaled by the Publisher
 */
public interface Processor<T, R> extends Subscriber<T>, Publisher<R> {
}
```

- Reactive Streams에서 정의한 Publisher 인터페이스와 Subscriber 인터페이스를 둘다 상속한 확장 인터페이스
- Processor는 Hot Publisher
- ex) PublishProcessor, AsyncProcessor, BehaviorProcessor, ReplayProcessor


## Subject

```java
package io.reactivex.subjects;

import io.reactivex.*;
import io.reactivex.annotations.*;

/**
 * Represents an Observer and an Observable at the same time, allowing
 * multicasting events from a single source to multiple child Observers.
 * 
 * All methods except the onSubscribe(io.reactivex.disposables.Disposable), onNext(Object),
 * onError(Throwable) and onComplete() are thread-safe.
 * Use toSerialized() to make these methods thread-safe as well.
 *
 * @param <T> the item value type
 */
public abstract class Subject<T> extends Observable<T> implements Observer<T> {
    /**
     * Returns true if the subject has any Observers.
     * The method is thread-safe.
     * @return true if the subject has any Observers
     */
    public abstract boolean hasObservers();

    /**
     * Returns true if the subject has reached a terminal state through an error event.
     * The method is thread-safe.
     * @return true if the subject has reached a terminal state through an error event
     * @see #getThrowable()
     * @see #hasComplete()
     */
    public abstract boolean hasThrowable();

    /**
     * Returns true if the subject has reached a terminal state through a complete event.
     * The method is thread-safe.
     * @return true if the subject has reached a terminal state through a complete event
     * @see #hasThrowable()
     */
    public abstract boolean hasComplete();

    /**
     * Returns the error that caused the Subject to terminate or null if the Subject
     * hasn't terminated yet.
     * The method is thread-safe.
     * @return the error that caused the Subject to terminate or null if the Subject
     * hasn't terminated yet
     */
    @Nullable
    public abstract Throwable getThrowable();

    /**
     * Wraps this Subject and serializes the calls to the onSubscribe, onNext, onError and
     * onComplete methods, making them thread-safe.
     * The method is thread-safe.
     * @return the wrapped and serialized subject
     */
    @NonNull
    public final Subject<T> toSerialized() {
        if (this instanceof SerializedSubject) {
            return this;
        }
        return new SerializedSubject<T>(this);
    }
}
```

- Reactive Streams의 Processor와 동일한 기능을 하나 배압 기능이 없는 추상 클래스
- ex) PublishSubject, AsyncSubject, BehaviorSubject, ReplaySubject

### [PublishSubject](6_1-publish_subject.md)
### [AsyncSubject](6_2-async_subject.md)
### [BehaviorSubject](6_3-behavior_subject.md)
### [ReplaySubject](6_4-replay_subject.md)
