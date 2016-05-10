package com.github.cementovoz.rxfx;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.util.Duration;
import rx.Scheduler;
import rx.Subscription;
import rx.functions.Action0;
import rx.subscriptions.Subscriptions;

import java.util.concurrent.TimeUnit;

public class FXScheduler extends Scheduler {

    private FXScheduler() {
    }

    public static FXScheduler instance() {
        return FXSchedulerHolder.HOLDER;
    }

    private static class FXSchedulerHolder {
        public static FXScheduler HOLDER = new FXScheduler();
    }

    @Override
    public Worker createWorker() {
        return new FXWorker();
    }

    public static class FXWorker extends Scheduler.Worker implements Subscription {

        private boolean isUnsubscribed;

        @Override
        public Subscription schedule(Action0 action, long delayTime, TimeUnit unit) {
            if (isUnsubscribed) {
                return Subscriptions.unsubscribed();
            }
            final long delay = unit.toMillis(Long.max(delayTime, 0));
            final Timeline timeline = new Timeline(new KeyFrame(Duration.millis(delay), event -> {
                action.call();
            }));
            timeline.play();
            timeline.setCycleCount(1);
            return Subscriptions.create(() -> {
                isUnsubscribed = true;
            });
        }

        @Override
        public Subscription schedule(Action0 action) {
            if (isUnsubscribed) {
                return Subscriptions.unsubscribed();
            }
            Platform.runLater(() -> {
                action.call();
            });
            return Subscriptions.create(() -> {
                isUnsubscribed = true;
            });
        }

        @Override
        public void unsubscribe() {
            isUnsubscribed = true;
        }

        @Override
        public boolean isUnsubscribed() {
            return isUnsubscribed;
        }
    }
}
