
package top.continew.admin.system.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * 角色和部门关联实体
 *
 * @author Charles7c
 * @since 2023/2/18 21:57
 */
@Data
@NoArgsConstructor
@TableName("sys_role_dept")
public class RoleDeptDO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 角色 ID
     */
    private Long roleId;

    /**
     * 部门 ID
     */
    private Long deptId;

    public RoleDeptDO(Long roleId, Long deptId) {
        this.roleId = roleId;
        this.deptId = deptId;
    }
}
