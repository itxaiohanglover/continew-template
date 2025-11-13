
package top.continew.admin.common.context;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.extra.spring.SpringUtil;
import top.continew.admin.common.api.system.UserApi;
import top.continew.starter.core.util.ExceptionUtils;

/**
 * 用户上下文 Holder
 *
 * @author Charles7c
 * @since 2022/12/24 12:58
 */
public class UserContextHolder {

    private static final ThreadLocal<UserContext> CONTEXT_HOLDER = new ThreadLocal<>();
    private static final ThreadLocal<UserExtraContext> EXTRA_CONTEXT_HOLDER = new ThreadLocal<>();

    private UserContextHolder() {
    }

    /**
     * 设置上下文
     *
     * @param context 上下文
     */
    public static void setContext(UserContext context) {
        setContext(context, true);
    }

    /**
     * 设置上下文
     *
     * @param context  上下文
     * @param isUpdate 是否更新
     */
    public static void setContext(UserContext context, boolean isUpdate) {
        CONTEXT_HOLDER.set(context);
        if (isUpdate) {
            StpUtil.getSessionByLoginId(context.getId()).set(SaSession.USER, context);
        }
    }

    /**
     * 获取上下文
     *
     * @return 上下文
     */
    public static UserContext getContext() {
        UserContext context = CONTEXT_HOLDER.get();
        if (context == null) {
            context = StpUtil.getSession().getModel(SaSession.USER, UserContext.class);
            CONTEXT_HOLDER.set(context);
        }
        return context;
    }

    /**
     * 获取指定用户的上下文
     *
     * @param userId 用户 ID
     * @return 上下文
     */
    public static UserContext getContext(Long userId) {
        SaSession session = StpUtil.getSessionByLoginId(userId, false);
        if (session == null) {
            return null;
        }
        return session.getModel(SaSession.USER, UserContext.class);
    }

    /**
     * 设置额外上下文
     *
     * @param context 额外上下文
     */
    public static void setExtraContext(UserExtraContext context) {
        EXTRA_CONTEXT_HOLDER.set(context);
    }

    /**
     * 获取额外上下文
     *
     * @return 额外上下文
     */
    public static UserExtraContext getExtraContext() {
        UserExtraContext context = EXTRA_CONTEXT_HOLDER.get();
        if (context == null) {
            context = getExtraContext(StpUtil.getTokenValue());
            EXTRA_CONTEXT_HOLDER.set(context);
        }
        return context;
    }

    /**
     * 获取额外上下文
     *
     * @param token 令牌
     * @return 额外上下文
     */
    public static UserExtraContext getExtraContext(String token) {
        UserExtraContext context = new UserExtraContext();
        context.setIp(Convert.toStr(StpUtil.getExtra(token, "ip")));
        context.setAddress(Convert.toStr(StpUtil.getExtra(token, "address")));
        context.setBrowser(Convert.toStr(StpUtil.getExtra(token, "browser")));
        context.setOs(Convert.toStr(StpUtil.getExtra(token, "os")));
        context.setLoginTime(Convert.toLocalDateTime(StpUtil.getExtra(token, "loginTime")));
        return context;
    }

    /**
     * 清除上下文
     */
    public static void clearContext() {
        CONTEXT_HOLDER.remove();
        EXTRA_CONTEXT_HOLDER.remove();
    }

    /**
     * 获取用户 ID
     *
     * @return 用户 ID
     */
    public static Long getUserId() {
        return ExceptionUtils.exToNull(() -> getContext().getId());
    }


    /**
     * 获取用户名
     *
     * @return 用户名
     */
    public static String getUsername() {
        return ExceptionUtils.exToNull(() -> getContext().getUsername());
    }

    /**
     * 获取用户昵称
     *
     * @return 用户昵称
     */
    public static String getNickname() {
        return getNickname(getUserId());
    }

    /**
     * 获取用户昵称
     *
     * @param userId 登录用户 ID
     * @return 用户昵称
     */
    public static String getNickname(Long userId) {
        return ExceptionUtils.exToNull(() -> SpringUtil.getBean(UserApi.class).getNicknameById(userId));
    }

    /**
     * 是否为超级管理员用户
     *
     * @return true：是；false：否
     */
    public static boolean isSuperAdminUser() {
        StpUtil.checkLogin();
        return getContext().isSuperAdminUser();
    }

}
