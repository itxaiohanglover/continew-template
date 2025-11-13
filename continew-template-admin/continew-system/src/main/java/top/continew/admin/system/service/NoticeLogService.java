
package top.continew.admin.system.service;

import java.util.List;

/**
 * 公告日志业务接口
 *
 * @author Charles7c
 * @since 2025/5/18 19:12
 */
public interface NoticeLogService {

    /**
     * 新增
     *
     * @param userIds  用户 ID 列表
     * @param noticeId 公告 ID
     * @return 是否新增成功（true：成功；false：无变更/失败）
     */
    boolean add(List<Long> userIds, Long noticeId);

    /**
     * 根据公告 ID 列表删除
     *
     * @param noticeIds 公告 ID 列表
     */
    void deleteByNoticeIds(List<Long> noticeIds);

    /**
     * 根据公告 ID 查询用户 ID 列表
     *
     * @param noticeId 公告 ID
     * @return 用户 ID 列表
     */
    List<Long> listUserIdByNoticeId(Long noticeId);
}