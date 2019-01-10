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
            buMenJiang(newUser);
            reZuzhiJiang(newUser);
            reXingYunFenHong(newUser);
            reSetMin();
        }
    }

    @Override
    public void getMyIncomes(Integer userId, AppWebResult<List<UserIncome>> result) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(new Date().getTime()-30*24*60*60*1000l);
        log.info("一月查询时间---》"+dateString+"**>"+userId);
        List<UserIncome> incomes = userIncomeDao.selectMyIncomes(userId,dateString);
        result.setData(incomes);
    }

    //平衡奖(先计算6层)
    private void pingHengJiang(SaleUser newUser){
        try {
            if(newUser.getJoinMoney()>0){
                //上一层
                SaleUser oneUser = userDao.selectUserById(newUser.getTreeSupId());
                if(newUser.getUserId().equals(oneUser.getTreeRight())){
                    int oneMoney = (int)(newUser.getJoinMoney()*Constants.MANAGE_MONEY);
                    userIncomeDao.insertUserIncome(new UserIncome(newUser.getTreeSupId(), Constants.PING_HENG_JIANG_CODE, Constants.PING_HENG_JIANG,oneMoney));
                    userDao.addBalance(oneMoney,newUser.getTreeSupId());
                    //上2层
                    Integer twoUserId = userDao.selectUserById(newUser.getTreeSupId()).getTreeSupId();
                    if(twoUserId!=null){
                        SaleUser twoUser = userDao.selectUserById(twoUserId);
                        SaleUser lUser = userDao.selectUserById(twoUser.getTreeLeft());
                        SaleUser rUser = userDao.selectUserById(twoUser.getTreeRight());
                        if(lUser.getTreeLeft()!=null&&rUser.getTreeLeft()!=null){
                            int mpney = (int)(newUser.getJoinMoney()/2*Constants.MANAGE_MONEY);
                            userIncomeDao.insertUserIncome(new UserIncome(twoUser.getUserId(),Constants.PING_HENG_JIANG_CODE, Constants.PING_HENG_JIANG,mpney));
                            userDao.addBalance(mpney,twoUser.getUserId());
                            //上3层
//                        SaleUser u1 = userDao.selectUserById(lUser.getTreeRight());
//                        SaleUser u2 = userDao.selectUserById(lUser.getTreeLeft());
//                        SaleUser u3 = userDao.selectUserById(rUser.getTreeRight());
//                        SaleUser u4 = userDao.selectUserById(rUser.getTreeLeft());
//                        if(u1.getJoinMoney()>0&&u2.getJoinMoney()>0&&u3.getJoinMoney()>0&&u4.getJoinMoney()>0){
//                            int mpney = (int)(newUser.getJoinMoney()/2*Constants.MANAGE_MONEY);
//                            userIncomeDao.insertUserIncome(new UserIncome(twoUser.getUserId(),Constants.PING_HENG_JIANG_CODE, Constants.PING_HENG_JIANG,mpney));
//                            userDao.addBalance(mpney,twoUser.getUserId());
//
//                        }
                        }
                    }
                }
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
    }

    //部门奖
    private void buMenJiang(SaleUser newUser){
        try {
            SaleUser upUser;
            Integer upUserId=newUser.getTreeSupId();
            Integer newUserId = newUser.getUserId();
            int money =(int)(Constants.BU_MEN_MONEY*Constants.MANAGE_MONEY);
            for(int i=0;i<20;i++){
                upUser = userDao.selectUserById(upUserId);
                //位于左区有效
                if(upUser.getTreeLeft().equals(newUserId)){
                    userIncomeDao.insertUserIncome(new UserIncome(upUserId,Constants.BU_MEN_JIANG_CODE, Constants.BU_MEN_JIANG,money));
                    userDao.addBalance(money,upUserId);
                }
                newUserId=upUserId;
                upUserId=upUser.getTreeSupId();
                if(upUserId==null){
                    break;
                }
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
    }

    //组织奖+管理奖
    private void reZuzhiJiang(SaleUser newUser){
        try {
            Integer nowMoney = newUser.getJoinMoney();
            Integer upUserId = newUser.getTreeSupId();
            Integer userId = newUser.getUserId();
            SaleUser u;
            if(nowMoney==null||nowMoney.equals(0)){
                return;
            }

            while (upUserId!=null){
                u = userDao.selectUserById(upUserId);
                upUserId=u.getTreeSupId();
                if(userId.equals(u.getTreeLeft())){
                    int money;
                    if(u.getLeftTotal()<3){
                        money= (int)(nowMoney*0.06*Constants.MANAGE_MONEY);
                    }else if(u.getLeftTotal()<4){
                        money= (int)(nowMoney*0.08*Constants.MANAGE_MONEY);
                    }else {
                        money= (int)(nowMoney*0.1*Constants.MANAGE_MONEY);
                    }
                    userIncomeDao.insertUserIncome(new UserIncome(u.getUserId(),Constants.ZU_ZHI_JIANG_CODE, Constants.ZU_ZHI_JIANG,money));
                    userDao.addBalance(money,u.getUserId());
                    //增加管理奖
                    Integer upRefereeId=u.getRefereeId();
                    if(upRefereeId!=null){
                        SaleUser upUser = userDao.selectUserById(upRefereeId);
                        int moneyG =(int)(money*0.05*Constants.MANAGE_MONEY);
                        for(int i=0;i<5;i++){
                            userIncomeDao.insertUserIncome(new UserIncome(upUser.getUserId(), Constants.GUAN_LI_JIANG_CODE, Constants.GUAN_LI_JIANG,moneyG));
                            userDao.addBalance(moneyG,upUser.getUserId());
                            upRefereeId=upUser.getRefereeId();
                            if(upRefereeId==null){
                                break;
                            }
                        }
                    }
                }
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
    }

    //刷新幸运分红
    private void reXingYunFenHong(SaleUser newUser){
        try {
            //计算当前自己的幸运分红（立即产生上20位分红）
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String sTime = sdf.format(newUser.getcTime());
            log.info("幸运分红当前时间---》"+sTime);
            List<SaleUser> users = userDao.selectUsersByCTime(sTime);
            //先获取自身前20位的分红
            int moneyPre = 0;
            if(users.size()<20){
                for(SaleUser u :users){
                    System.out.println("****>"+u);
                    Integer uMoney = userIncomeDao.selectMyAllZuZhiJiang(u.getUserId());
                    if(uMoney==null||uMoney.equals(0)){

                    }else {
                        moneyPre=moneyPre+uMoney;
                    }

                }
            }else {
                int i =0;
                for(SaleUser u :users){
                    if(i>20){
                        break;
                    }
                    Integer uMoney = userIncomeDao.selectMyAllZuZhiJiang(u.getUserId());
                    if(uMoney==null||uMoney.equals(0)){

                    }else {
                        moneyPre=moneyPre+uMoney;
                    }
                    i++;
                }
            }
            if(moneyPre>0){
                int money = (int)(moneyPre*0.01*Constants.MANAGE_MONEY);
                userIncomeDao.insertUserIncome(new UserIncome(newUser.getUserId(), Constants.XING_YUN_FEN_HONG_CODE, Constants.XING_YUN_FEN_HONG,money));
                userDao.addBalance(money,newUser.getUserId());
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }

    }

    //刷新所有用户人数最小区人数
    private void reSetMin(){
        Integer maxId = userDao.selectUserMaxId();
        SaleUser u;
        while (maxId>0){
            try {
                u=userDao.selectUserById(maxId);
                if(u!=null){
                    if(u.getLeftTotal()>u.getRightTotal()){
                        userDao.reSetMin(u.getRightTotal(),u.getUserId());
                    }else {
                        userDao.reSetMin(u.getLeftTotal(),u.getUserId());
                    }
                }
                maxId--;
            }catch (Exception e){
                log.error(e.getMessage(),e);
            }
        }
    }

}
