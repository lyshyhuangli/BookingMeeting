package com.api.response;

import com.api.common.CommonResult;
import com.api.entity.BookMeetingDbInfoRecord;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class GetMeetingInfoByIdResp extends CommonResult
{
    private BookMeetingDbInfoRecord info;

    public BookMeetingDbInfoRecord getInfo()
    {
        return info;
    }

    public void setInfo(BookMeetingDbInfoRecord info)
    {
        this.info = info;
    }

    public String toString()
    {
        String str = ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
        return str;
    }
}
