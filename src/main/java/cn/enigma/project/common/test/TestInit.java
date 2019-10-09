package cn.enigma.project.common.test;

import cn.enigma.project.common.startup.SystemInit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author luzh
 * Create: 2019/10/9 下午3:32
 * Modified By:
 * Description:
 */
@Slf4j
@Component("TestInit")
public class TestInit extends SystemInit {

    public void initTest() {
        lock.lock();
        try {
            log.info("{}", LocalDateTime.now().toString());
            Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 3000));
            log.info("{} sleep end", LocalDateTime.now().toString());
        } catch (Exception ignored) {
        } finally {
            lock.unlock();
        }
    }
}
