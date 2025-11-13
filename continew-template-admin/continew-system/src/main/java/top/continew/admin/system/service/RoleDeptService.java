
package top.continew.admin.system.service;

import java.util.List;

/**
 * 角色和部门关联业务接口
 *
 * @author Charles7c
 * @since 2023/2/19 10:40
 */
public interface RoleDeptService {

    /**
     * 新增
     *
     * @param deptIds 部门 ID 列表
     * @param roleId  角色 ID
     * @return 是否新增成功（true：成功；false：无变更/失败）
     */
    boolean add(List<Long> deptIds, Long roleId);

    /**
     * 根据角色 ID 删除
     *
     * @param roleIds 角色 ID 列表
     */
    void deleteByRoleIds(List<Long> roleIds);

    /**
     * 根据部门 ID 删除
     *
     * @param deptIds 部门 ID 列表
     */
    void deleteByDeptIds(List<Long> deptIds);

    /**
     * 根据角色 ID 查询
     *
     * @param roleId 角色 ID
     * @return 部门 ID 列表
     */
    List<Long> listDeptIdByRoleId(Long roleId);
}