
package top.continew.admin.system.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.continew.admin.system.model.entity.UserRoleDO;
import top.continew.admin.system.model.resp.role.RoleUserResp;
import top.continew.starter.data.mapper.BaseMapper;

/**
 * 用户和角色 Mapper
 *
 * @author Charles7c
 * @since 2023/2/13 23:13
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRoleDO> {

    /**
     * 分页查询列表
     *
     * @param page         分页条件
     * @param queryWrapper 查询条件
     * @return 分页列表信息
     */
    IPage<RoleUserResp> selectUserPage(@Param("page") IPage<UserRoleDO> page,
                                       @Param(Constants.WRAPPER) QueryWrapper<UserRoleDO> queryWrapper);

}
