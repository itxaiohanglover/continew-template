
package top.continew.admin.system.model.resp;

import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import top.continew.admin.common.base.model.resp.BaseDetailResp;
import top.continew.admin.common.enums.DisEnableStatusEnum;
import top.continew.starter.excel.converter.ExcelBaseEnumConverter;

import java.io.Serial;

/**
 * 字典项响应参数
 *
 * @author Charles7c
 * @since 2023/9/11 21:29
 */
@Data
@Schema(description = "字典项响应参数")
public class DictItemResp extends BaseDetailResp {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 标签
     */
    @Schema(description = "标签", example = "通知")
    private String label;

    /**
     * 值
     */
    @Schema(description = "值", example = "1")
    private String value;

    /**
     * 标签颜色
     */
    @Schema(description = "标签颜色", example = "blue")
    private String color;

    /**
     * 状态
     */
    @Schema(description = "状态", example = "1")
    @ExcelProperty(value = "状态", converter = ExcelBaseEnumConverter.class)
    private DisEnableStatusEnum status;

    /**
     * 排序
     */
    @Schema(description = "排序", example = "1")
    private Integer sort;

    /**
     * 描述
     */
    @Schema(description = "描述", example = "通知描述信息")
    private String description;

    /**
     * 字典 ID
     */
    @Schema(description = "字典 ID", example = "1")
    private Long dictId;
}