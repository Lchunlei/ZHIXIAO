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

    @Insert("INSERT INTO sale_user(`userName`,`userNick`,`phoneNum`,`treeSupId`,`puserId`, `refereeId`,`firstPwd`,`secondPwd`,`thirdPwd`,`registeCore`,`registeCoreMoney`,`joinMoney`,`cTime`) VALUES (#{userName},#{userNick},#{phoneNum},#{treeSupId},#{puserId},#{refereeId},#{firstPwd},#{secondPwd},#{thirdPwd},#{registeCore},#{registeCoreMoney},#{joinMoney},NOW())")
    Integer insertUser(SaleUser saleUser);

    @Select("SELECT * FROM sale_user WHERE phoneNum=#{phoneNum}")
    SaleUser selectUserByPhoneNum(@Param("phoneNum") String phoneNum);

    @Select("SELECT * FROM sale_user WHERE userId=${userId}")
    SaleUser selectUserById(@Param("userId") Integer userId);

    @Select("SELECT * FROM sale_user WHERE treeRight=${treeRight}")
    SaleUser selectUserBytreeRight(@Param("treeRight") Integer treeRight);

    @Select("SELECT * FROM sale_user WHERE puserId=${puserId} ORDER BY userId")
    List<SaleUser> selectUsersByPusID(@Param("puserId") Integer puserId);

    @Select("SELECT * FROM sale_user WHERE treeSupId=${treeSupId} ORDER BY userId")
    List<SaleUser> selectUsersByTreeSupId(@Param("treeSupId") Integer treeSupId);

    @SelectProvider(type=LayuiAutoPageSql.class, method="reqList")
    List<SaleUser> selectByPage(LayUiAuToReq layUiAuToReq);
    @SelectProvider(type=LayuiAutoPageSql.class, method="reqListTotal")
    Integer selectTotalNum(LayUiAuToReq layUiAuToReq);

    /**
     * 数据修改
     */
    @Update("UPDATE sale_user SET userStatus=${userStatus},uTime=NOW() WHERE userId=${userId}")
    Integer updateUserStatus(@Param("userStatus")Integer userStatus,@Param("userId")Integer userId);

    @Update("UPDATE sale_user SET registeCore=1,registeCoreMoney=${registeCoreMoney},uTime=NOW() WHERE userId=${userId}")
    Integer upUserToCore(@Param("registeCoreMoney")Integer registeCoreMoney);

    @Update("UPDATE sale_user SET treeLeft=${treeLeft} WHERE userId=${userId}")
    Integer upUserLeft(@Param("treeLeft")Integer treeLeft,@Param("userId") Integer userId);

    @Update("UPDATE sale_user SET treeRight=${treeRight} WHERE userId=${userId}")
    Integer upUserRight(@Param("treeRight")Integer treeRight,@Param("userId") Integer userId);

    @Update("UPDATE sale_user SET firstPwd=#{firstPwd},secondPwd=#{secondPwd},thirdPwd=#{thirdPwd},uTime=NOW() WHERE userId=${userId}")
    Integer updateUserPwd(SaleUser saleUser);


}
