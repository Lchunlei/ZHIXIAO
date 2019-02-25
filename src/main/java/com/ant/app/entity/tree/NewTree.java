package com.ant.app.entity.tree;

import com.ant.app.model.SaleUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sfb_liuchunlei on 2019/2/21.
 */
public class NewTree {
    private String name;
    private String userNum;
    private Integer cardType;//默认为1
    private Integer leftTotal;
    private Integer rightTotal;
    private Integer leftSurplus;
    private Integer rightSurplus;
    private List<NewTree> children;

    public NewTree() {
    }

    public NewTree(SaleUser user) {
        this.name = user.getUserName();
        this.cardType = 1;
        this.userNum = user.getUserNum();
        this.leftTotal = user.getLeftTotal();
        this.rightTotal = user.getRightTotal();
        this.leftSurplus = user.getLeftSurplus();
        this.rightSurplus = user.getRightSurplus();
        this.children = new ArrayList();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public Integer getCardType() {
        return cardType;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    public Integer getLeftTotal() {
        return leftTotal;
    }

    public void setLeftTotal(Integer leftTotal) {
        this.leftTotal = leftTotal;
    }

    public Integer getRightTotal() {
        return rightTotal;
    }

    public void setRightTotal(Integer rightTotal) {
        this.rightTotal = rightTotal;
    }

    public Integer getLeftSurplus() {
        return leftSurplus;
    }

    public void setLeftSurplus(Integer leftSurplus) {
        this.leftSurplus = leftSurplus;
    }

    public Integer getRightSurplus() {
        return rightSurplus;
    }

    public void setRightSurplus(Integer rightSurplus) {
        this.rightSurplus = rightSurplus;
    }

    public List<NewTree> getChildren() {
        return children;
    }

    public void setChildren(List<NewTree> children) {
        this.children = children;
    }
}
