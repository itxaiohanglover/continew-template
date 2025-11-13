
package top.continew.admin.auth.service;

import jakarta.servlet.http.HttpServletRequest;
import top.continew.admin.auth.model.req.LoginReq;
import top.continew.admin.auth.model.resp.LoginResp;
import top.continew.admin.auth.model.resp.RouteResp;

import java.util.List;

/**
 * 认证业务接口
 *
 * @author Charles7c
 * @since 2022/12/21 21:48
 */
public interface AuthService {

    /**
     * 登录
     *
     * @param req     请求参数
     * @param request 请求对象
     * @return 登录响应参数
     */
    LoginResp login(LoginReq req, HttpServletRequest request);

    /**
     * 构建路由树
     *
     * @param userId 用户 ID
     * @return 路由树
     */
    List<RouteResp> buildRouteTree(Long userId);
}
