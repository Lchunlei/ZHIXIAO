package com.ant.app.dao;

import com.ant.app.entity.req.LayUiAuToReq;
import com.ant.app.model.UserDraw;
import com.ant.app.sql.LayuiAutoPageSql;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lchunlei
 * @date 2019/1/7
 */
@Repository
public interface UserDrawDao {

    @Insert("INSERT INTO user_draw(`userId`,`drawMoney`,`realDrawMoney`,`serviceMoney`, `ins`,`drawWay`,`drawWayUser`,`drawWayAccount`,`cTime`) VALUES (#{userId},#{drawMoney},#{realDrawMoney},#{serviceMoney},#{ins},#{drawWay},#{drawWayUser},#{drawWayAccount},NOW())")
    Integer insertUserDraw(UserDraw userDraw);

    @Select("SELECT * FROM user_draw WHERE drawId=#{drawId}")
    UserDraw selectDrawById(@Param("drawId") Integer drawId);

    @Select("SELECT * FROM user_draw WHERE userId=#{userId} ORDER BY drawId LIMIT 20")
    List<UserDraw> selectMyDraws(@Param("userId") Integer userId);

    @SelectProvider(type=LayuiAutoPageSql.class, method="reqList")
    List<UserDraw> selectByPage(LayUiAuToReq layUiAuToReq);
    @SelectProvider(type=LayuiAutoPageSql.class, method="reqListTotal")
    Integer selectTotalNum(LayUiAuToReq layUiAuToReq);

    @Update("UPDATE user_draw SET drawStatus=${drawStatus},adminId=${adminId},ins=#{ins},confirmTime=NOW() WHERE drawId=${drawId}")
    Integer updateDrawStatus(UserDraw userDraw);


}
