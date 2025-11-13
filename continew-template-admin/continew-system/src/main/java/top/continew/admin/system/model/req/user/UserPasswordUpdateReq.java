
package top.continew.admin.system.model.req.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 用户密码修改请求参数
 *
 * @author Charles7c
 * @since 2023/1/9 23:28
 */
@Data
@Schema(description = "用户密码修改请求参数")
public class UserPasswordUpdateReq implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 当前密码（加密）
     */
    @Schema(description = "当前密码（加密）", example = "E7c72TH+LDxKTwavjM99W1MdI9Lljh79aPKiv3XB9MXcplhm7qJ1BJCj28yaflbdVbfc366klMtjLIWQGqb0qw==")
    private String oldPassword;

    /**
     * 新密码（加密）
     */
    @Schema(description = "新密码（加密）", example = "Gzc78825P5baH190lRuZFb9KJxRt/psN2jiyOMPoc5WRcCvneCwqDm3Q33BZY56EzyyVy7vQu7jQwYTK4j1+5w==")
    @NotBlank(message = "新密码不能为空")
    private String newPassword;
}
