
package top.continew.admin.system.service;

import java.util.List;

/**
 * 用户历史密码业务接口
 *
 * @author Charles7c
 * @since 2024/5/16 21:58
 */
public interface UserPasswordHistoryService {

    /**
     * 新增
     *
     * @param userId   用户 ID
     * @param password 密码
     * @param count    保留 N 个历史
     */
    void add(Long userId, String password, int count);

    /**
     * 根据用户 ID 删除
     *
     * @param userIds 用户 ID 列表
     */
    void deleteByUserIds(List<Long> userIds);

    /**
     * 密码是否为重复使用
     *
     * @param userId   用户 ID
     * @param password 密码
     * @param count    最近 N 次
     * @return 是否为重复使用
     */
    boolean isPasswordReused(Long userId, String password, int count);
}