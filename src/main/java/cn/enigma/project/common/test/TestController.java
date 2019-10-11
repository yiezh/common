package cn.enigma.project.common.test;

import cn.enigma.project.common.controller.trace.annotation.HttpTrace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author luzh
 * Create: 2019/9/25 下午6:09
 * Modified By:
 * Description:
 */
@Slf4j
@RestController
@RequestMapping("test")
public class TestController {

    @HttpTrace
    @GetMapping
    public String test() {
        long sleep = ThreadLocalRandom.current().nextInt(1000, 5000);
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("test {}, sleep {}", LocalDateTime.now().toString(), sleep);
        return LocalDateTime.now().toString();
    }
}
