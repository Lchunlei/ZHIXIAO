package com.ant.app.service;

import com.ant.app.entity.AppWebResult;
import com.ant.app.entity.tree.TreNode;

/**
 * @author lchunlei
 * @date 2018/12/25
 */
public interface TreeService {

    public void initTree(Integer userId, AppWebResult<TreNode> result);

    public void preFindTree(Integer userId, String secondPwd, AppWebResult result);


}
