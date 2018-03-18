package com.example.learningrxjava.chapter1;

import io.reactivex.Observable;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class HotObservables extends Application {

    /*
    A hot observable broadcasts the same emissions to all Observers at the same time.
    If an Observer subscribes to a hot Observable, receives some emissions,
    abd then another Observer comes in afterwards, that second Observer will have missed
    those emissions

    Logically hot Observables represents events rather than finite datasets
     */

//    public static void main(String[] args) {
//
//    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ToggleButton toggleButton = new ToggleButton("Toggle Me");
        Label label = new Label();

        // A hot Observables observing togglebutton state changes
        Observable<Boolean> selectedStates = valuesOf(toggleButton.selectedProperty());
        selectedStates.map(selected -> selected ? "DOWN" : "UP").subscribe(label::setText);

        VBox vBox = new VBox(toggleButton, label);

        primaryStage.setScene(new Scene(vBox));
        primaryStage.show();
    }

    private static <T> Observable<T> valuesOf(final ObservableValue<T> fxObservable) {
        return Observable.create(emitter -> {
            // Emit initial state
            emitter.onNext(fxObservable.getValue());

            // emit value changes uses a listener
            final ChangeListener<T> listener = ((observable, oldValue, newValue) -> emitter.onNext(newValue));

            fxObservable.addListener(listener);
        });
    }
}
