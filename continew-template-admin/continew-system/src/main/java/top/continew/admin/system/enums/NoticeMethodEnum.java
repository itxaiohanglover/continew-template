
package top.continew.admin.system.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import top.continew.starter.core.enums.BaseEnum;

/**
 * 公告通知方式枚举
 *
 * @author Charles7c
 * @since 2025/5/8 21:18
 */
@Getter
@RequiredArgsConstructor
public enum NoticeMethodEnum implements BaseEnum<Integer> {

    /**
     * 系统消息
     */
    SYSTEM_MESSAGE(1, "系统消息"),

    /**
     * 登录弹窗
     */
    POPUP(2, "登录弹窗"),;

    private final Integer value;
    private final String description;
}
