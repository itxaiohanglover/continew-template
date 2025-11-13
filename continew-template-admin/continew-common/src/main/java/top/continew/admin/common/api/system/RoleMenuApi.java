
package top.continew.admin.common.api.system;

import java.util.List;
import java.util.Set;

/**
 * 角色和菜单关联业务 API
 *
 * @author Charles7c
 * @since 2025/7/26 9:39
 */
public interface RoleMenuApi {

    /**
     * 根据菜单 ID 列表查询角色 ID 列表
     *
     * @param menuIds 菜单 ID 列表（Not In）
     * @return 角色 ID 列表
     */
    Set<Long> listRoleIdByNotInMenuIds(List<Long> menuIds);

    /**
     * 根据角色 ID 列表查询菜单 ID 列表
     *
     * @param roleIds 角色 ID 列表
     * @return 菜单 ID 列表
     */
    List<Long> listMenuIdByRoleIds(List<Long> roleIds);

    /**
     * 根据菜单 ID 列表删除
     *
     * @param menuIds 菜单 ID 列表（Not In）
     */
    void deleteByNotInMenuIds(List<Long> menuIds);

    /**
     * 新增
     *
     * @param menuIds 菜单 ID 列表
     * @param roleId  角色 ID
     * @return 是否新增成功（true：成功；false：无变更/失败）
     */
    boolean add(List<Long> menuIds, Long roleId);
}
