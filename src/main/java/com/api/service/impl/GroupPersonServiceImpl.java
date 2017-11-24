package com.api.service.impl;

import com.api.entity.GroupPersonRecord;
import com.api.mapper.GroupPersonMapper;
import com.api.service.GroupPersonService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GroupPersonServiceImpl implements GroupPersonService
{
    @Autowired
    private GroupPersonMapper groupPersonMapper;


    /**
     * 根据Id获取群组信息
     *
     * @param id
     * @return
     */
    public GroupPersonRecord getGroupPersonMapperById(int id)
    {
        return groupPersonMapper.getGroupPersonMapperById(id);
    }

    /**
     * 根据Id更新群组信息
     *
     * @param id
     * @return
     */
    public boolean updateGroupPersonMapperById(GroupPersonRecord info)
    {

        int result = groupPersonMapper.updateGroupPersonMapperById(info.getId(),
                info.getUserPhone(), info.getGroupName(), info.getGroupUserId(), info.getGroupUserName());

        if (result != 0)
        {
            return true;
        }

        return false;
    }

    /**
     * 根据userPhone获取群组信息
     *
     * @param userPhone
     * @return
     */
    public List<GroupPersonRecord> getAllGroupPersonByUserPhone(String userPhone)
    {
        return groupPersonMapper.getAllGroupPersonByUserPhone(userPhone);
    }
}
