
package top.continew.admin.common.util;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.ReUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import top.continew.admin.common.config.RsaProperties;
import top.continew.admin.common.constant.RegexConstants;
import top.continew.starter.core.util.ExceptionUtils;
import top.continew.starter.core.util.validation.ValidationUtils;

/**
 * 加密/解密工具类
 *
 * @author Charles7c
 * @since 2022/12/21 21:41
 */
public class SecureUtils {

    private SecureUtils() {
    }

    /**
     * 公钥加密
     *
     * @param data 要加密的内容
     * @return 加密后的内容
     */
    public static String encryptByRsaPublicKey(String data) {
        String publicKey = RsaProperties.PUBLIC_KEY;
        ValidationUtils.throwIfBlank(publicKey, "请配置 RSA 公钥");
        return encryptByRsaPublicKey(data, publicKey);
    }

    /**
     * 私钥解密
     *
     * @param data 要解密的内容（Base64 加密过）
     * @return 解密后的内容
     */
    public static String decryptByRsaPrivateKey(String data) {
        String privateKey = RsaProperties.PRIVATE_KEY;
        ValidationUtils.throwIfBlank(privateKey, "请配置 RSA 私钥");
        return decryptByRsaPrivateKey(data, privateKey);
    }

    /**
     * 公钥加密
     *
     * @param data      要加密的内容
     * @param publicKey 公钥
     * @return 加密后的内容
     */
    public static String encryptByRsaPublicKey(String data, String publicKey) {
        return new String(SecureUtil.rsa(null, publicKey).encrypt(data, KeyType.PublicKey));
    }

    /**
     * 私钥解密
     *
     * @param data       要解密的内容（Base64 加密过）
     * @param privateKey 私钥
     * @return 解密后的内容
     */
    public static String decryptByRsaPrivateKey(String data, String privateKey) {
        return new String(SecureUtil.rsa(privateKey, null).decrypt(Base64.decode(data), KeyType.PrivateKey));
    }

    /**
     * 解密密码
     *
     * @param encryptedPasswordByRsaPublicKey 密码（已被 Rsa 公钥加密）
     * @param errorMsg                        错误信息
     * @return 解密后的密码
     */
    public static String decryptPasswordByRsaPrivateKey(String encryptedPasswordByRsaPublicKey, String errorMsg) {
        return decryptPasswordByRsaPrivateKey(encryptedPasswordByRsaPublicKey, errorMsg, false);
    }

    /**
     * 解密密码
     *
     * @param encryptedPasswordByRsaPublicKey 密码（已被 Rsa 公钥加密）
     * @param errorMsg                        错误信息
     * @param isVerifyPattern                 是否验证密码格式
     * @return 解密后的密码
     */
    public static String decryptPasswordByRsaPrivateKey(String encryptedPasswordByRsaPublicKey,
                                                        String errorMsg,
                                                        boolean isVerifyPattern) {
        String rawPassword = ExceptionUtils.exToNull(() -> decryptByRsaPrivateKey(encryptedPasswordByRsaPublicKey));
        ValidationUtils.throwIfBlank(rawPassword, errorMsg);
        if (isVerifyPattern) {
            ValidationUtils.throwIf(!ReUtil
                .isMatch(RegexConstants.PASSWORD, rawPassword), "密码长度为 8-32 个字符，支持大小写字母、数字、特殊字符，至少包含字母和数字");
        }
        return rawPassword;
    }
}
