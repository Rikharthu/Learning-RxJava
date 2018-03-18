package com.example.learningrxjava.chapter1.disposing;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.ResourceObserver;

import java.util.concurrent.TimeUnit;

public class CustomObserver {

    /*
    Observer does receive disposably through it's onSubscribe method, but
    doesn't return one in Observable...subscribe(<Observer>)

    If you want to use Observer and return disposable, then extend ResourceObserver<> instead
    and use subscribeWith() method
    It subscribes the given observer and returns it (ResourceObserver implements Disposable)
     */

    Observer<Integer> myObserver = new Observer<Integer>() {
        private Disposable disposable;

        @Override
        public void onSubscribe(Disposable disposable) {
            this.disposable = disposable;
        }

        @Override
        public void onNext(Integer integer) {
            // Has access to disposable
        }

        @Override
        public void onError(Throwable e) {
            // Has access to disposable
        }

        @Override
        public void onComplete() {
            // Has access to disposable
        }
    };

    public static void main(String[] args) {

        Observable<Long> source = Observable.interval(1, TimeUnit.SECONDS);
        ResourceObserver<Long> resourceObserver = new ResourceObserver<Long>() {
            // Doesn't have reference to onSubscribe
            @Override
            public void onNext(Long aLong) {
                System.out.println("Received: " + aLong);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.println("Done!");
            }
        };

        // Capture disposably
        Disposable disposable=source.subscribeWith(resourceObserver);
    }
}
