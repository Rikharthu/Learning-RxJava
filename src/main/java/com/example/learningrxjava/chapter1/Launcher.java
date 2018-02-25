package com.example.learningrxjava.chapter1;

import io.reactivex.Observable;

public class Launcher implements Runnable {

    @Override
    public void run() {
        Observable<String> source = Observable.create(emitter -> {
            try {
                emitter.onNext("Alpha");
                emitter.onNext("Beta");
                emitter.onNext("Gamma");
                emitter.onNext("Delta");
                emitter.onNext("Epsilon");
                // You can not emit null values
                emitter.onComplete();
            } catch (Throwable err) {
                emitter.onError(err);
            }
        });

        source.subscribe(s -> System.out.println("RECEIVED: " + s),
                Throwable::printStackTrace);

//        Observable<Integer> lengths = source.map(String::length);
//        Observable<Integer> filtered = lengths.filter(i -> i >= 5);
        Observable<Integer> filtered = source
                .map(String::length)
                .filter(i -> i >= 5);
        filtered.subscribe(s -> System.out.println("RECEIVED: " +
                s));
    }

    public static void main(String[] args) {
        new Launcher().run();
    }
}