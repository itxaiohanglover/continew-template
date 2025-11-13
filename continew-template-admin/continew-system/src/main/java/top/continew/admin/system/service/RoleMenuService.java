
package top.continew.admin.system.service;

import top.continew.admin.system.model.entity.RoleMenuDO;
import top.continew.starter.data.service.IService;

import java.util.List;

/**
 * 角色和菜单业务接口
 *
 * @author Charles7c
 * @since 2023/2/19 10:40
 */
public interface RoleMenuService extends IService<RoleMenuDO> {

    /**
     * 新增
     *
     * @param menuIds 菜单 ID 列表
     * @param roleId  角色 ID
     * @return 是否新增成功（true：成功；false：无变更/失败）
     */
    boolean add(List<Long> menuIds, Long roleId);

    /**
     * 根据角色 ID 列表删除
     *
     * @param roleIds 角色 ID 列表
     */
    void deleteByRoleIds(List<Long> roleIds);

    /**
     * 根据角色 ID 列表查询
     *
     * @param roleIds 角色 ID 列表
     * @return 菜单 ID 列表
     */
    List<Long> listMenuIdByRoleIds(List<Long> roleIds);
}