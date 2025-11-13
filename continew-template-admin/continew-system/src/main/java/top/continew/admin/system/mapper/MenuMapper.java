
package top.continew.admin.system.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.continew.admin.system.model.entity.MenuDO;
import top.continew.starter.data.mapper.BaseMapper;

import java.util.List;
import java.util.Set;

/**
 * 菜单 Mapper
 *
 * @author Charles7c
 * @since 2023/2/15 20:30
 */
@Mapper
public interface MenuMapper extends BaseMapper<MenuDO> {

    /**
     * 根据用户 ID 查询权限码
     *
     * @param userId 用户 ID
     * @return 权限码集合
     */
    Set<String> selectPermissionByUserId(@Param("userId") Long userId);

    /**
     * 根据角色 ID 查询
     *
     * @param roleId 角色 ID
     * @return 菜单列表
     */
    List<MenuDO> selectListByRoleId(@Param("roleId") Long roleId);
}
