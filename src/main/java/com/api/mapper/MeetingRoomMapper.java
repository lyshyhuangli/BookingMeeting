package com.api.mapper;

import com.api.entity.UserDepMeetingRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MeetingRoomMapper
{

    /**
     * 增加会议室信息
     *
     * @param info
     * @return
     */
    int addMeetingRoom(UserDepMeetingRecord info);

    /**
     * 获取最大的meetingroomSort
     *
     * @return
     */
    int getMaxMeetingRoomSort(@Param("parentId") int parentId);

    /**
     * 修改会议室信息
     *
     * @param info
     * @return
     */
    int editMeetingRoom(UserDepMeetingRecord info);
}
