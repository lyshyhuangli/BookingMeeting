package com.api.restfulApi;

import com.api.common.ResultCode;
import com.api.entity.BookMeetingDbInfoRecord;
import com.api.entity.MeetingConfirmRecord;
import com.api.request.*;
import com.api.response.*;
import com.api.service.MeetingConfirmService;
import com.api.service.PublishMeetingRoomService;
import com.api.utils.PropertyUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/restfulApi", produces = "application/json;charset=UTF-8")
@ResponseBody
public class MeetingConfirmController
{

    private static Log logger = LogFactory.getLog(MeetingConfirmController.class);

    @Autowired
    private MeetingConfirmService meetingConfirmService;

    @Autowired
    private PublishMeetingRoomService publishMeetingRoomService;

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

        int result = meetingConfirmService.saveMeetingConfirm(req.getMeetingId(), req.getPhone(),
                req.getUserName(), req.getAttendType(), req.getReason(), req.getIsSign());

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

//        if (null == result)
//        {
//            resp.setResultCode(ResultCode.COMMON_DB_OPERATE_ERROR.getCode());
//            resp.setResultDesc(PropertyUtil.getProperty(String.valueOf(ResultCode.COMMON_DB_OPERATE_ERROR.getCode())));
//            return resp;
//        }
        if (null == result)
        {
            logger.error("result is null");
        }
        else
        {

            logger.info("result is " + result.toString());
        }


        resp.setInfo(result);

        return resp;
    }

    /**
     * 根据meetingId查看参加情况
     *
     * @param
     * @param
     * @return
     */
    @RequestMapping(value = "/viewMeetingConfirmInfoByMeetingId", method = {RequestMethod.POST})
    public ViewMeetingConfirmInfoResp viewMeetingConfirmInfoByMeetingId(@RequestBody ViewMeetingConfirmInfoReq req)
    {
        ViewMeetingConfirmInfoResp resp = new ViewMeetingConfirmInfoResp();
        resp.setResultCode(ResultCode.SUCCESS.getCode());
        resp.setResultDesc(PropertyUtil.getProperty(String.valueOf(ResultCode.SUCCESS.getCode())));

        if (null == req)
        {
            resp.setResultCode(ResultCode.COMMON_REQ_NULL.getCode());
            resp.setResultDesc(PropertyUtil.getProperty(String.valueOf(ResultCode.COMMON_REQ_NULL.getCode())));
            return resp;
        }

        logger.info("req is " + req.toString());

        //统计出未确认参加会议的信息
        BookMeetingDbInfoRecord meetingInfo = publishMeetingRoomService.getMeetingInfoById(req.getMeetingId());
        if(null == meetingInfo)
        {
            logger.error("Failed to get meetingInfo by id " + req.getMeetingId());
            resp.setResultCode(ResultCode.COMMON_DB_OPERATE_ERROR.getCode());
            resp.setResultDesc(PropertyUtil.getProperty(String.valueOf(ResultCode.COMMON_DB_OPERATE_ERROR.getCode())));
            return resp;
        }

        //查询出有的参会信息
        List<MeetingConfirmRecord> result = meetingConfirmService.getMeetingConfirmByMeetingId(req.getMeetingId());

        if(null == result)
        {
            //为空表示参会人都还没有点击确认
            List<MeetingConfirmRecord> r = new ArrayList<MeetingConfirmRecord>();
            String name  = meetingInfo.getPersonName();
            String[] names = name.split(",");

            for(String s:names)
            {
                MeetingConfirmRecord  m = new MeetingConfirmRecord();
                m.setIsSign(0);
                m.setAttendType(0);
                m.setUserName(s);
                r.add(m);
            }

            resp.setInfo(r);
        }
        else
        {
            List<MeetingConfirmRecord> temp = new ArrayList<MeetingConfirmRecord>();

            String name  = meetingInfo.getPersonName();
            String[] names = name.split(",");
            for(String s:names)
            {
                boolean isExist = false;
                for (MeetingConfirmRecord m: result)
                {
                    if(m.getUserName().equals(s))
                    {
                        isExist = true;
                        break;
                    }

                }

                if(!isExist)
                {
                    MeetingConfirmRecord  m = new MeetingConfirmRecord();
                    m.setIsSign(0);
                    m.setAttendType(0);
                    m.setUserName(s);
                    temp.add(m);
                }

            }

            resp.setInfo(result);
            resp.getInfo().addAll(temp);
        }

        return resp;
    }


}
