
package top.continew.admin.system.model.req.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import top.continew.admin.common.enums.DisEnableStatusEnum;
import top.continew.admin.system.enums.ImportPolicyEnum;

import java.io.Serial;
import java.io.Serializable;

/**
 * 用户导入请求参数
 *
 * @author Kils
 * @since 2024/6/17 16:42
 */
@Data
@Schema(description = "用户导入请求参数")
public class UserImportReq implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 导入会话KEY
     */
    @Schema(description = "导入会话KEY", example = "1b9d6bcd-bbfd-4b2d-9b5d-ab8dfbbd4bed")
    @NotBlank(message = "导入已过期，请重新上传")
    private String importKey;

    /**
     * 用户重复策略
     */
    @Schema(description = "重复用户策略", example = "1")
    @NotNull(message = "重复用户策略不能为空")
    private ImportPolicyEnum duplicateUser;

    /**
     * 重复邮箱策略
     */
    @Schema(description = "重复邮箱策略", example = "1")
    @NotNull(message = "重复邮箱策略不能为空")
    private ImportPolicyEnum duplicateEmail;

    /**
     * 重复手机策略
     */
    @Schema(description = "重复手机策略", example = "1")
    @NotNull(message = "重复手机策略不能为空")
    private ImportPolicyEnum duplicatePhone;

    /**
     * 默认状态
     */
    @Schema(description = "默认状态", example = "1")
    private DisEnableStatusEnum defaultStatus;
}
