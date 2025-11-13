
package top.continew.admin.system.model.req.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import top.continew.admin.common.constant.RegexConstants;
import top.continew.admin.common.enums.GenderEnum;

import java.io.Serial;
import java.io.Serializable;

/**
 * 用户基础信息修改请求参数
 *
 * @author Charles7c
 * @since 2023/1/7 23:08
 */
@Data
@Schema(description = "用户基础信息修改请求参数")
public class UserBasicInfoUpdateReq implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 昵称
     */
    @Schema(description = "昵称", example = "张三")
    @NotBlank(message = "昵称不能为空")
    @Pattern(regexp = RegexConstants.GENERAL_NAME, message = "昵称长度为 2-30 个字符，支持中文、字母、数字、下划线，短横线")
    private String nickname;

    /**
     * 性别
     */
    @Schema(description = "性别", example = "1")
    @NotNull(message = "性别无效")
    private GenderEnum gender;
}
