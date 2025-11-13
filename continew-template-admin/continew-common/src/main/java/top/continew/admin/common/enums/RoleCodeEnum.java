
package top.continew.admin.common.enums;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import top.continew.admin.common.constant.RegexConstants;

import java.util.Collections;
import java.util.List;

/**
 * 角色编码枚举
 *
 * @author Charles7c
 * @since 2025/7/26 19:18
 */
@Getter
@RequiredArgsConstructor
public enum RoleCodeEnum {

    /**
     * 超级管理员（内置且仅有一位超级管理员）
     */
    SUPER_ADMIN("super_admin", "超级管理员"),

    /**
     * 系统管理员
     */
    SYSTEM_ADMIN("sys_admin", "系统管理员"),

    /**
     * 普通用户
     */
    GENERAL_USER("general", "普通用户");

    private final String code;
    private final String description;

    /**
     * 获取超级管理员角色编码列表
     *
     * @return 超级管理员角色编码列表
     */
    public static List<String> getSuperRoleCodes() {
        return List.of(SUPER_ADMIN.getCode());
    }

    /**
     * 判断是否为超级管理员角色编码
     *
     * @param code 角色编码
     * @return 是否为超级管理员角色编码
     */
    public static boolean isSuperRoleCode(String code) {
        return getSuperRoleCodes().contains(code);
    }
}
