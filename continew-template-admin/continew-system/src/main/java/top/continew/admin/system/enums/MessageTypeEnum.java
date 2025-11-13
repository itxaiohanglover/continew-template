
package top.continew.admin.system.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import top.continew.admin.common.constant.UiConstants;
import top.continew.starter.core.enums.BaseEnum;

/**
 * 消息类型枚举
 *
 * @author Charles7c
 * @since 2023/11/2 20:08
 */
@Getter
@RequiredArgsConstructor
public enum MessageTypeEnum implements BaseEnum<Integer> {

    /**
     * 系统消息
     */
    SYSTEM(1, "系统消息", UiConstants.COLOR_PRIMARY),

    /**
     * 安全消息
     */
    SECURITY(2, "安全消息", UiConstants.COLOR_WARNING),;

    private final Integer value;
    private final String description;
    private final String color;
}
