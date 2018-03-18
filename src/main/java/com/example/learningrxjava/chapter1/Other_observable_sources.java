package com.example.learningrxjava.chapter1;

import io.reactivex.Observable;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class Other_observable_sources {

    public static void main(String[] args) throws InterruptedException {
        // .just()
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .subscribe((value) -> System.out.println(".just: " + value));

        // .fromIterable()
        List<String> items = Arrays.asList("Alpha", "Beta", "Gama", "Delta", "Epsilon");
        Observable<String> source = Observable.fromIterable(items);
        source.map(String::length).filter(i -> i >= 5)
                .subscribe((value) -> System.out.println(".fromIterable: " + value));

        // .range()
        // emit each number from a start value and increment each until the specified count is reached
        Observable.range(23, 10)
                .subscribe(s -> System.out.println(".range: " + s));
        // -> 23..32 (10 is count of emissions here, not upper bound

        // .interval()
        // Will emit a consecutive long emissions (starting from 0) at every specified time interval
        Observable<Long> timer =
                Observable.interval(1, TimeUnit.SECONDS);
        timer.subscribe(s -> System.out.println(s + " Mississippi"));
        // Runs on the computation Scheduler by default
        Thread.sleep(3 * 1000);
        // It's cold observable will restart timer for each subsequent subscribtion/use same value
        timer.subscribe(s -> System.out.println(s + " Mississippi (2)"));
        // To start simultaneously make it ConnectableObservable
        /*
        timer.publish()
        timer.subscribe(...)
        timer.connect()
        *some delay*
        timer.subscribe(...)
        *will use same values in both subscribers*
        */

        // .future()
        /*
        Future<String> futureValue =...;
        Observable.fromFuture(futureValue)
                .map(String::length)
                .subscribe(System.out::println);
        */

        // .empty()
        // Usually represent empty dataset
        // Or result from filter() operators when all emissions fail to meet a condition
        Observable<String> empty = Observable.empty();
        empty.subscribe(System.out::println, Throwable::printStackTrace, () -> System.out.println("Empty: Done!"));

        // .never()
        // Often used for just testing
        Observable.never().subscribe(System.out::println, Throwable::printStackTrace, () -> System.out.println("Never: Done!"));

        // .error()
        Observable<String> error = Observable.error(new Exception("Crash and burn!"));
        error.subscribe(System.out::println,
                (err) -> {
                    System.out.println("Observer 1, received error: " + err.getMessage());
                },
                () -> System.out.println("Error: Done!"));
        error.subscribe(System.out::println,
                (err) -> {
                    System.out.println("Observer 2, received error: " + err.getMessage());
                },
                () -> System.out.println("Error: Done!"));

        // Or use lambda function to construct a brand new instance of exception rather than passing single one
        Observable<String> errorLambda = Observable.error(() -> new Exception("Crash and burn!"));

        // .defer()
        // Create a separate state for each Observer
        // TODO see separate sample DeferredObservables.java

        // .fromCallable()
        // Observable.just(1/0) ERROR - would immediatelly crash instead of pushin onError downstream
        Observable.fromCallable(() -> 1 / 0) // Correct!
                .subscribe(i -> System.out.println(".fromCallable: " + i),
                        e -> System.out.println(".fromCallable: error - " + e.getMessage()));
        Callable<String> greetingsCallable = () -> {
            Thread.sleep(1000);
            return "Hello, World!";
        };
        Observable.fromCallable(greetingsCallable)
                .subscribe((greeting) -> System.out.println("Greeting from callable: " + greeting));

        Thread.sleep(10 * 1000);
    }
}
