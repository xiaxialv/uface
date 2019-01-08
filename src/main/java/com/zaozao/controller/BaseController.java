package com.zaozao.controller;

import com.zaozao.error.BusinessException;
import com.zaozao.error.EmBusinessError;
import com.zaozao.response.CommonReturnType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Sidney 2018-12-23.
 */
public class BaseController {

    public static final String CONTENT_TYPE_FORMED = "application/x-www-form-urlencoded";
    //定义exceptionhandler来解决未被controller层吸收的exception,
    // ExceptionHandler(Exception.class)指明收到何种异常会进入其处理环节
    //捕获异常并且返回HttpStatus为ok-->200
    //
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handlerException(HttpServletRequest request,Exception ex) {
        Map<String,Object> responseData = new HashMap<>();
        if (ex instanceof BusinessException) {
            BusinessException businessException = (BusinessException) ex;

            responseData.put("errCode", businessException.getErrCode());
            responseData.put("errMsg", businessException.getErrMsg());

        } else {
            responseData.put("errCode", EmBusinessError.UNKNOWN_ERROR.getErrCode());
            responseData.put("errMsg", EmBusinessError.UNKNOWN_ERROR.getErrMsg());
        }
        //        BusinessException businessException = (BusinessException)ex;
        //        //CommonReturnType commonReturnType = new CommonReturnType();
        //        //commonReturnType.setStatus("fail");
        //        Map<String,Object> responseData = new HashMap<>();
        //        responseData.put("errCode",businessException.getErrCode());
        //        responseData.put("errMsg",businessException.getErrMsg());
        //        //commonReturnType.setData(responseData);
        return CommonReturnType.create(responseData,"fail");
    }
}
