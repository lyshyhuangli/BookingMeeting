package com.api.service;

import com.api.entity.UserDepMeetingRecord;

public interface MeetingRoomService
{

    /**
     * 增加会议室信息
     *
     * @param info
     * @return
     */
    int addMeetingRoom(UserDepMeetingRecord info);

    /**
     * 获取最大的depSort
     *
     * @return
     */
    int getMaxMeetingRoomSort(int parentId);

    /**
     * 修改会议室信息
     *
     * @param info
     * @return
     */
    int editMeetingRoom(UserDepMeetingRecord info);

}
