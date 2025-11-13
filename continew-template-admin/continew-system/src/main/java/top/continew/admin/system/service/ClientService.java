
package top.continew.admin.system.service;

import top.continew.admin.common.base.service.BaseService;
import top.continew.admin.system.model.query.ClientQuery;
import top.continew.admin.system.model.req.ClientReq;
import top.continew.admin.system.model.resp.ClientResp;

/**
 * 客户端业务接口
 *
 * @author KAI
 * @author Charles7c
 * @since 2024/12/03 16:04
 */
public interface ClientService extends BaseService<ClientResp, ClientResp, ClientQuery, ClientReq> {

    /**
     * 根据客户端 ID 查詢
     *
     * @param clientId 客户端 ID
     * @return 客户端信息
     */
    ClientResp getByClientId(String clientId);
}