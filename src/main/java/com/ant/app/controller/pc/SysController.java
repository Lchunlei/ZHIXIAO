package com.ant.app.controller.pc;

import com.ant.app.Constants;
import com.ant.app.entity.AppWebResult;
import com.ant.app.entity.req.AdminLogin;
import com.ant.app.entity.resp.IndexData;
import com.ant.app.entity.tree.NewTree;
import com.ant.app.entity.tree.TreNode;
import com.ant.app.model.SaleUser;
import com.ant.app.model.SysAdmin;
import com.ant.app.service.*;
import com.ant.app.utils.StringTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author lchunlei
 * @date 2019/1/4
 */
@RestController
@RequestMapping("/pc/sys")
public class SysController {
    private static final Logger log = LoggerFactory.getLogger(SysController.class);

    @Autowired
    SysService sysService;
    @Autowired
    AdminService adminService;
    @Autowired
    TreeService treeService;
    @Autowired
    UserService userService;

    /**
     * 初始化首页
     */
    @RequestMapping(value = "/index/init",method = RequestMethod.GET)
    public AppWebResult initIndex(){
        AppWebResult<IndexData> result = new AppWebResult();
        sysService.initPcIndex(result);
        log.info("初始化首页--->"+result);
        return result;
    }

    /**
     * 初始化首页排行榜
     */
    @RequestMapping(value = "/index/rank",method = RequestMethod.GET)
    public AppWebResult initRank(){
        AppWebResult<List<SaleUser>> result = new AppWebResult();
        userService.userMinHead(result);
        log.info("初始化首页排行榜--->"+result);
        return result;
    }

    /**
     * 查看树
     */
    @RequestMapping(value = "/tree/init",method = RequestMethod.GET)
    public TreNode loginApp(Integer nodeUserId){
        AppWebResult<TreNode> result = new AppWebResult();
        log.info("PC加载树节点---》"+nodeUserId);
        treeService.initTree(nodeUserId,result);
        if(Constants.SUCCESS_CODE.equals(result.getResultCode())){
            return result.getData();
        }else {
            return null;
        }
    }

    /**
     * 查看新树
     */
    @RequestMapping(value = "/newTree",method = RequestMethod.GET)
    public List newTree(HttpSession session,String userNum){
        List<NewTree> list =new ArrayList();
        AppWebResult<NewTree> result = new AppWebResult();
        log.info("PC加载树节点userNum---》"+userNum);
        Object obj = session.getAttribute(Constants.ADMIN_ID);
        if(obj==null){
            result.setResultCode(Constants.NO_LOGIN_CODE);
            result.setResultMsg(Constants.NO_LOGIN);
        }else {
            if(StringTool.isRealStr(userNum)){
                treeService.initNewTree(userNum,result);
            }else {
                treeService.initNewTree(result);
            }
            if(Constants.SUCCESS_CODE.equals(result.getResultCode())){
                list.add(result.getData());
                return list;
            }else {
                return null;
            }
        }
        return list;
    }

    /**
     * 查看权限
     */
    @RequestMapping(value = "/auth",method = RequestMethod.GET)
    public AppWebResult findAdminAuth(HttpSession session){
        AppWebResult<SysAdmin> re = new AppWebResult();
        AppWebResult<Integer> result = new AppWebResult();
        Object obj = session.getAttribute(Constants.ADMIN_ID);

        if(obj==null){
            result.setResultCode(Constants.NO_LOGIN_CODE);
            result.setResultMsg(Constants.NO_LOGIN);
        }else {
            Integer nowAdminId = (int)obj;
            adminService.findAdmin(nowAdminId,re);
            if(re.getData()!=null){
                result.setData(re.getData().getSysRole());
            }else {
                result.setFail(re.getResultMsg());
            }
        }
        log.info("查看权限--->"+result);
        return result;
    }

}
