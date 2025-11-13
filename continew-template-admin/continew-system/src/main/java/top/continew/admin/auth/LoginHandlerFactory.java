
package top.continew.admin.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.continew.admin.auth.enums.AuthTypeEnum;
import top.continew.admin.auth.model.req.LoginReq;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * 登录处理器工厂
 *
 * @author KAI
 * @author Charles7c
 * @since 2024/12/20 15:16
 */
@Component
public class LoginHandlerFactory {

    private final Map<AuthTypeEnum, LoginHandler<? extends LoginReq>> handlerMap = new EnumMap<>(AuthTypeEnum.class);

    @Autowired
    public LoginHandlerFactory(List<LoginHandler<? extends LoginReq>> handlers) {
        for (LoginHandler<? extends LoginReq> handler : handlers) {
            handlerMap.put(handler.getAuthType(), handler);
        }
    }

    /**
     * 根据认证类型获取
     *
     * @param authType 认证类型
     * @return 认证处理器
     */
    public LoginHandler<LoginReq> getHandler(AuthTypeEnum authType) {
        return (LoginHandler<LoginReq>)handlerMap.get(authType);
    }
}
