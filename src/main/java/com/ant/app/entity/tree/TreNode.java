package com.ant.app.entity.tree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lchunlei
 * @date 2018/12/25
 */
public class TreNode implements Serializable{
    private String name;
    private String symbol;
    private Integer symbolSize;
    private List<TreNode> children;

    public TreNode() {
    }

    public TreNode(String name) {
        this.name = name;
        this.symbol="http://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=ico%E5%9B%BE%E6%A0%87&step_word=&hs=0&pn=30&spn=0&di=121220&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=0&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=-1&cs=876553535%2C3281807237&os=4155647016%2C119343791&simid=4202118878%2C535746215&adpicid=0&lpn=0&ln=1790&fr=&fmq=1546425962544_R&fm=rs10&ic=undefined&s=undefined&hd=undefined&latest=undefined&copyright=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=icon&objurl=http%3A%2F%2Fimage.tupian114.com%2F20140424%2F14534208.png.238.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Bp7rtwg889_z%26e3Bv54AzdH3Fp7rtwgAzdH3Fqqp7ktw5_z%26e3Bip4s&gsm=0&rpstart=0&rpnum=0&islist=&querylist=&force=undefined";
        this.symbolSize=40;
        this.children = new ArrayList();
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "name='" + name + '\'' +
                ", children=" + children.size() +
                '}';
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Integer getSymbolSize() {
        return symbolSize;
    }

    public void setSymbolSize(Integer symbolSize) {
        this.symbolSize = symbolSize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TreNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreNode> children) {
        this.children = children;
    }
}
