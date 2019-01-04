package com.ant.app.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author lchunlei
 * @date 2019/1/4
 */
@Repository
public interface IncomeDao {

    @Select("SELECT SUM(joinMoney) FROM sale_user")
    Integer getTotalIncome();

    @Select("SELECT SUM(joinMoney) FROM sale_user WHERE cTime>#{sTime} AND cTime<#{eTime}")
    Integer getYesTotalIncome(@Param("sTime")String sTime,@Param("eTime")String eTime);

    @Select("SELECT COUNT(userId) FROM sale_user")
    Integer getTotalUser();

    @Select("SELECT COUNT(userId) FROM sale_user WHERE cTime>#{sTime} AND cTime<#{eTime}")
    Integer getYesTotalUser(@Param("sTime")String sTime,@Param("eTime")String eTime);


}
