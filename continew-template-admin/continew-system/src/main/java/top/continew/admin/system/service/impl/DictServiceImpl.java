
package top.continew.admin.system.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.continew.admin.common.base.service.BaseServiceImpl;
import top.continew.admin.system.mapper.DictMapper;
import top.continew.admin.system.model.entity.DictDO;
import top.continew.admin.system.model.query.DictQuery;
import top.continew.admin.system.model.req.DictReq;
import top.continew.admin.system.model.resp.DictResp;
import top.continew.admin.system.service.DictItemService;
import top.continew.admin.system.service.DictService;
import top.continew.starter.core.util.CollUtils;
import top.continew.starter.core.util.validation.CheckUtils;
import top.continew.starter.extension.crud.model.resp.LabelValueResp;

import java.util.List;
import java.util.Optional;

/**
 * 字典业务实现
 *
 * @author Charles7c
 * @since 2023/9/11 21:29
 */
@Service
@RequiredArgsConstructor
public class DictServiceImpl extends BaseServiceImpl<DictMapper, DictDO, DictResp, DictResp, DictQuery, DictReq> implements DictService {

    private final DictItemService dictItemService;

    @Override
    public void beforeCreate(DictReq req) {
        this.checkNameRepeat(req.getName(), null);
        this.checkCodeRepeat(req.getCode(), null);
    }

    @Override
    public void beforeUpdate(DictReq req, Long id) {
        this.checkNameRepeat(req.getName(), id);
        DictDO oldDict = super.getById(id);
        CheckUtils.throwIfNotEqual(req.getCode(), oldDict.getCode(), "不允许修改字典编码");
    }

    @Override
    public void beforeDelete(List<Long> ids) {
        List<DictDO> list = baseMapper.lambdaQuery()
            .select(DictDO::getName, DictDO::getIsSystem)
            .in(DictDO::getId, ids)
            .list();
        Optional<DictDO> isSystemData = list.stream().filter(DictDO::getIsSystem).findFirst();
        CheckUtils.throwIf(isSystemData::isPresent, "所选字典 [{}] 是系统内置字典，不允许删除", isSystemData.orElseGet(DictDO::new)
            .getName());
        dictItemService.deleteByDictIds(ids);
    }

    @Override
    public List<LabelValueResp> listEnumDict() {
        List<String> enumDictNameList = dictItemService.listEnumDictNames();
        return CollUtils.mapToList(enumDictNameList, name -> new LabelValueResp(name, name));
    }

    /**
     * 检查名称是否重复
     *
     * @param name 名称
     * @param id   ID
     */
    private void checkNameRepeat(String name, Long id) {
        CheckUtils.throwIf(baseMapper.lambdaQuery()
            .eq(DictDO::getName, name)
            .ne(id != null, DictDO::getId, id)
            .exists(), "名称为 [{}] 的字典已存在", name);
    }

    /**
     * 检查编码是否重复
     *
     * @param code 编码
     * @param id   ID
     */
    private void checkCodeRepeat(String code, Long id) {
        CheckUtils.throwIf(baseMapper.lambdaQuery()
            .eq(DictDO::getCode, code)
            .ne(id != null, DictDO::getId, id)
            .exists(), "编码为 [{}] 的字典已存在", code);
    }
}