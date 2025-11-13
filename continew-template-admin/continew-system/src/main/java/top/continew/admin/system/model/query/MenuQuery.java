
package top.continew.admin.system.model.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.continew.admin.common.enums.DisEnableStatusEnum;
import top.continew.starter.data.annotation.Query;
import top.continew.starter.data.enums.QueryType;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 菜单查询条件
 *
 * @author Charles7c
 * @since 2023/2/15 20:21
 */
@Data
@NoArgsConstructor
@Schema(description = "菜单查询条件")
public class MenuQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 标题
     */
    @Schema(description = "标题", example = "用户管理")
    @Query(type = QueryType.LIKE)
    private String title;

    /**
     * 状态
     */
    @Schema(description = "状态", example = "1")
    private DisEnableStatusEnum status;

    public MenuQuery(DisEnableStatusEnum status) {
        this.status = status;
    }

    /**
     * 排除的菜单 ID 列表
     */
    @Schema(hidden = true, description = "排除的菜单 ID 列表", example = "[9000]")
    @Query(columns = "id", type = QueryType.NOT_IN)
    private List<Long> excludeMenuIdList;
}
