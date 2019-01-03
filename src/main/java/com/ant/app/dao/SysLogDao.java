package com.ant.app.dao;

import com.ant.app.model.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

/**
 * @author lchunlei
 * @date 2018/12/26
 */
@Repository
public interface SysLogDao {

    @Insert("INSERT INTO sys_log(`adminId`, `logInfo`,`cTime`) VALUES (${adminId},#{logInfo},NOW())")
    Integer insertSysLog(SysLog sysLog);

}
