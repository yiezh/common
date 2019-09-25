package cn.enigma.project.common.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @author luzh
 * Create: 2019/9/25 下午6:09
 * Modified By:
 * Description:
 */
@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping
    public String test() {
        return LocalDateTime.now().toString();
    }
}
