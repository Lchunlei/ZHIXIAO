package com.ant.app.dao;

import com.ant.app.entity.req.LayUiAuToReq;
import com.ant.app.model.UserDraw;
import com.ant.app.model.UserIncome;
import com.ant.app.model.WebBIncome;
import com.ant.app.sql.LayuiAutoPageSql;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sfb_liuchunlei on 2019/1/6.
 */
@Repository
public interface UserIncomeDao {

    @Insert("INSERT INTO user_income( `userId`,`incomeType`,`ins`,`money`,`cTime`) VALUES (#{userId},${incomeType},#{ins},#{money},NOW())")
    Integer insertUserIncome(UserIncome userIncome);

    @Insert("INSERT INTO web_b_income( `userId`,`money`,`cTime`) VALUES (#{userId},#{money},NOW())")
    Integer insertBIncome(WebBIncome webBIncome);

    @Select("SELECT * FROM user_income WHERE userId=#{userId} AND cTime>#{sTime} ORDER BY incomeId DESC LIMIT 20")
    List<UserIncome> selectMyIncomes(@Param("userId") Integer userId, @Param("sTime")String sTime);

    @Select("SELECT SUM(money) FROM user_income WHERE userId=#{userId} AND incomeType=4")
    Integer selectMyAllZuZhiJiang(@Param("userId") Integer userId);

    @Select("SELECT SUM(money) FROM user_income WHERE userId=#{userId}")
    Integer selectMyAllIncome(@Param("userId") Integer userId);

    @SelectProvider(type=LayuiAutoPageSql.class, method="reqList")
    List<UserIncome> selectByPage(LayUiAuToReq layUiAuToReq);
    @SelectProvider(type=LayuiAutoPageSql.class, method="reqListTotal")
    Integer selectTotalNum(LayUiAuToReq layUiAuToReq);

    @SelectProvider(type=LayuiAutoPageSql.class, method="reqList")
    List<WebBIncome> selectBwebByPage(LayUiAuToReq layUiAuToReq);
    @SelectProvider(type=LayuiAutoPageSql.class, method="reqListTotal")
    Integer selectBwebTotalNum(LayUiAuToReq layUiAuToReq);

}
