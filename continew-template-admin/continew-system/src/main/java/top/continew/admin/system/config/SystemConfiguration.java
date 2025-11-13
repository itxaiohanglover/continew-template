
package top.continew.admin.system.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 系统管理配置
 *
 * @author Charles7c
 * @since 2025/6/14 21:22
 */
@Configuration
public class SystemConfiguration {

    /**
     * API 文档分组配置
     */
    @Bean
    public GroupedOpenApi systemApi() {
        return GroupedOpenApi.builder().group("system").displayName("系统管理").pathsToMatch("/system/**").build();
    }
}
