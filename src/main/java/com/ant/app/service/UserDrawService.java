package com.ant.app.service;

import com.ant.app.entity.AppWebResult;
import com.ant.app.entity.LayUiResult;
import com.ant.app.entity.req.CheckDraw;
import com.ant.app.entity.req.DrawMoneyReq;
import com.ant.app.entity.req.LayUiAuToReq;
import com.ant.app.model.UserDraw;

import java.util.List;

/**
 * @author lchunlei
 * @date 2019/1/7
 */
public interface UserDrawService {

    public void addUserDraw(DrawMoneyReq drawMoneyReq, AppWebResult result);

    public void getUserDraws(Integer userId,AppWebResult<List<UserDraw>> result);

    public void findDraws(LayUiAuToReq req, LayUiResult<UserDraw> result);

    public void findDrawById(Integer drawId,AppWebResult<UserDraw> result);

    public void checkDraw(CheckDraw checkDraw, AppWebResult result);


}
