package com.api.service;

import com.api.entity.GroupPersonRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GroupPersonService
{
    /**
     * 根据Id获取群组信息
     *
     * @param id
     * @return
     */
    GroupPersonRecord getGroupPersonMapperById(int id);

    /**
     * 根据Id更新群组信息
     *
     * @param info
     * @return
     */
    boolean updateGroupPersonMapperById( GroupPersonRecord info);

    /**
     * 根据userPhone获取群组信息
     *
     * @param userPhone
     * @return
     */
    List<GroupPersonRecord> getAllGroupPersonByUserPhone (String userPhone);

}
