package com.example.learningrxjava.chapter1;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class ObserverInterface {

    /**
     * Each Observable returned by an operator is internally an Observer that receives, transforms and relays
     * emissions to the next Observer downstream. It doesn't know whether the next Observer is another operator or
     * the final Observer at the end of the Observable chain
     */
    public static void main(String[] args) {
        Observable<Integer> source = Observable.just("Alpha", "Beta", "Gama", "Delta", "Epsilon").map(String::length);

        // Upon Observable#subscribe() method call, Observer is used to consume
        // the incoming Observable events
        source.subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("Subscribed");
            }

            @Override
            public void onNext(Integer i) {
                // Receives each emission after all operators and transformations
                System.out.println("Received: " + i);
            }

            @Override
            public void onError(Throwable e) {
                // Receives error if one occur !anywhere! in our Observable chain
                System.out.println("Error: " + e.toString());
            }

            @Override
            public void onComplete() {
                // When the source has no more emissions
                // (used to signal that Observable will no longer emit any items)
                System.out.println("Done");
            }
        });

        // Observable.subscribe() has some useful overloads only for necessary operations:
        Consumer<Integer> onNext = i -> System.out.println("Consumer received: " + i);
        Consumer<Throwable> onError = err -> System.out.println("Consumer error: " + err);
        Action onComplete = () -> System.out.println("Consumer done!");
        source.subscribe(onNext);
        // All others are valid too
        // Though implementing onError is a good practice (otherwise error will be unhandled)
//        source.subscribe(onNext, onError);
//        source.subscribe(onNext, onError, onComplete);
//        source.subscribe(onNext, onError, onComplete, onSubscribe);
    }
}
