package com.ant.app.service.impl;

import com.ant.app.Constants;
import com.ant.app.dao.UserDao;
import com.ant.app.dao.UserDrawDao;
import com.ant.app.entity.AppWebResult;
import com.ant.app.entity.LayUiResult;
import com.ant.app.entity.req.CheckDraw;
import com.ant.app.entity.req.DrawMoneyReq;
import com.ant.app.entity.req.LayUiAuToReq;
import com.ant.app.model.SaleUser;
import com.ant.app.model.UserDraw;
import com.ant.app.service.UserDrawService;
import com.ant.app.utils.MoneyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author lchunlei
 * @date 2019/1/7
 */
@Service
public class UserDrawServiceImpl implements UserDrawService {
    private static final Logger log = LoggerFactory.getLogger(UserDrawServiceImpl.class);
    @Autowired
    UserDrawDao userDrawDao;
    @Resource
    UserDao userDao;

    @Override
    @Transactional
    public void addUserDraw(DrawMoneyReq drawMoneyReq, AppWebResult result) {
        log.info("用户提现请求---》"+drawMoneyReq);
        Integer drawMoney = Integer.parseInt(MoneyUtil.yuanTurnFen(drawMoneyReq.getDrawMoney()));
        SaleUser user = userDao.selectUserById(drawMoneyReq.getUserId());
        if(drawMoney>user.getBalance()){
            result.setFail("账户余额不足！");
        }else {
            Integer serviceMoney = (int)(drawMoney* Constants.DRAW_SERVICE_MONEY);
            Integer realDrawMoney = drawMoney-serviceMoney;
            String ins = Constants.APPLY_DRAW_ING;
            UserDraw userDraw = new UserDraw(drawMoneyReq.getUserId(),drawMoney,realDrawMoney,serviceMoney,ins,drawMoneyReq.getDrawWayAccount(),drawMoneyReq.getDrawWay(),drawMoneyReq.getDrawWayUser());
            userDrawDao.insertUserDraw(userDraw);
            userDao.minusBalance(drawMoney,drawMoneyReq.getUserId());
        }
    }

    @Override
    public void getUserDraws(Integer userId, AppWebResult<List<UserDraw>> result) {
        List<UserDraw> userDraws = userDrawDao.selectMyDraws(userId);
        if(userDraws.isEmpty()){
            result.setFail(Constants.NULL_DATA);
        }else {
            result.setData(userDraws);
        }
    }

    @Override
    public void findDraws(LayUiAuToReq req, LayUiResult<UserDraw> result) {
        req.setStartNum((req.getPage()-1)*10);
        Integer totallNumAll = userDrawDao.selectTotalNum(req);
        if(totallNumAll>0){
            result.setCode(0);
            result.setMsg("成功");
            result.setCount(totallNumAll);
            result.setData(userDrawDao.selectByPage(req));
        }else{
            result.setCode(Constants.PAGE_ERROR_CODE);
            result.setMsg(Constants.NOT_MORE_INFO);
        }
    }

    @Override
    public void findDrawById(Integer drawId, AppWebResult<UserDraw> result) {
        UserDraw draw = userDrawDao.selectDrawById(drawId);
        if(draw==null){
            result.setFail(Constants.NOT_MORE_INFO);
        }else {
            result.setData(draw);
        }
    }

    @Override
    public void checkDraw(CheckDraw checkDraw, AppWebResult result) {
        UserDraw draw = userDrawDao.selectDrawById(checkDraw.getDrawId());
        if(draw==null){
            result.setFail(Constants.NOT_MORE_INFO);
        }else if(draw.getDrawStatus()!=0){
            result.setFail("此条记录已处理！");
        }else if(checkDraw.getDrawStatus().equals(0)){

        }else {
            draw.setDrawStatus(checkDraw.getDrawStatus());
            draw.setAdminId(checkDraw.getAdminId());
            //提现失败，返还到余额
            if(checkDraw.getDrawStatus().equals(2)){
                draw.setIns("失败");
                //userDao.addBalance(draw.getDrawMoney(),draw.getUserId());
            }else if(checkDraw.getDrawStatus().equals(1)){
                draw.setIns("成功");
            }
            userDrawDao.updateDrawStatus(draw);
        }
    }


}
