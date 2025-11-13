
package top.continew.admin.system.model.resp.message;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 未读消息响应参数
 *
 * @author Charles7c
 * @since 2023/11/2 23:00
 */
@Data
@Schema(description = "未读消息响应参数")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MessageUnreadResp implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 未读消息数量
     */
    @Schema(description = "未读消息数量", example = "20")
    private Long total;

    /**
     * 各类型未读消息数量
     */
    @Schema(description = "各类型未读消息数量")
    private List<MessageTypeUnreadResp> details;
}