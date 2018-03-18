package com.example.learningrxjava.examples;


import io.reactivex.Observable;

public class ReadFileDemo {
    public static void main(String[] args) {
        Observable<String> observable = Observable.defer(() -> new FileObservableSource("hello.txt"));
        observable.subscribe(System.out::println);
    }
}
