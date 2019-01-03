package com.ant.app.service;

import com.ant.app.entity.AppWebResult;
import com.ant.app.entity.LayUiResult;
import com.ant.app.entity.req.AdminLogin;
import com.ant.app.entity.req.AdminUse;
import com.ant.app.entity.req.LayUiAuToReq;
import com.ant.app.model.SysAdmin;

/**
 * @author lchunlei
 * @date 2018/12/26
 */
public interface AdminService {

    public void webLogin(AdminLogin login, AppWebResult<Integer> result);

    public void findAdmins(LayUiAuToReq req, LayUiResult<SysAdmin> result);

    public void addAdmin(SysAdmin sysAdmin,Integer nowAdminId, AppWebResult result);

    public void updaAdminUse(Integer adminId, Integer nowAdminId, AppWebResult result);

    public void updaAdmin(SysAdmin newInfo, Integer nowAdminId, AppWebResult result);

    public void findAdmin(Integer adminId, AppWebResult<SysAdmin> result);

}
