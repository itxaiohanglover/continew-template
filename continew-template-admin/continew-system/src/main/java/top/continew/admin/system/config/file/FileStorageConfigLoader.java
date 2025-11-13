
package top.continew.admin.system.config.file;

import cn.hutool.core.collection.CollUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import top.continew.admin.common.enums.DisEnableStatusEnum;
import top.continew.admin.system.model.entity.StorageDO;
import top.continew.admin.system.service.StorageService;

import java.util.List;

/**
 * 文件存储配置加载器
 *
 * @author Charles7c
 * @since 2023/12/24 22:31
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class FileStorageConfigLoader implements ApplicationRunner {

    private final StorageService storageService;

    @Override
    public void run(ApplicationArguments args) {
        List<StorageDO> list = storageService.lambdaQuery().eq(StorageDO::getStatus, DisEnableStatusEnum.ENABLE).list();
        if (CollUtil.isEmpty(list)) {
            return;
        }
        list.forEach(storageService::load);
    }
}
