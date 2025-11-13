
package top.continew.admin.system.validation;

import jakarta.validation.groups.Default;

/**
 * 分组校验
 *
 * @author Charles7c
 * @since 2024/7/3 22:01
 */
public interface ValidationGroup extends Default {

    /**
     * 分组校验-增删改查
     */
    interface Storage extends ValidationGroup {
        /**
         * 本地存储
         */
        interface Local extends Storage {
        }

        /**
         * 对象存储
         */
        interface OSS extends Storage {
        }
    }
}