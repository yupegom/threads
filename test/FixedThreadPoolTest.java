import main.fixed.pool.FixedThreadPool;
import main.fixed.worker.Task;

/**
 * Created by yuliban on 7/26/16.
 */
public class FixedThreadPoolTest {

    public static void main(String[] args) throws Exception {
        FixedThreadPool pool = new FixedThreadPool(3);
        Task task = new Task();
        pool.execute(task);
        pool.shutdown();

    }
}
