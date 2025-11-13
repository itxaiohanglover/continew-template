
package top.continew.admin.system.mapper.user;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.continew.admin.system.model.entity.user.UserSocialDO;
import top.continew.starter.data.mapper.BaseMapper;

/**
 * 用户社会化关联 Mapper
 *
 * @author Charles7c
 * @since 2023/10/11 22:10
 */
@Mapper
public interface UserSocialMapper extends BaseMapper<UserSocialDO> {

    /**
     * 根据来源和开放 ID 查询
     *
     * @param source 来源
     * @param openId 开放 ID
     * @return 用户社会化关联信息
     */
    UserSocialDO selectBySourceAndOpenId(@Param("source") String source, @Param("openId") String openId);
}
