
package top.continew.admin.system.enums;

import cn.hutool.core.collection.CollUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import top.continew.starter.core.enums.BaseEnum;

import java.util.List;

/**
 * 数据导入策略
 *
 * @author Kils
 * @since 2024/6/17 18:33
 */
@Getter
@RequiredArgsConstructor
public enum ImportPolicyEnum implements BaseEnum<Integer> {

    /**
     * 跳过该行
     */
    SKIP(1, "跳过该行"),

    /**
     * 修改数据
     */
    UPDATE(2, "修改数据"),

    /**
     * 停止导入
     */
    EXIT(3, "停止导入");

    private final Integer value;
    private final String description;

    public boolean validate(ImportPolicyEnum importPolicy, String data, List<String> existList) {
        return this == importPolicy && CollUtil.isNotEmpty(existList) && existList.contains(data);
    }
}
