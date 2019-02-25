package com.ant.app.controller.sj;

import com.ant.app.Constants;
import com.ant.app.entity.LayUiResult;
import com.ant.app.entity.req.LayUiAuToReq;
import com.ant.app.model.UserDraw;
import com.ant.app.model.UserIncome;
import com.ant.app.model.WebBIncome;
import com.ant.app.service.InComeService;
import com.ant.app.sql.SysTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpSession;

/**
 * Created by sfb_liuchunlei on 2019/2/2.
 */
@RestController
@RequestMapping("/sj/income")
public class IncomeController {
    private static final Logger log = LoggerFactory.getLogger(IncomeController.class);

    @Autowired
    InComeService inComeService;


    /**
     * 个人收益列表 PC
     */
    @RequestMapping(value = "/myList",method = RequestMethod.POST)
    public LayUiResult myPcList(HttpSession session, LayUiAuToReq layUiAuToReq){
        log.info("个人收益列表 PC查看--->"+layUiAuToReq);
        layUiAuToReq.tableSet(SysTable.USER_INCOME,SysTable.INCOME_ID,SysTable.C_TIME,SysTable.INS,SysTable.INCOME_ID);
        LayUiResult<UserIncome> result = new LayUiResult();
        Object obj = session.getAttribute(Constants.USER_ID);
        if(obj==null){
            result.setCode(Constants.PAGE_ERROR_CODE);
            result.setMsg(Constants.NO_LOGIN);
        }else {
            Integer nowUserId = (int)obj;
            layUiAuToReq.setAntherWhere(" userId="+nowUserId);
            inComeService.findPcIncomes(layUiAuToReq,result);
        }
        return result;
    }

    /**
     * B网个人收益列表
     */
    @RequestMapping(value = "/bin",method = RequestMethod.POST)
    public LayUiResult myBin(HttpSession session, LayUiAuToReq layUiAuToReq){
        log.info("B网个人收益列表--->"+layUiAuToReq);
        layUiAuToReq.tableSet(SysTable.WEB_B_INCOME,SysTable.WEB_B_ID,SysTable.C_TIME,SysTable.INS,SysTable.WEB_B_ID);
        LayUiResult<WebBIncome> result = new LayUiResult();
        Object obj = session.getAttribute(Constants.USER_ID);
        if(obj==null){
            result.setCode(Constants.PAGE_ERROR_CODE);
            result.setMsg(Constants.NO_LOGIN);
        }else {
            Integer nowUserId = (int)obj;
            layUiAuToReq.setAntherWhere(" userId="+nowUserId);
            inComeService.findBwebIncomes(layUiAuToReq,result);
        }
        return result;
    }



}
