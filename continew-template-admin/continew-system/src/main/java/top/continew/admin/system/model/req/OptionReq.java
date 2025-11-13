
package top.continew.admin.system.model.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serial;
import java.io.Serializable;

/**
 * 参数修改请求参数
 *
 * @author Bull-BCLS
 * @since 2023/8/26 19:38
 */
@Data
@Schema(description = "参数修改请求参数")
public class OptionReq implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @Schema(description = "ID", example = "1")
    @NotNull(message = "ID不能为空")
    private Long id;

    /**
     * 键
     */
    @Schema(description = "键", example = "site_title")
    @NotBlank(message = "键不能为空")
    @Length(max = 100, message = "键长度不能超过 {max} 个字符")
    private String code;

    /**
     * 值
     */
    @Schema(description = "值", example = "ContiNew Admin")
    private String value;
}