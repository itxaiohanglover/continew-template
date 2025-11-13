
package top.continew.admin.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.continew.admin.common.constant.CacheConstants;
import top.continew.admin.common.context.UserContextHolder;
import top.continew.admin.common.util.SecureUtils;
import top.continew.admin.system.enums.SocialSourceEnum;
import top.continew.admin.system.model.entity.user.UserSocialDO;
import top.continew.admin.system.model.req.user.UserBasicInfoUpdateReq;
import top.continew.admin.system.model.req.user.UserEmailUpdateReq;
import top.continew.admin.system.model.req.user.UserPasswordUpdateReq;
import top.continew.admin.system.model.req.user.UserPhoneUpdateReq;
import top.continew.admin.system.model.resp.AvatarResp;
import top.continew.admin.system.model.resp.user.UserSocialBindResp;
import top.continew.admin.system.service.UserService;
import top.continew.admin.system.service.UserSocialService;
import top.continew.starter.cache.redisson.util.RedisUtils;
import top.continew.starter.core.util.CollUtils;
import top.continew.starter.core.util.validation.ValidationUtils;

import java.io.IOException;
import java.util.List;

/**
 * 个人信息 API
 *
 * @author Charles7c
 * @since 2023/1/2 11:41
 */
@Tag(name = "个人信息 API")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/user/profile")
public class UserProfileController {

    private static final String DECRYPT_FAILED = "当前密码解密失败";
    private static final String CAPTCHA_EXPIRED = "验证码已失效";
    private final UserService userService;

    @Operation(summary = "修改头像", description = "用户修改个人头像")
    @PatchMapping("/avatar")
    public AvatarResp updateAvatar(@NotNull(message = "头像不能为空") MultipartFile avatarFile) throws IOException {
        ValidationUtils.throwIf(avatarFile::isEmpty, "头像不能为空");
        String newAvatar = userService.updateAvatar(avatarFile, UserContextHolder.getUserId());
        return AvatarResp.builder().avatar(newAvatar).build();
    }

    @Operation(summary = "修改基础信息", description = "修改用户基础信息")
    @PatchMapping("/basic/info")
    public void updateBasicInfo(@RequestBody @Valid UserBasicInfoUpdateReq req) {
        userService.updateBasicInfo(req, UserContextHolder.getUserId());
    }

    @Operation(summary = "修改密码", description = "修改用户登录密码")
    @PatchMapping("/password")
    public void updatePassword(@RequestBody @Valid UserPasswordUpdateReq updateReq) {
        String oldPassword = SecureUtils.decryptPasswordByRsaPrivateKey(updateReq.getOldPassword(), DECRYPT_FAILED);
        String newPassword = SecureUtils.decryptPasswordByRsaPrivateKey(updateReq.getNewPassword(), "新密码解密失败");
        userService.updatePassword(oldPassword, newPassword, UserContextHolder.getUserId());
    }

    @Operation(summary = "修改手机号", description = "修改手机号")
    @PatchMapping("/phone")
    public void updatePhone(@RequestBody @Valid UserPhoneUpdateReq updateReq) {
        String oldPassword = SecureUtils.decryptPasswordByRsaPrivateKey(updateReq.getOldPassword(), DECRYPT_FAILED);
        String captchaKey = CacheConstants.CAPTCHA_KEY_PREFIX + updateReq.getPhone();
        String captcha = RedisUtils.get(captchaKey);
        ValidationUtils.throwIfBlank(captcha, CAPTCHA_EXPIRED);
        ValidationUtils.throwIfNotEqualIgnoreCase(updateReq.getCaptcha(), captcha, "验证码不正确");
        RedisUtils.delete(captchaKey);
        userService.updatePhone(updateReq.getPhone(), oldPassword, UserContextHolder.getUserId());
    }

    @Operation(summary = "修改邮箱", description = "修改用户邮箱")
    @PatchMapping("/email")
    public void updateEmail(@RequestBody @Valid UserEmailUpdateReq updateReq) {
        String oldPassword = SecureUtils.decryptPasswordByRsaPrivateKey(updateReq.getOldPassword(), DECRYPT_FAILED);
        String captchaKey = CacheConstants.CAPTCHA_KEY_PREFIX + updateReq.getEmail();
        String captcha = RedisUtils.get(captchaKey);
        ValidationUtils.throwIfBlank(captcha, CAPTCHA_EXPIRED);
        ValidationUtils.throwIfNotEqualIgnoreCase(updateReq.getCaptcha(), captcha, "验证码不正确");
        RedisUtils.delete(captchaKey);
        userService.updateEmail(updateReq.getEmail(), oldPassword, UserContextHolder.getUserId());
    }
}
