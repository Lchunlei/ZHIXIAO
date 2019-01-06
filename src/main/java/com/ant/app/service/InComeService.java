package com.ant.app.service;

import com.ant.app.entity.AppWebResult;
import com.ant.app.model.UserIncome;

import java.util.List;

/**
 * Created by sfb_liuchunlei on 2019/1/6.
 */
public interface InComeService {

    //每次新加一个人，刷新一次全局收入
    public void refreshIncome(Integer newUserId);

    public void getMyIncomes(Integer userId,AppWebResult<List<UserIncome>> result);

}
