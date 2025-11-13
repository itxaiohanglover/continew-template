
package top.continew.admin.system.api;

import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.continew.admin.common.api.system.UserApi;
import top.continew.admin.common.constant.CacheConstants;
import top.continew.admin.system.mapper.user.UserMapper;
import top.continew.admin.system.model.req.user.UserPasswordResetReq;
import top.continew.admin.system.service.UserService;

/**
 * 用户业务 API 实现
 *
 * @author Charles7c
 * @since 2025/7/23 20:57
 */
@Service
@RequiredArgsConstructor
public class UserApiImpl implements UserApi {

    private final UserService baseService;
    private final UserMapper baseMapper;

    @Override
    @Cached(key = "#id", name = CacheConstants.USER_KEY_PREFIX, cacheType = CacheType.BOTH, syncLocal = true)
    public String getNicknameById(Long id) {
        return baseMapper.selectNicknameById(id);
    }

    @Override
    public void resetPassword(String newPassword, Long id) {
        UserPasswordResetReq req = new UserPasswordResetReq();
        req.setNewPassword(newPassword);
        baseService.resetPassword(req, id);
    }
}
