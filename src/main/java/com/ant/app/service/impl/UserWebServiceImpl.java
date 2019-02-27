package com.ant.app.service.impl;

import com.ant.app.dao.UserDao;
import com.ant.app.entity.AppWebResult;
import com.ant.app.entity.resp.UserWebInit;
import com.ant.app.model.SaleUser;
import com.ant.app.service.UserWebService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sfb_liuchunlei on 2019/2/26.
 */
@Service
public class UserWebServiceImpl implements UserWebService{
    private static final Logger log = LoggerFactory.getLogger(UserWebServiceImpl.class);
    @Autowired
    UserDao userDao;


    @Override
    public void initUserWeb(Integer nowUserId, AppWebResult<UserWebInit> result) {
        SaleUser user = userDao.selectUserById(nowUserId);
        UserWebInit webInit = new UserWebInit();
        if(user.getRegisteCoreMoney()==null||user.getRegisteCoreMoney().equals(0)){
            webInit.setPoint(0);
        }else {
            webInit.setPoint(user.getRegisteCoreMoney()/100);
        }
        if(user.getJoinMoney()==null||user.getJoinMoney().equals(0)){
            webInit.setRegistCoin(0);
        }else {
            webInit.setRegistCoin(user.getJoinMoney()/100);
        }
        webInit.setUserNum(user.getUserNum());
        webInit.setBalance(user.getBalance());
        webInit.setbWebIn(user.getbWebIn());
        result.setData(webInit);
    }

}
