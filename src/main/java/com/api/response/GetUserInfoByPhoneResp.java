package com.api.response;

import com.api.common.CommonResult;
import com.api.entity.UserDepMeetingRecord;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class GetUserInfoByPhoneResp extends CommonResult
{

    private UserDepMeetingRecord userInfo;

    public UserDepMeetingRecord getUserInfo()
    {
        return userInfo;
    }

    public void setUserInfo(UserDepMeetingRecord userInfo)
    {
        this.userInfo = userInfo;
    }

    public String toString()
    {
        String str = ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
        return str;
    }

}
