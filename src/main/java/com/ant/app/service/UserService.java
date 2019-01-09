package com.ant.app.service;

import com.ant.app.entity.AppWebResult;
import com.ant.app.entity.LayUiResult;
import com.ant.app.entity.req.LayUiAuToReq;
import com.ant.app.entity.req.UserLogin;
import com.ant.app.model.SaleUser;

import java.util.List;


/**
 * @author lchunlei
 * @date 2018/12/12
 */
public interface UserService {

    public void appLogin(UserLogin login, AppWebResult<Integer> result);

    public void findUsers(LayUiAuToReq req, LayUiResult<SaleUser> result);

    public void findUserRank(LayUiAuToReq req, LayUiResult<SaleUser> result);

    public void updaUserUse(Integer userId, Integer nowAdminId, AppWebResult result);

    public void addUserCore(SaleUser user, Integer nowAdminId, AppWebResult result);

    public void addSaleUser(SaleUser user, Integer nowAdminId, AppWebResult result);

    public void changePwd(SaleUser user, Integer nowUserId, AppWebResult result);

    public void userInfo(Integer userId, AppWebResult<SaleUser> result);

    public void userMinHead(AppWebResult<List<SaleUser>> result);


}
