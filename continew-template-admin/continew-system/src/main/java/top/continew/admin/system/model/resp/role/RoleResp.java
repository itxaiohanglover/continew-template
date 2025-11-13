
package top.continew.admin.system.model.resp.role;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import top.continew.admin.common.base.model.resp.BaseDetailResp;
import top.continew.admin.common.enums.DataScopeEnum;

import java.io.Serial;

/**
 * 角色响应参数
 *
 * @author Charles7c
 * @since 2023/2/8 23:05
 */
@Data
@Schema(description = "角色响应参数")
public class RoleResp extends BaseDetailResp {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    @Schema(description = "名称", example = "测试人员")
    private String name;

    /**
     * 编码
     */
    @Schema(description = "编码", example = "test")
    private String code;

    /**
     * 数据权限
     */
    @Schema(description = "数据权限", example = "5")
    private DataScopeEnum dataScope;

    /**
     * 排序
     */
    @Schema(description = "排序", example = "1")
    private Integer sort;

    /**
     * 是否为系统内置数据
     */
    @Schema(description = "是否为系统内置数据", example = "false")
    private Boolean isSystem;

    /**
     * 描述
     */
    @Schema(description = "描述", example = "测试人员描述信息")
    private String description;
}
