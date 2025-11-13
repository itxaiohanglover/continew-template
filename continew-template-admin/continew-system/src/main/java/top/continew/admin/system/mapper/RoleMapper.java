
package top.continew.admin.system.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.continew.admin.system.model.entity.RoleDO;
import top.continew.starter.data.mapper.BaseMapper;

/**
 * 角色 Mapper
 *
 * @author Charles7c
 * @since 2023/2/8 23:17
 */
@Mapper
public interface RoleMapper extends BaseMapper<RoleDO> {
}
