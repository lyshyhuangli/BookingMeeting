package com.api.restfulApi;

import com.api.common.ResultCode;
import com.api.entity.*;
import com.api.request.*;
import com.api.response.*;
import com.api.service.PublishMeetingRoomService;
import com.api.service.UserService;
import com.api.utils.CommonUtils;
import com.api.utils.PropertyUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping(value = "/restfulApi", produces = "application/json;charset=UTF-8")
@ResponseBody
public class PublishMeetingRoomController
{
    private static Log logger = LogFactory.getLog(PublishMeetingRoomController.class);

    @Autowired
    private PublishMeetingRoomService publishMeetingRoomService;

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/getMyMeetingRoomByPhone", method = {RequestMethod.POST})
    public GetMyMeetingRoomByPhoneResp getMyMeetingRoomByPhone(@RequestBody GetMyMeetingRoomByPhoneReq req)
    {
        GetMyMeetingRoomByPhoneResp resp = new GetMyMeetingRoomByPhoneResp();
        resp.setResultCode(ResultCode.SUCCESS.getCode());
        resp.setResultDesc(PropertyUtil.getProperty(String.valueOf(ResultCode.SUCCESS.getCode())));

        if (null == req)
        {
            resp.setResultCode(ResultCode.COMMON_REQ_NULL.getCode());
            resp.setResultDesc(PropertyUtil.getProperty(String.valueOf(ResultCode.COMMON_REQ_NULL.getCode())));
            return resp;
        }

        logger.info("req is " + req.toString());

        //获取本部门的所有会议室
        List<ShowMeetingRedord> result = publishMeetingRoomService.getMyMeetingRoomByPhoneAndDate(req.getPhone(), req.getDate());
        logger.info("result:" + result.toString());

        for (ShowMeetingRedord s : result)
        {
            int id = s.getId();
            List<BookingMeetingRecord> m = resp.getMyMeetingRoom();
            boolean isExist = false;

            for (BookingMeetingRecord bb : m)
            {
                int bId = bb.getId();
                if (id == bId)
                {
                    isExist = true;

                    EveryMeeting e = new EveryMeeting();
                    e.setAmOrPm(s.getAmOrPm());
                    e.setBookUser(s.getBookUser());
                    e.setEndTime(s.getEndTime());
                    e.setMid(s.getMid());
                    e.setStartTime(s.getStartTime());
                    e.setThreaf(s.getThreaf());
                    bb.getMeetingList().add(e);
                    break;
                }
            }

            if (!isExist)
            {
                BookingMeetingRecord b = new BookingMeetingRecord();
                b.setId(s.getId());
                b.setMeetingDate(s.getMeetingDate());
                b.setName(s.getName());

                EveryMeeting e = new EveryMeeting();
                e.setAmOrPm(s.getAmOrPm());
                e.setBookUser(s.getBookUser());
                e.setEndTime(s.getEndTime());
                e.setMid(s.getMid());
                e.setStartTime(s.getStartTime());
                e.setThreaf(s.getThreaf());
                b.getMeetingList().add(e);

                resp.getMyMeetingRoom().add(b);
            }

        }

        logger.info("resp:" + resp.toString());
        return resp;
    }

    @RequestMapping(value = "/insertPublishMeeting", method = {RequestMethod.POST})
    public InsertPublishMeetingResp insertPublishMeeting(@RequestBody InsertPublishMeetingReq req)
    {
        InsertPublishMeetingResp resp = new InsertPublishMeetingResp();
        resp.setResultCode(ResultCode.SUCCESS.getCode());
        resp.setResultDesc(PropertyUtil.getProperty(String.valueOf(ResultCode.SUCCESS.getCode())));

        if (null == req)
        {
            resp.setResultCode(ResultCode.COMMON_REQ_NULL.getCode());
            resp.setResultDesc(PropertyUtil.getProperty(String.valueOf(ResultCode.COMMON_REQ_NULL.getCode())));
            return resp;
        }

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String dateTime = df.format(new Date());// new Date()为获取当前系统时间

        logger.info("req is " + req.toString());

        BookMeetingDbInfoRecord info = new BookMeetingDbInfoRecord();
        info.setAmOrPm(req.getAmOrPm());
        info.setBookUser(req.getBookUser());
        info.setClothes(req.getClothes());
        info.setConnectPerson(req.getConnectPerson());
        info.setConnectPhone(req.getConnectPhone());
        info.setContent(req.getContent());
        info.setCreateTime(dateTime);
        info.setEndTime(req.getEndTime());
        info.setFiles(req.getFiles());
        info.setMeetingDate(req.getMeetingDate());
        info.setMeetingDiscipline(req.getMeetingDiscipline());
        info.setMeetingroom(req.getMeetingroom());
        info.setPerson(req.getPerson());
        info.setPersonName(req.getPersonName());
        info.setQRcode(req.getQRcode());
        info.setRemark(req.getRemark());
        info.setStartTime(req.getStartTime());
        info.setThreaf(req.getThreaf());
        info.setWakeType(req.getWakeType());
        info.setDepartmentName(req.getDepartmentName());

        synchronized (this)
        {
            BookMeetingDbInfoRecord db = publishMeetingRoomService.
                    checkMeetingInfoByDateAmPmRoomId(info.getMeetingDate(), info.getAmOrPm(), Integer.parseInt(info.getMeetingroom()));


            if (null != db && StringUtils.isNotBlank(db.getBookUser()))
            {
                resp.setResultCode(ResultCode.MEETING_IS_BOOKED.getCode());
                resp.setResultDesc(PropertyUtil.getProperty(String.valueOf(ResultCode.MEETING_IS_BOOKED.getCode())));
                logger.error("result: meeting room is already booked.");
                resp.setResult(ResultCode.MEETING_IS_BOOKED.getCode());
                return resp;
            }
            else
            {
                int result = publishMeetingRoomService.insertPublishMeeting(info);
                logger.info("result:" + result);

                resp.setResult(result);
            }

        }


        return resp;
    }

