package com.example.learningrxjava.chapter1.disposing;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class Using_CompositeDisposable {

    /*
    CompositeDisposable is used when several subscriptions need to be managed and disposed of
     */

    public static void main(String[] args) throws InterruptedException {
        CompositeDisposable disposables = new CompositeDisposable();

        Observable<Long> seconds = Observable.interval(1, TimeUnit.SECONDS);

        // Subscribe and capture disposables
        Disposable disposable1 = seconds.subscribe(l -> System.out.println("Observer 1: " + l));
        Disposable disposable2 = seconds.subscribe(l -> System.out.println("Observer 2: " + l));

        // Put both disposables into CompositeDisposable
        disposables.addAll(disposable1, disposable2);

        // Sleep for 5 seconds
        sleep(5000);

        // Dispose all disposables
        disposables.dispose();

        // Sleep for another 5 seconds
        sleep(5000);
    }
}
