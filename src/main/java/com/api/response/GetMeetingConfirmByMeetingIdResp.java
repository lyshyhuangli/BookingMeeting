package com.api.response;

import com.api.common.CommonResult;
import com.api.entity.MeetingConfirmRecord;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;

public class GetMeetingConfirmByMeetingIdResp extends CommonResult
{
    List<MeetingConfirmRecord> myMeeting = new ArrayList<MeetingConfirmRecord>();

    public List<MeetingConfirmRecord> getMyMeeting()
    {
        return myMeeting;
    }

    public void setMyMeeting(List<MeetingConfirmRecord> myMeeting)
    {
        this.myMeeting = myMeeting;
    }

    public String toString()
    {
        String str = ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
        return str;
    }
}
