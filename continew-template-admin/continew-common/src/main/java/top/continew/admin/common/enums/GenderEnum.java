
package top.continew.admin.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import top.continew.starter.core.enums.BaseEnum;

/**
 * 性别枚举
 *
 * @author Charles7c
 * @since 2022/12/29 21:59
 */
@Getter
@RequiredArgsConstructor
public enum GenderEnum implements BaseEnum<Integer> {

    /**
     * 未知
     */
    UNKNOWN(0, "未知"),

    /**
     * 男
     */
    MALE(1, "男"),

    /**
     * 女
     */
    FEMALE(2, "女"),;

    private final Integer value;
    private final String description;
}
