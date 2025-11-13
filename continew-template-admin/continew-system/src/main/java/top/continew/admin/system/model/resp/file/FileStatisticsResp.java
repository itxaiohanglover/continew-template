
package top.continew.admin.system.model.resp.file;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import top.continew.admin.system.enums.FileTypeEnum;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 文件资源统计响应参数
 *
 * @author Kils
 * @since 2024/4/30 14:30
 */
@Data
@Schema(description = "文件资源统计响应参数")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FileStatisticsResp implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 文件类型
     */
    @Schema(description = "类型", example = "2")
    private FileTypeEnum type;

    /**
     * 大小（字节）
     */
    @Schema(description = "大小（字节）", example = "4096")
    private Long size;

    /**
     * 数量
     */
    @Schema(description = "数量", example = "1000")
    private Long number;

    /**
     * 分类数据
     */
    @Schema(description = "分类数据")
    private List<FileStatisticsResp> data;
}