package com.github.cementovoz.rxfx;

import javafx.event.ActionEvent;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.MenuItem;
import rx.Observable;
import rx.Subscriber;

public class FXEvents {

    /**
     * Generate events from clicks for button,checkbox,etc...
     *
     * @param button
     * @return
     */
    public static Observable<ActionEvent> events(ButtonBase button) {
        return Observable.<ActionEvent>create(new Observable.OnSubscribe<ActionEvent>() {
            @Override
            public void call(Subscriber<? super ActionEvent> subscriber) {
                button.setOnAction((e) -> subscriber.onNext(e));
            }
        });
    }

    /**
     * Generate events from clicks for menu
     *
     * @param node
     * @return
     */
    public static Observable<ActionEvent> events(MenuItem node) {
        return Observable.<ActionEvent>create(new Observable.OnSubscribe<ActionEvent>() {
            @Override
            public void call(Subscriber<? super ActionEvent> subscriber) {
                node.setOnAction((e) -> subscriber.onNext(e));
            }
        });
    }
}
