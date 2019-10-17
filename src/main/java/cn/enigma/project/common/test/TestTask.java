package cn.enigma.project.common.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author luzh
 * Create: 2019/10/17 9:24 下午
 * Modified By:
 * Description:
 */
@Slf4j
@Component
public class TestTask {

    private final TestService testService;

    public TestTask(TestService testService) {
        this.testService = testService;
    }

    @Async
    public void run() {
        log.info("test task run {}", LocalDateTime.now().toString());
        testService.run();
    }
}
