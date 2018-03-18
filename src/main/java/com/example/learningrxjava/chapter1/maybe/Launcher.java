package com.example.learningrxjava.chapter1.maybe;

import io.reactivex.Maybe;
import io.reactivex.Observable;

public class Launcher {

    /*
    Maybe is like a Single except that it allows no emission to occurr at all
    * Will emit 0 or 1 emissions

    MaybeObserver has the following methods:
    onSubscribe
    onSuccess (instead of onNext)
    onError
    onComplete
     */

    public static void main(String[] args) {
        // Has emission
        Maybe<Integer> presentSource = Maybe.just(100);
        presentSource.subscribe(
                s -> System.out.println("Process 1 received: " + s),
                Throwable::printStackTrace,
                () -> System.out.println("Process 1 done!"));

        //no emission
        Maybe<Integer> emptySource = Maybe.empty();
        emptySource.subscribe(s -> System.out.println("Process 2 received: " + s),
                Throwable::printStackTrace,
                () -> System.out.println("Process 2 done!"));

        // Certain operators also yield Maybe
        Observable<String> source = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");
        source.firstElement().subscribe(
                s -> System.out.println("RECEIVED " + s),
                Throwable::printStackTrace,
                () -> System.out.println("DONE"));
    }
}
