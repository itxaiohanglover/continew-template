
package top.continew.admin.system.model.req.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import top.continew.starter.validation.constraints.Mobile;

import java.io.Serial;
import java.io.Serializable;

/**
 * 用户手机号修改请求参数
 *
 * @author Charles7c
 * @since 2023/10/27 20:11
 */
@Data
@Schema(description = "用户手机号修改请求参数")
public class UserPhoneUpdateReq implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 新手机号
     */
    @Schema(description = "新手机号", example = "13811111111")
    @NotBlank(message = "新手机号不能为空")
    @Mobile(message = "新手机号格式不正确")
    private String phone;

    /**
     * 验证码
     */
    @Schema(description = "验证码", example = "888888")
    @NotBlank(message = "验证码不能为空")
    @Length(max = 6, message = "验证码无效")
    private String captcha;

    /**
     * 当前密码（加密）
     */
    @Schema(description = "当前密码（加密）", example = "SYRLSszQGcMv4kP2Yolou9zf28B9GDakR9u91khxmR7V++i5A384kwnNZxqgvT6bjT4zqpIDuMFLWSt92hQJJA==")
    @NotBlank(message = "当前密码不能为空")
    private String oldPassword;
}
