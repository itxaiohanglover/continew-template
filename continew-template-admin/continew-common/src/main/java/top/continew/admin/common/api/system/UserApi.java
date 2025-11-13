
package top.continew.admin.common.api.system;

import cn.crane4j.annotation.ContainerMethod;
import cn.crane4j.annotation.MappingType;
import top.continew.admin.common.constant.ContainerConstants;

/**
 * 用户业务 API
 *
 * @author Charles7c
 * @since 2025/1/9 20:17
 */
public interface UserApi {

    /**
     * 根据 ID 查询昵称
     *
     * <p>
     * 数据填充容器 {@link ContainerConstants#USER_NICKNAME}
     * </p>
     * 
     * @param id ID
     * @return 昵称
     */
    @ContainerMethod(namespace = ContainerConstants.USER_NICKNAME, type = MappingType.ORDER_OF_KEYS)
    String getNicknameById(Long id);

    /**
     * 重置密码
     *
     * @param newPassword 新密码
     * @param id          ID
     */
    void resetPassword(String newPassword, Long id);
}
