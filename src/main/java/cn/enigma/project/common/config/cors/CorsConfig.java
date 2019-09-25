package cn.enigma.project.common.config.cors;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author luzh
 * Create: 2019/9/25 下午5:54
 * Modified By:
 * Description:
 */
@Data
@ConfigurationProperties(prefix = "cors")
public class CorsConfig {
    private String headers;
}
