
package top.continew.admin.common.base.service;

import cn.crane4j.core.support.OperateTemplate;
import cn.hutool.extra.spring.SpringUtil;
import top.continew.starter.data.mapper.BaseMapper;
import top.continew.starter.extension.crud.model.entity.BaseIdDO;
import top.continew.starter.extension.crud.service.CrudServiceImpl;

/**
 * 业务实现基类
 *
 *
 * <p>
 * 根据实际项目需要，自行重写 CRUD 接口或增加自定义通用业务方法实现
 * </p>
 * 
 * @param <M> Mapper 接口
 * @param <T> 实体类型
 * @param <L> 列表类型
 * @param <D> 详情类型
 * @param <Q> 查询条件类型
 * @param <C> 创建或修改请求参数类型
 * @author Charles7c
 * @since 2024/12/6 20:30
 */
public class BaseServiceImpl<M extends BaseMapper<T>, T extends BaseIdDO, L, D, Q, C> extends CrudServiceImpl<M, T, L, D, Q, C> implements BaseService<L, D, Q, C> {

    /**
     * 填充数据
     *
     * @param obj 待填充信息
     */
    @Override
    protected void fill(Object obj) {
        if (obj == null) {
            return;
        }
        OperateTemplate operateTemplate = SpringUtil.getBean(OperateTemplate.class);
        operateTemplate.execute(obj);
    }
}
