
package top.continew.admin.system.model.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 消息查询条件
 *
 * @author Bull-BCLS
 * @since 2023/10/15 19:05
 */
@Data
@Schema(description = "消息查询条件")
public class MessageQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @Schema(description = "ID", example = "1")
    private Long id;

    /**
     * 标题
     */
    @Schema(description = "标题", example = "欢迎注册 xxx")
    private String title;

    /**
     * 类型
     */
    @Schema(description = "类型", example = "1")
    private Integer type;

    /**
     * 是否已读
     */
    @Schema(description = "是否已读", example = "true")
    private Boolean isRead;

    /**
     * 用户 ID
     */
    @Schema(hidden = true)
    private Long userId;
}