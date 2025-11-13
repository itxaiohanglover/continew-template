
package top.continew.admin.system.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.continew.admin.common.api.system.RoleMenuApi;
import top.continew.admin.system.model.entity.RoleMenuDO;
import top.continew.admin.system.service.RoleMenuService;
import top.continew.starter.core.util.CollUtils;

import java.util.List;
import java.util.Set;

/**
 * 角色和菜单关联业务 API 实现
 *
 * @author Charles7c
 * @since 2025/7/26 9:39
 */
@Service
@RequiredArgsConstructor
public class RoleMenuApiImpl implements RoleMenuApi {

    private final RoleMenuService baseService;

    @Override
    public Set<Long> listRoleIdByNotInMenuIds(List<Long> menuIds) {
        List<RoleMenuDO> roleMenuList = baseService.lambdaQuery()
            .select(RoleMenuDO::getRoleId)
            .notIn(RoleMenuDO::getMenuId, menuIds)
            .list();
        return CollUtils.mapToSet(roleMenuList, RoleMenuDO::getRoleId);
    }

    @Override
    public List<Long> listMenuIdByRoleIds(List<Long> roleIds) {
        return baseService.listMenuIdByRoleIds(roleIds);
    }

    @Override
    public void deleteByNotInMenuIds(List<Long> menuIds) {
        baseService.lambdaUpdate().notIn(RoleMenuDO::getMenuId, menuIds).remove();
    }

    @Override
    public boolean add(List<Long> menuIds, Long roleId) {
        return baseService.add(menuIds, roleId);
    }
}
