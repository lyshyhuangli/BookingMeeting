package com.api.response;

import com.api.common.CommonResult;
import com.api.entity.BookingDiningRecord;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class GetBookDiningByIdResp extends CommonResult
{
    BookingDiningRecord info = new BookingDiningRecord();

    public BookingDiningRecord getInfo()
    {
        return info;
    }

    public void setInfo(BookingDiningRecord info)
    {
        this.info = info;
    }

    public String toString()
    {
        String str = ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
        return str;
    }
}
