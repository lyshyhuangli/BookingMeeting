package com.api.response;

import com.api.common.CommonResult;
import com.api.entity.MyMeetingInfoRecord;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.LinkedList;
import java.util.List;

public class GetMyBedMeetingResp  extends CommonResult
{
    private List<MyMeetingInfoRecord> myBedMeetingInfo = new LinkedList<MyMeetingInfoRecord>();

    public List<MyMeetingInfoRecord> getMyBedMeetingInfo()
    {
        return myBedMeetingInfo;
    }

    public void setMyBedMeetingInfo(List<MyMeetingInfoRecord> myBedMeetingInfo)
    {
        this.myBedMeetingInfo = myBedMeetingInfo;
    }

    public String toString()
    {
        String str = ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
        return str;
    }
}
