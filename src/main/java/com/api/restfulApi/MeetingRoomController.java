package com.api.restfulApi;

import com.api.common.ResultCode;
import com.api.entity.UserDepMeetingRecord;
import com.api.request.AddMeetingRoomRequest;
import com.api.request.EditMeetingRoomRequest;
import com.api.response.AddMeetingRoomResp;
import com.api.response.EditMeetingRoomResp;
import com.api.service.MeetingRoomService;
import com.api.utils.PropertyUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/restfulApi", produces = "application/json;charset=UTF-8")
@ResponseBody
public class MeetingRoomController
{
    private static Log logger = LogFactory.getLog(MeetingRoomController.class);

    @Autowired
    private MeetingRoomService meetingRoomService;

    @RequestMapping(value = "/addMeetingRoom", method = {RequestMethod.POST})
    public AddMeetingRoomResp addMeetingRoom(@RequestBody AddMeetingRoomRequest req)
    {
        AddMeetingRoomResp resp = new AddMeetingRoomResp();
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

            int maxMeetingRoomSort = meetingRoomService.getMaxMeetingRoomSort(req.getParentId());

            UserDepMeetingRecord info = new UserDepMeetingRecord();
            info.setName(req.getName());
            info.setParentId(req.getParentId());
            info.setRemark(req.getRemark());
            info.setType(req.getType());
            info.setMeetingroomSort(maxMeetingRoomSort);

            int result = meetingRoomService.addMeetingRoom(info);
            logger.info("result:" + result);
            resp.setResult(result);
        }

        logger.info("resp:" + resp.toString());
        return resp;
    }

    @RequestMapping(value = "/editMeetingRoom", method = {RequestMethod.POST})
    public EditMeetingRoomResp editMeetingRoom(@RequestBody EditMeetingRoomRequest req)
    {
        EditMeetingRoomResp resp = new EditMeetingRoomResp();
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
        info.setMeetingroomSort(req.getMeetingroomSort());

        int result = meetingRoomService.editMeetingRoom(info);
        logger.info("result:" + result);
        resp.setResult(result);


        logger.info("resp:" + resp.toString());
        return resp;
    }


}
