package com.example.learningrxjava.chapter1.disposing;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class Launcher {

    /*
    The Disposable is a link between and Observable and an active Observer
    You can call it's dispose() method to stop emissions and dispose all resources
    user for that Observer (to prevent memory leaks)
     */

    public static void main(String[] args) throws InterruptedException {
        Observable<Long> seconds = Observable.interval(1, TimeUnit.SECONDS);

        Disposable disposable =
                seconds.subscribe(l -> System.out.println("Received: " + l));

        // Sleep for 5 seconds
        sleep(5000);

        // Dispose and stop emissions
        disposable.dispose();

        // Sleep for 5 seconds to prove there are no more emissions
        sleep(5000);
    }
}
