package com.api.restfulApi;


import com.api.common.ResultCode;
import com.api.entity.BookingDiningRecord;
import com.api.entity.CookBookRecord;
import com.api.request.*;
import com.api.response.*;
import com.api.service.BookingDiningService;
import com.api.utils.PropertyUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/restfulApi", produces = "application/json;charset=UTF-8")
@ResponseBody
public class BookingDiningController
{
    private static Log logger = LogFactory.getLog(BookingDiningController.class);

    @Autowired
    private BookingDiningService bookingDiningService;

    @RequestMapping(value = "/saveBookingDining", method = {RequestMethod.POST})
    public AddEditBookDiningResp saveBookingDining(@RequestBody AddEditBookDiningReq req)
    {
        AddEditBookDiningResp resp = new AddEditBookDiningResp();
        resp.setResultCode(ResultCode.SUCCESS.getCode());
        resp.setResultDesc(PropertyUtil.getProperty(String.valueOf(ResultCode.SUCCESS.getCode())));

        if (null == req)
        {
            resp.setResultCode(ResultCode.COMMON_REQ_NULL.getCode());
            resp.setResultDesc(PropertyUtil.getProperty(String.valueOf(ResultCode.COMMON_REQ_NULL.getCode())));
            return resp;
        }

        logger.info("req is " + req.toString());

        BookingDiningRecord info = new BookingDiningRecord();
        info.setBookerUser(req.getBookerUser());
        info.setContent(req.getContent());
        info.setDiningDate(req.getDiningDate());
        info.setDiningRoom(req.getDiningRoom());
        info.setDiningType(req.getDiningType());
        info.setDiningTypeNumber(req.getDiningTypeNumber());

        int result = bookingDiningService.saveBookingDining(info);
        if (0 == result)
        {
            logger.info("result is 0");
            resp.setResultCode(ResultCode.COMMON_DB_OPERATE_ERROR.getCode());
            resp.setResultDesc(PropertyUtil.getProperty(String.valueOf(ResultCode.COMMON_DB_OPERATE_ERROR.getCode())));
            return resp;
        }
        else
        {
            resp.setResult("OK");
        }

        return resp;
    }


    @RequestMapping(value = "/updateBookingDiningById", method = {RequestMethod.POST})
    public AddEditBookDiningResp updateBookingDiningById(@RequestBody AddEditBookDiningReq req)
    {
        AddEditBookDiningResp resp = new AddEditBookDiningResp();
        resp.setResultCode(ResultCode.SUCCESS.getCode());
        resp.setResultDesc(PropertyUtil.getProperty(String.valueOf(ResultCode.SUCCESS.getCode())));

        if (null == req)
        {
            resp.setResultCode(ResultCode.COMMON_REQ_NULL.getCode());
            resp.setResultDesc(PropertyUtil.getProperty(String.valueOf(ResultCode.COMMON_REQ_NULL.getCode())));
            return resp;
        }

        logger.info("req is " + req.toString());


        BookingDiningRecord info = new BookingDiningRecord();
        info.setContent(req.getContent());
        info.setDiningDate(req.getDiningDate());
        info.setDiningRoom(req.getDiningRoom());
        info.setDiningType(req.getDiningType());
        info.setDiningTypeNumber(req.getDiningTypeNumber());
        info.setId(req.getId());

        int result = bookingDiningService.updateBookingDiningById(info);
        if (0 == result)
        {
            logger.info("result is 0");
            resp.setResultCode(ResultCode.COMMON_DB_OPERATE_ERROR.getCode());
            resp.setResultDesc(PropertyUtil.getProperty(String.valueOf(ResultCode.COMMON_DB_OPERATE_ERROR.getCode())));
            return resp;
        }
        else
        {
            resp.setResult("OK");
        }

        return resp;
    }

    @RequestMapping(value = "/getAllBookingDining", method = {RequestMethod.POST})
    public GetAllBookDiningResp getAllBookingDining(@RequestBody GetAllBookDiningReq req)
    {
        GetAllBookDiningResp resp = new GetAllBookDiningResp();
        resp.setResultCode(ResultCode.SUCCESS.getCode());
        resp.setResultDesc(PropertyUtil.getProperty(String.valueOf(ResultCode.SUCCESS.getCode())));

        if (null == req)
        {
            resp.setResultCode(ResultCode.COMMON_REQ_NULL.getCode());
            resp.setResultDesc(PropertyUtil.getProperty(String.valueOf(ResultCode.COMMON_REQ_NULL.getCode())));
            return resp;
        }

        logger.info("req is " + req.toString());


        List<BookingDiningRecord> result = bookingDiningService.getAllBookingDining(req.getCount() * 20);

        if (null != result)
        {
            logger.info("result is " + result.toString());
        }

        resp.setResult(result);
        return resp;
    }

    @RequestMapping(value = "/getBookingDiningById", method = {RequestMethod.POST})
    public GetBookDiningByIdResp getBookingDiningById(@RequestBody GetBookDiningByIdReq req)
    {
        GetBookDiningByIdResp resp = new GetBookDiningByIdResp();
        resp.setResultCode(ResultCode.SUCCESS.getCode());
        resp.setResultDesc(PropertyUtil.getProperty(String.valueOf(ResultCode.SUCCESS.getCode())));

        if (null == req)
        {
            resp.setResultCode(ResultCode.COMMON_REQ_NULL.getCode());
            resp.setResultDesc(PropertyUtil.getProperty(String.valueOf(ResultCode.COMMON_REQ_NULL.getCode())));
            return resp;
        }

        logger.info("req is " + req.toString());


        BookingDiningRecord result = bookingDiningService.getBookingDiningById(req.getId());

        if (null != result)
        {
            logger.info("result is " + result.toString());
        }

        resp.setInfo(result);
        return resp;
    }


}
