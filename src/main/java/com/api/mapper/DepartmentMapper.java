package com.api.mapper;

import com.api.entity.UserDepMeetingRecord;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DepartmentMapper
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
