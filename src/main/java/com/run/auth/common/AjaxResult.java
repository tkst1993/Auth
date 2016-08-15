package com.run.auth.common;

import java.io.Serializable;

/**
 * Created by dello on 2016/7/27.
 */
public class AjaxResult implements Serializable {

    public static final Integer AJAX_STATUS_CODE_SUCCESS=0;
    public static final Integer AJAX_STATUS_CODE_WARN=1;
    public static final Integer AJAX_STATUS_CODE_ERROR=2;

    private Integer statusCode;

    private String message;

    public AjaxResult() {
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AjaxResult(Integer statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public static AjaxResult success(){
        AjaxResult ajaxResult=new AjaxResult();
        ajaxResult.setMessage("操作成功！");
        ajaxResult.setStatusCode(AJAX_STATUS_CODE_SUCCESS);
        return ajaxResult;
    }

    public static AjaxResult error(){
        AjaxResult ajaxResult=new AjaxResult();
        ajaxResult.setStatusCode(AJAX_STATUS_CODE_ERROR);
        return ajaxResult;
    }

    public static AjaxResult Warn(){
        AjaxResult ajaxResult=new AjaxResult();
        ajaxResult.setStatusCode(AJAX_STATUS_CODE_WARN);
        return ajaxResult;
    }
}
