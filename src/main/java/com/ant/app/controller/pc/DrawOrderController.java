package com.ant.app.controller.pc;

import com.ant.app.Constants;
import com.ant.app.entity.AppWebResult;
import com.ant.app.entity.LayUiResult;
import com.ant.app.entity.req.CheckDraw;
import com.ant.app.entity.req.LayUiAuToReq;
import com.ant.app.model.UserDraw;
import com.ant.app.service.UserDrawService;
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
 * @date 2019/1/7
 */
@RestController
@RequestMapping("/pc/draw")
public class DrawOrderController {
    private static final Logger log = LoggerFactory.getLogger(DrawOrderController.class);
    @Autowired
    UserDrawService userDrawService;

    /**
     * 提现列表查看
     */
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public LayUiResult userList(LayUiAuToReq layUiAuToReq){
        log.info("提现列表查看查看--->"+layUiAuToReq);
        layUiAuToReq.tableSet(SysTable.USER_DRAW,SysTable.DRAW_ID,SysTable.C_TIME,SysTable.DRAW_WAY_USER,SysTable.DRAW_ID);
        LayUiResult<UserDraw> result = new LayUiResult();
        userDrawService.findDraws(layUiAuToReq,result);
        log.info("提现列表查看返回--->"+result);
        return result;
    }

    /**
     * 提现详情查看
     */
    @RequestMapping(value = "/byId",method = RequestMethod.GET)
    public AppWebResult findDraw(Integer drawId){
        log.info("提现详情查看--->"+drawId);
        AppWebResult<UserDraw> result = new AppWebResult();
        userDrawService.findDrawById(drawId,result);
        log.info("提现详情查看返回--->"+result);
        return result;
    }

    /**
     * 提现处理
     */
    @RequestMapping(value = "/check",method = RequestMethod.POST)
    public AppWebResult checkDraw(HttpSession session,CheckDraw checkDraw){
        log.info("提现处理--->"+checkDraw);
        AppWebResult result = new AppWebResult();
        Object obj = session.getAttribute(Constants.ADMIN_ID);

        if(obj==null){
            result.setResultCode(Constants.NO_LOGIN_CODE);
            result.setResultMsg(Constants.NO_LOGIN);
        }else {
            Integer nowAdminId = (int)obj;
            checkDraw.setAdminId(nowAdminId);
            userDrawService.checkDraw(checkDraw,result);
        }

        log.info("提现处理返回--->"+result);
        return result;
    }


}
