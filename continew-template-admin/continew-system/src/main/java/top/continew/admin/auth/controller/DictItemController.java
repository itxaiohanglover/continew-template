
package top.continew.admin.auth.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RestController;
import top.continew.admin.common.base.controller.BaseController;
import top.continew.admin.system.model.query.DictItemQuery;
import top.continew.admin.system.model.req.DictItemReq;
import top.continew.admin.system.model.resp.DictItemResp;
import top.continew.admin.system.service.DictItemService;
import top.continew.starter.extension.crud.annotation.CrudRequestMapping;
import top.continew.starter.extension.crud.enums.Api;
import top.continew.starter.log.annotation.Log;

/**
 * 字典项管理 API
 *
 * @author Charles7c
 * @since 2023/9/11 21:29
 */
@Log(module = "字典管理")
@Tag(name = "字典项管理 API")
@RestController
@CrudRequestMapping(value = "/system/dict/item", api = {Api.PAGE, Api.GET, Api.CREATE, Api.UPDATE, Api.BATCH_DELETE})
public class DictItemController extends BaseController<DictItemService, DictItemResp, DictItemResp, DictItemQuery, DictItemReq> {
}