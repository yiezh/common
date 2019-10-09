package cn.enigma.project.common.config;

import cn.enigma.project.common.controller.trace.HttpTraceAop;
import cn.enigma.project.common.startup.SystemInit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author luzh
 * Create: 2019/9/25 下午5:35
 * Modified By:
 * Description:
 */
@Configuration
public class CommonBeanConfig {

    // 接口请求aop拦截bean
    @Bean
    HttpTraceAop httpTraceAop() {
        return new HttpTraceAop();
    }

    // 项目启动执行方法bean
    @Bean
    SystemInit systemInit() {
        return new SystemInit();
    }

}
