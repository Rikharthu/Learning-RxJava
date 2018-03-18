package com.example.learningrxjava.examples;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileObservableSource implements ObservableSource<String> {

    private final String filename;

    public FileObservableSource(String filename) {
        this.filename = filename;
    }

    @Override
    public void subscribe(Observer<? super String> observer) {
        try {
            Files.lines(Paths.get(filename)).forEach(observer::onNext);
        } catch (IOException e) {
            observer.onError(e);
        }
    }
}
