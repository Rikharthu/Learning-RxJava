package com.example.learningrxjava.examples.setup

import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import java.util.*

fun main(args: Array<String>) {
    val networkingSetup = setupNetworking().retry()
    val cameraSetup = setupCamera().retry(3)
    val locationSetup = setupLocation().retry()
    val d = Completable.merge(mutableListOf(networkingSetup, cameraSetup, locationSetup))
            .subscribe({
                println("-- Setup completed")
            }, {
                println("-- Error: ${it.message}")
            })

    Thread.sleep(60 * 60 * 1000)
}

fun setupNetworking(): Completable {
    return Completable.create { emitter ->
        println("Setting up networking...")
        Thread.sleep(1000)
        val isSuccess = Random().nextBoolean()
        if (isSuccess) {
            println("!Networking is set up")
            emitter.onComplete()
        } else {
            println("Could not setup networking")
            emitter.onError(Throwable("Could not setup networking"))
        }
    }.subscribeOn(Schedulers.computation())
}

fun setupCamera(): Completable {
    return Completable.create { emitter ->
        println("Setting up camera...")
        Thread.sleep(1700)
        val isSuccess = Random().nextBoolean()
        if (isSuccess) {
            println("!Camera is set up")
            emitter.onComplete()
        } else {
            println("Could not setup camera")
            emitter.onError(Throwable("Could not setup camera"))
        }
    }.subscribeOn(Schedulers.computation())
}

fun setupLocation(): Completable {
    return Completable.create { emitter ->
        println("Setting up location...")
        Thread.sleep(300)
        val isSuccess = Random().nextFloat() > 0.96
        if (isSuccess) {
            println("!location is set up")
            emitter.onComplete()
        } else {
            println("Could not setup location")
            emitter.onError(Throwable("Could not setup location"))
        }
    }.subscribeOn(Schedulers.computation())
}