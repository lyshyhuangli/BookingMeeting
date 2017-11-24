package com.api.service;

import com.api.entity.BookMeetingDbInfoRecord;
import com.api.entity.MyMeetingInfoRecord;
import com.api.entity.ShowMeetingRedord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PublishMeetingRoomService
{
    /**
     * 根据  phone获取本部门的会议室
     *
     * @param phone
     * @return
     */
    List<ShowMeetingRedord> getMyMeetingRoomByPhoneAndDate(String phone, String date);

    /**
     * 插入会议信息
     *
     * @param info
     * @return
     */
    int insertPublishMeeting(BookMeetingDbInfoRecord info);

    /**
     * 根据Id查询会议信息
     *
     * @param id
     * @return
     */
    BookMeetingDbInfoRecord getMeetingInfoById(int id);

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
    List<MyMeetingInfoRecord> getMyBeingMeeting(
            String bookUser, String date1, String date2, String date3, String personId
    );
}