    /**
     * 根据Id查询会议信息
     *
     * @param req
     * @return
     */
    @RequestMapping(value = "/getMeetingInfoById", method = {RequestMethod.POST})
    public GetMeetingInfoByIdResp getMeetingInfoById(@RequestBody GetMeetingInfoByIdReq req)
    {
        GetMeetingInfoByIdResp resp = new GetMeetingInfoByIdResp();
        resp.setResultCode(ResultCode.SUCCESS.getCode());
        resp.setResultDesc(PropertyUtil.getProperty(String.valueOf(ResultCode.SUCCESS.getCode())));

        if (null == req)
        {
            resp.setResultCode(ResultCode.COMMON_REQ_NULL.getCode());
            resp.setResultDesc(PropertyUtil.getProperty(String.valueOf(ResultCode.COMMON_REQ_NULL.getCode())));
            return resp;
        }

        logger.info("req is " + req.toString());

        BookMeetingDbInfoRecord result = publishMeetingRoomService.getMeetingInfoById(req.getId());
        logger.info("result:" + result);

        resp.setInfo(result);
        return resp;

    }

    /**
     * 获取要开的会议信息
     *
     * @param req
     * @return
     */
    @RequestMapping(value = "/getMyBingMeeting", method = {RequestMethod.POST})
    public GetMyBeingMeetingResp getMyBingMeeting(@RequestBody GetMyBeingMeetingReq req)
    {
        GetMyBeingMeetingResp resp = new GetMyBeingMeetingResp();
        resp.setResultCode(ResultCode.SUCCESS.getCode());
        resp.setResultDesc(PropertyUtil.getProperty(String.valueOf(ResultCode.SUCCESS.getCode())));

        if (null == req)
        {
            resp.setResultCode(ResultCode.COMMON_REQ_NULL.getCode());
            resp.setResultDesc(PropertyUtil.getProperty(String.valueOf(ResultCode.COMMON_REQ_NULL.getCode())));
            return resp;
        }

        UserDepMeetingRecord userInfo = userService.getUserInfoByPhone(req.getPhone());
        if (null == userInfo)
        {
            logger.error("result is null.");
            resp.setResultCode(ResultCode.COMMON_DB_OPERATE_ERROR.getCode());
            resp.setResultDesc(PropertyUtil.getProperty(String.valueOf(ResultCode.COMMON_DB_OPERATE_ERROR.getCode())));
            return resp;

        }

        logger.info("req is " + req.toString());
        String date1 = CommonUtils.getSpecifiedDayAfter(0);
        String date2 = CommonUtils.getSpecifiedDayAfter(1);
        String date3 = CommonUtils.getSpecifiedDayAfter(2);

        //获取
        List<MyMeetingInfoRecord> result = publishMeetingRoomService.getMyBeingMeeting(req.getPhone(), date1, date2, date3, String.valueOf(userInfo.getId()));
        logger.info("result:" + result);
        List<MyMeetingInfoRecord> temp = new ArrayList<MyMeetingInfoRecord>();
        if (null != result)
        {
            for (MyMeetingInfoRecord m : result)
            {
                if (req.getPhone().equalsIgnoreCase(m.getBookUser()))
                {
                    temp.add(m);
                    continue;
                }

                String id = m.getPerson();
                String[] ids = id.split(",");
                List<String> l = Arrays.asList(ids);
                if (l.contains(String.valueOf(userInfo.getId())))
                {
                    temp.add(m);
                }

            }
        }

        resp.setMyBeingMeetingInfo(temp);
        return resp;

    }

