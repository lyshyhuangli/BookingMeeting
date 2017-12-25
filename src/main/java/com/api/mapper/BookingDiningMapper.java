package com.api.mapper;

import com.api.entity.BookingDiningRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BookingDiningMapper
{
    /**
     * 保存菜谱信息
     *
     * @param info
     * @return
     */
    int saveBookingDining(BookingDiningRecord info);

    /**
     * 更新菜谱信息
     *
     * @param info
     * @return
     */
    int updateBookingDiningById(BookingDiningRecord info);


    /**
     * 获取所有菜谱信息
     *
     * @return
     */
    List<BookingDiningRecord> getAllBookingDining(@Param("count") int count);


    /**
     * 根据Id获取菜谱信息
     *
     * @param id
     * @return
     */
    BookingDiningRecord getBookingDiningById(@Param("id") int id);
}
