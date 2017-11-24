package com.api.service.impl;

import com.api.entity.UserDepMeetingRecord;
import com.api.mapper.UserDepMeetingMapper;
import com.api.service.UserDepMeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserDepMeetingServiceImpl implements UserDepMeetingService
{
    @Autowired
    private UserDepMeetingMapper userDepMeetingMapper;

    /**
     * 根据phone获取用户信息
     *
     * @param phone
     * @return
     */
    public UserDepMeetingRecord getUserInfoByPhone(String phone)
    {
        return userDepMeetingMapper.getUserInfoByPhone(phone);
    }
}
