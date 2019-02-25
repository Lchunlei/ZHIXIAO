package com.ant.app.controller.sj;

import com.ant.app.Constants;
import com.ant.app.entity.AppWebResult;
import com.ant.app.entity.LayUiResult;
import com.ant.app.entity.req.DrawMoneyReq;
import com.ant.app.entity.req.LayUiAuToReq;
import com.ant.app.model.SaleUser;
import com.ant.app.model.UserDraw;
import com.ant.app.service.UserDrawService;
import com.ant.app.service.UserService;
import com.ant.app.sql.SysTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author lchunlei
 * @date 2019/1/7
 */
@RestController
@RequestMapping("/sj/draw")
public class DrawMoneyController {
    private static final Logger log = LoggerFactory.getLogger(DrawMoneyController.class);

    @Autowired
    UserDrawService userDrawService;
    @Autowired
    UserService userService;

    /**
     * 申请提现
     */
    @RequestMapping(value = "/apply",method = RequestMethod.POST)
    public AppWebResult addDraw(HttpSession session, DrawMoneyReq drawMoneyReq){
        AppWebResult result = new AppWebResult();
        Object obj = session.getAttribute(Constants.USER_ID);
        if(obj==null){
            result.setResultCode(Constants.NO_LOGIN_CODE);
            result.setResultMsg(Constants.NO_LOGIN);
        }else {
            Integer nowUserCore = (int)obj;
            drawMoneyReq.setUserId(nowUserCore);
            userDrawService.addUserDraw(drawMoneyReq,result);
            log.info("申请提现返回--->"+result);
        }
        return result;
    }

    /**
     * 三级密码校验
     */
    @RequestMapping(value = "/pwd",method = RequestMethod.GET)
    public AppWebResult authPwd(HttpSession session, String thirdPwd){
        log.info("三级密码校验--->"+thirdPwd);
        AppWebResult result = new AppWebResult();
        Object obj = session.getAttribute(Constants.USER_ID);
        if(obj==null){
            result.setResultCode(Constants.NO_LOGIN_CODE);
            result.setResultMsg(Constants.NO_LOGIN);
        }else {
            Integer nowUserId = (int)obj;
            AppWebResult<SaleUser> re = new AppWebResult();
            userService.userInfo(nowUserId,re);
            if(re.getData()==null){
                result.setFail(Constants.ERR_MSG);
            }else if(!thirdPwd.equals(re.getData().getThirdPwd())){
                result.setFail(Constants.PWD_ERR);
            }
            log.info("三级密码校验返回--->"+result);
        }
        return result;
    }

    /**
     * 个人提现列表
     */
    @RequestMapping(value = "/my",method = RequestMethod.GET)
    public AppWebResult addDraw(HttpSession session,String thirdPwd){
        AppWebResult<List<UserDraw>> result = new AppWebResult();
        Object obj = session.getAttribute(Constants.USER_ID);
        if(obj==null){
            result.setResultCode(Constants.NO_LOGIN_CODE);
            result.setResultMsg(Constants.NO_LOGIN);
        }else {
            Integer nowUserId = (int)obj;
            AppWebResult<SaleUser> re = new AppWebResult();
            userService.userInfo(nowUserId,re);
            if(Constants.SUCCESS_CODE.equals(re.getResultCode())){
                if(thirdPwd.equals(re.getData().getThirdPwd())){
                    userDrawService.getUserDraws(nowUserId,result);
                }else {
                    result.setFail(Constants.PWD_ERR);
                }
            }else {
                return re;
            }
        }
        return result;
    }

    /**
     * 个人提现列表 PC
     */
    @RequestMapping(value = "/myList",method = RequestMethod.POST)
    public LayUiResult myPcList(HttpSession session, LayUiAuToReq layUiAuToReq){
        log.info("个人提现列表 PC查看--->"+layUiAuToReq);
        layUiAuToReq.tableSet(SysTable.USER_DRAW,SysTable.DRAW_ID,SysTable.C_TIME,SysTable.DRAW_WAY_USER,SysTable.DRAW_ID);
        LayUiResult<UserDraw> result = new LayUiResult();
        Object obj = session.getAttribute(Constants.USER_ID);
        if(obj==null){
            result.setCode(Constants.PAGE_ERROR_CODE);
            result.setMsg(Constants.NO_LOGIN);
        }else {
            Integer nowUserId = (int)obj;
            layUiAuToReq.setAntherWhere(" userId="+nowUserId);
            userDrawService.findDraws(layUiAuToReq,result);
        }
        return result;
    }

}
