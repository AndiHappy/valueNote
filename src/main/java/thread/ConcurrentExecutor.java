package thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ConcurrentExecutor {

    private static ExecutorService threadPool = Executors.newFixedThreadPool(20);

    private static Logger LOGGER = LoggerFactory.getLogger(ConcurrentExecutor.class);

    public static <T> boolean concurrentExecute(List<T> configs,
                                                ConfigConverter<T> converter,
                                                final AfterExecHandle<T> after,
                                                long timeoutMills) {
        final CountDownLatch countDown = new CountDownLatch(configs.size());
        for (final T config : configs) {
            final Computable computable = converter.convert(config);
            threadPool.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        computable.execute(after);
                    } catch (Exception e) {
                        LOGGER.error("risk-compute-center||execute [{}] failed", config.toString(), e);
                    } finally {
                        countDown.countDown();
                    }
                }
            });
        }
        try {
            return countDown.await(timeoutMills, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            LOGGER.error("thread interrupted !", e);
        }
        return false;
    }

    private static class ConfigConverter<T> {
        public Computable convert(T config) {
            config.equals(null);
            return null;

        }
    }


    private static class AfterExecHandle<T> {
    }

    private static class Computable {
        public <T> void execute(AfterExecHandle<T> after) {

        }
    }
}
