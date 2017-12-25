package com.api.service.impl;

import com.api.entity.CookBookRecord;
import com.api.mapper.CookBookMapper;
import com.api.service.CookBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CookBookServiceImpl implements CookBookService
{

    @Autowired
    private CookBookMapper cookBookMapper;

    /**
     * 保存菜谱信息
     *
     * @param info
     * @return
     */
    public int saveCookBook(CookBookRecord info)
    {
        return cookBookMapper.saveCookBook(info);
    }

    /**
     * 更新菜谱信息
     *
     * @param info
     * @return
     */
    public int updateCookBookById(CookBookRecord info)
    {
        return cookBookMapper.updateCookBookById(info);
    }


    /**
     * 获取所有菜谱信息
     *
     * @return
     */
    public List<CookBookRecord> getAllCookBook(int count)
    {
        return cookBookMapper.getAllCookBook(count);
    }


    /**
     * 根据Id获取菜谱信息
     *
     * @param id
     * @return
     */
    public CookBookRecord getCookBookById(int id)
    {
        return cookBookMapper.getCookBookById(id);
    }

}
