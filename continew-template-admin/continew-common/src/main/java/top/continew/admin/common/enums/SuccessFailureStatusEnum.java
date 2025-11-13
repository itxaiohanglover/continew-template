
package top.continew.admin.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import top.continew.admin.common.constant.UiConstants;
import top.continew.starter.core.enums.BaseEnum;

/**
 * 成功/失败状态枚举
 *
 * @author Charles7c
 * @since 2023/2/26 21:35
 */
@Getter
@RequiredArgsConstructor
public enum SuccessFailureStatusEnum implements BaseEnum<Integer> {

    /**
     * 成功
     */
    SUCCESS(1, "成功", UiConstants.COLOR_SUCCESS),

    /**
     * 失败
     */
    FAILURE(2, "失败", UiConstants.COLOR_ERROR),;

    private final Integer value;
    private final String description;
    private final String color;
}
