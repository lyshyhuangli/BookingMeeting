package com.api.restfulApi;

import com.api.common.ResultCode;
import com.api.entity.GroupPersonRecord;
import com.api.entity.UserDepMeetingRecord;
import com.api.request.CheckUserByUserAndPwdReq;
import com.api.request.GetGroupPersonByIdReq;
import com.api.request.GetUserInfoByPhoneReq;
import com.api.response.GetGroupPersonByIdResp;
import com.api.response.GetUserInfoByPhoneResp;
import com.api.response.ModifyPwdByUserNameResp;
import com.api.service.GroupPersonService;
import com.api.service.UserDepMeetingService;
import com.api.utils.PropertyUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/restfulApi", produces = "application/json;charset=UTF-8")
@ResponseBody
public class UserDepMeetingController
{

    private static Log logger = LogFactory.getLog(UserDepMeetingController.class);

    @Autowired
    private UserDepMeetingService userDepMeetingService;


    @RequestMapping(value = "/getUserInfoByPhone", method = {RequestMethod.POST})
    public GetUserInfoByPhoneResp getUserInfoByPhone(@RequestBody GetUserInfoByPhoneReq req)
    {
        GetUserInfoByPhoneResp resp = new GetUserInfoByPhoneResp();
        resp.setResultCode(ResultCode.SUCCESS.getCode());
        resp.setResultDesc(PropertyUtil.getProperty(String.valueOf(ResultCode.SUCCESS.getCode())));

        if (null == req)
        {
            resp.setResultCode(ResultCode.COMMON_REQ_NULL.getCode());
            resp.setResultDesc(PropertyUtil.getProperty(String.valueOf(ResultCode.COMMON_REQ_NULL.getCode())));
            return resp;
        }

        logger.info("req is " + req.toString());

        UserDepMeetingRecord result = userDepMeetingService.getUserInfoByPhone(req.getPhone());
        if (null != result)
        {
            logger.info("result:" + result.toString());
        }

        resp.setUserInfo(result);
        return resp;
    }

    /**
     * 修改用户密码
     *
     * @return
     */
    @RequestMapping(value = "/modifyPwdByUserName", method = {RequestMethod.POST})
    public ModifyPwdByUserNameResp modifyPwdByUserName(@RequestBody CheckUserByUserAndPwdReq req)
    {
        ModifyPwdByUserNameResp resp = new ModifyPwdByUserNameResp();
        resp.setResultCode(ResultCode.SUCCESS.getCode());
        resp.setResultDesc(PropertyUtil.getProperty(String.valueOf(ResultCode.SUCCESS.getCode())));

        if (null == req)
        {
            resp.setResultCode(ResultCode.COMMON_REQ_NULL.getCode());
            resp.setResultDesc(PropertyUtil.getProperty(String.valueOf(ResultCode.COMMON_REQ_NULL.getCode())));
            return resp;
        }

        logger.info("usesrName:" + req.getUserName());
        boolean result = userDepMeetingService.modifyPwdByUserName(req.getUserName(), req.getPwd());
        logger.info("result:" + result);

        resp.setIsOK(result);
        return resp;
    }


}
