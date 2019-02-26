package com.ant.app.service;

import com.ant.app.entity.AppWebResult;
import com.ant.app.entity.resp.UserWebInit;

/**
 * Created by sfb_liuchunlei on 2019/2/26.
 */
public interface UserWebService {

    public void initUserWeb(Integer nowUserId,AppWebResult<UserWebInit> result);

}
