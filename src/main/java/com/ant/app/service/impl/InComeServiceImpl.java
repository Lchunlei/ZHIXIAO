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
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public void refreshIncome(Integer newUserId) {
        SaleUser newUser = userDao.selectUserById(newUserId);
        if(newUser!=null){
            pingHengJiang(newUser);
            guanLiJiang(newUser);
            buMenJiang(newUser);
            reZuzhiJiang(newUser);
            reXingYunFenHong(newUser);
        }
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

    //平衡奖(先计算6层)
    private void pingHengJiang(SaleUser newUser){
        if(newUser.getJoinMoney()>0){
            //上一层
            SaleUser oneUser = userDao.selectUserById(newUser.getTreeSupId());
            if(newUser.getUserId().equals(oneUser.getTreeRight())){
                int oneMoney = (int)(newUser.getJoinMoney()*Constants.MANAGE_MONEY);
                userIncomeDao.insertUserIncome(new UserIncome(newUser.getTreeSupId(), Constants.PING_HENG_JIANG,oneMoney));
                userDao.addBalance(oneMoney,newUser.getTreeSupId());
                //上2层
                SaleUser twoUser = userDao.selectUserById(userDao.selectUserById(newUser.getTreeSupId()).getTreeSupId());
                SaleUser lUser = userDao.selectUserById(twoUser.getTreeLeft());
                SaleUser rUser = userDao.selectUserById(twoUser.getTreeRight());
                if(lUser.getTreeRight()!=null&&lUser.getTreeLeft()!=null&&rUser.getTreeRight()!=null&&rUser.getTreeLeft()!=null){
                    SaleUser u1 = userDao.selectUserById(lUser.getTreeRight());
                    SaleUser u2 = userDao.selectUserById(lUser.getTreeLeft());
                    SaleUser u3 = userDao.selectUserById(rUser.getTreeRight());
                    SaleUser u4 = userDao.selectUserById(rUser.getTreeLeft());
                    if(u1.getJoinMoney()>0&&u2.getJoinMoney()>0&&u3.getJoinMoney()>0&&u4.getJoinMoney()>0){
                        int mpney = (int)(newUser.getJoinMoney()/2*Constants.MANAGE_MONEY);
                        userIncomeDao.insertUserIncome(new UserIncome(twoUser.getUserId(), Constants.PING_HENG_JIANG,mpney));
                        userDao.addBalance(mpney,twoUser.getUserId());
                    }
                    //上3层

                }

            }
        }
    }

    //管理奖
    private void guanLiJiang(SaleUser newUser){
        if(newUser.getJoinMoney()>0){
            Integer upUserId=newUser.getTreeSupId();
            int money =(int)(newUser.getJoinMoney()*0.05*Constants.MANAGE_MONEY);
            for(int i=0;i<5;i++){
                userIncomeDao.insertUserIncome(new UserIncome(upUserId, Constants.GUAN_LI_JIANG,money));
                userDao.addBalance(money,upUserId);
                upUserId=userDao.selectUserById(upUserId).getTreeSupId();
                if(upUserId==null){
                    break;
                }
            }
        }
    }

    //部门奖
    private void buMenJiang(SaleUser newUser){
        SaleUser upUser;
        Integer upUserId=newUser.getTreeSupId();
        int money =(int)(Constants.BU_MEN_MONEY*Constants.MANAGE_MONEY);
        for(int i=0;i<20;i++){
            upUser = userDao.selectUserById(upUserId);
            //位于左区有效
            if(upUser.getTreeLeft().equals(upUserId)){
                userIncomeDao.insertUserIncome(new UserIncome(upUserId, Constants.BU_MEN_JIANG,money));
                userDao.addBalance(money,upUserId);
            }
            upUserId=upUser.getTreeSupId();
            if(upUserId==null){
                break;
            }
        }
    }

    //组织奖
    private void reZuzhiJiang(SaleUser newUser){

        Integer supUserId = newUser.getTreeSupId();
        Integer nowMoney = newUser.getJoinMoney();
        if(nowMoney>0){
            int i=0;
            int money;
            while (supUserId!=null){
                SaleUser u = userDao.selectUserById(supUserId);
                supUserId=u.getTreeSupId();

                if(i<2){
                    money= (int)(nowMoney*0.06*Constants.MANAGE_MONEY);
                }else if(i<3){
                    money= (int)(nowMoney*0.08*Constants.MANAGE_MONEY);
                }else {
                    money= (int)(nowMoney*0.1*Constants.MANAGE_MONEY);
                }

                userIncomeDao.insertUserIncome(new UserIncome(u.getUserId(), Constants.ZU_ZHI_JIANG,money));
                userDao.addBalance(money,u.getUserId());
                i++;
            }
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
            int money = (int)(upRealMoney*0.001*Constants.MANAGE_MONEY);
            log.info("上级幸运分红实际金额----》"+upRealMoney);
            userIncomeDao.insertUserIncome(new UserIncome(newUser.getUserId(), Constants.XING_YUN_FEN_HONG,money));
            userDao.addBalance(money,newUser.getUserId());
        }
        //新人幸运分红计算E

        if(!allUpUser.isEmpty()&&newUser.getJoinMoney()>0){
            for(SaleUser u:allUpUser){
                if(u.getLuckEnd()<30){
                    int money = (int)(newUser.getJoinMoney()*0.001*Constants.MANAGE_MONEY);
                    userIncomeDao.insertUserIncome(new UserIncome(u.getUserId(), Constants.XING_YUN_FEN_HONG,money));
                    userDao.addOneSunLuck(money,u.getUserId());
                }
            }
        }
        //上面的父级没有满30的都分红
    }



}
