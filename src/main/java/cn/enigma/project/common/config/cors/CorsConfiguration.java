package cn.enigma.project.common.config.cors;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author luzh
 * Create: 2019/9/25 下午5:54
 * Modified By:
 * Description:
 */
@Configuration
@EnableConfigurationProperties(CorsConfig.class)
public class CorsConfiguration {
}
