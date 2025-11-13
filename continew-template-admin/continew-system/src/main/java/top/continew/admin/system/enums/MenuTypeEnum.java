
package top.continew.admin.system.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import top.continew.starter.core.enums.BaseEnum;

/**
 * 菜单类型枚举
 *
 * @author Charles7c
 * @since 2023/2/15 20:12
 */
@Getter
@RequiredArgsConstructor
public enum MenuTypeEnum implements BaseEnum<Integer> {

    /**
     * 目录
     */
    DIR(1, "目录"),

    /**
     * 菜单
     */
    MENU(2, "菜单"),

    /**
     * 按钮
     */
    BUTTON(3, "按钮"),;

    private final Integer value;
    private final String description;
}
