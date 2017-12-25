package com.api.restfulApi;

import com.api.common.ResultCode;
import com.api.entity.CookBookRecord;
import com.api.request.AddEditCookBookReq;
import com.api.request.GetAllCookBookReq;
import com.api.request.GetCookBookByIdReq;
import com.api.response.AddEditCookBookResp;
import com.api.response.GetAllCookBookResp;
import com.api.response.GetCookBookByIdResp;
import com.api.service.CookBookService;
import com.api.utils.PropertyUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/restfulApi", produces = "application/json;charset=UTF-8")
@ResponseBody
public class CookBookController
{
    private static Log logger = LogFactory.getLog(CookBookController.class);

    @Autowired
    private CookBookService cookBookService;

    @RequestMapping(value = "/saveCookBook", method = {RequestMethod.POST})
    public AddEditCookBookResp saveCookBook(@RequestBody AddEditCookBookReq req)
    {
        AddEditCookBookResp resp = new AddEditCookBookResp();
        resp.setResultCode(ResultCode.SUCCESS.getCode());
        resp.setResultDesc(PropertyUtil.getProperty(String.valueOf(ResultCode.SUCCESS.getCode())));

        if (null == req)
        {
            resp.setResultCode(ResultCode.COMMON_REQ_NULL.getCode());
            resp.setResultDesc(PropertyUtil.getProperty(String.valueOf(ResultCode.COMMON_REQ_NULL.getCode())));
            return resp;
        }

        logger.info("req is " + req.toString());

        CookBookRecord info = new CookBookRecord();
        info.setMenuContent(req.getMenuContent());
        info.setMenuType(req.getMenuType());
        info.setPublishDate(req.getPublishDate());
        info.setPublishUser(req.getPublishUser());
        info.setMenuTypeNumber(req.getMenuTypeNumber());

        int result = cookBookService.saveCookBook(info);
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


    @RequestMapping(value = "/updateCookBookById", method = {RequestMethod.POST})
    public AddEditCookBookResp updateCookBookById(@RequestBody AddEditCookBookReq req)
    {
        AddEditCookBookResp resp = new AddEditCookBookResp();
        resp.setResultCode(ResultCode.SUCCESS.getCode());
        resp.setResultDesc(PropertyUtil.getProperty(String.valueOf(ResultCode.SUCCESS.getCode())));

        if (null == req)
        {
            resp.setResultCode(ResultCode.COMMON_REQ_NULL.getCode());
            resp.setResultDesc(PropertyUtil.getProperty(String.valueOf(ResultCode.COMMON_REQ_NULL.getCode())));
            return resp;
        }

        logger.info("req is " + req.toString());

        CookBookRecord info = new CookBookRecord();
        info.setId(req.getId());
        info.setMenuContent(req.getMenuContent());
        info.setMenuType(req.getMenuType());
        info.setPublishDate(req.getPublishDate());
        info.setMenuTypeNumber(req.getMenuTypeNumber());

        int result = cookBookService.updateCookBookById(info);
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

    @RequestMapping(value = "/getAllCookBook", method = {RequestMethod.POST})
    public GetAllCookBookResp getAllCookBook(@RequestBody GetAllCookBookReq req)
    {
        GetAllCookBookResp resp = new GetAllCookBookResp();
        resp.setResultCode(ResultCode.SUCCESS.getCode());
        resp.setResultDesc(PropertyUtil.getProperty(String.valueOf(ResultCode.SUCCESS.getCode())));

        if (null == req)
        {
            resp.setResultCode(ResultCode.COMMON_REQ_NULL.getCode());
            resp.setResultDesc(PropertyUtil.getProperty(String.valueOf(ResultCode.COMMON_REQ_NULL.getCode())));
            return resp;
        }

        logger.info("req is " + req.toString());


        List<CookBookRecord> result = cookBookService.getAllCookBook(req.getCount() * 20);

        if (null != result)
        {
            logger.info("result is " + result.toString());
        }

        resp.setResult(result);
        return resp;
    }

    @RequestMapping(value = "/getCookBookById", method = {RequestMethod.POST})
    public GetCookBookByIdResp getCookBookById(@RequestBody GetCookBookByIdReq req)
    {
        GetCookBookByIdResp resp = new GetCookBookByIdResp();
        resp.setResultCode(ResultCode.SUCCESS.getCode());
        resp.setResultDesc(PropertyUtil.getProperty(String.valueOf(ResultCode.SUCCESS.getCode())));

        if (null == req)
        {
            resp.setResultCode(ResultCode.COMMON_REQ_NULL.getCode());
            resp.setResultDesc(PropertyUtil.getProperty(String.valueOf(ResultCode.COMMON_REQ_NULL.getCode())));
            return resp;
        }

        logger.info("req is " + req.toString());


        CookBookRecord result = cookBookService.getCookBookById(req.getId());

        if (null != result)
        {
            logger.info("result is " + result.toString());
        }

        resp.setInfo(result);
        return resp;
    }


}
