
package top.continew.admin.system.service;

import top.continew.admin.common.base.service.BaseService;
import top.continew.admin.system.model.entity.DictItemDO;
import top.continew.admin.system.model.query.DictItemQuery;
import top.continew.admin.system.model.req.DictItemReq;
import top.continew.admin.system.model.resp.DictItemResp;
import top.continew.starter.data.service.IService;
import top.continew.starter.extension.crud.model.resp.LabelValueResp;

import java.util.List;

/**
 * 字典项业务接口
 *
 * @author Charles7c
 * @since 2023/9/11 21:29
 */
public interface DictItemService extends BaseService<DictItemResp, DictItemResp, DictItemQuery, DictItemReq>, IService<DictItemDO> {

    /**
     * 根据字典编码查询字典项列表
     *
     * @param dictCode 字典编码
     * @return 字典项列表
     */
    List<LabelValueResp> listByDictCode(String dictCode);

    /**
     * 根据字典 ID 列表删除
     *
     * @param dictIds 字典 ID 列表
     */
    void deleteByDictIds(List<Long> dictIds);

    /**
     * 查询枚举字典名称列表
     *
     * @return 枚举字典名称列表
     */
    List<String> listEnumDictNames();
}