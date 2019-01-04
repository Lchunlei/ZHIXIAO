package com.ant.app.service;

import com.ant.app.entity.AppWebResult;
import com.ant.app.entity.resp.IndexData;

/**
 * @author lchunlei
 * @date 2019/1/4
 */
public interface SysService {

    public void initPcIndex(AppWebResult<IndexData> result);

}
