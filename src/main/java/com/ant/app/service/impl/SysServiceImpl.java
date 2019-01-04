package com.ant.app.service.impl;

import com.ant.app.dao.IncomeDao;
import com.ant.app.entity.AppWebResult;
import com.ant.app.entity.resp.IndexData;
import com.ant.app.service.SysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.util.Date;

/**
 * @author lchunlei
 * @date 2019/1/4
 */
@Service
public class SysServiceImpl implements SysService{

    @Autowired
    IncomeDao incomeDao;

    @Override
    public void initPcIndex(AppWebResult<IndexData> result) {
        DateFormat format1 = new java.text.SimpleDateFormat("yyyy-MM-dd");
        String s = format1.format(new Date().getTime()-24*60*60*1000);
        String sTime = s+" 00:00:00";
        String eTime = s+" 23:59:59";
        Integer totalIncome = incomeDao.getTotalIncome();
        Integer otalUser = incomeDao.getTotalUser();
        Integer yesTotalIncome = incomeDao.getYesTotalIncome(sTime,eTime);
        Integer yesTotalUser = incomeDao.getYesTotalUser(sTime,eTime);
        IndexData data = new IndexData();
        data.setTotalIncome(totalIncome);
        data.setTotalUser(otalUser);
        data.setYesIncome(yesTotalIncome);
        data.setYesUser(yesTotalUser);
        result.setData(data);
    }


}
