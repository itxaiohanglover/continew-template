
package top.continew.admin.system.service;

import top.continew.admin.common.base.service.BaseService;
import top.continew.admin.system.enums.NoticeMethodEnum;
import top.continew.admin.system.model.entity.NoticeDO;
import top.continew.admin.system.model.query.NoticeQuery;
import top.continew.admin.system.model.req.NoticeReq;
import top.continew.admin.system.model.resp.dashboard.DashboardNoticeResp;
import top.continew.admin.system.model.resp.notice.NoticeDetailResp;
import top.continew.admin.system.model.resp.notice.NoticeResp;
import top.continew.starter.data.service.IService;

import java.util.List;

/**
 * 公告业务接口
 *
 * @author Charles7c
 * @since 2023/8/20 10:55
 */
public interface NoticeService extends BaseService<NoticeResp, NoticeDetailResp, NoticeQuery, NoticeReq>, IService<NoticeDO> {

    /**
     * 发布公告
     *
     * @param notice 公告信息
     */
    void publish(NoticeDO notice);

    /**
     * 查询未读公告 ID 列表
     *
     * @param method 通知方式
     * @param userId 用户 ID
     * @return 未读公告 ID 响应参数
     */
    List<Long> listUnreadIdsByUserId(NoticeMethodEnum method, Long userId);

    /**
     * 阅读公告
     *
     * @param id     公告 ID
     * @param userId 用户 ID
     */
    void readNotice(Long id, Long userId);

    /**
     * 查询仪表盘公告列表
     *
     * @return 仪表盘公告列表
     */
    List<DashboardNoticeResp> listDashboard();
}