package com.api.response;

import com.api.common.CommonResult;
import com.api.entity.GroupPersonRecord;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class GetGroupPersonByIdResp extends CommonResult
{
    private GroupPersonRecord info;

    public GroupPersonRecord getInfo()
    {
        return info;
    }

    public void setInfo(GroupPersonRecord info)
    {
        this.info = info;
    }

    public String toString()
    {
        String str = ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
        return str;
    }
}
