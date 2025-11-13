
package top.continew.admin.system.model.resp;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 参数响应参数
 *
 * @author Bull-BCLS
 * @since 2023/8/26 19:38
 */
@Data
@Schema(description = "参数响应参数")
public class OptionResp implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @Schema(description = "ID", example = "1")
    private Long id;

    /**
     * 名称
     */
    @Schema(description = "名称", example = "系统标题")
    private String name;

    /**
     * 键
     */
    @Schema(description = "键", example = "site_title")
    private String code;

    /**
     * 值
     */
    @Schema(description = "值", example = "ContiNew Admin")
    private String value;

    /**
     * 默认值
     */
    @JsonIgnore
    private String defaultValue;

    /**
     * 描述
     */
    @Schema(description = "描述", example = "用于显示登录页面的系统标题。")
    private String description;

    public String getValue() {
        return StrUtil.nullToDefault(value, defaultValue);
    }
}