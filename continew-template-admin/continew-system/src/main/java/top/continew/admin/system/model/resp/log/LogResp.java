
package top.continew.admin.system.model.resp.log;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import top.continew.admin.system.enums.LogStatusEnum;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 日志响应参数
 *
 * @author Charles7c
 * @since 2023/1/14 18:27
 */
@Data
@Schema(description = "日志响应参数")
public class LogResp implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @Schema(description = "ID", example = "1")
    private Long id;

    /**
     * 日志描述
     */
    @Schema(description = "日志描述", example = "新增数据")
    private String description;

    /**
     * 所属模块
     */
    @Schema(description = "所属模块", example = "部门管理")
    private String module;

    /**
     * 耗时（ms）
     */
    @Schema(description = "耗时（ms）", example = "58")
    private Long timeTaken;

    /**
     * IP
     */
    @Schema(description = "IP", example = "")
    private String ip;

    /**
     * IP 归属地
     */
    @Schema(description = "IP 归属地", example = "中国北京北京市")
    private String address;

    /**
     * 浏览器
     */
    @Schema(description = "浏览器", example = "Chrome 115.0.0.0")
    private String browser;

    /**
     * 操作系统
     */
    @Schema(description = "操作系统", example = "Windows 10")
    private String os;

    /**
     * 状态
     */
    @Schema(description = "状态", example = "1")
    private LogStatusEnum status;

    /**
     * 错误信息
     */
    @Schema(description = "错误信息")
    private String errorMsg;

    /**
     * 创建人
     */
    @JsonIgnore
    private Long createUser;

    /**
     * 创建人
     */
    @Schema(description = "创建人", example = "张三")
    private String createUserString;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间", example = "2023-08-08 08:08:08", type = "string")
    private LocalDateTime createTime;
}
