package com.ant.app.service.impl;

import com.ant.app.Constants;
import com.ant.app.dao.UserDao;
import com.ant.app.entity.AppWebResult;
import com.ant.app.entity.tree.TreNode;
import com.ant.app.model.SaleUser;
import com.ant.app.service.TreeService;
import com.ant.app.utils.TreeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lchunlei
 * @date 2018/12/25
 */
@Service
public class TreeServiceImpl implements TreeService {
    private final static String ME = "根节点";
    @Resource
    UserDao userDao;

    //初始化用户关系树
    @Override
    public void initTree(Integer userId, AppWebResult<TreNode> result) {
        SaleUser supUser = userDao.selectUserById(userId);
        TreNode supTreNode = new TreNode(ME);
        if(supUser.getTreeLeft()!=null){
            TreNode floor2l = new TreNode(supUser.getTreeLeft().toString());
            //第3层
            SaleUser u2l = userDao.selectUserById(supUser.getTreeLeft());

            setTotal(u2l,floor2l);
            supTreNode.getChildren().add(floor2l);
        }
        if(supUser.getTreeRight()!=null){
            TreNode floor2r = new TreNode(supUser.getTreeRight().toString());
            //第3层
            SaleUser u2l = userDao.selectUserById(supUser.getTreeRight());

            setTotal(u2l,floor2r);
            supTreNode.getChildren().add(floor2r);
        }
        result.setData(supTreNode);
    }

    @Override
    public void preFindTree(Integer userId, String secondPwd, AppWebResult result) {
        SaleUser supUser = userDao.selectUserById(userId);
        if(supUser==null){
            result.setFail(Constants.USERID_NULL);
        }else {
            if(!supUser.getSecondPwd().equals(secondPwd)){
                result.setFail(Constants.PWD_ERR);
            }
        }
    }

    private void setTotal(SaleUser user,TreNode node){
        if(user.getTreeLeft()!=null){
            TreNode floor3l = new TreNode(user.getTreeLeft().toString());
            node.getChildren().add(floor3l);
            //第4层
            SaleUser u3l = userDao.selectUserById(user.getTreeLeft());
            TreNode node3 = new TreNode(user.getTreeLeft().toString());
            setSuns(u3l,floor3l);
        }
        if(user.getTreeRight()!=null){
            TreNode floor3r = new TreNode(user.getTreeRight().toString());
            node.getChildren().add(floor3r);
            //第4层
            SaleUser u3l = userDao.selectUserById(user.getTreeRight());
            TreNode node3 = new TreNode(user.getTreeRight().toString());
            setSuns(u3l,floor3r);
        }
    }
    private void setSuns(SaleUser user,TreNode node){
        if(user.getTreeLeft()!=null){
            TreNode floor2l = new TreNode(user.getTreeLeft().toString());
            node.getChildren().add(floor2l);
        }
        if(user.getTreeRight()!=null){
            TreNode floor2r = new TreNode(user.getTreeRight().toString());
            node.getChildren().add(floor2r);
        }
    }

}
