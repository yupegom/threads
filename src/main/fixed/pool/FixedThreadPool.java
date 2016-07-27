package main.fixed.pool;

import main.fixed.worker.Task;
import main.fixed.worker.Worker;

import java.util.LinkedList;

/**
 * Created by yuliban on 7/25/16.
 */
public class FixedThreadPool {

    private LinkedList<Task> queue;
    private boolean poolShutDownInitiated = false;

    public FixedThreadPool(int threadPoolSize) {
        queue = new LinkedList<Task>();
        for(int i=0; i<threadPoolSize; i++) {
            Worker worker = new Worker(queue, this);
            worker.start();
        }
    }

    public void execute(Task r) throws Exception {
        if(this.poolShutDownInitiated)
            throw new Exception("ThreadPool has been shutDown, no further tasks can be added");
        System.out.println("task has been added.");
        synchronized(queue) {
            queue.add(r);
            queue.notify();
        }
    }

    public boolean isPoolShutDownInitiated() {
        return poolShutDownInitiated;
    }

    public synchronized void shutdown(){
        this.poolShutDownInitiated = true;
        System.out.println("ThreadPool SHUTDOWN initiated.");
    }
}


