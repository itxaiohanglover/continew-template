
package top.continew.admin.system.model.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 角色关联用户查询条件
 *
 * @author Charles7c
 * @since 2025/2/5 22:01
 */
@Data
@Schema(description = "角色关联用户查询条件")
public class RoleUserQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 角色 ID
     */
    @Schema(description = "角色 ID", example = "1")
    private Long roleId;

    /**
     * 关键词
     */
    @Schema(description = "关键词", example = "zhangsan")
    private String description;
}
