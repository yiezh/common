package cn.enigma.project.common.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author luzh
 * Create: 2019/10/17 10:34 下午
 * Modified By:
 * Description:
 */
@Slf4j
@Service
public class TestService {

    public void run () {
        log.info("test service run");
    }
}
