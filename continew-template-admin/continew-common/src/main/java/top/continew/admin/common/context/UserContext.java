
package top.continew.admin.common.context;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.extra.spring.SpringUtil;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.continew.admin.common.constant.GlobalConstants;
import top.continew.admin.common.enums.RoleCodeEnum;
import top.continew.starter.core.util.CollUtils;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * 用户上下文
 *
 * @author Charles7c
 * @since 2024/10/9 20:29
 */
@Data
@NoArgsConstructor
public class UserContext implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 部门 ID
     */
    private Long deptId;

    /**
     * 最后一次修改密码时间
     */
    private LocalDateTime pwdResetTime;

    /**
     * 登录时系统设置的密码过期天数
     */
    private Integer passwordExpirationDays;

    /**
     * 权限码集合
     */
    private Set<String> permissions;

    /**
     * 角色编码集合
     */
    private Set<String> roleCodes;

    /**
     * 角色集合
     */
    private Set<RoleContext> roles;

    /**
     * 客户端类型
     */
    private String clientType;

    /**
     * 客户端 ID
     */
    private String clientId;


    public UserContext(Set<String> permissions, Set<RoleContext> roles, Integer passwordExpirationDays) {
        this.permissions = permissions;
        this.setRoles(roles);
        this.passwordExpirationDays = passwordExpirationDays;
    }

    /**
     * 设置角色
     *
     * @param roles 角色
     */
    public void setRoles(Set<RoleContext> roles) {
        this.roles = roles;
        this.roleCodes = CollUtils.mapToSet(roles, RoleContext::getCode);
    }

    /**
     * 密码是否已过期
     *
     * @return 是否过期
     */
    public boolean isPasswordExpired() {
        // 永久有效
        if (this.passwordExpirationDays == null || this.passwordExpirationDays <= GlobalConstants.Boolean.NO) {
            return false;
        }
        // 初始密码（第三方登录用户）暂不提示修改
        if (this.pwdResetTime == null) {
            return false;
        }
        return this.pwdResetTime.plusDays(this.passwordExpirationDays).isBefore(LocalDateTime.now());
    }

    /**
     * 是否为超级管理员用户
     *
     * @return true：是；false：否
     */
    public boolean isSuperAdminUser() {
        if (CollUtil.isEmpty(roleCodes)) {
            return false;
        }
        return roleCodes.contains(RoleCodeEnum.SUPER_ADMIN.getCode());
    }


}
