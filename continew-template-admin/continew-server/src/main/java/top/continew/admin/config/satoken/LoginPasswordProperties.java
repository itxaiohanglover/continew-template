
package top.continew.admin.config.satoken;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 密码配置属性
 *
 * @author Charles7c
 * @since 2024/6/15 22:15
 */
@Data
@Component
@ConfigurationProperties(prefix = "auth.password")
public class LoginPasswordProperties {

    /**
     * 排除（放行）路径配置
     */
    private String[] excludes = new String[0];
}