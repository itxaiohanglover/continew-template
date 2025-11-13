
package top.continew.admin.system.service;

import top.continew.admin.system.enums.OptionCategoryEnum;
import top.continew.admin.system.model.query.OptionQuery;
import top.continew.admin.system.model.req.OptionReq;
import top.continew.admin.system.model.req.OptionResetValueReq;
import top.continew.admin.system.model.resp.OptionResp;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * 参数业务接口
 *
 * @author Bull-BCLS
 * @since 2023/8/26 19:38
 */
public interface OptionService {

    /**
     * 查询列表
     *
     * @param query 查询条件
     * @return 列表信息
     */
    List<OptionResp> list(OptionQuery query);

    /**
     * 根据类别查询
     *
     * @param category 类别
     * @return 参数信息
     */
    Map<String, String> getByCategory(OptionCategoryEnum category);

    /**
     * 修改参数
     *
     * @param options 参数列表
     */
    void update(List<OptionReq> options);

    /**
     * 重置参数
     *
     * @param req 重置信息
     */
    void resetValue(OptionResetValueReq req);

    /**
     * 根据编码查询参数值
     *
     * @param code 编码
     * @return 参数值（自动转换为 int 类型）
     */
    int getValueByCode2Int(String code);

    /**
     * 根据编码查询参数值
     *
     * @param code   编码
     * @param mapper 转换方法 e.g.：value -> Integer.parseInt(value)
     * @return 参数值
     */
    <T> T getValueByCode(String code, Function<String, T> mapper);
}
