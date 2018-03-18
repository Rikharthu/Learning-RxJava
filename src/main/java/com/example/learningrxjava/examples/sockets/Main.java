package com.example.learningrxjava.examples.sockets;

import io.reactivex.Observable;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        Observable.create(emitter -> {
            Socket socket = new Socket("127.0.0.1", 4444);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                emitter.onNext(line);
            }
        }).subscribe(System.out::println);

        Thread.sleep(60 * 60 * 1000);
    }
}
