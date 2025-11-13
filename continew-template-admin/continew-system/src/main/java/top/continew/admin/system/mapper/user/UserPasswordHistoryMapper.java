
package top.continew.admin.system.mapper.user;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.continew.admin.system.model.entity.user.UserPasswordHistoryDO;
import top.continew.starter.data.mapper.BaseMapper;

/**
 * 用户历史密码 Mapper
 *
 * @author Charles7c
 * @since 2024/5/16 21:58
 */
@Mapper
public interface UserPasswordHistoryMapper extends BaseMapper<UserPasswordHistoryDO> {

    /**
     * 删除过期历史密码
     *
     * @param userId 用户 ID
     * @param count  保留 N 个历史
     */
    void deleteExpired(@Param("userId") Long userId, @Param("count") int count);
}