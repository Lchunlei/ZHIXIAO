package com.ant.app.controller.pc;

import com.ant.app.Constants;
import com.ant.app.entity.AppWebResult;
import com.ant.app.service.InComeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lchunlei
 * @date 2019/1/11
 */
@RestController
@RequestMapping("/timer")
public class TimerController {
    private static final Logger log = LoggerFactory.getLogger(TimerController.class);
    @Autowired
    InComeService inComeService;

    //计算效益分红
    @RequestMapping(value = "/bonus",method = RequestMethod.GET)
    @Scheduled(cron = "0 0 5 * * *")
    public void getBonus(){
        //yyyy-MM-dd HH:mm:ss
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        String nowTime = sdf.format(new Date());
        if(Constants.XIAO_YI_SET_TIME.equals(nowTime)){
            log.info("-----今日不是一号---"+nowTime);
        }else {
            //先刷新是否回本
            log.info("-----刷新是否回本S---");
            inComeService.reBackMoney();
            log.info("-----刷新是否回本E---");
            //后计算效益分红
            log.info("-----计算效益分红S---");
            inComeService.reXiaoYiFenHong();
            log.info("-----计算效益分红E---");
        }
    }
}
