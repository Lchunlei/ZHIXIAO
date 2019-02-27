package com.ant.app.dao;

import com.ant.app.entity.req.LayUiAuToReq;
import com.ant.app.model.SaleUser;
import com.ant.app.model.SysAdmin;
import com.ant.app.sql.LayuiAutoPageSql;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lchunlei
 * @date 2018/12/12
 */
@Repository
public interface UserDao {

    @Insert("INSERT INTO sale_user(`userName`,`userNum`,`treeSupId`,`puserId`, `refereeId`,`firstPwd`,`secondPwd`,`thirdPwd`,`registeCore`,`registeCoreMoney`,`joinMoney`,`coin`,`cTime`) VALUES (#{userName},#{userNum},#{treeSupId},#{puserId},#{refereeId},#{firstPwd},#{secondPwd},#{thirdPwd},#{registeCore},#{registeCoreMoney},#{joinMoney},#{coin},NOW())")
    Integer insertUser(SaleUser saleUser);

    @Insert("INSERT INTO sale_user(`userName`,`phoneNum`, `firstPwd`,`secondPwd`,`thirdPwd`,`registeCore`,`registeCoreMoney`,`cTime`) VALUES (#{userName},#{phoneNum},#{firstPwd},#{secondPwd},#{thirdPwd},#{registeCore},#{registeCoreMoney},NOW())")
    Integer insertCore(SaleUser saleUser);

    @Select("SELECT * FROM sale_user WHERE phoneNum=#{phoneNum}")
    SaleUser selectUserByPhoneNum(@Param("phoneNum") String phoneNum);

    @Select("SELECT * FROM sale_user WHERE userNum=#{userNum}")
    SaleUser selectUserByUserNum(@Param("userNum") String userNum);

    @Select("SELECT MIN(userId) FROM sale_user")
    Integer selectMinId();

    @Select("SELECT * FROM sale_user WHERE userId=${userId}")
    SaleUser selectUserById(@Param("userId") Integer userId);

    @Select("SELECT * FROM sale_user WHERE treeRight=${treeRight}")
    SaleUser selectUserBytreeRight(@Param("treeRight") Integer treeRight);

    @Select("SELECT * FROM sale_user WHERE puserId=${puserId} ORDER BY userId")
    List<SaleUser> selectUsersByPusID(@Param("puserId") Integer puserId);

    @Select("SELECT * FROM sale_user WHERE treeSupId=${treeSupId} ORDER BY userId")
    List<SaleUser> selectUsersByTreeSupId(@Param("treeSupId") Integer treeSupId);

    @Select("SELECT * FROM sale_user WHERE cTime<#{sTime} ORDER BY userId DESC LIMIT 30")
    List<SaleUser> selectUsersByCTime(@Param("sTime")String sTime);

    @Select("SELECT * FROM sale_user WHERE refereeId=#{refereeId}")
    List<SaleUser> selectUsersByRefereeId(@Param("refereeId")String refereeId);

    @Select("SELECT * FROM sale_user ORDER BY minTotal DESC LIMIT 15")
    List<SaleUser> selectUserMinHead();

    @Select("SELECT userId,balance,joinMoney FROM sale_user WHERE reBack=0")
    List<SaleUser> selectAllNoReBack();

    @Select("SELECT SUM(joinMoney) FROM sale_user WHERE cTime>#{sTime} AND cTime<#{eTime}")
    Integer selectMonthIncome(@Param("sTime")String sTime,@Param("eTime")String eTime);

    @Select("SELECT MAX(userId) FROM sale_user")
    Integer selectUserMaxId();

    @SelectProvider(type=LayuiAutoPageSql.class, method="reqList")
    List<SaleUser> selectByPage(LayUiAuToReq layUiAuToReq);
    @SelectProvider(type=LayuiAutoPageSql.class, method="reqListTotal")
    Integer selectTotalNum(LayUiAuToReq layUiAuToReq);

    @SelectProvider(type=LayuiAutoPageSql.class, method="reqList")
    List<SaleUser> selectByPageRank(LayUiAuToReq layUiAuToReq);
    @SelectProvider(type=LayuiAutoPageSql.class, method="reqListTotal")
    Integer selectTotalNumRank(LayUiAuToReq layUiAuToReq);

    /**
     * 数据修改
     */
    @Update("UPDATE sale_user SET userStatus=${userStatus},uTime=NOW() WHERE userId=${userId}")
    Integer updateUserStatus(@Param("userStatus")Integer userStatus,@Param("userId")Integer userId);

    @Update("UPDATE sale_user SET registeCore=1,registeCoreMoney=${registeCoreMoney},uTime=NOW() WHERE userId=${userId}")
    Integer upUserToCore(@Param("registeCoreMoney")Integer registeCoreMoney,@Param("userId")Integer userId);

    @Update("UPDATE sale_user SET treeLeft=${treeLeft} WHERE userId=${userId}")
    Integer upUserLeft(@Param("treeLeft")Integer treeLeft,@Param("userId") Integer userId);

    @Update("UPDATE sale_user SET treeRight=${treeRight} WHERE userId=${userId}")
    Integer upUserRight(@Param("treeRight")Integer treeRight,@Param("userId") Integer userId);

    @Update("UPDATE sale_user SET reBack=1 WHERE userId=${userId}")
    Integer haveReBack(@Param("userId") Integer userId);

    @Update("UPDATE sale_user SET treeSupId=${treeSupId},puserId=${puserId},refereeId=${refereeId},joinMoney=${joinMoney},coin=${coin},uTime=NOW() WHERE userId=${userId}")
    Integer upNewUser(SaleUser saleUser);

    @Update("UPDATE sale_user SET firstPwd=#{firstPwd},secondPwd=#{secondPwd},thirdPwd=#{thirdPwd},userName=#{userName},uTime=NOW() WHERE userId=${userId}")
    Integer updateUserPwd(SaleUser saleUser);

    /**
     * 收入修改
     */
    @Update("UPDATE sale_user SET balance=${money}+balance WHERE userId=${userId}")
    Integer addBalance(@Param("money")Integer money,@Param("userId") Integer userId);

    @Update("UPDATE sale_user SET bWebIn=${bWebIn}+bWebIn WHERE userId=${userId}")
    Integer addBwebIn(@Param("bWebIn")Integer bWebIn,@Param("userId") Integer userId);

    @Update("UPDATE sale_user SET luckEnd=1+luckEnd,balance=${money}+balance WHERE userId=${userId}")
    Integer addOneSunLuck(@Param("money")Integer money,@Param("userId") Integer userId);

    @Update("UPDATE sale_user SET balance=balance-${money} WHERE userId=${userId}")
    Integer minusBalance(@Param("money")Integer money,@Param("userId") Integer userId);

    @Update("UPDATE sale_user SET leftTotal=1+leftTotal WHERE userId=${userId}")
    Integer addLeftTotal(@Param("userId") Integer userId);

    @Update("UPDATE sale_user SET rightTotal=1+rightTotal WHERE userId=${userId}")
    Integer addRightTotal(@Param("userId") Integer userId);

    @Update("UPDATE sale_user SET minTotal=${minTotal} WHERE userId=${userId}")
    Integer reSetMin(@Param("minTotal") Integer minTotal,@Param("userId") Integer userId);

}
