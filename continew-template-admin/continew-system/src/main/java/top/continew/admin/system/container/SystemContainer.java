
package top.continew.admin.system.container;

import cn.crane4j.annotation.ContainerMethod;
import cn.crane4j.annotation.MappingType;
import cn.hutool.core.collection.CollUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import top.continew.admin.common.constant.ContainerConstants;
import top.continew.admin.system.mapper.RoleMapper;
import top.continew.admin.system.mapper.UserRoleMapper;
import top.continew.admin.system.model.entity.RoleDO;
import top.continew.admin.system.model.entity.UserRoleDO;

import java.util.Collections;
import java.util.List;

/**
 * 系统管理容器（Crane4j 数据填充）
 * <p>不建议复用，Crane4j 对列表填充聚合查询，优化性能</p>
 *
 * @author Charles7c
 * @since 2025/6/29 11:51
 */
@Component
@RequiredArgsConstructor
public class SystemContainer {

    private final UserRoleMapper userRoleMapper;
    private final RoleMapper roleMapper;

    /**
     * 根据用户 ID 列表获取角色 ID 列表
     *
     * @param userIds 用户 ID 列表
     * @return 角色 ID 列表
     */
    @ContainerMethod(namespace = ContainerConstants.USER_ROLE_ID_LIST, resultKey = "userId", resultType = UserRoleDO.class, type = MappingType.ONE_TO_MANY)
    public List<UserRoleDO> listRoleIdByUserId(List<Long> userIds) {
        return userRoleMapper.lambdaQuery()
            .select(UserRoleDO::getRoleId, UserRoleDO::getUserId)
            .in(UserRoleDO::getUserId, userIds)
            .list();
    }

    /**
     * 根据角色 ID 列表获取角色名称列表
     *
     * @param ids 角色 ID 列表
     * @return 角色名称列表
     */
    @ContainerMethod(namespace = ContainerConstants.USER_ROLE_NAME_LIST, resultType = RoleDO.class)
    public List<RoleDO> listRoleNameByIds(List<Long> ids) {
        if (CollUtil.isEmpty(ids)) {
            return Collections.emptyList();
        }
        return roleMapper.lambdaQuery().select(RoleDO::getName, RoleDO::getId).in(RoleDO::getId, ids).list();
    }
}
