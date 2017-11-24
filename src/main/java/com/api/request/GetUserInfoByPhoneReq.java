package com.api.request;

import com.api.common.CommonRequest;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class GetUserInfoByPhoneReq extends CommonRequest
{
    private String phone;

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String toString()
    {
        String str = ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
        return str;
    }

}
