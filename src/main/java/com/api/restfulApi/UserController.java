package com.api.restfulApi;

import com.api.common.ResultCode;
import com.api.entity.UserDepMeetingRecord;
import com.api.request.*;
import com.api.response.AddUserInfoResp;
import com.api.response.EditUserInfoResp;
import com.api.response.GetUserInfoByPhoneResp;
import com.api.response.ModifyPwdByUserNameResp;
import com.api.service.UserService;
import com.api.utils.PropertyUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/restfulApi", produces = "application/json;charset=UTF-8")
@ResponseBody
public class UserController
{

    private static Log logger = LogFactory.getLog(UserController.class);

    @Autowired
    private UserService userService;


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

        UserDepMeetingRecord result = userService.getUserInfoByPhone(req.getPhone());
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
        boolean result = userService.modifyPwdByUserName(req.getUserName(), req.getPwd());
        logger.info("result:" + result);

        resp.setIsOK(result);
        return resp;
    }

    /**
     * 增加用户
     *
     * @return
     */
    @RequestMapping(value = "/addUserInfo", method = {RequestMethod.POST})
    public AddUserInfoResp addUserInfo(@RequestBody AddUserInfoReq req)
    {
        AddUserInfoResp resp = new AddUserInfoResp();
        resp.setResultCode(ResultCode.SUCCESS.getCode());
        resp.setResultDesc(PropertyUtil.getProperty(String.valueOf(ResultCode.SUCCESS.getCode())));

        if (null == req)
        {
            resp.setResultCode(ResultCode.COMMON_REQ_NULL.getCode());
            resp.setResultDesc(PropertyUtil.getProperty(String.valueOf(ResultCode.COMMON_REQ_NULL.getCode())));
            return resp;
        }

        logger.info("req:" + req.toString());

        //做同步锁，防止获取同样的max
        synchronized (this)
        {

            int maxPersonSort = userService.getMaxPersonSort(req.getParentId());

            UserDepMeetingRecord u = new UserDepMeetingRecord();
            u.setName(req.getName());
            u.setParentId(req.getParentId());
            u.setRemark(req.getRemark());
            u.setType(req.getType());
            u.setPhone(req.getPhone());
            u.setIdcard(req.getIdCard());
            u.setPersonSort(maxPersonSort);
            u.setPost(req.getPost());
            u.setCarId(req.getCarId());

            int result = userService.addUserInfo(u);
            logger.info("result:" + result);
            resp.setResult(result);
        }
        return resp;
    }

    /**
     * 编辑用户
     *
     * @return
     */
    @RequestMapping(value = "/editUserInfo", method = {RequestMethod.POST})
    public EditUserInfoResp editUserInfo(@RequestBody EditUserInfoReq req)
    {
        EditUserInfoResp resp = new EditUserInfoResp();
        resp.setResultCode(ResultCode.SUCCESS.getCode());
        resp.setResultDesc(PropertyUtil.getProperty(String.valueOf(ResultCode.SUCCESS.getCode())));

        if (null == req)
        {
            resp.setResultCode(ResultCode.COMMON_REQ_NULL.getCode());
            resp.setResultDesc(PropertyUtil.getProperty(String.valueOf(ResultCode.COMMON_REQ_NULL.getCode())));
            return resp;
        }

        logger.info("req:" + req.toString());
        UserDepMeetingRecord u = new UserDepMeetingRecord();
        u.setName(req.getName());
        u.setParentId(req.getParentId());
        u.setRemark(req.getRemark());
        u.setType(req.getType());
        u.setPhone(req.getPhone());
        u.setIdcard(req.getIdCard());
        u.setPersonSort(req.getPersonSort());
        u.setPost(req.getPost());
        u.setCarId(req.getCarId());
        u.setRole(req.getRole());
        u.setSex(req.getSex());
        u.setId(req.getId());

        int result = userService.editUserInfo(u);
        logger.info("result:" + result);

        resp.setResult(result);
        return resp;
    }


}
