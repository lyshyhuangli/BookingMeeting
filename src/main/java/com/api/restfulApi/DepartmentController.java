package com.api.restfulApi;


import com.api.common.ResultCode;
import com.api.entity.UserDepMeetingRecord;
import com.api.request.AddDepartmentRequest;
import com.api.request.EditDepartmentRequest;
import com.api.response.AddDepartmentResp;
import com.api.response.EditDepartmentResp;
import com.api.service.DepartmentService;
import com.api.utils.PropertyUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/restfulApi", produces = "application/json;charset=UTF-8")
@ResponseBody
public class DepartmentController
{
    private static Log logger = LogFactory.getLog(DepartmentController.class);

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping(value = "/addDepartment", method = {RequestMethod.POST})
    public AddDepartmentResp addDepartment(@RequestBody AddDepartmentRequest req)
    {
        AddDepartmentResp resp = new AddDepartmentResp();
        resp.setResultCode(ResultCode.SUCCESS.getCode());
        resp.setResultDesc(PropertyUtil.getProperty(String.valueOf(ResultCode.SUCCESS.getCode())));

        if (null == req)
        {
            resp.setResultCode(ResultCode.COMMON_REQ_NULL.getCode());
            resp.setResultDesc(PropertyUtil.getProperty(String.valueOf(ResultCode.COMMON_REQ_NULL.getCode())));
            return resp;
        }

        logger.info("req is " + req.toString());

        //做同步锁，防止获取同样的max
        synchronized (this)
        {

            int maxDepSort = departmentService.getMaxDepSort();

            UserDepMeetingRecord info = new UserDepMeetingRecord();
            info.setName(req.getName());
            info.setParentId(req.getParentId());
            info.setRemark(req.getRemark());
            info.setType(req.getType());
            info.setDepSort(maxDepSort);

            int result = departmentService.addDepartment(info);
            logger.info("result:" + result);
            resp.setResult(result);
        }

        logger.info("resp:" + resp.toString());
        return resp;
    }

    @RequestMapping(value = "/editDepartment", method = {RequestMethod.POST})
    public EditDepartmentResp editDepartment(@RequestBody EditDepartmentRequest req)
    {
        EditDepartmentResp resp = new EditDepartmentResp();
        resp.setResultCode(ResultCode.SUCCESS.getCode());
        resp.setResultDesc(PropertyUtil.getProperty(String.valueOf(ResultCode.SUCCESS.getCode())));

        if (null == req)
        {
            resp.setResultCode(ResultCode.COMMON_REQ_NULL.getCode());
            resp.setResultDesc(PropertyUtil.getProperty(String.valueOf(ResultCode.COMMON_REQ_NULL.getCode())));
            return resp;
        }

        logger.info("req is " + req.toString());

        UserDepMeetingRecord info = new UserDepMeetingRecord();
        info.setId(req.getId());
        info.setName(req.getName());
        info.setParentId(req.getParentId());
        info.setRemark(req.getRemark());
        info.setType(req.getType());
        info.setDepSort(req.getDepSort());

        int result = departmentService.editDepartment(info);
        logger.info("result:" + result);
        resp.setResult(result);


        logger.info("resp:" + resp.toString());
        return resp;
    }


}
