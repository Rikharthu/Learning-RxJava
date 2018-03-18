package com.example.learningrxjava.chapter1.completable;

import io.reactivex.Completable;
import io.reactivex.Observable;

public class Launcher {

    /*
    Completable is concerned with an action being executed
    * Doesn't receive any emissions

    onSubscribe
    onComplete
    onError
    NO onNext!
     */

    public static void main(String[] args) {
        Completable.fromRunnable(() -> runProcess())
                .subscribe(() -> System.out.println("Done!"));
        // or .subscribe();
    }

    public static void runProcess() {
        System.out.println("Some computation...");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Some computation...");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Some computation...");
    }
}
