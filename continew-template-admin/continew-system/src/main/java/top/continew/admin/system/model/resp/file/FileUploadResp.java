
package top.continew.admin.system.model.resp.file;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;

/**
 * 文件上传响应参数
 *
 * @author Charles7c
 * @since 2024/3/6 22:26
 */
@Data
@Builder
@Schema(description = "文件上传响应参数")
public class FileUploadResp implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 文件 id
     */
    @Schema(description = "文件 id", example = "1897293810343682049")
    private String id;

    /**
     * 文件 URL
     */
    @Schema(description = "文件 URL", example = "http://localhost:8000/file/65e87dc3fb377a6fb58bdece.jpg")
    private String url;

    /**
     * 缩略图文件 URL
     */
    @Schema(description = "缩略图文件 URL", example = "http://localhost:8000/file/65e87dc3fb377a6fb58bdece.jpg")
    private String thUrl;

    /**
     * 元数据
     */
    @Schema(description = "元数据")
    private Map<String, String> metadata;
}
