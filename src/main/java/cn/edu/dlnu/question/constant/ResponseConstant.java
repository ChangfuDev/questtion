package cn.edu.dlnu.question.constant;

import java.io.Serializable;

/**
 * @program: e3
 * @description: ${description}
 * @author: 张家豪
 * @create: 2018-11-08 23:28
 **/
public class ResponseConstant implements Serializable {
    
    public static final String NO_CONTENT_CODE = "204";
    
    public static final String NO_CONTENT_MESSAGE = "No Content";
    
    public static final String PARTIAL_CONTENT_CODE = "206";
    
    public static final String PARTIAL_CONTENT_MESSAGE = "Partial Content";
    
    public static final String MOVED_PERMANENTLY_CODE = "301";
    
    public static final String MOVED_PERMANENTLY_MESSAGE = "Moved Permanently";
    
    public static final String BAD_REQUEST_CODE = "400";
    
    public static final String BAD_REQUEST_MESSAGE = "Bad Request";
    
    public static final String UNAUTHORIZED_CODE = "401";
    
    public static final String UNAUTHORIZED_MESSAGE = "Unauthorized";
    
    public static final String FORBIDDEN_CODE = "403";
    
    public static final String FORBIDDEN_MESSAGE = "Forbidden";
    
    public static final String NOT_FOUND_CODE = "404";
    
    public static final String ERROR_CODE = "100";
    
    public static final String ERROR_MESSAGE = "请求数据错误,请修正";
    
    public static final String NOT_FOUND_MESSAGE = "Not Found";
    
    public static final String INTERNAL_SERVER_ERROR_CODE = "500";
    
    public static final String INTERNAL_SERVER_ERROR_MESSAGE = "Internal Server Error";
    
    public static final String OK_CODE = "200";
    
    public static final String OK_MESSAGE = "OK";
    
    public static final String FAIL_CODE = "0";
    
    public static final String FAIL_MESSAGE = "失败";
}
