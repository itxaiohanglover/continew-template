
package top.continew.admin.system.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.continew.admin.system.model.entity.MessageLogDO;
import top.continew.starter.data.mapper.BaseMapper;

/**
 * 消息日志 Mapper
 *
 * @author Bull-BCLS
 * @author Charles7c
 * @since 2023/10/15 20:25
 */
@Mapper
public interface MessageLogMapper extends BaseMapper<MessageLogDO> {
}