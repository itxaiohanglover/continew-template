
package top.continew.admin.system.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.continew.admin.system.model.entity.NoticeDO;
import top.continew.admin.system.model.query.NoticeQuery;
import top.continew.admin.system.model.resp.dashboard.DashboardNoticeResp;
import top.continew.admin.system.model.resp.notice.NoticeResp;
import top.continew.starter.data.mapper.BaseMapper;

import java.util.List;

/**
 * 公告 Mapper
 *
 * @author Charles7c
 * @since 2023/8/20 10:55
 */
@Mapper
public interface NoticeMapper extends BaseMapper<NoticeDO> {

    /**
     * 分页查询公告列表
     *
     * @param page  分页条件
     * @param query 查询条件
     * @return 公告列表
     */
    IPage<NoticeResp> selectNoticePage(@Param("page") Page<NoticeDO> page, @Param("query") NoticeQuery query);

    /**
     * 查询未读公告 ID 列表
     *
     * @param noticeMethod 通知方式
     * @param userId       用户 ID
     * @return 未读公告 ID 列表
     */
    List<Long> selectUnreadIdsByUserId(@Param("noticeMethod") Integer noticeMethod, @Param("userId") Long userId);

    /**
     * 查询仪表盘公告列表
     *
     * @param userId 用户 ID
     * @return 仪表盘公告列表
     */
    List<DashboardNoticeResp> selectDashboardList(@Param("userId") Long userId);
}