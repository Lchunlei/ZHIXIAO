package com.ant.app.controller.sj;

import com.ant.app.Constants;
import com.ant.app.entity.AppWebResult;
import com.ant.app.entity.req.UserLogin;
import com.ant.app.model.SaleUser;
import com.ant.app.model.UserIncome;
import com.ant.app.service.InComeService;
import com.ant.app.service.UserService;
import com.ant.app.utils.MoneyUtil;
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
 * @date 2018/12/12
 */
@RestController
@RequestMapping("/sj/user")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;
    @Autowired
    InComeService inComeService;

    /**
     * 用户登录
     * data携带用户ID，并保存到session
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public AppWebResult loginApp(HttpSession session,UserLogin userLogin){
        AppWebResult<Integer> result = new AppWebResult();
        userService.appLogin(userLogin,result);
        if(Constants.SUCCESS_CODE.equals(result.getResultCode())||Constants.RE_SET_PWD.equals(result.getResultCode())){
            session.setAttribute(Constants.USER_ID,result.getData());
        }
        log.info("用户登陆返回--->"+result);
        return result;
    }

    /**
     * 用户退出
     */
    @RequestMapping(value = "/sign/out",method = RequestMethod.GET)
    public AppWebResult signOut(HttpSession session){
        AppWebResult result = new AppWebResult();
        session.removeAttribute(Constants.USER_ID);
        log.info("用户退出返回--->"+result);
        return result;
    }

    /**
     * 用户添加
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public AppWebResult addUser(HttpSession session,SaleUser saleUser){
        AppWebResult result = new AppWebResult();
        Object obj = session.getAttribute(Constants.USER_ID);
        if(obj==null){
            result.setResultCode(Constants.NO_LOGIN_CODE);
            result.setResultMsg(Constants.NO_LOGIN);
        }else {
            Integer nowUserCore = (int)obj;
            saleUser.setRegisteCore(0);
            userService.addSaleUser(saleUser,nowUserCore,result);
            log.info("用户添加返回--->"+result);
        }
        return result;
    }

    /**
     * 用户修改密码
     */
    @RequestMapping(value = "/change/pwd",method = RequestMethod.POST)
    public AppWebResult changePwd(HttpSession session,SaleUser saleUser){
        AppWebResult result = new AppWebResult();
        Object obj = session.getAttribute(Constants.USER_ID);
        if(obj==null){
            result.setResultCode(Constants.NO_LOGIN_CODE);
            result.setResultMsg(Constants.NO_LOGIN);
        }else if(Constants.PWD.equals(saleUser.getFirstPwd())||Constants.PWD.equals(saleUser.getSecondPwd())||Constants.PWD_LAST.equals(saleUser.getThirdPwd())){
            result.setFail("新密码和默认密码一致，重置失败！");
        }else {
            Integer nowUserId = (int)obj;
            userService.changePwd(saleUser,nowUserId,result);
        }
        log.info("用户修改密码--->"+result);
        return result;
    }

    /**
     * 用户中心数据
     */
    @RequestMapping(value = "/info/index",method = RequestMethod.GET)
    public AppWebResult findUserInfo(HttpSession session){
        AppWebResult<SaleUser> result = new AppWebResult();
        Object obj = session.getAttribute(Constants.USER_ID);
        if(obj==null){
            result.setResultCode(Constants.NO_LOGIN_CODE);
            result.setResultMsg(Constants.NO_LOGIN);
        }else {
            Integer nowUserId = (int)obj;
            log.info("用户中心数据--->"+nowUserId);
            userService.userInfo(nowUserId,result);
            log.info("用户中心数据返回--->"+result);
        }
        return result;
    }

    /**
     * 查看用户资产
     */
    @RequestMapping(value = "/income",method = RequestMethod.GET)
    public AppWebResult findUserInfo(HttpSession session,String thirdPwd){
        AppWebResult<List<UserIncome>> result = new AppWebResult();
        Object obj = session.getAttribute(Constants.USER_ID);
        if(obj==null){
            result.setResultCode(Constants.NO_LOGIN_CODE);
            result.setResultMsg(Constants.NO_LOGIN);
        }else {
            Integer nowUserId = (int)obj;
            log.info("查看用户资产--->"+nowUserId+"***>"+thirdPwd);
            AppWebResult<SaleUser> re = new AppWebResult();
            userService.userInfo(nowUserId,re);
            if(!re.getData().getThirdPwd().equals(thirdPwd)){
                result.setFail(Constants.PWD_ERR);
                result.setData(null);
            }else {
                inComeService.getMyIncomes(nowUserId,result);
                result.setBalance(MoneyUtil.FenTurnYuan(re.getData().getBalance()+""));
                result.setCoin(re.getData().getCoin());
            }
            log.info("查看用户资产返回--->"+result);
        }
        return result;
    }

    /**
     * 查看是否是报单中心
     */
    @RequestMapping(value = "/core/status",method = RequestMethod.GET)
    public AppWebResult findCoreStatus(HttpSession session){
        AppWebResult<SaleUser> result = new AppWebResult();
        Object obj = session.getAttribute(Constants.USER_ID);
        if(obj==null){
            result.setResultCode(Constants.NO_LOGIN_CODE);
            result.setResultMsg(Constants.NO_LOGIN);
        }else {
            Integer nowUserId = (int)obj;
            log.info("查看是否是报单中心--->"+nowUserId);
            userService.userInfo(nowUserId,result);
            if(!result.getData().getRegisteCore().equals(1)){
                result.setFail(Constants.AUTH_LESS);
                result.setData(null);
            }
            log.info("查看是否是报单中心--->"+result);
        }
        return result;
    }

}
