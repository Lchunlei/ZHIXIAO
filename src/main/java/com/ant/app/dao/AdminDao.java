package com.ant.app.dao;

import com.ant.app.entity.req.LayUiAuToReq;
import com.ant.app.model.SysAdmin;
import com.ant.app.sql.LayuiAutoPageSql;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lchunlei
 * @date 2018/12/26
 */
@Repository
public interface AdminDao {

    @Select("SELECT * FROM sys_admin WHERE phoneNum=#{phoneNum}")
    SysAdmin selectAdminByPhoneNum(@Param("phoneNum") String phoneNum);

    @Select("SELECT * FROM sys_admin WHERE adminId=${adminId}")
    SysAdmin selectAdminById(@Param("adminId") Integer adminId);

    @SelectProvider(type=LayuiAutoPageSql.class, method="reqList")
    List<SysAdmin> selectByPage(LayUiAuToReq layUiAuToReq);
    @SelectProvider(type=LayuiAutoPageSql.class, method="reqListTotal")
    Integer selectTotalNum(LayUiAuToReq layUiAuToReq);

    @Insert("INSERT INTO sys_admin(`phoneNum`, `password`,`nickName`,`sysRole`,`cTime`) VALUES (#{phoneNum},#{password},#{nickName},#{sysRole},NOW())")
    Integer insertAdmin(SysAdmin sysAdmin);

    @Update("UPDATE sys_admin SET usable=${usable} WHERE adminId=${adminId}")
    Integer updateAdminStatus(@Param("usable")Integer usable,@Param("adminId")Integer adminId);

    @Update("UPDATE sys_admin SET phoneNum=#{phoneNum},password=#{password},nickName=#{nickName},cTime=NOW() WHERE adminId=${adminId}")
    Integer updateAdmin(SysAdmin sysAdmin);


}
