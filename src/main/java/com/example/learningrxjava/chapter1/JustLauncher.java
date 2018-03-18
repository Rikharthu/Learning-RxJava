package com.example.learningrxjava.chapter1;


import io.reactivex.Observable;

public class JustLauncher {

    public static void main(String[] args) {
        // Can pass up to 10 items that way
        Observable<String> source = Observable.just("Alpha", "Beta", "Gama", "Delta", "Epsilon");
        source.map(String::length).filter(i -> i >= 5)
                .subscribe(System.out::println);
    }
}
