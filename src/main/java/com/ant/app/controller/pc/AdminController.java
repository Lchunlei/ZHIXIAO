package com.ant.app.controller.pc;

import com.ant.app.Constants;
import com.ant.app.entity.AppWebResult;
import com.ant.app.entity.LayUiResult;
import com.ant.app.entity.req.AdminLogin;
import com.ant.app.entity.req.LayUiAuToReq;
import com.ant.app.model.SysAdmin;
import com.ant.app.service.AdminService;
import com.ant.app.sql.SysTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpSession;

/**
 * @author lchunlei
 * @date 2018/12/26
 */
@RestController
@RequestMapping("/pc/admin")
public class AdminController {
    private static final Logger log = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    AdminService adminService;

    /**
     * 管理员登录
     * data携带用户ID，并保存到session
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public AppWebResult loginApp(HttpSession session, AdminLogin adminLogin){
        log.info("管理员登录--->"+adminLogin);
        AppWebResult<Integer> result = new AppWebResult();
        adminService.webLogin(adminLogin,result);
        if(Constants.SUCCESS_CODE.equals(result.getResultCode())){
            session.setAttribute(Constants.ADMIN_ID,result.getData());
        }
        log.info("管理员登录返回--->"+result);
        return result;
    }

    /**
     * 管理员列表查看
     */
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public LayUiResult adminList(HttpSession session, LayUiAuToReq layUiAuToReq){
        log.info("管理员列表查看--->"+layUiAuToReq);
        layUiAuToReq.tableSet(SysTable.SYS_ADMIN,SysTable.ADMIN_ID,SysTable.C_TIME,SysTable.OHONE_NUM,SysTable.ADMIN_ID);
        LayUiResult<SysAdmin> result = new LayUiResult();
        adminService.findAdmins(layUiAuToReq,result);
        log.info("管理员列表查看返回--->"+result);
        return result;
    }

    /**
     * 查看自身
     */
    @RequestMapping(value = "/find",method = RequestMethod.GET)
    public AppWebResult findAdmin(HttpSession session){
        AppWebResult<SysAdmin> result = new AppWebResult();
        Object obj = session.getAttribute(Constants.ADMIN_ID);

        if(obj==null){
            result.setResultCode(Constants.NO_LOGIN_CODE);
            result.setResultMsg(Constants.NO_LOGIN);
        }else {
            Integer nowAdminId = (int)obj;
            adminService.findAdmin(nowAdminId,result);
        }
        log.info("管理员查看自身--->"+result);
        return result;
    }

    /**
     * 修改自身
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public AppWebResult adminList(HttpSession session,SysAdmin sysAdmin){
        AppWebResult result = new AppWebResult();
        Object obj = session.getAttribute(Constants.ADMIN_ID);

        if(obj==null){
            result.setResultCode(Constants.NO_LOGIN_CODE);
            result.setResultMsg(Constants.NO_LOGIN);
        }else {
            Integer nowAdminId = (int)obj;
            adminService.updaAdmin(sysAdmin,nowAdminId,result);
        }
        log.info("管理员查看自身--->"+result);
        return result;
    }

    /**
     * 创建管理员
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public AppWebResult addAdmin(HttpSession session, SysAdmin sysAdmin){
        log.info("创建管理员查看--->"+sysAdmin);
        AppWebResult result = new AppWebResult();
        Object obj = session.getAttribute(Constants.ADMIN_ID);

        if(obj==null){
            result.setResultCode(Constants.NO_LOGIN_CODE);
            result.setResultMsg(Constants.NO_LOGIN);
        }else {
            Integer nowAdminId = (int)obj;
            adminService.addAdmin(sysAdmin,nowAdminId,result);
        }

        log.info("创建管理员返回--->"+result);
        return result;
    }

    /**
     * 更改管理员状态
     */
    @RequestMapping(value = "/use",method = RequestMethod.GET)
    public AppWebResult updateAdminUse(HttpSession session,Integer adminId){
        log.info("更改管理员状态查看--->"+adminId);
        AppWebResult result = new AppWebResult();
        Object obj = session.getAttribute(Constants.ADMIN_ID);

        if(obj==null){
            result.setResultCode(Constants.NO_LOGIN_CODE);
            result.setResultMsg(Constants.NO_LOGIN);
        }else {
            Integer nowAdminId = (int)obj;
            adminService.updaAdminUse(adminId,nowAdminId,result);
        }

        log.info("更改管理员状态返回--->"+result);
        return result;
    }

}
