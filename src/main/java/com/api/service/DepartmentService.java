package com.api.service;

import com.api.entity.UserDepMeetingRecord;

public interface DepartmentService
{
    /**
     * 增加部门信息
     *
     * @param info
     * @return
     */
    int addDepartment(UserDepMeetingRecord info);

    /**
     * 获取最大的depSort
     * @return
     */
    int getMaxDepSort();

    /**
     * 修改部门信息
     *
     * @param info
     * @return
     */
    int editDepartment(UserDepMeetingRecord info);



}
