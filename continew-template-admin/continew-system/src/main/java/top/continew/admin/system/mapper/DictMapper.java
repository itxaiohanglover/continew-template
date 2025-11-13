
package top.continew.admin.system.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.continew.admin.system.model.entity.DictDO;
import top.continew.starter.data.mapper.BaseMapper;

/**
 * 字典 Mapper
 *
 * @author Charles7c
 * @since 2023/9/11 21:29
 */
@Mapper
public interface DictMapper extends BaseMapper<DictDO> {
}