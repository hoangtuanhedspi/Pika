package com.pikapika.control;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by anonymousjp on 5/21/17.
 */
public class CountDownTimer extends Timer{
    public CountDownTimer() {
        super();
    }

    public CountDownTimer(boolean isDaemon) {
        super(isDaemon);
    }

    public CountDownTimer(String name) {
        super(name);
    }

    public CountDownTimer(String name, boolean isDaemon) {
        super(name, isDaemon);
    }

    @Override
    public void schedule(TimerTask task, long delay) {
        super.schedule(task, delay);
    }

    @Override
    public void schedule(TimerTask task, Date time) {
        super.schedule(task, time);
    }

    @Override
    public void schedule(TimerTask task, long delay, long period) {
        super.schedule(task, delay, period);
    }

    @Override
    public void schedule(TimerTask task, Date firstTime, long period) {
        super.schedule(task, firstTime, period);
    }

    @Override
    public void scheduleAtFixedRate(TimerTask task, long delay, long period) {
        super.scheduleAtFixedRate(task, delay, period);
    }

    @Override
    public void scheduleAtFixedRate(TimerTask task, Date firstTime, long period) {
        super.scheduleAtFixedRate(task, firstTime, period);
    }

    @Override
    public void cancel() {
        super.cancel();
    }

    @Override
    public int purge() {
        return super.purge();
    }
}
