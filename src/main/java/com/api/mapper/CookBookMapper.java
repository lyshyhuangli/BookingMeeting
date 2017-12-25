package com.api.mapper;

import com.api.entity.CookBookRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CookBookMapper
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
    List<CookBookRecord> getAllCookBook(@Param("count") int count);


    /**
     * 根据Id获取菜谱信息
     *
     * @param id
     * @return
     */
    CookBookRecord getCookBookById(@Param("id") int id);


}