    /**
     * 获取已经开完的会议
     *
     * @param req
     * @return
     */
    @RequestMapping(value = "/getMyBedMeeting", method = {RequestMethod.POST})
    public GetMyBedMeetingResp getMyBedMeeting(@RequestBody GetMyBedMeetingReq req)
    {
        GetMyBedMeetingResp resp = new GetMyBedMeetingResp();
        resp.setResultCode(ResultCode.SUCCESS.getCode());
        resp.setResultDesc(PropertyUtil.getProperty(String.valueOf(ResultCode.SUCCESS.getCode())));

        if (null == req)
        {
            resp.setResultCode(ResultCode.COMMON_REQ_NULL.getCode());
            resp.setResultDesc(PropertyUtil.getProperty(String.valueOf(ResultCode.COMMON_REQ_NULL.getCode())));
            return resp;
        }

        UserDepMeetingRecord userInfo = userService.getUserInfoByPhone(req.getPhone());
        if (null == userInfo)
        {
            logger.info("result is null");
            resp.setResultCode(ResultCode.COMMON_DB_OPERATE_ERROR.getCode());
            resp.setResultDesc(PropertyUtil.getProperty(String.valueOf(ResultCode.COMMON_DB_OPERATE_ERROR.getCode())));
            return resp;

        }

        logger.info("req is " + req.toString());
        String date1 = CommonUtils.getSpecifiedDayAfter(0);
        String date2 = CommonUtils.getSpecifiedDayAfter(1);
        String date3 = CommonUtils.getSpecifiedDayAfter(2);

        //获取
        List<MyMeetingInfoRecord> result = publishMeetingRoomService.getMyBedMeeting(req.getPhone(), date1, date2, date3, String.valueOf(userInfo.getId()), req.getCount());
        logger.info("result:" + result);
        List<MyMeetingInfoRecord> temp = new ArrayList<MyMeetingInfoRecord>();
        if (null != result)
        {
            for (MyMeetingInfoRecord m : result)
            {
                if (req.getPhone().equalsIgnoreCase(m.getBookUser()))
                {
                    temp.add(m);
                    continue;
                }

                String id = m.getPerson();
                String[] ids = id.split(",");
                List l = Arrays.asList(ids);
                if (l.contains(String.valueOf(userInfo.getId())))
                {
                    temp.add(m);
                }
            }
        }

        resp.setMyBedMeetingInfo(temp);
        return resp;


    }

    /**
     * 更新会议信息
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/updateMeetingInfoById", method = {RequestMethod.POST})
    public UpdateMeetingInfoByIdResp updateMeetingInfoById(@RequestBody UpdateMeetingInfoByIdReq req)
    {
        UpdateMeetingInfoByIdResp resp = new UpdateMeetingInfoByIdResp();
        resp.setResultCode(ResultCode.SUCCESS.getCode());
        resp.setResultDesc(PropertyUtil.getProperty(String.valueOf(ResultCode.SUCCESS.getCode())));

        if (null == req)
        {
            resp.setResultCode(ResultCode.COMMON_REQ_NULL.getCode());
            resp.setResultDesc(PropertyUtil.getProperty(String.valueOf(ResultCode.COMMON_REQ_NULL.getCode())));
            return resp;
        }

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String dateTime = df.format(new Date());// new Date()为获取当前系统时间

        logger.info("req is " + req.toString());

        BookMeetingDbInfoRecord info = new BookMeetingDbInfoRecord();
        info.setId(req.getId());
        info.setAmOrPm(req.getAmOrPm());
        info.setBookUser(req.getBookUser());
        info.setClothes(req.getClothes());
        info.setConnectPerson(req.getConnectPerson());
        info.setConnectPhone(req.getConnectPhone());
        info.setContent(req.getContent());
        info.setCreateTime(dateTime);
        info.setEndTime(req.getEndTime());
        info.setFiles(req.getFiles());
        info.setMeetingDate(req.getMeetingDate());
        info.setMeetingDiscipline(req.getMeetingDiscipline());
        info.setMeetingroom(req.getMeetingroom());
        info.setPerson(req.getPerson());
        info.setPersonName(req.getPersonName());
        info.setQRcode(req.getQRcode());
        info.setRemark(req.getRemark());
        info.setStartTime(req.getStartTime());
        info.setThreaf(req.getThreaf());
        info.setWakeType(req.getWakeType());
        info.setDepartmentName(req.getDepartmentName());

        int result = publishMeetingRoomService.updateMeetingInfoById(info);
        logger.info("result:" + result);

        resp.setResult(result);
        return resp;
    }

    /**
     * 取消会议
     *
     * @param req
     * @return
     */
    @RequestMapping(value = "/delMeetingInfoById", method = {RequestMethod.POST})
    public DelMeetingInfoByIdResp delMeetingInfoById(@RequestBody DelMeetingInfoByIdReq req)
    {
        DelMeetingInfoByIdResp resp = new DelMeetingInfoByIdResp();
        resp.setResultCode(ResultCode.SUCCESS.getCode());
        resp.setResultDesc(PropertyUtil.getProperty(String.valueOf(ResultCode.SUCCESS.getCode())));

        if (null == req)
        {
            resp.setResultCode(ResultCode.COMMON_REQ_NULL.getCode());
            resp.setResultDesc(PropertyUtil.getProperty(String.valueOf(ResultCode.COMMON_REQ_NULL.getCode())));
            return resp;
        }

        logger.info("req is " + req.toString());

        int result = publishMeetingRoomService.deleteMeetingInfoById(req.getId());
        logger.info("result:" + result);

        resp.setResult(result);
        return resp;
    }

}
