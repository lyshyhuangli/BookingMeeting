package com.api.restfulApi;

import com.api.common.ResultCode;
import com.api.entity.GroupPersonRecord;
import com.api.request.GetAllGroupPersonByUserPhoneReq;
import com.api.request.GetGroupPersonByIdReq;
import com.api.request.UpdateGroupPersonByIdReq;
import com.api.response.GetAllGroupPersonByUserPhoneResp;
import com.api.response.GetGroupPersonByIdResp;
import com.api.response.UpdateGroupPersonByIdResp;
import com.api.service.GroupPersonService;
import com.api.utils.PropertyUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/restfulApi", produces = "application/json;charset=UTF-8")
@ResponseBody
public class GroupPersionController
{
    private static Log logger = LogFactory.getLog(GroupPersionController.class);

    @Autowired
    private GroupPersonService groupPersonService;

    @RequestMapping(value = "/getGroupPersonById", method = {RequestMethod.POST})
    public GetGroupPersonByIdResp getGroupPersonById(@RequestBody GetGroupPersonByIdReq req)
    {
        GetGroupPersonByIdResp resp = new GetGroupPersonByIdResp();
        resp.setResultCode(ResultCode.SUCCESS.getCode());
        resp.setResultDesc(PropertyUtil.getProperty(String.valueOf(ResultCode.SUCCESS.getCode())));

        if (null == req)
        {
            resp.setResultCode(ResultCode.COMMON_REQ_NULL.getCode());
            resp.setResultDesc(PropertyUtil.getProperty(String.valueOf(ResultCode.COMMON_REQ_NULL.getCode())));
            return resp;
        }

        logger.info("req is " + req.toString());

        GroupPersonRecord result = groupPersonService.getGroupPersonMapperById(Integer.valueOf(req.getId()));
        if (null != result)
        {
            logger.info("result:" + result.toString());
        }

        resp.setInfo(result);
        return resp;
    }

    @RequestMapping(value = "/updateGroupPersonById", method = {RequestMethod.POST})
    public UpdateGroupPersonByIdResp updateGroupPersonById(@RequestBody UpdateGroupPersonByIdReq req)
    {
        UpdateGroupPersonByIdResp resp = new UpdateGroupPersonByIdResp();
        resp.setResultCode(ResultCode.SUCCESS.getCode());
        resp.setResultDesc(PropertyUtil.getProperty(String.valueOf(ResultCode.SUCCESS.getCode())));

        if (null == req)
        {
            resp.setResultCode(ResultCode.COMMON_REQ_NULL.getCode());
            resp.setResultDesc(PropertyUtil.getProperty(String.valueOf(ResultCode.COMMON_REQ_NULL.getCode())));
            return resp;
        }

        logger.info("req is " + req.toString());

        GroupPersonRecord info = new GroupPersonRecord();
        info.setId(req.getId());
        info.setGroupName(req.getGroupName());
        info.setGroupUserId(req.getGroupUserId());
        info.setGroupUserName(req.getGroupUserName());
        info.setUserPhone(req.getUserPhone());

        boolean result = groupPersonService.updateGroupPersonMapperById(info);
        logger.info("result:" + result);

        resp.setIsOK(result);
        return resp;
    }

    @RequestMapping(value = "/getAllGroupPersonByUserPhone", method = {RequestMethod.POST})
    public GetAllGroupPersonByUserPhoneResp getAllGroupPersonByUserPhone(@RequestBody GetAllGroupPersonByUserPhoneReq req)
    {
        GetAllGroupPersonByUserPhoneResp resp = new GetAllGroupPersonByUserPhoneResp();
        resp.setResultCode(ResultCode.SUCCESS.getCode());
        resp.setResultDesc(PropertyUtil.getProperty(String.valueOf(ResultCode.SUCCESS.getCode())));

        if (null == req)
        {
            resp.setResultCode(ResultCode.COMMON_REQ_NULL.getCode());
            resp.setResultDesc(PropertyUtil.getProperty(String.valueOf(ResultCode.COMMON_REQ_NULL.getCode())));
            return resp;
        }

        logger.info("req is " + req.toString());


        List<GroupPersonRecord> result = groupPersonService.getAllGroupPersonByUserPhone(req.getUserPhone());
        logger.info("result:" + result);

        resp.setInfo(result);
        return resp;
    }

}
