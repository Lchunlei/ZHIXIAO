package com.ant.app.service;

import com.ant.app.entity.AppWebResult;
import com.ant.app.entity.LayUiResult;
import com.ant.app.entity.req.LayUiAuToReq;
import com.ant.app.model.SaleUser;
import com.ant.app.model.UserDraw;
import com.ant.app.model.UserIncome;
import com.ant.app.model.WebBIncome;

import java.util.List;

/**
 * Created by sfb_liuchunlei on 2019/1/6.
 */
public interface InComeService {

    //每次新加一个人，刷新一次全局收入
    public void refreshIncome(SaleUser user);

    public void getMyIncomes(Integer userId,AppWebResult<List<UserIncome>> result);

    //判断是否回本
    public void reBackMoney();

    //刷新效益分红
    public void reXiaoYiFenHong();

    //电脑个人收益列表
    public void findPcIncomes(LayUiAuToReq req, LayUiResult<UserIncome> result);

    //B收益列表
    public void findBwebIncomes(LayUiAuToReq req, LayUiResult<WebBIncome> result);


}
