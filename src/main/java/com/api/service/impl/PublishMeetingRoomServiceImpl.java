package com.api.service.impl;

import com.api.entity.BookMeetingDbInfoRecord;
import com.api.entity.MyMeetingInfoRecord;
import com.api.entity.ShowMeetingRedord;
import com.api.mapper.PublishMeetingRoomMapper;
import com.api.service.PublishMeetingRoomService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PublishMeetingRoomServiceImpl implements PublishMeetingRoomService
{

    @Autowired
    private PublishMeetingRoomMapper publishMeetingRoomMapper;

    /**
     * 根据  phone获取本部门的会议室
     *
     * @param phone
     * @return
     */
    public List<ShowMeetingRedord> getMyMeetingRoomByPhoneAndDate(String phone, String date)
    {
        return publishMeetingRoomMapper.getMyMeetingRoomByPhoneAndDate(phone, date);

    }

    /**
     * 插入会议信息
     *
     * @param info
     * @return
     */
    public int insertPublishMeeting(BookMeetingDbInfoRecord info)
    {
        return publishMeetingRoomMapper.insertPublishMeeting(info);
    }

    /**
     * 根据Id查询会议信息
     *
     * @param id
     * @return
     */
    public BookMeetingDbInfoRecord getMeetingInfoById(int id)
    {
        return publishMeetingRoomMapper.getMeetingInfoById(id);

    }

    /**
     *取消会议
     */
    public int deleteMeetingInfoById(int id)
    {

        return publishMeetingRoomMapper.deleteMeetingInfoById(id);
    }

    /**
     * 获取跟我相关的3天会议信息
     *
     * @param bookUser
     * @param date1
     * @param date2
     * @param date3
     * @param personId
     * @return
     */
    public List<MyMeetingInfoRecord> getMyBeingMeeting(
            String bookUser, String date1, String date2, String date3, String personId
    )
    {
        return publishMeetingRoomMapper.getMyBeingMeeting(bookUser, date1, date2, date3, personId);
    }

    /**
     * 获取跟我相关的已经完成的会议信息
     *
     * @param bookUser
     * @param date1
     * @param date2
     * @param date3
     * @param personId
     * @return
     */
    public List<MyMeetingInfoRecord> getMyBedMeeting(
            String bookUser, String date1, String date2, String date3, String personId, int count
    )
    {
        return publishMeetingRoomMapper.getMyBedMeeting(bookUser, date1, date2, date3, personId, count);
    }

    /**
     * 更新会议信息
     *
     * @param info
     * @return
     */
    public int updateMeetingInfoById(BookMeetingDbInfoRecord info)
    {
        return publishMeetingRoomMapper.updateMeetingInfoById(info);
    }

    /**
     * 查询会议室是否被占用
     *
     * @param meetingDate
     * @param amOrPm
     * @param meetingroom
     * @return
     */
    public BookMeetingDbInfoRecord checkMeetingInfoByDateAmPmRoomId(
            String meetingDate, String amOrPm, int meetingroom
    )
    {
        return publishMeetingRoomMapper.checkMeetingInfoByDateAmPmRoomId(meetingDate, amOrPm, meetingroom);
    }


}
