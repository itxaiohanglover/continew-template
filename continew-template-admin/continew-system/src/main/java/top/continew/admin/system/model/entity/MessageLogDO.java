
package top.continew.admin.system.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 消息日志实体
 *
 * @author Charles7c
 * @author Bull-BCLS
 * @since 2023/10/15 20:25
 */
@Data
@NoArgsConstructor
@TableName("sys_message_log")
public class MessageLogDO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 消息 ID
     */
    private Long messageId;

    /**
     * 用户 ID
     */
    private Long userId;

    /**
     * 读取时间
     */
    private LocalDateTime readTime;

    public MessageLogDO(Long messageId, Long userId, LocalDateTime readTime) {
        this.messageId = messageId;
        this.userId = userId;
        this.readTime = readTime;
    }
}