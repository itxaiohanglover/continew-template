
package top.continew.admin.system.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import top.continew.starter.core.enums.BaseEnum;

/**
 * 公告通知范围枚举
 *
 * @author Charles7c
 * @since 2023/8/20 10:55
 */
@Getter
@RequiredArgsConstructor
public enum NoticeScopeEnum implements BaseEnum<Integer> {

    /**
     * 所有人
     */
    ALL(1, "所有人"),

    /**
     * 指定用户
     */
    USER(2, "指定用户"),;

    private final Integer value;
    private final String description;
}
