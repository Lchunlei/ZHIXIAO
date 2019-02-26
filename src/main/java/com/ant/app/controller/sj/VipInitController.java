package com.ant.app.controller.sj;

import com.ant.app.Constants;
import com.ant.app.entity.AppWebResult;
import com.ant.app.entity.req.UserLogin;
import com.ant.app.entity.resp.UserWebInit;
import com.ant.app.service.UserWebService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by sfb_liuchunlei on 2019/2/23.
 */
@RestController
@RequestMapping("/vip")
public class VipInitController {
    private static final Logger log = LoggerFactory.getLogger(VipInitController.class);
    @Autowired
    UserWebService userWebService;

    /**
     * 初始化用户页面数据
     */
    @RequestMapping(value = "/web/init",method = RequestMethod.GET)
    public AppWebResult loginApp(HttpSession session){
        AppWebResult<UserWebInit> re = new AppWebResult();
        AppWebResult<Integer> result = new AppWebResult();
        Object obj = session.getAttribute(Constants.USER_ID);

        if(obj==null){
            result.setResultCode(Constants.NO_LOGIN_CODE);
            result.setResultMsg(Constants.NO_LOGIN);
            return result;
        }else {
            Integer nowUserId = (int)obj;
            userWebService.initUserWeb(nowUserId,re);
            return re;
        }
    }


}
