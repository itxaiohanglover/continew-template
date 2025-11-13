
package top.continew.admin.system.model.query;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Data;
import top.continew.admin.common.enums.DisEnableStatusEnum;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户查询条件
 *
 * @author Charles7c
 * @since 2023/2/20 21:01
 */
@Data
@Schema(description = "用户查询条件")
public class UserQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 关键词
     */
    @Schema(description = "关键词", example = "zhangsan")
    private String description;

    /**
     * 状态
     */
    @Schema(description = "状态", example = "1")
    private DisEnableStatusEnum status;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间", example = "2023-08-08 00:00:00,2023-08-08 23:59:59")
    @Size(max = 2, message = "创建时间必须是一个范围")
    private List<LocalDateTime> createTime;

    /**
     * 部门 ID
     */
    @Schema(description = "部门 ID", example = "1")
    private Long deptId;

    /**
     * 用户 ID 列表
     */
    @Schema(description = "用户 ID 列表", example = "[1,2,3]")
    private List<Long> userIds;

    /**
     * 角色 ID
     * <p>用于在角色授权用户时，过滤掉已经分配给该角色的用户</p>
     */
    @Schema(description = "角色 ID", example = "1")
    private Long roleId;
}
