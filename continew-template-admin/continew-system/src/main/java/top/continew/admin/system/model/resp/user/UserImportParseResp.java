
package top.continew.admin.system.model.resp.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * 用户导入解析响应参数
 *
 * @author kils
 * @since 2024/6/18 14:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "用户导入解析响应参数")
public class UserImportParseResp implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 导入会话 Key
     */
    @Schema(description = "导入会话Key", example = "1b9d6bcd-bbfd-4b2d-9b5d-ab8dfbbd4bed")
    private String importKey;

    /**
     * 总计行数
     */
    @Schema(description = "总计行数", example = "100")
    private Integer totalRows;

    /**
     * 有效行数
     */
    @Schema(description = "有效行数", example = "100")
    private Integer validRows;

    /**
     * 重复行数
     */
    @Schema(description = "重复行数", example = "100")
    private Integer duplicateUserRows;

    /**
     * 重复邮箱行数
     */
    @Schema(description = "重复邮箱行数", example = "100")
    private Integer duplicateEmailRows;

    /**
     * 重复手机行数
     */
    @Schema(description = "重复手机行数", example = "100")
    private Integer duplicatePhoneRows;
}
