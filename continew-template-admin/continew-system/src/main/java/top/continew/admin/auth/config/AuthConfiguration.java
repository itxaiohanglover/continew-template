
package top.continew.admin.auth.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 系统认证配置
 *
 * @author Charles7c
 * @since 2025/6/14 21:22
 */
@Configuration
public class AuthConfiguration {

    /**
     * API 文档分组配置
     */
    @Bean
    public GroupedOpenApi authApi() {
        return GroupedOpenApi.builder()
            .group("auth")
            .displayName("系统认证")
            .pathsToMatch("/auth/**", "/monitor/online/**")
            .build();
    }
}
