
package top.continew.admin.system.service.impl;

import cn.hutool.core.collection.CollUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.continew.admin.system.mapper.RoleDeptMapper;
import top.continew.admin.system.model.entity.RoleDeptDO;
import top.continew.admin.system.service.RoleDeptService;
import top.continew.starter.core.util.CollUtils;

import java.util.List;

/**
 * 角色和部门关联业务实现
 *
 * @author Charles7c
 * @since 2023/2/19 10:47
 */
@Service
@RequiredArgsConstructor
public class RoleDeptServiceImpl implements RoleDeptService {

    private final RoleDeptMapper baseMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean add(List<Long> deptIds, Long roleId) {
        // 检查是否有变更
        List<Long> oldDeptIdList = baseMapper.lambdaQuery()
            .select(RoleDeptDO::getDeptId)
            .eq(RoleDeptDO::getRoleId, roleId)
            .list()
            .stream()
            .map(RoleDeptDO::getDeptId)
            .toList();
        if (CollUtil.isEmpty(CollUtil.disjunction(deptIds, oldDeptIdList))) {
            return false;
        }
        // 删除原有关联
        baseMapper.lambdaUpdate().eq(RoleDeptDO::getRoleId, roleId).remove();
        // 保存最新关联
        List<RoleDeptDO> roleDeptList = CollUtils.mapToList(deptIds, deptId -> new RoleDeptDO(roleId, deptId));
        return baseMapper.insertBatch(roleDeptList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByRoleIds(List<Long> roleIds) {
        if (CollUtil.isEmpty(roleIds)) {
            return;
        }
        baseMapper.lambdaUpdate().in(RoleDeptDO::getRoleId, roleIds).remove();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByDeptIds(List<Long> deptIds) {
        if (CollUtil.isEmpty(deptIds)) {
            return;
        }
        baseMapper.lambdaUpdate().in(RoleDeptDO::getDeptId, deptIds).remove();
    }

    @Override
    public List<Long> listDeptIdByRoleId(Long roleId) {
        return baseMapper.selectDeptIdByRoleId(roleId);
    }
}
