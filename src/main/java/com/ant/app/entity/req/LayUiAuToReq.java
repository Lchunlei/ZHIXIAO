package com.ant.app.entity.req;

/**
 * @author lchunlei
 * @date 2018/12/26
 */
public class LayUiAuToReq {
    private Integer page;
    private Integer limit;
    private String searchValue;
    private String sDate;
    private String eDate;
    private String antherWhere;
    /**
     *  数据库字段如下
     **/
    private String tableName;
    private String tableKey;
    //筛选的时间段
    private String seleTime;
    //筛选名称
    private String seleName;
    //排序字段
    private String tableSort;
    //分页开始数
    private Integer startNum;

    @Override
    public String toString() {
        return "LayUiAuToReq{" +
                "page=" + page +
                ", limit=" + limit +
                ", searchValue='" + searchValue + '\'' +
                ", sDate='" + sDate + '\'' +
                ", eDate='" + eDate + '\'' +
                '}';
    }

    public void tableSet(String tablName,String tablKey,String selTime,String selName,String tablSort){
        this.tableName=tablName;
        this.tableKey=tablKey;
        this.seleTime=selTime;
        this.seleName=selName;
        this.tableSort=tablSort;
    }

    public String getAntherWhere() {
        return antherWhere;
    }

    public void setAntherWhere(String antherWhere) {
        this.antherWhere = antherWhere;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public String getsDate() {
        return sDate;
    }

    public void setsDate(String sDate) {
        this.sDate = sDate;
    }

    public String geteDate() {
        return eDate;
    }

    public void seteDate(String eDate) {
        this.eDate = eDate;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableKey() {
        return tableKey;
    }

    public void setTableKey(String tableKey) {
        this.tableKey = tableKey;
    }

    public String getSeleTime() {
        return seleTime;
    }

    public void setSeleTime(String seleTime) {
        this.seleTime = seleTime;
    }

    public String getSeleName() {
        return seleName;
    }

    public void setSeleName(String seleName) {
        this.seleName = seleName;
    }

    public String getTableSort() {
        return tableSort;
    }

    public void setTableSort(String tableSort) {
        this.tableSort = tableSort;
    }

    public Integer getStartNum() {
        return startNum;
    }

    public void setStartNum(Integer startNum) {
        this.startNum = startNum;
    }
}
