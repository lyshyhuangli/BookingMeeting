package com.api.mapper;


import com.api.entity.MeetingConfirmRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MeetingConfirmMapper
{

    /**
     * 保存和更新参会信息
     *
     * @return
     */
    int saveMeetingConfirm(MeetingConfirmRecord info);

    /**
     * 更新参会信息
     *
     * @param attendType
     * @param reason
     * @param isSign
     * @param meetingId
     * @param phone
     * @return
     */
    int updateMeetingConfirmByMeetingIdAndPhone(
            @Param("attendType") int attendType, @Param("reason") String reason, @Param("isSign") int isSign, @Param("meetingId") int meetingId, @Param("phone") String phone
    );

    /**
     * 根据会议Id查询出参会信息
     *
     * @param id
     * @return
     */
    List<MeetingConfirmRecord> getMeetingConfirmByMeetingId(@Param("meetingId") int id);


    /**
     * 根据meetingId和phone查询出参会信息
     *
     * @param phone
     * @param meetingId
     * @return
     */
    MeetingConfirmRecord getMeetingConfirmByMeetingIdAndPhone(@Param("phone") String phone, @Param("meetingId") int meetingId);


}
