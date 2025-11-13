
package top.continew.admin.common.base.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import top.continew.starter.extension.crud.model.entity.BaseIdDO;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * 实体类基类
 *
 * <p>
 * 通用字段：ID、修改人、修改时间
 * </p>
 *
 * @author Charles7c
 * @since 2025/1/12 23:00
 */
@Data
public class BaseUpdateDO extends BaseIdDO {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 修改人
     */
    @TableField(fill = FieldFill.UPDATE)
    private Long updateUser;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;
}
