package com.ant.app.controller.pc;

import com.ant.app.entity.LayUiResult;
import com.ant.app.entity.req.LayUiAuToReq;
import com.ant.app.model.SaleUser;
import com.ant.app.service.UserService;
import com.ant.app.sql.SysTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lchunlei
 * @date 2019/1/9
 */
@RestController
@RequestMapping("/pc/rank")
public class RankController {
    private static final Logger log = LoggerFactory.getLogger(RankController.class);
    @Autowired
    UserService userService;

    /**
     * 排行列表查看
     */
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public LayUiResult userList(LayUiAuToReq layUiAuToReq){
        log.info("排行列表查看--->"+layUiAuToReq);
        layUiAuToReq.tableSet(SysTable.SALE_USER,SysTable.USER_ID,SysTable.C_TIME,SysTable.USER_NAME,SysTable.MIN_TOTAL);
        LayUiResult<SaleUser> result = new LayUiResult();
        userService.findUsers(layUiAuToReq,result);
        log.info("排行列表查看返回--->"+result);
        return result;
    }

}
