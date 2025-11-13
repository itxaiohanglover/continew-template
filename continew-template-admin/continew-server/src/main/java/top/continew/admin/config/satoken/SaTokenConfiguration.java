
package top.continew.admin.config.satoken;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.context.model.SaRequest;
import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.sign.SaSignManager;
import cn.dev33.satoken.sign.template.SaSignTemplate;
import cn.dev33.satoken.sign.template.SaSignUtil;
import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.AnnotationUtils;
import top.continew.admin.common.config.crud.CrudApiPermissionPrefixCache;
import top.continew.admin.common.context.UserContext;
import top.continew.admin.common.context.UserContextHolder;
import top.continew.starter.auth.satoken.autoconfigure.SaTokenExtensionProperties;
import top.continew.starter.core.constant.StringConstants;
import top.continew.starter.core.exception.BusinessException;
import top.continew.starter.core.util.validation.CheckUtils;
import top.continew.starter.extension.crud.annotation.CrudRequestMapping;

import java.util.*;

/**
 * Sa-Token 配置
 *
 * @author Charles7c
 * @author chengzi
 * @since 2022/12/19 22:13
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class SaTokenConfiguration {

    private final SaTokenExtensionProperties properties;
    private final LoginPasswordProperties loginPasswordProperties;
    private final ApplicationContext applicationContext;

    /**
     * Sa-Token 权限认证配置
     */
    @Bean
    public StpInterface stpInterface() {
        return new SaTokenPermissionImpl();
    }

    /**
     * SaToken 拦截器配置
     */
    @Bean
    public SaInterceptor saInterceptor() {
        return new SaExtensionInterceptor(handle -> SaRouter.match(StringConstants.PATH_PATTERN)
            .notMatch(properties.getSecurity().getExcludes())
            .check(r -> {
                // 如果包含 sign，进行 API 接口参数签名验证
                SaRequest saRequest = SaHolder.getRequest();
                Collection<String> paramNames = saRequest.getParamNames();
                if (paramNames.stream().anyMatch(SaSignTemplate.sign::equals)) {
                    try {
                        SaSignUtil.checkRequest(saRequest);
                    } catch (Exception e) {
                        throw new BusinessException(e.getMessage());
                    }
                    return;
                }
                // 不包含 sign 参数，进行普通登录验证
                StpUtil.checkLogin();
                if (SaRouter.isMatchCurrURI(loginPasswordProperties.getExcludes())) {
                    return;
                }
                UserContext userContext = UserContextHolder.getContext();
                CheckUtils.throwIf(userContext.isPasswordExpired(), "密码已过期，请修改密码");
            }));
    }

    /**
     * 配置 sa-token {@link SaIgnore} 注解排除路径
     * <p>主要针对 @CrudRequestMapping 注解</p>
     */
    @EventListener(ApplicationReadyEvent.class)
    public void configureSaTokenExcludes() {
        String[] beanNames = applicationContext.getBeanDefinitionNames();
        List<String> additionalExcludes = Arrays.stream(beanNames).parallel().map(beanName -> {
            Object bean = applicationContext.getBean(beanName);
            Class<?> clazz = bean.getClass();
            if (AopUtils.isAopProxy(bean)) {
                clazz = AopProxyUtils.ultimateTargetClass(bean);
            }
            CrudRequestMapping crudRequestMapping = AnnotationUtils.findAnnotation(clazz, CrudRequestMapping.class);
            SaIgnore saIgnore = AnnotationUtils.findAnnotation(clazz, SaIgnore.class);
            if (crudRequestMapping != null) {
                // 缓存权限前缀
                CrudApiPermissionPrefixCache.put(clazz, crudRequestMapping.value());
                // 使用 @CrudRequestMapping 的 Controller，如果使用了 @SaIgnore 注解，则表示忽略认证和权限校验
                if (saIgnore != null) {
                    return crudRequestMapping.value() + StringConstants.PATH_PATTERN;
                }
            }
            return null;
        }).filter(Objects::nonNull).toList();
        if (!additionalExcludes.isEmpty()) {
            // 合并现有的 excludes 和新扫描到的
            List<String> allExcludes = new ArrayList<>(Arrays.asList(properties.getSecurity().getExcludes()));
            allExcludes.addAll(additionalExcludes);
            // 转回数组
            properties.getSecurity().setExcludes(allExcludes.toArray(new String[0]));
        }
        log.debug("缓存 CRUD API 权限前缀完成：{}", CrudApiPermissionPrefixCache.getAll().values());
    }
}
