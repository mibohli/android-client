package com.buddycloud;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

import android.util.Log;

public class TaskQueueThread extends Thread {

    private ArrayBlockingQueue<Runnable> taskQueue
                = new ArrayBlockingQueue<Runnable>(20);

    public void run() {
        while (taskQueue != null) {
            try {
                Runnable runnable = taskQueue.poll(1800, TimeUnit.SECONDS);
                if (runnable != null) {
                    runnable.run();
                }
            } catch (Exception e) {
                Log.d("Service", e.toString(), e);
            }
        }
    }

    public void stopQueue() {
        taskQueue = null;
    }

    public boolean add(Runnable run) throws InterruptedException {
        return taskQueue.offer(run, 10, TimeUnit.SECONDS);
    }
}
