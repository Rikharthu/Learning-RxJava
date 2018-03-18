package com.example.learningrxjava.chapter1.single;

import io.reactivex.Observable;
import io.reactivex.Single;

public class Launcher {

    /*
    Single<T> is an Observable<T> that will only emit one item

    It has its own SingleObserver interface with methods:
    onSubscribe
    onSuccess
    onError
     */

    public static void main(String[] args) {
        Single.just("Hello")
                .map(String::length)
                .subscribe(System.out::println,
                        Throwable::printStackTrace);

        // Certain RxJava operators will yield a Single (such as first() operator)
        Observable<String> source = Observable.just("Alpha", "Beta", "Gamma");
        source.first("Nil")// Returns a Single
                .subscribe(System.out::println);
        // -> Alpha
    }
}
