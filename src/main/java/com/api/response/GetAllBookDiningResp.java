package com.api.response;

import com.api.common.CommonResult;
import com.api.entity.BookingDiningRecord;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.List;

public class GetAllBookDiningResp extends CommonResult
{
    List<BookingDiningRecord> result;

    public List<BookingDiningRecord> getResult()
    {
        return result;
    }

    public void setResult(List<BookingDiningRecord> result)
    {
        this.result = result;
    }
    public String toString()
    {
        String str = ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
        return str;
    }
}
