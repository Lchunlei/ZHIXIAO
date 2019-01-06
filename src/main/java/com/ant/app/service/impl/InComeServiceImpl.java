package com.ant.app.service.impl;

import com.ant.app.Constants;
import com.ant.app.dao.UserDao;
import com.ant.app.dao.UserIncomeDao;
import com.ant.app.entity.AppWebResult;
import com.ant.app.model.SaleUser;
import com.ant.app.model.UserIncome;
import com.ant.app.service.InComeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by sfb_liuchunlei on 2019/1/6.
 */
@Service
public class InComeServiceImpl implements InComeService{
    private static final Logger log = LoggerFactory.getLogger(InComeServiceImpl.class);

    @Autowired
    UserIncomeDao userIncomeDao;
    @Autowired
    UserDao userDao;

    @Override
    public void refreshIncome(Integer newUserId) {

    }

    @Override
    public void getMyIncomes(Integer userId, AppWebResult<List<UserIncome>> result) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(new Date().getTime()-30*24*60*60*1000l);
        log.info("一月查询时间---》"+dateString+"**>"+userId);
        List<UserIncome> incomes = userIncomeDao.selectMyIncomes(userId,dateString);
        if(incomes.isEmpty()){
            result.setFail(Constants.NOT_MORE_INFO);
        }else {
            result.setData(incomes);
        }
    }

    //组织奖
    private void reZuzhiJiang(SaleUser newUser){
        Integer supUserId = newUser.getTreeSupId();
        Integer nowMoney = newUser.getJoinMoney();
        while (supUserId!=null){
            SaleUser u = userDao.selectUserById(supUserId);
            supUserId=u.getTreeSupId();
            int money = (int)(nowMoney*0.1);
            userIncomeDao.insertUserIncome(new UserIncome(u.getUserId(), Constants.ZU_ZHI_JIANG,money));
            userDao.addBalance(money,u.getUserId());
        }
    }
    //刷新幸运分红
    private void reXingYunFenHong(SaleUser newUser){
        //计算当前自己的幸运分红（立即产生上20位分红）
        List<SaleUser> allUpUser = new ArrayList();
        int upRealMoney = 0;
        Integer reSupId = newUser.getTreeSupId();
        for(int i=0;i<20;i++){
            if(reSupId==null){
                break;
            }else {
                SaleUser u = userDao.selectUserById(reSupId);
                allUpUser.add(u);
                upRealMoney=upRealMoney+u.getJoinMoney();
                reSupId = u.getTreeSupId();
            }
        }
        if(upRealMoney>0){
            int money = (int)(upRealMoney*0.001);
            log.info("上级幸运分红实际金额----》"+upRealMoney);
            userIncomeDao.insertUserIncome(new UserIncome(newUser.getUserId(), Constants.XING_YUN_FEN_HONG,money));
            userDao.addBalance(money,newUser.getUserId());
        }
        //新人幸运分红计算E

        if(!allUpUser.isEmpty()&&newUser.getJoinMoney()>0){
            for(SaleUser u:allUpUser){
                if(u.getLuckEnd()<30){
                    int money = (int)(newUser.getJoinMoney()*0.001);
                    userIncomeDao.insertUserIncome(new UserIncome(u.getUserId(), Constants.XING_YUN_FEN_HONG,money));
                    userDao.addOneSunLuck(money,u.getUserId());
                }
            }
        }
        //上面的父级没有满30的都分红
    }



}
