
package top.continew.admin.system.model.resp.role;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import top.continew.admin.system.enums.MenuTypeEnum;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 角色权限树响应参数
 *
 * @author Charles7c
 * @since 2025/7/27 10:57
 */
@Data
@Schema(description = "角色权限树响应参数")
public class RolePermissionResp implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @Schema(description = "ID", example = "1")
    private Long id;

    /**
     * 标题
     */
    @Schema(description = "标题", example = "用户管理")
    private String title;

    /**
     * 上级菜单 ID
     */
    @Schema(description = "上级菜单 ID", example = "1000")
    private Long parentId;

    /**
     * 类型
     */
    @Schema(description = "类型", example = "2")
    private MenuTypeEnum type;

    /**
     * 权限标识
     */
    @Schema(description = "权限标识", example = "system:user:list")
    private String permission;

    /**
     * 子菜单列表
     */
    @Schema(description = "子菜单")
    private List<RolePermissionResp> children;
}
