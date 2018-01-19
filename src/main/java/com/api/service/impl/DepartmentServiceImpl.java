package com.api.service.impl;

import com.api.entity.UserDepMeetingRecord;
import com.api.mapper.DepartmentMapper;
import com.api.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService
{
    @Autowired
    private DepartmentMapper departmentMapper;


    /**
     * 增加部门信息
     *
     * @param info
     * @return
     */
    public int addDepartment(UserDepMeetingRecord info)
    {
        return departmentMapper.addDepartment(info);
    }

    /**
     * 获取最大的depSort
     *
     * @return
     */
    public int getMaxDepSort()
    {
        return departmentMapper.getMaxDepSort();
    }

    /**
     * 修改部门信息
     *
     * @param info
     * @return
     */
    public int editDepartment(UserDepMeetingRecord info)
    {
        return departmentMapper.editDepartment(info);
    }

}
