package com.java_core.lesson5.dz;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CalcThread {

    private final int size = 10000000;
    private final int h = size / 2;

    private float[] arr = new float[size];

    public void fillArray() {
        for (int i = 0; i < size; i++) {
            arr[i] = i * 2;
        }
    }

    private class MyCallable implements Callable {

        private float[] array;

        public MyCallable(float[] array) {
            this.array = array;
        }

        public float[] call() throws Exception {
            for (int i = 0; i < array.length; i++) {
                array[i] = (float) (array[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
            return array;
        }
    }

    public void someMethod() {
        final long time = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println("Один поток: " + (System.currentTimeMillis() - time));
    }

    public void someMethodTwo() {
        final long time = System.currentTimeMillis();

        float[] a1 = new float[h];
        float[] a2 = new float[h];

        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);

        FutureTask futureTask1 = new FutureTask(new MyCallable(a1));
        FutureTask futureTask2 = new FutureTask(new MyCallable(a2));

        new Thread(futureTask1).start();
        new Thread(futureTask2).start();

        try {
            a1 = (float[]) futureTask1.get();
            a2 = (float[]) futureTask2.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);

        System.out.println("Два потока: " + (System.currentTimeMillis() - time));
    }
}
