package com.ant.app.sql;

import com.ant.app.entity.req.LayUiAuToReq;
import com.ant.app.utils.StringTool;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author lchunlei
 * @date 2018/12/26
 */
public class LayuiAutoPageSql {
    public String reqList(LayUiAuToReq reqList){
        return new SQL() {
            {
                SELECT("*");
                FROM(reqList.getTableName());
                if(StringTool.isRealStr(reqList.getSearchValue())) {
                    WHERE(reqList.getSeleName()+" LIKE CONCAT('%', #{searchValue},'%')");
                }
                if(StringTool.isRealStr(reqList.getsDate())&&StringTool.isRealStr(reqList.geteDate())) {
                    WHERE(reqList.getSeleTime()+">#{sDate} AND "+reqList.getSeleTime()+"<#{eDate}");
                }
                if(StringTool.isRealStr(reqList.getAntherWhere())) {
                    WHERE(reqList.getAntherWhere());
                }
            }
        }.toString()+ " ORDER BY "+reqList.getTableSort()+" DESC"+" limit" + " #{startNum},10";
    }

    public String reqListTotal(LayUiAuToReq reqList){
        return new SQL() {
            {
                SELECT("COUNT("+reqList.getTableKey()+")");
                FROM(reqList.getTableName());
                if(StringTool.isRealStr(reqList.getSearchValue())) {
                    WHERE(reqList.getSeleName()+" LIKE CONCAT('%', #{searchValue},'%')");
                }
                if(StringTool.isRealStr(reqList.getsDate())&&StringTool.isRealStr(reqList.geteDate())) {
                    WHERE(reqList.getSeleTime()+">#{sDate} AND "+reqList.getSeleTime()+"<#{eDate}");
                }
                if(StringTool.isRealStr(reqList.getAntherWhere())) {
                    WHERE(reqList.getAntherWhere());
                }
            }
        }.toString();
    }
}
