
package top.continew.admin.auth.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RestController;
import top.continew.admin.common.base.controller.BaseController;
import top.continew.admin.system.model.query.DeptQuery;
import top.continew.admin.system.model.req.DeptReq;
import top.continew.admin.system.model.resp.DeptResp;
import top.continew.admin.system.service.DeptService;
import top.continew.starter.extension.crud.annotation.CrudRequestMapping;
import top.continew.starter.extension.crud.enums.Api;

/**
 * 部门管理 API
 *
 * @author Charles7c
 * @since 2023/1/22 17:50
 */
@Tag(name = "部门管理 API")
@RestController
@CrudRequestMapping(value = "/system/dept", api = {Api.TREE, Api.GET, Api.CREATE, Api.UPDATE, Api.BATCH_DELETE,
    Api.EXPORT, Api.DICT_TREE})
public class DeptController extends BaseController<DeptService, DeptResp, DeptResp, DeptQuery, DeptReq> {
}
