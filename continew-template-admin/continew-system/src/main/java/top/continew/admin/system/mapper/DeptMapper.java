
package top.continew.admin.system.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.continew.admin.system.model.entity.DeptDO;
import top.continew.starter.data.mapper.BaseMapper;

/**
 * 部门 Mapper
 *
 * @author Charles7c
 * @since 2023/1/22 17:56
 */
@Mapper
public interface DeptMapper extends BaseMapper<DeptDO> {
}
