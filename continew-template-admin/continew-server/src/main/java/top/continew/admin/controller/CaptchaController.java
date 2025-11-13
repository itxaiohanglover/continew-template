
package top.continew.admin.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.JakartaServletUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.anji.captcha.model.common.RepCodeEnum;
import com.anji.captcha.model.common.ResponseModel;
import com.anji.captcha.model.vo.CaptchaVO;
import com.anji.captcha.service.CaptchaService;
import com.wf.captcha.base.Captcha;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.continew.admin.auth.model.resp.CaptchaResp;
import top.continew.admin.common.config.CaptchaProperties;
import top.continew.admin.common.constant.CacheConstants;
import top.continew.admin.common.constant.GlobalConstants;
import top.continew.admin.system.service.OptionService;
import top.continew.starter.cache.redisson.util.RedisUtils;
import top.continew.starter.captcha.graphic.core.GraphicCaptchaService;
import top.continew.starter.core.autoconfigure.application.ApplicationProperties;
import top.continew.starter.core.util.validation.CheckUtils;
import top.continew.starter.log.annotation.Log;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * 验证码 API
 *
 * @author Charles7c
 * @since 2022/12/11 14:00
 */
@Tag(name = "验证码 API")
@SaIgnore
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/captcha")
public class CaptchaController {

    private final ApplicationProperties applicationProperties;
    private final CaptchaProperties captchaProperties;
    private final GraphicCaptchaService graphicCaptchaService;
    private final OptionService optionService;


    @Log(ignore = true)
    @Operation(summary = "获取行为验证码", description = "获取行为验证码（Base64编码）")
    @GetMapping("/behavior")
    public Object getBehaviorCaptcha(CaptchaVO captchaReq, HttpServletRequest request) {
        CaptchaService behaviorCaptchaService = SpringUtil.getBean(CaptchaService.class);
        captchaReq.setBrowserInfo(JakartaServletUtil.getClientIP(request) + request.getHeader(HttpHeaders.USER_AGENT));
        ResponseModel responseModel = behaviorCaptchaService.get(captchaReq);
        CheckUtils.throwIf(() -> !StrUtil.equals(RepCodeEnum.SUCCESS.getCode(), responseModel
            .getRepCode()), responseModel.getRepMsg());
        return responseModel.getRepData();
    }

    @Log(ignore = true)
    @Operation(summary = "校验行为验证码", description = "校验行为验证码")
    @PostMapping("/behavior")
    public Object checkBehaviorCaptcha(@RequestBody CaptchaVO captchaReq, HttpServletRequest request) {
        CaptchaService behaviorCaptchaService = SpringUtil.getBean(CaptchaService.class);
        captchaReq.setBrowserInfo(JakartaServletUtil.getClientIP(request) + request.getHeader(HttpHeaders.USER_AGENT));
        return behaviorCaptchaService.check(captchaReq);
    }

    @Log(ignore = true)
    @Operation(summary = "获取图片验证码", description = "获取图片验证码（Base64编码，带图片格式：data:image/gif;base64）")
    @GetMapping("/image")
    public CaptchaResp getImageCaptcha() {
        int loginCaptchaEnabled = optionService.getValueByCode2Int("LOGIN_CAPTCHA_ENABLED");
        if (GlobalConstants.Boolean.NO.equals(loginCaptchaEnabled)) {
            return CaptchaResp.builder().isEnabled(false).build();
        }
        String uuid = IdUtil.fastUUID();
        String captchaKey = CacheConstants.CAPTCHA_KEY_PREFIX + uuid;
        Captcha captcha = graphicCaptchaService.getCaptcha();
        long expireTime = LocalDateTimeUtil.toEpochMilli(LocalDateTime.now()
            .plusMinutes(captchaProperties.getExpirationInMinutes()));
        RedisUtils.set(captchaKey, captcha.text(), Duration.ofMinutes(captchaProperties.getExpirationInMinutes()));
        return CaptchaResp.of(uuid, captcha.toBase64(), expireTime);
    }


}
