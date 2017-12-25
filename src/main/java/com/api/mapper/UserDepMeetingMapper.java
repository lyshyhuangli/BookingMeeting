package com.api.mapper;

import com.api.entity.UserDepMeetingRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDepMeetingMapper
{
    /**
     * 根据phone获取用户信息
     *
     * @param phone
     * @return
     */
    UserDepMeetingRecord getUserInfoByPhone(@Param("phone") String phone);

    /**
     * 修改用户密码
     *
     * @param userName
     * @param pwd
     * @return
     */
    int modifyPwdByUserName(@Param("userName") String userName, @Param("pwd") String pwd);

}
