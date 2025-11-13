
package top.continew.admin.config.satoken;

import cn.dev33.satoken.stp.StpInterface;
import top.continew.admin.common.context.UserContext;
import top.continew.admin.common.context.UserContextHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Sa-Token 权限认证实现
 *
 * @author Charles7c
 * @since 2023/3/1 22:28
 */
public class SaTokenPermissionImpl implements StpInterface {

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        UserContext userContext = UserContextHolder.getContext();
        return new ArrayList<>(userContext.getPermissions());
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        UserContext userContext = UserContextHolder.getContext();
        return new ArrayList<>(userContext.getRoleCodes());
    }
}
