package com.api.mapper;

import com.api.entity.GroupPersonRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GroupPersonMapper
{

    /**
     * 根据Id获取群组信息
     *
     * @param id
     * @return
     */
    GroupPersonRecord getGroupPersonMapperById(@Param("id") int id);

    /**
     * 根据userPhone获取群组信息
     *
     * @param userPhone
     * @return
     */
    List<GroupPersonRecord> getAllGroupPersonByUserPhone(@Param("userPhone") String userPhone);


    /**
     * 根据Id更新群组信息
     *
     * @param id
     * @return
     */
    int updateGroupPersonMapperById(@Param("id") int id, @Param("userPhone") String userPhone,
                                    @Param("groupName") String groupName,
                                    @Param("groupUserId") String groupUserId,
                                    @Param("groupUserName") String groupUserName);
}
