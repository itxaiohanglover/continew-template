
package top.continew.admin.system.model.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serial;
import java.io.Serializable;

/**
 * 文件修改请求参数
 *
 * @author Charles7c
 * @since 2023/12/23 10:38
 */
@Data
@Schema(description = "文件修改请求参数")
public class FileReq implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    @Schema(description = "名称", example = "example")
    @NotBlank(message = "名称不能为空")
    @Length(max = 255, message = "名称长度不能超过 {max} 个字符")
    private String originalName;

    /**
     * 上级目录
     */
    @Schema(description = "上级目录", example = "/")
    private String parentPath;
}