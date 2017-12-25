package com.api.service;

import com.api.entity.CookBookRecord;

import java.util.List;

public interface CookBookService
{
    /**
     * 保存菜谱信息
     *
     * @param info
     * @return
     */
    int saveCookBook(CookBookRecord info);

    /**
     * 更新菜谱信息
     *
     * @param info
     * @return
     */
    int updateCookBookById(CookBookRecord info);


    /**
     * 获取所有菜谱信息
     *
     * @return
     */
    List<CookBookRecord> getAllCookBook(int count);


    /**
     * 根据Id获取菜谱信息
     *
     * @param id
     * @return
     */
    CookBookRecord getCookBookById(int id);

}
