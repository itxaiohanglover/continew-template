
package top.continew.admin.common.context;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.JakartaServletUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.continew.starter.core.util.ExceptionUtils;
import top.continew.starter.core.util.IpUtils;
import top.continew.starter.core.util.ServletUtils;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户额外上下文
 *
 * @author Charles7c
 * @since 2024/10/9 20:29
 */
@Data
@NoArgsConstructor
public class UserExtraContext implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * IP
     */
    private String ip;

    /**
     * IP 归属地
     */
    private String address;

    /**
     * 浏览器
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 登录时间
     */
    private LocalDateTime loginTime;

    public UserExtraContext(HttpServletRequest request) {
        this.ip = JakartaServletUtil.getClientIP(request);
        this.address = ExceptionUtils.exToNull(() -> IpUtils.getIpv4Address(this.ip));
        this.setBrowser(ServletUtils.getBrowser(request));
        this.setLoginTime(LocalDateTime.now());
        this.setOs(StrUtil.subBefore(ServletUtils.getOs(request), " or", false));
    }
}
