
package top.continew.admin.system.model.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import top.continew.admin.system.enums.OptionCategoryEnum;
import top.continew.starter.validation.constraints.EnumValue;
import top.continew.starter.data.annotation.Query;
import top.continew.starter.data.enums.QueryType;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 参数查询条件
 *
 * @author Bull-BCLS
 * @since 2023/8/26 19:38
 */
@Data
@Schema(description = "参数查询条件")
public class OptionQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 键列表
     */
    @Schema(description = "键列表", example = "SITE_TITLE,SITE_COPYRIGHT")
    @Query(type = QueryType.IN)
    private List<String> code;

    /**
     * 类别
     */
    @Schema(description = "类别", example = "SITE")
    @EnumValue(value = OptionCategoryEnum.class, message = "类别无效")
    private String category;
}