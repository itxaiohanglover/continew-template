
package top.continew.admin.system.service;

import top.continew.admin.system.model.entity.user.UserSocialDO;

import java.util.List;

/**
 * 用户社会化关联业务接口
 *
 * @author Charles7c
 * @since 2023/10/11 22:10
 */
public interface UserSocialService {

    /**
     * 根据来源和开放 ID 查询
     *
     * @param source 来源
     * @param openId 开放 ID
     * @return 用户社会化关联信息
     */
    UserSocialDO getBySourceAndOpenId(String source, String openId);

    /**
     * 保存
     *
     * @param userSocial 用户社会化关联信息
     */
    void saveOrUpdate(UserSocialDO userSocial);

    /**
     * 根据用户 ID 查询
     *
     * @param userId 用户 ID
     * @return 用户社会化关联信息
     */
    List<UserSocialDO> listByUserId(Long userId);


    /**
     * 根据用户 ID 删除
     *
     * @param userIds 用户 ID 列表
     */
    void deleteByUserIds(List<Long> userIds);
}