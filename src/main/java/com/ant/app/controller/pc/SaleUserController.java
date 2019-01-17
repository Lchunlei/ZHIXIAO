package com.ant.app.controller.pc;

import com.ant.app.Constants;
import com.ant.app.entity.AppWebResult;
import com.ant.app.entity.LayUiResult;
import com.ant.app.entity.req.LayUiAuToReq;
import com.ant.app.model.SaleUser;
import com.ant.app.service.UserService;
import com.ant.app.sql.SysTable;
import com.ant.app.utils.MoneyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;


/**
 * @author lchunlei
 * @date 2018/12/27
 */
@RestController
@RequestMapping("/pc/user")
public class SaleUserController {
    private static final Logger log = LoggerFactory.getLogger(SaleUserController.class);
    @Autowired
    UserService userService;

    /**
     * 会员列表查看
     */
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public LayUiResult userList(LayUiAuToReq layUiAuToReq){
        log.info("会员列表查看--->"+layUiAuToReq);
        layUiAuToReq.tableSet(SysTable.SALE_USER,SysTable.USER_ID,SysTable.C_TIME,SysTable.USER_NAME,SysTable.USER_ID);
        LayUiResult<SaleUser> result = new LayUiResult();
        userService.findUsers(layUiAuToReq,result);
        log.info("会员列表查看返回--->"+result);
        return result;
    }

    /**
     * 报单中心列表查看
     */
    @RequestMapping(value = "/core/list",method = RequestMethod.POST)
    public LayUiResult coreUserList(LayUiAuToReq layUiAuToReq){
        log.info("报单中心列表查看--->"+layUiAuToReq);
        layUiAuToReq.tableSet(SysTable.SALE_USER,SysTable.USER_ID,SysTable.C_TIME,SysTable.USER_NAME,SysTable.USER_ID);
        layUiAuToReq.setAntherWhere(" registeCore=1 ");
        LayUiResult<SaleUser> result = new LayUiResult();
        userService.findUsers(layUiAuToReq,result);
        log.info("报单中心列表查看返回--->"+result);
        return result;
    }

    /**
     * 创建报单中心
     */
    @RequestMapping(value = "/core/add",method = RequestMethod.POST)
    public AppWebResult addAdmin(HttpSession session, SaleUser saleUser){
        log.info("创建报单中心--->"+saleUser);
        AppWebResult result = new AppWebResult();
        Object obj = session.getAttribute(Constants.ADMIN_ID);

        if(obj==null){
            result.setResultCode(Constants.NO_LOGIN_CODE);
            result.setResultMsg(Constants.NO_LOGIN);
        }else {
            try {
                String fen = MoneyUtil.yuanTurnFen(saleUser.getRegisteCoreMoney().toString());
                saleUser.setRegisteCoreMoney(Integer.parseInt(fen));
            }catch (Exception e){
                result.setFail(Constants.PARM_ERR);
            }
            Integer nowAdminId = (int)obj;
            userService.addUserCore(saleUser,nowAdminId,result);
        }

        log.info("创建报单中心返回--->"+result);
        return result;
    }

    /**
     * 更改会员状态
     */
    @RequestMapping(value = "/use",method = RequestMethod.GET)
    public AppWebResult updateAdminUse(HttpSession session, Integer userId){
        log.info("更改会员状态查看--->"+userId);
        AppWebResult result = new AppWebResult();
        Object obj = session.getAttribute(Constants.ADMIN_ID);

        if(obj==null){
            result.setResultCode(Constants.NO_LOGIN_CODE);
            result.setResultMsg(Constants.NO_LOGIN);
        }else {
            Integer nowAdminId = (int)obj;
            userService.updaUserUse(userId,nowAdminId,result);
        }
        log.info("更改会员状态返回--->"+result);
        return result;
    }

    /**
     * 查看会员详情
     */
    @RequestMapping(value = "/info",method = RequestMethod.GET)
    public AppWebResult getUserInfo(Integer userId){
        log.info("查看会员详情查看--->"+userId);
        AppWebResult<SaleUser> result = new AppWebResult();
        userService.userInfo(userId,result);
        log.info("查看会员详情返回--->"+result);
        return result;
    }


}
