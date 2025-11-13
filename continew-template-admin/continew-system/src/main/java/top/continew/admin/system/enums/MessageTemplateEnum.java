
package top.continew.admin.system.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 消息模板枚举
 *
 * @author Bull-BCLS
 * @since 2023/10/15 19:51
 */
@Getter
@RequiredArgsConstructor
public enum MessageTemplateEnum {

    /**
     * 第三方登录
     */
    SOCIAL_REGISTER("欢迎注册 %s", "尊敬的 %s，欢迎注册使用，请及时配置您的密码。", "/user/profile"),

    /**
     * 公告发布
     */
    NOTICE_PUBLISH("您有一条新的公告", "公告《%s》已发布，请及时查看。", "/user/notice?id=%s");

    private final String title;
    private final String content;
    private final String path;
}
