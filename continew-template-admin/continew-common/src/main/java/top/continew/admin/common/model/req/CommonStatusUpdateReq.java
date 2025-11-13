
package top.continew.admin.common.model.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import top.continew.admin.common.enums.DisEnableStatusEnum;

import java.io.Serializable;

/**
 * 状态修改请求参数
 *
 * @author Charles7c
 * @since 2025/3/4 20:09
 */
@Data
@Schema(description = "状态修改请求参数")
public class CommonStatusUpdateReq implements Serializable {

    /**
     * 状态
     */
    @Schema(description = "状态", example = "1")
    @NotNull(message = "状态无效")
    private DisEnableStatusEnum status;
}
