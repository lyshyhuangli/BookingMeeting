package com.api.service;

import com.api.entity.UserDepMeetingRecord;

public interface UserDepMeetingService
{
    /**
     * 根据phone获取用户信息
     *
     * @param phone
     * @return
     */
    UserDepMeetingRecord getUserInfoByPhone(String phone);

    /**
     * 修改用户密码
     * @param userName
     * @param pwd
     * @return
     */
    boolean modifyPwdByUserName(String userName,String pwd);

}
