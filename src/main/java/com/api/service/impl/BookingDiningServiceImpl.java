package com.api.service.impl;

import com.api.entity.BookingDiningRecord;
import com.api.mapper.BookingDiningMapper;
import com.api.service.BookingDiningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookingDiningServiceImpl implements BookingDiningService
{
    @Autowired
    private BookingDiningMapper bookingDiningMapper;

    /**
     * 保存菜谱信息
     *
     * @param info
     * @return
     */
    public int saveBookingDining(BookingDiningRecord info)
    {
        return bookingDiningMapper.saveBookingDining(info);
    }

    /**
     * 更新菜谱信息
     *
     * @param info
     * @return
     */
    public int updateBookingDiningById(BookingDiningRecord info)
    {
        return bookingDiningMapper.updateBookingDiningById(info);
    }


    /**
     * 获取所有菜谱信息
     *
     * @return
     */
    public List<BookingDiningRecord> getAllBookingDining(int count)
    {
        return bookingDiningMapper.getAllBookingDining(count);
    }


    /**
     * 根据Id获取菜谱信息
     *
     * @param id
     * @return
     */
    public BookingDiningRecord getBookingDiningById(int id)
    {
        return bookingDiningMapper.getBookingDiningById(id);
    }

}
