package com.example.learningrxjava.chapter1.disposing;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static java.lang.Thread.sleep;

/**
 * Handling Disposal with Observable.create()
 */
public class Dispose_Observable_create {

    /*
    If your Observable.create() is returning a long-running or infinite Observable, you
    should check the isDisposed() method of ObservableEMitter regularly, to see
    whether you should keep sending emissions
    This prevents unnecessary work from being done if the subscribtion is no longer active
     */

    public static void main(String[] args) throws InterruptedException {
        Observable<Integer> source = Observable.create(emitter -> {
            try {
                emitter.setCancellable(() -> {
                    // This action will be executed when dispose() is called
                    // Free all your resources and unattach listeners here!
                    //freeResources();
                    System.out.println("Freeing used resources");
                });

                for (int i = 0; i < 1000; i++) {
                    // Emit only while there is an active subscribtion
                    while (!emitter.isDisposed()) {
                        emitter.onNext(i);
                    }
                    if (emitter.isDisposed()) {
                        return;
                    }
                }
            } catch (Throwable e) {
                emitter.onError(e);
            }
        });

        Disposable subscribtion = source
                .subscribeOn(Schedulers.io())
                .subscribe(value -> {
                    System.out.println("Received: " + value);
                });

        sleep(5000);

        System.out.println("Disposing");
        subscribtion.dispose();
        System.out.println("Disposed");

        sleep(5000);
    }
}
