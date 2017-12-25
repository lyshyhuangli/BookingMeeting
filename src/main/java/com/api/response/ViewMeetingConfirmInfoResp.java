package com.api.response;

import com.api.common.CommonResult;
import com.api.entity.MeetingConfirmRecord;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;

public class ViewMeetingConfirmInfoResp extends CommonResult
{

    List<MeetingConfirmRecord> info = new ArrayList<MeetingConfirmRecord>();

    public List<MeetingConfirmRecord> getInfo()
    {
        return info;
    }

    public void setInfo(List<MeetingConfirmRecord> info)
    {
        this.info = info;
    }

    public String toString()
    {
        String str = ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
        return str;
    }
}
