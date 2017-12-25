package com.api.service;

import com.api.entity.BookingDiningRecord;

import java.util.List;

public interface BookingDiningService
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
    List<BookingDiningRecord> getAllBookingDining( int count);


    /**
     * 根据Id获取菜谱信息
     *
     * @param id
     * @return
     */
    BookingDiningRecord getBookingDiningById( int id);

}
