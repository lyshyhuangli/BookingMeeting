package com.api.response;

import com.api.common.CommonResult;
import com.api.entity.MyMeetingInfoRecord;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.LinkedList;
import java.util.List;

public class GetMyBeingMeetingResp extends CommonResult
{

    private List<MyMeetingInfoRecord> myMeetingInfo = new LinkedList<MyMeetingInfoRecord>();

    public List<MyMeetingInfoRecord> getMyMeetingInfo()
    {
        return myMeetingInfo;
    }

    public void setMyMeetingInfo(List<MyMeetingInfoRecord> myMeetingInfo)
    {
        this.myMeetingInfo = myMeetingInfo;
    }

    public String toString()
    {
        String str = ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
        return str;
    }

}
