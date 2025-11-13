
package top.continew.admin.system.model.resp.notice;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * 未读公告数量响应参数
 *
 * @author Charles7c
 * @since 2025/5/22 22:15
 */
@Data
@NoArgsConstructor
@Schema(description = "未读公告数量响应参数")
public class NoticeUnreadCountResp implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 未读公告数量
     */
    @Schema(description = "未读公告数量", example = "1")
    private Integer total;

    public NoticeUnreadCountResp(Integer total) {
        this.total = total;
    }
}