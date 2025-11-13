
package top.continew.admin.system.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import top.continew.admin.common.base.model.entity.BaseUpdateDO;

import java.io.Serial;

/**
 * 参数实体
 *
 * @author Bull-BCLS
 * @since 2023/8/26 19:20
 */
@Data
@TableName("sys_option")
public class OptionDO extends BaseUpdateDO {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 类别
     */
    private String category;

    /**
     * 名称
     */
    private String name;

    /**
     * 键
     */
    private String code;

    /**
     * 值
     */
    private String value;

    /**
     * 默认值
     */
    private String defaultValue;

    /**
     * 描述
     */
    private String description;
}
