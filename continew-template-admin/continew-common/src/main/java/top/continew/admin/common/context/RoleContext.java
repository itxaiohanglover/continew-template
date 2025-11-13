
package top.continew.admin.common.context;

import lombok.Data;
import lombok.NoArgsConstructor;
import top.continew.admin.common.enums.DataScopeEnum;

import java.io.Serial;
import java.io.Serializable;

/**
 * 角色上下文
 *
 * @author Charles7c
 * @since 2023/3/7 22:08
 */
@Data
@NoArgsConstructor
public class RoleContext implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 角色编码
     */
    private String code;

    /**
     * 数据权限
     */
    private DataScopeEnum dataScope;

    public RoleContext(Long id, String code, DataScopeEnum dataScope) {
        this.id = id;
        this.code = code;
        this.dataScope = dataScope;
    }
}
