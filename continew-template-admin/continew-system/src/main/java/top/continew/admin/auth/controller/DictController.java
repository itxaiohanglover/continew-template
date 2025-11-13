
package top.continew.admin.auth.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import top.continew.admin.common.constant.CacheConstants;
import top.continew.admin.common.base.controller.BaseController;
import top.continew.admin.system.model.query.DictQuery;
import top.continew.admin.system.model.req.DictReq;
import top.continew.admin.system.model.resp.DictResp;
import top.continew.admin.system.service.DictService;
import top.continew.starter.cache.redisson.util.RedisUtils;
import top.continew.starter.extension.crud.annotation.CrudRequestMapping;
import top.continew.starter.extension.crud.enums.Api;

/**
 * 字典管理 API
 *
 * @author Charles7c
 * @since 2023/9/11 21:29
 */
@Tag(name = "字典管理 API")
@RestController
@CrudRequestMapping(value = "/system/dict", api = {Api.LIST, Api.GET, Api.CREATE, Api.UPDATE, Api.BATCH_DELETE})
public class DictController extends BaseController<DictService, DictResp, DictResp, DictQuery, DictReq> {

    @Operation(summary = "清除缓存", description = "清除缓存")
    @SaCheckPermission("system:dict:clearCache")
    @DeleteMapping("/cache/{code}")
    public void clearCache(@PathVariable String code) {
        RedisUtils.deleteByPattern(CacheConstants.DICT_KEY_PREFIX + code);
    }
}