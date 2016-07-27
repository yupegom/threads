package main.fixed.worker;

import main.fixed.pool.FixedThreadPool;

import java.util.LinkedList;

/**
 * Created by yuliban on 7/25/16.
 */
public class Worker extends Thread {
    private LinkedList<Task> queue;
    public FixedThreadPool pool;

    public Worker(LinkedList<Task> queue, FixedThreadPool pool) {
        this.queue = queue;
        this.pool = pool;
    }

    public void run() {
        try {
                  /*
                   * ThreadPool's threads will keep on running
                   * until ThreadPool is not shutDown (shutDown will interrupt thread) and
                   * queue contains some unExecuted tasks.
                   */
            while (true) {
                System.out.println(Thread.currentThread().getName()
                        +" is READY to execute task.");
                        /*ThreadPool's thread will take() task from sharedQueue
                         * only if tasks are available else
                         * waits for tasks to become available.
                         */
                Task runnable = queue.element();
                System.out.println(Thread.currentThread().getName()
                        +" has taken task.");
                //Now, execute task with current thread.
                runnable.run();

                System.out.println(Thread.currentThread().getName()
                        +" has EXECUTED task.");
                if(this.pool.isPoolShutDownInitiated()
                        &&  this.queue.size()==0){
                    this.interrupt();
                             /*
                                *  Interrupting basically sends a message to the thread
                                *  indicating it has been interrupted but it doesn't cause
                                *  a thread to stop immediately,
                                *
                                *  if sleep is called, thread immediately throws InterruptedException
                                */
                    Thread.sleep(1);
                }


            }
        } catch (Exception e) {
            System.out.println(Thread.currentThread().getName()+" has been STOPPED.");
        }
    }
}

