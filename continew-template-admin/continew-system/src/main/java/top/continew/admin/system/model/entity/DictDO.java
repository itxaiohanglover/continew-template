
package top.continew.admin.system.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import top.continew.starter.extension.crud.annotation.DictModel;
import top.continew.admin.common.base.model.entity.BaseDO;

import java.io.Serial;

/**
 * 字典实体
 *
 * @author Charles7c
 * @since 2023/9/11 21:29
 */
@Data
@DictModel(valueKey = "code")
@TableName("sys_dict")
public class DictDO extends BaseDO {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    private String name;

    /**
     * 编码
     */
    private String code;

    /**
     * 描述
     */
    private String description;

    /**
     * 是否为系统内置数据
     */
    private Boolean isSystem;
}