
package top.continew.admin.common.base.service;

import top.continew.starter.extension.crud.service.CrudService;

/**
 * 业务接口基类
 *
 * <p>
 * 根据实际项目需要，自行重写 CRUD 接口或增加自定义通用业务方法
 * </p>
 * 
 * @param <L> 列表类型
 * @param <D> 详情类型
 * @param <Q> 查询条件类型
 * @param <C> 创建或修改请求参数类型
 * @author Charles7c
 * @since 2024/12/6 20:30
 */
public interface BaseService<L, D, Q, C> extends CrudService<L, D, Q, C> {
}
