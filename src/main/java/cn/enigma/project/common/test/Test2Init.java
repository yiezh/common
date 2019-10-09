package cn.enigma.project.common.test;

import cn.enigma.project.common.startup.SystemInit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author luzh
 * Create: 2019/10/9 下午3:35
 * Modified By:
 * Description:
 */
@Slf4j
@Component
@DependsOn("TestInit")
public class Test2Init extends SystemInit {

    public void initTest() {
        lock.lock();
        try {
            log.info("{}", LocalDateTime.now().toString());
        } finally {
            lock.unlock();
        }
    }
}
