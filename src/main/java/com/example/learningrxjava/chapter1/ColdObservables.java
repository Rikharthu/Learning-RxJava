package com.example.learningrxjava.chapter1;

import io.reactivex.Observable;

public class ColdObservables {

    /*
    Cold Observables will replay the emissions to each Observer, ensuring that all Observers get all the data
    Most data-driven Observables are cold, and this includes .just() and .fromIterable() factories

    Operators such as .map() and .filter() maintain the  cold nature of the yielded Observables
     */

    public static void main(String[] args) {
        Observable<String> source =
                Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");
        // These both receive the same datasets by getting two separate streams each
        //first observer
        source.subscribe(s -> System.out.println("Observer 1 Received: " + s));
        //second observer
        source.subscribe(s -> System.out.println("Observer 2 Received: " + s));
    }
}
