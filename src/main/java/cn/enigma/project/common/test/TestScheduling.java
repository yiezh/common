package cn.enigma.project.common.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author luzh
 * Create: 2019/10/17 9:46 下午
 * Modified By:
 * Description:
 */
@Slf4j
@Component
public class TestScheduling {

    private final TestTask testTask;

    public TestScheduling(TestTask testTask) {
        this.testTask = testTask;
    }

    @Scheduled(cron = "*/10 * * * * *")
    public void run() {
      log.info("looo");
      testTask.run();
    }

    @Scheduled(cron = "*/2 * * * * *")
    public void run1() {
        log.info("looo 111");
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(10000, 60000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("looo 111 end");
    }
}
