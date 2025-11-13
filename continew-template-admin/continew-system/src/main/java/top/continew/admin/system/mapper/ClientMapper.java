
package top.continew.admin.system.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.continew.admin.system.model.entity.ClientDO;
import top.continew.starter.data.mapper.BaseMapper;

/**
 * 客户端 Mapper
 *
 * @author KAI
 * @since 2024/12/03 16:04
 */
@Mapper
public interface ClientMapper extends BaseMapper<ClientDO> {
}