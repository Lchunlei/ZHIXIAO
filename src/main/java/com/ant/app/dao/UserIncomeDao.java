package com.ant.app.dao;

import com.ant.app.model.UserIncome;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sfb_liuchunlei on 2019/1/6.
 */
@Repository
public interface UserIncomeDao {

    @Insert("INSERT INTO user_income( `userId`,`incomeType`,`ins`,`money`,`cTime`) VALUES (#{userId},${incomeType},#{ins},#{money},NOW())")
    Integer insertUserIncome(UserIncome userIncome);

    @Select("SELECT * FROM user_income WHERE userId=#{userId} AND cTime>#{sTime} ORDER BY incomeId LIMIT 20")
    List<UserIncome> selectMyIncomes(@Param("userId") Integer userId, @Param("sTime")String sTime);

    @Select("SELECT SUM(money) FROM user_income WHERE userId=#{userId} AND incomeType=4")
    Integer selectMyAllZuZhiJiang(@Param("userId") Integer userId);

    @Select("SELECT SUM(money) FROM user_income WHERE userId=#{userId}")
    Integer selectMyAllIncome(@Param("userId") Integer userId);


}
