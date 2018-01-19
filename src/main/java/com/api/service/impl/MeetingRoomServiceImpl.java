package com.api.service.impl;

import com.api.entity.UserDepMeetingRecord;
import com.api.mapper.MeetingRoomMapper;
import com.api.service.MeetingRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MeetingRoomServiceImpl implements MeetingRoomService
{
    @Autowired
    private MeetingRoomMapper meetingRoomMapper;


    /**
     * 增加会议室信息
     *
     * @param info
     * @return
     */
    public int addMeetingRoom(UserDepMeetingRecord info)
    {
        return meetingRoomMapper.addMeetingRoom(info);
    }

    /**
     * 获取最大的depSort
     *
     * @return
     */
    public int getMaxMeetingRoomSort(int parentId)
    {
        return meetingRoomMapper.getMaxMeetingRoomSort( parentId);
    }

    /**
     * 修改会议室信息
     *
     * @param info
     * @return
     */
    public int editMeetingRoom(UserDepMeetingRecord info)
    {
        return meetingRoomMapper.editMeetingRoom(info);
    }
}
