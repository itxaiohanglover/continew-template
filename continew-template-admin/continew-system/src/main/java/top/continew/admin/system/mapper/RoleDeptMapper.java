
package top.continew.admin.system.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import top.continew.admin.system.model.entity.RoleDeptDO;
import top.continew.starter.data.mapper.BaseMapper;

import java.util.List;

/**
 * 角色和部门关联 Mapper
 *
 * @author Charles7c
 * @since 2023/2/18 21:57
 */
@Mapper
public interface RoleDeptMapper extends BaseMapper<RoleDeptDO> {

    /**
     * 根据角色 ID 查询
     *
     * @param roleId 角色 ID
     * @return 部门 ID 列表
     */
    @Select("SELECT dept_id FROM sys_role_dept WHERE role_id = #{roleId}")
    List<Long> selectDeptIdByRoleId(@Param("roleId") Long roleId);
}
