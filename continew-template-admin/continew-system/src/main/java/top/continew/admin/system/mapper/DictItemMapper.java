
package top.continew.admin.system.mapper;

import com.alicp.jetcache.anno.Cached;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.continew.admin.common.constant.CacheConstants;
import top.continew.admin.system.model.entity.DictItemDO;
import top.continew.starter.data.mapper.BaseMapper;
import top.continew.starter.extension.crud.model.resp.LabelValueResp;

import java.util.List;

/**
 * 字典项 Mapper
 *
 * @author Charles7c
 * @since 2023/9/11 21:29
 */
@Mapper
public interface DictItemMapper extends BaseMapper<DictItemDO> {

    /**
     * 根据字典编码查询
     *
     * @param dictCode 字典编码
     * @return 字典项列表
     */
    @Cached(key = "#dictCode", name = CacheConstants.DICT_KEY_PREFIX)
    List<LabelValueResp> listByDictCode(@Param("dictCode") String dictCode);
}