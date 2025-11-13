
package top.continew.admin.common.config.mybatis;

import top.continew.admin.common.context.UserContext;
import top.continew.admin.common.context.UserContextHolder;
import top.continew.starter.core.util.CollUtils;
import top.continew.starter.extension.datapermission.enums.DataScope;
import top.continew.starter.extension.datapermission.model.RoleData;
import top.continew.starter.extension.datapermission.model.UserData;
import top.continew.starter.extension.datapermission.provider.DataPermissionUserDataProvider;

/**
 * 数据权限用户数据提供者
 *
 * @author Charles7c
 * @since 2023/12/21 21:19
 */
public class DefaultDataPermissionUserDataProvider implements DataPermissionUserDataProvider {

    @Override
    public boolean isFilter() {
        return !UserContextHolder.isSuperAdminUser();
    }

    @Override
    public UserData getUserData() {
        UserContext userContext = UserContextHolder.getContext();
        UserData userData = new UserData();
        userData.setUserId(userContext.getId());
        userData.setDeptId(userContext.getDeptId());
        userData.setRoles(CollUtils.mapToSet(userContext.getRoles(), r -> new RoleData(r.getId(), DataScope.valueOf(r
            .getDataScope()
            .name()))));
        return userData;
    }
}
