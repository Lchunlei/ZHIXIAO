package com.ant.app.exception;

import com.alibaba.fastjson.JSON;
import com.ant.app.entity.AppWebResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by sfb_liuchunlei on 2017/3/24 0024.
 */
@Component
public class AuToCatch implements HandlerExceptionResolver {
    private static final Logger log = LoggerFactory.getLogger(AuToCatch.class);
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView mv=new ModelAndView();
        AppWebResult respApp=new AppWebResult();
        /*  使用response返回 */
        response.setStatus(HttpStatus.OK.value()); //设置状态码200
        response.setContentType(MediaType.APPLICATION_JSON_VALUE); //设置ContentType
        response.setCharacterEncoding("UTF-8"); //避免乱码
        response.setHeader("Cache-Control", "no-cache, must-revalidate");
        respApp.setResultCode("R444");
        respApp.setResultMsg("系统异常！");
        log.error(ex.getMessage(),ex);

        try {
            response.getWriter().write(JSON.toJSONString(respApp));
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage(),e);
        }
        return mv;
    }
}
