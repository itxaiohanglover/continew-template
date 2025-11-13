
package top.continew.admin.system.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 公告日志实体
 *
 * @author Charles7c
 * @since 2025/5/18 19:16
 */
@Data
@NoArgsConstructor
@TableName("sys_notice_log")
public class NoticeLogDO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 公告 ID
     */
    private Long noticeId;

    /**
     * 用户 ID
     */
    private Long userId;

    /**
     * 读取时间
     */
    private LocalDateTime readTime;

    public NoticeLogDO(Long noticeId, Long userId, LocalDateTime readTime) {
        this.noticeId = noticeId;
        this.userId = userId;
        this.readTime = readTime;
    }
}