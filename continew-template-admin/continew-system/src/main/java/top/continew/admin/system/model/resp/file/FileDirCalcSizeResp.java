
package top.continew.admin.system.model.resp.file;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * 文件夹计算大小响应参数
 *
 * @author Charles7c
 * @since 2025/5/16 21:32
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "文件夹计算大小响应参数")
public class FileDirCalcSizeResp implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 大小（字节）
     */
    @Schema(description = "大小（字节）", example = "4096")
    private Long size;
}