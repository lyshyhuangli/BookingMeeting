package com.api.service.impl;

import com.api.entity.MeetingConfirmRecord;
import com.api.mapper.MeetingConfirmMapper;
import com.api.restfulApi.MeetingConfirmController;
import com.api.service.MeetingConfirmService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MeetingConfirmServiceImpl implements MeetingConfirmService
{


    private static Log logger = LogFactory.getLog(MeetingConfirmServiceImpl.class);

    @Autowired
    private MeetingConfirmMapper meetingConfirmMapper;


    /**
     * 保存和更新参会信息
     *
     * @param phone
     * @return
     */
    public int saveMeetingConfirm(
            int meetingId, String phone, String userName, int attendType, String reason
    )
    {
        try
        {
            MeetingConfirmRecord info = new MeetingConfirmRecord();
            info.setAttendType(attendType);
            info.setMeetingId(meetingId);
            info.setPhone(phone);
            info.setReason(reason);
            info.setUserName(userName);

            return meetingConfirmMapper.saveMeetingConfirm(info);
        }
        catch (Exception e)
        {
            logger.error(e.getMessage());
            return 0;
        }

    }

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
    public int updateMeetingConfirmByMeetingIdAndPhone(
            int attendType, String reason, int isSign, int meetingId, String phone
    )
    {

        return meetingConfirmMapper.updateMeetingConfirmByMeetingIdAndPhone(attendType, reason, isSign, meetingId, phone);
    }

    /**
     * 根据会议Id查询出参会信息
     *
     * @param id
     * @return
     */
    public List<MeetingConfirmRecord> getMeetingConfirmByMeetingId(int id)
    {

        return meetingConfirmMapper.getMeetingConfirmByMeetingId(id);
    }

    /**
     * 根据meetingId和phone查询出参会信息
     *
     * @param phone
     * @param meetingId
     * @return
     */
    public MeetingConfirmRecord getMeetingConfirmByMeetingIdAndPhone(String phone, int meetingId)
    {
        return meetingConfirmMapper.getMeetingConfirmByMeetingIdAndPhone(phone, meetingId);
    }


}
