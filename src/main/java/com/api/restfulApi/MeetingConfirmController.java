package com.api.restfulApi;

import com.api.common.ResultCode;
import com.api.entity.MeetingConfirmRecord;
import com.api.request.GetMeetingConfirmByMeetingIdAndPhoneReq;
import com.api.request.GetMeetingConfirmByMeetingIdReq;
import com.api.request.SaveMeetingConfirmReq;
import com.api.request.UpdateMeetingConfirmByMeetingIdAndPhoneReq;
import com.api.response.GetMeetingConfirmByMeetingIdAndPhoneResp;
import com.api.response.GetMeetingConfirmByMeetingIdResp;
import com.api.response.SaveMeetingConfirmResp;
import com.api.response.UpdateMeetingConfirmByMeetingIdAndPhoneResp;
import com.api.service.MeetingConfirmService;
import com.api.utils.PropertyUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/restfulApi", produces = "application/json;charset=UTF-8")
@ResponseBody
public class MeetingConfirmController
{

    private static Log logger = LogFactory.getLog(MeetingConfirmController.class);

    @Autowired
    private MeetingConfirmService meetingConfirmService;

    /**
     * 保存参会信息
     *
     * @param req
     * @return
     */
    @RequestMapping(value = "/saveMeetingConfirm", method = {RequestMethod.POST})
    public SaveMeetingConfirmResp saveMeetingConfirm(@RequestBody SaveMeetingConfirmReq req)
    {
        SaveMeetingConfirmResp resp = new SaveMeetingConfirmResp();
        resp.setResultCode(ResultCode.SUCCESS.getCode());
        resp.setResultDesc(PropertyUtil.getProperty(String.valueOf(ResultCode.SUCCESS.getCode())));

        if (null == req)
        {
            resp.setResultCode(ResultCode.COMMON_REQ_NULL.getCode());
            resp.setResultDesc(PropertyUtil.getProperty(String.valueOf(ResultCode.COMMON_REQ_NULL.getCode())));
            return resp;
        }

        logger.info("req is " + req.toString());

        int result = meetingConfirmService.saveMeetingConfirm(req.getMeetingId(), req.getPhone(), req.getUserName(), req.getAttendType(), req.getReason());

        if (0 == result)
        {
            resp.setResultCode(ResultCode.COMMON_DB_OPERATE_ERROR.getCode());
            resp.setResultDesc(PropertyUtil.getProperty(String.valueOf(ResultCode.COMMON_DB_OPERATE_ERROR.getCode())));
            return resp;
        }

        return resp;
    }

    /**
     * 更新参会信息
     *
     * @param
     * @param
     * @param
     * @param
     * @return
     */
    @RequestMapping(value = "/updateMeetingConfirmByMeetingIdAndPhone", method = {RequestMethod.POST})
    public UpdateMeetingConfirmByMeetingIdAndPhoneResp updateMeetingConfirmByMeetingIdAndPhone(@RequestBody UpdateMeetingConfirmByMeetingIdAndPhoneReq req)
    {
        UpdateMeetingConfirmByMeetingIdAndPhoneResp resp = new UpdateMeetingConfirmByMeetingIdAndPhoneResp();
        resp.setResultCode(ResultCode.SUCCESS.getCode());
        resp.setResultDesc(PropertyUtil.getProperty(String.valueOf(ResultCode.SUCCESS.getCode())));

        if (null == req)
        {
            resp.setResultCode(ResultCode.COMMON_REQ_NULL.getCode());
            resp.setResultDesc(PropertyUtil.getProperty(String.valueOf(ResultCode.COMMON_REQ_NULL.getCode())));
            return resp;
        }

        logger.info("req is " + req.toString());

        int result = meetingConfirmService.updateMeetingConfirmByMeetingIdAndPhone(req.getAttendType(), req.getReason(), req.getIsSign(), req.getMeetingId(), req.getPhone());

        if (0 == result)
        {
            resp.setResultCode(ResultCode.COMMON_DB_OPERATE_ERROR.getCode());
            resp.setResultDesc(PropertyUtil.getProperty(String.valueOf(ResultCode.COMMON_DB_OPERATE_ERROR.getCode())));
            return resp;
        }

        return resp;

    }

    /**
     * 根据会议Id查询出参会信息
     *
     * @return
     */
    @RequestMapping(value = "/getMeetingConfirmByMeetingId", method = {RequestMethod.POST})
    public GetMeetingConfirmByMeetingIdResp getMeetingConfirmByMeetingId(@RequestBody GetMeetingConfirmByMeetingIdReq req)
    {
        GetMeetingConfirmByMeetingIdResp resp = new GetMeetingConfirmByMeetingIdResp();
        resp.setResultCode(ResultCode.SUCCESS.getCode());
        resp.setResultDesc(PropertyUtil.getProperty(String.valueOf(ResultCode.SUCCESS.getCode())));

        if (null == req)
        {
            resp.setResultCode(ResultCode.COMMON_REQ_NULL.getCode());
            resp.setResultDesc(PropertyUtil.getProperty(String.valueOf(ResultCode.COMMON_REQ_NULL.getCode())));
            return resp;
        }

        logger.info("req is " + req.toString());

        List<MeetingConfirmRecord> result = meetingConfirmService.getMeetingConfirmByMeetingId(req.getMeetingId());

        if (null == result)
        {
            resp.setResultCode(ResultCode.COMMON_DB_OPERATE_ERROR.getCode());
            resp.setResultDesc(PropertyUtil.getProperty(String.valueOf(ResultCode.COMMON_DB_OPERATE_ERROR.getCode())));
            return resp;
        }

        logger.info("result is " + result.toString());

        resp.setMyMeeting(result);

        return resp;
    }

    /**
     * 根据meetingId和phone查询出参会信息
     *
     * @param
     * @param
     * @return
     */
    @RequestMapping(value = "/getMeetingConfirmByMeetingIdAndPhone", method = {RequestMethod.POST})
    public GetMeetingConfirmByMeetingIdAndPhoneResp getMeetingConfirmByMeetingIdAndPhone(@RequestBody GetMeetingConfirmByMeetingIdAndPhoneReq req)
    {
        GetMeetingConfirmByMeetingIdAndPhoneResp resp = new GetMeetingConfirmByMeetingIdAndPhoneResp();
        resp.setResultCode(ResultCode.SUCCESS.getCode());
        resp.setResultDesc(PropertyUtil.getProperty(String.valueOf(ResultCode.SUCCESS.getCode())));

        if (null == req)
        {
            resp.setResultCode(ResultCode.COMMON_REQ_NULL.getCode());
            resp.setResultDesc(PropertyUtil.getProperty(String.valueOf(ResultCode.COMMON_REQ_NULL.getCode())));
            return resp;
        }

        logger.info("req is " + req.toString());

        MeetingConfirmRecord result = meetingConfirmService.getMeetingConfirmByMeetingIdAndPhone(req.getPhone(), req.getMeetingId());

        if (null == result)
        {
            resp.setResultCode(ResultCode.COMMON_DB_OPERATE_ERROR.getCode());
            resp.setResultDesc(PropertyUtil.getProperty(String.valueOf(ResultCode.COMMON_DB_OPERATE_ERROR.getCode())));
            return resp;
        }

        logger.info("result is " + result.toString());

        resp.setInfo(result);

        return resp;
    }


}
