
package top.continew.admin.system.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.continew.admin.system.model.entity.StorageDO;
import top.continew.starter.data.mapper.BaseMapper;

/**
 * 存储 Mapper
 *
 * @author Charles7c
 * @since 2023/12/26 22:09
 */
@Mapper
public interface StorageMapper extends BaseMapper<StorageDO> {
}