package main.fixed.worker;

import java.util.concurrent.TimeUnit;

/**
 * Created by yuliban on 7/26/16.
 */
public class Task implements Runnable {

    @Override
    public void run() {
        try
        {
            Long duration = (long) (Math.random() * 10);
            System.out.println("Doing a task during : " + duration);
            TimeUnit.SECONDS.sleep(2000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
