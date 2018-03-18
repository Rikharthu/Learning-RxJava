package com.example.learningrxjava.chapter1;


import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

public class ConnectableObservables {

    /*
    Connectable Observable is a helpful form of hot Observable
    It will take any Observable, event if it's cold, and make it hot so that
    all emissions are played to all observers at once

    To do this conversion, simply call .publish() on any Observable,
    and it will yield a ConnectableObservable,
    but subscribing will not start firing emissions.
    You need to call its connect() method first.

    If new subscribtions occur after .connect() method call, they will miss emissions that
    were fired previously

    !!! It is helpful in preventing the replay of data to each Observer
    by emitting them to all Observers at once
     */

    public static void main(String[] args) {
        ConnectableObservable<String> source =
                Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                        .publish(); // Make it connectable

        // Set up observer 1
        source.subscribe(s-> System.out.println("Observer 1: "+s));

        // Set up observer 2
        source.subscribe(s-> System.out.println("Observer 2: "+s));

        // Fire!
        source.connect();

        // Notice that  Rather than Observer 1
        // processing all the emissions before Observer 2, each emission goes to each Observer
        // simultaneously. Observer 1 receives Alpha and Observer 2 receives 5 and then
        // Beta and 4, and so on. Using ConnectableObservable to force each emission to go to all
        // Observers simultaneously is known as multicasting
    }
}
