package com.example.learningrxjava.chapter1;

import io.reactivex.Observable;

import java.util.Arrays;
import java.util.List;

public class IterableLauncher {

    public static void main(String[] args) {
        // You can create observables from iterables
        List<String> items = Arrays.asList("Alpha", "Beta", "Gama", "Delta", "Epsilon");
        Observable<String> source = Observable.fromIterable(items);
        source.map(String::length).filter(i -> i >= 5)
                .subscribe(System.out::println);
    }
}
