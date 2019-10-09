package cn.enigma.project.common.config;

import cn.enigma.project.common.controller.CommonErrorController;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.SearchStrategy;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author luzh
 * Create: 2019/10/9 下午5:05
 * Modified By:
 * Description:
 */
@Component
@AutoConfigureBefore(ErrorMvcAutoConfiguration.class)
public class ErrorControllerConfig {

    @Bean
    @ConditionalOnMissingBean(value = CommonErrorController.class, search = SearchStrategy.CURRENT)
    CommonErrorController commonErrorController() {
        return new CommonErrorController();
    }
}
