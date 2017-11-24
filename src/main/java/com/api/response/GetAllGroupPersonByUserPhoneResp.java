package com.api.response;

import com.api.common.CommonResult;
import com.api.entity.GroupPersonRecord;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.LinkedList;
import java.util.List;

public class GetAllGroupPersonByUserPhoneResp extends CommonResult
{
    private List<GroupPersonRecord> info = new LinkedList<GroupPersonRecord>();

    public List<GroupPersonRecord> getInfo()
    {
        return info;
    }

    public void setInfo(List<GroupPersonRecord> info)
    {
        this.info = info;
    }

    public String toString()
    {
        String str = ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
        return str;
    }
}
