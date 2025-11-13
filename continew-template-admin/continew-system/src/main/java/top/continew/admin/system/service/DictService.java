
package top.continew.admin.system.service;

import top.continew.admin.common.base.service.BaseService;
import top.continew.admin.system.model.entity.DictDO;
import top.continew.admin.system.model.query.DictQuery;
import top.continew.admin.system.model.req.DictReq;
import top.continew.admin.system.model.resp.DictResp;
import top.continew.starter.data.service.IService;
import top.continew.starter.extension.crud.model.resp.LabelValueResp;

import java.util.List;

/**
 * 字典业务接口
 *
 * @author Charles7c
 * @since 2023/9/11 21:29
 */
public interface DictService extends BaseService<DictResp, DictResp, DictQuery, DictReq>, IService<DictDO> {

    /**
     * 查询枚举字典
     *
     * @return 枚举字典列表
     */
    List<LabelValueResp> listEnumDict();
}