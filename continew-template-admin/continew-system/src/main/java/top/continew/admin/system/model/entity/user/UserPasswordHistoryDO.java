
package top.continew.admin.system.model.entity.user;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户历史密码实体
 *
 * @author Charles7c
 * @since 2024/5/16 21:58
 */
@Data
@NoArgsConstructor
@TableName("sys_user_password_history")
public class UserPasswordHistoryDO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 用户 ID
     */
    private Long userId;

    /**
     * 密码
     */
    private String password;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    public UserPasswordHistoryDO(Long userId, String password) {
        this.userId = userId;
        this.password = password;
    }
}