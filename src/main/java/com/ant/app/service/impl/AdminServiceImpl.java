package com.ant.app.service.impl;

import com.ant.app.Constants;
import com.ant.app.dao.AdminDao;
import com.ant.app.dao.SysLogDao;
import com.ant.app.entity.AppWebResult;
import com.ant.app.entity.LayUiResult;
import com.ant.app.entity.req.AdminLogin;
import com.ant.app.entity.req.AdminUse;
import com.ant.app.entity.req.LayUiAuToReq;
import com.ant.app.model.SysAdmin;
import com.ant.app.model.SysLog;
import com.ant.app.service.AdminService;
import com.ant.app.utils.StringTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lchunlei
 * @date 2018/12/26
 */
@Service
public class AdminServiceImpl implements AdminService{
    private static final Logger log = LoggerFactory.getLogger(AdminServiceImpl.class);
    @Autowired
    AdminDao adminDao;
    @Autowired
    SysLogDao sysLogDao;

    @Override
    public void webLogin(AdminLogin login, AppWebResult<Integer> result) {
        SysAdmin admin = adminDao.selectAdminByPhoneNum(login.getPhoneNum());
        if(admin==null){
            result.setFail(Constants.USER_NULL);
        }else if(!admin.getPassword().equals(login.getPassword())){
            result.setFail(Constants.PWD_ERR);
        }else if(!admin.getUsable().equals(0)){
            result.setFail(Constants.USE_ERR);
        }else {
            result.setData(admin.getAdminId());
        }
    }

    @Override
    public void findAdmins(LayUiAuToReq req, LayUiResult<SysAdmin> result) {
        req.setStartNum((req.getPage()-1)*10);
        Integer totallNumAll = adminDao.selectTotalNum(req);
        if(totallNumAll>0){
            result.setCode(0);
            result.setMsg("成功");
            result.setCount(totallNumAll);
            result.setData(adminDao.selectByPage(req));
        }else{
            result.setCode(Constants.PAGE_ERROR_CODE);
            result.setMsg(Constants.NOT_MORE_INFO);
        }
    }

    @Override
    @Transactional
    public void addAdmin(SysAdmin sysAdmin, Integer nowAdminId, AppWebResult result) {
        Integer s = adminDao.insertAdmin(sysAdmin);
        if(!s.equals(1)){
            result.setFail(Constants.ERR_MSG);
        }else {
            SysLog sysLog = new SysLog(nowAdminId,"addAdmin_"+sysAdmin.getPhoneNum());
            sysLogDao.insertSysLog(sysLog);
        }
    }

    @Override
    @Transactional
    public void updaAdminUse(Integer adminId, Integer nowAdminId, AppWebResult result) {
        SysAdmin sysAdmin = adminDao.selectAdminById(adminId);
        if(sysAdmin==null){
            result.setFail(Constants.NULL_DATA);
        }else {
            Integer use = 0;
            if(sysAdmin.getUsable().equals(0)){
                use=1;
            }
            Integer s = adminDao.updateAdminStatus(use,adminId);
            if(!s.equals(1)){
                result.setFail(Constants.ERR_MSG);
            }else {
                SysLog sysLog = new SysLog(nowAdminId,"updaAdminUse_"+adminId);
                sysLogDao.insertSysLog(sysLog);
            }
        }
    }

    @Override
    public void updaAdmin(SysAdmin newInfo, Integer nowAdminId, AppWebResult result) {
        SysAdmin sysAdmin = adminDao.selectAdminById(nowAdminId);
        if(sysAdmin==null){
            result.setFail(Constants.NULL_DATA);
        }else {
            if(StringTool.isRealStr(newInfo.getPassword())){
                sysAdmin.setPassword(newInfo.getPassword());
            }
            if(StringTool.isRealStr(newInfo.getNickName())){
                sysAdmin.setNickName(newInfo.getNickName());
            }
            if(StringTool.isRealStr(newInfo.getPhoneNum())){
                sysAdmin.setPhoneNum(newInfo.getPhoneNum());
            }
            adminDao.updateAdmin(sysAdmin);
        }
    }

    @Override
    public void findAdmin(Integer adminId, AppWebResult<SysAdmin> result) {
        SysAdmin sysAdmin = adminDao.selectAdminById(adminId);
        if(sysAdmin==null){
            result.setFail(Constants.NULL_DATA);
        }else {
            result.setData(sysAdmin);
        }
    }


}
