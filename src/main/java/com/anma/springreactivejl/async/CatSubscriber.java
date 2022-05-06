package com.anma.springreactivejl.async;

import com.anma.springreactivejl.model.Cat;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class CatSubscriber implements Subscriber<Cat> {

    @Override
    public void onSubscribe(Subscription subscription) {

    }

    @Override
    public void onNext(Cat cat) {

    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onComplete() {

    }
}

class CatPublisher implements Publisher<Cat> {

    @Override
    public void subscribe(Subscriber<? super Cat> subscriber) {

    }
}