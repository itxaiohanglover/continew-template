
package top.continew.admin.auth.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RestController;
import top.continew.admin.common.base.controller.BaseController;
import top.continew.admin.system.model.query.ClientQuery;
import top.continew.admin.system.model.req.ClientReq;
import top.continew.admin.system.model.resp.ClientResp;
import top.continew.admin.system.service.ClientService;
import top.continew.starter.extension.crud.annotation.CrudRequestMapping;
import top.continew.starter.extension.crud.enums.Api;

/**
 * 客户端管理 API
 *
 * @author KAI
 * @since 2024/12/03 16:04
 */
@Tag(name = "客户端管理 API")
@RestController
@CrudRequestMapping(value = "/system/client", api = {Api.PAGE, Api.GET, Api.CREATE, Api.UPDATE, Api.BATCH_DELETE})
public class ClientController extends BaseController<ClientService, ClientResp, ClientResp, ClientQuery, ClientReq> {
}