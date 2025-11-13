
package top.continew.admin.system.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 第三方账号平台枚举
 *
 * @author Charles7c
 * @since 2023/10/19 21:22
 */
@Getter
@RequiredArgsConstructor
public enum SocialSourceEnum {

    /**
     * 码云
     */
    GITEE("码云"),

    /**
     * GitHub
     */
    GITHUB("GitHub"),;

    private final String description;
}
