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
}
