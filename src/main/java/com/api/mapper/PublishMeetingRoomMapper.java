package com.api.mapper;

import com.api.entity.BookMeetingDbInfoRecord;
import com.api.entity.MyMeetingInfoRecord;
import com.api.entity.ShowMeetingRedord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PublishMeetingRoomMapper
{

    /**
     * 根据  phone获取本部门的会议室
     *
     * @param phone
     * @return
     */
    List<ShowMeetingRedord> getMyMeetingRoomByPhoneAndDate(@Param("phone") String phone, @Param("dateStr") String date);

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
    BookMeetingDbInfoRecord getMeetingInfoById(@Param("id") int id);

    /**
     * 获取跟我相关的3天会议信息
     * @param bookUser
     * @param date1
     * @param date2
     * @param date3
     * @param personId
     * @return
     */
    List<MyMeetingInfoRecord> getMyBeingMeeting(@Param("bookUser") String bookUser,
                                          @Param("date1") String date1, @Param("date2") String date2,
                                          @Param("date3") String date3, @Param("personId") String personId);

    /**
     * 获取跟我相关的已经完成的会议信息
     * @param bookUser
     * @param date1
     * @param date2
     * @param date3
     * @param personId
     * @return
     */
    List<MyMeetingInfoRecord> getMyBedMeeting(@Param("bookUser") String bookUser,
                                                @Param("date1") String date1, @Param("date2") String date2,
                                                @Param("date3") String date3, @Param("personId") String personId,
                                                @Param("count") int count);


}
