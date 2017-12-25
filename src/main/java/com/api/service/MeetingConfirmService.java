package com.api.service;

import com.api.entity.MeetingConfirmRecord;

import java.util.List;

public interface MeetingConfirmService
{
    /**
     * 保存参会信息
     *
     * @param phone
     * @return
     */
    int saveMeetingConfirm(
            int meetingId, String phone, String userName, int attendType, String reason,int isSign
    );

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
            int attendType, String reason, int isSign, int meetingId, String phone
    );

    /**
     * 根据会议Id查询出参会信息
     *
     * @param id
     * @return
     */
    List<MeetingConfirmRecord> getMeetingConfirmByMeetingId(int id);

    /**
     * 根据meetingId和phone查询出参会信息
     *
     * @param phone
     * @param meetingId
     * @return
     */
    MeetingConfirmRecord getMeetingConfirmByMeetingIdAndPhone( String phone, int meetingId);




}
