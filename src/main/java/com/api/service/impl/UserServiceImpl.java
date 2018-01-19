package com.api.service.impl;

import com.api.entity.UserDepMeetingRecord;
import com.api.mapper.UserMapper;
import com.api.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserMapper userDepMeetingMapper;

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

    /**
     * 修改用户密码
     *
     * @param userName
     * @param pwd
     * @return
     */
    public boolean modifyPwdByUserName(String userName, String pwd)
    {
        int result = userDepMeetingMapper.modifyPwdByUserName(userName, pwd);

        if (result != 0)
        {
            return true;
        }

        return false;

    }

    /**
     * 保存用户信息
     *
     * @param info
     * @return
     */
    public int addUserInfo(UserDepMeetingRecord info)
    {
        return userDepMeetingMapper.addUserInfo(info);
    }

    /**
     * 获取当前部门人员的最大排序号
     *
     * @param parentId
     * @return
     */
    public int getMaxPersonSort(@Param("parentId") int parentId)
    {
        return userDepMeetingMapper.getMaxPersonSort(parentId);
    }

    /**
     * 编辑用户信息
     *
     * @param info
     * @return
     */
    public int editUserInfo(UserDepMeetingRecord info)
    {
        return userDepMeetingMapper.editUserInfo(info);
    }


}
