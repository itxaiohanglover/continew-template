
package top.continew.admin.system.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.continew.admin.common.api.system.DictApi;
import top.continew.admin.system.service.DictService;
import top.continew.starter.extension.crud.model.resp.LabelValueResp;

import java.util.List;

/**
 * 字典业务 API 实现
 *
 * @author Charles7c
 * @since 2025/7/26 10:16
 */
@Service
@RequiredArgsConstructor
public class DictApiImpl implements DictApi {

    private final DictService baseService;

    @Override
    public List<LabelValueResp> listAll() {
        List<LabelValueResp> list = baseService.dict(null, null);
        list.addAll(baseService.listEnumDict());
        return list;
    }
}
