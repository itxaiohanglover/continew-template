
package top.continew.admin.common.api.system;

import top.continew.starter.extension.crud.model.resp.LabelValueResp;

import java.util.List;

/**
 * 字典项业务 API
 *
 * @author Charles7c
 * @since 2025/4/9 20:17
 */
public interface DictItemApi {

    /**
     * 根据字典编码查询
     *
     * @param dictCode 字典编码
     * @return 字典项列表
     */
    List<LabelValueResp> listByDictCode(String dictCode);
}
