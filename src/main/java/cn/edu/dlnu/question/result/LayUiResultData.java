package cn.edu.dlnu.question.result;

import cn.edu.dlnu.question.constant.ResponseConstant;
import java.io.Serializable;
import lombok.Data;

/**
 * @program: e3
 * @description: ${description}
 * @author: 张家豪
 * @create: 2018-11-08 23:24
 **/
@Data
public class LayUiResultData implements Serializable {
	private String code;
	private String msg;
	private Object data;

	public LayUiResultData(String code, String msg, Object data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	/**
	 * 204  No Content 表示请求已成功处理，但是没有内容返回（就应该没有内容返回的状况）
	 * @return LayUiResultData
	 */
	public static LayUiResultData noData(){
		return new LayUiResultData(ResponseConstant.NO_CONTENT_CODE,ResponseConstant.NO_CONTENT_MESSAGE,null);
	}
    
    /**
     * 0 失败
     * 
     * @return LayUiResultData
     */
    public static LayUiResultData fail() {
        return new LayUiResultData(ResponseConstant.FAIL_CODE, ResponseConstant.FAIL_MESSAGE, null);
    }
    
    /**
     * 0 Message
     *
     * @return LayUiResultData
     */
    public static LayUiResultData fail(String message) {
        return new LayUiResultData(ResponseConstant.FAIL_CODE, message, null);
    }
	/**
	 * 206 Partial Content 表示服务器已经完成了部分GET请求（客户端进行了范围请求）
	 * @return LayUiResultData
	 */
	public static LayUiResultData doGetOk(Object object){
		return new LayUiResultData(ResponseConstant.PARTIAL_CONTENT_CODE,ResponseConstant.PARTIAL_CONTENT_MESSAGE,object);
	}

	/**
	 * 301 Moved Permanently 永久重定向，表示请求的资源已经永久的搬到了其他位置
	 * @return LayUiResultData
	 */
	public static LayUiResultData noUrl(){
		return new LayUiResultData(ResponseConstant.MOVED_PERMANENTLY_CODE,ResponseConstant.MOVED_PERMANENTLY_MESSAGE,null);
	}

	/**
	 * 400 Bad Request 表示请求报文存在语法错误或参数错误，服务器不理解
	 * @return LayUiResultData
	 */
	public static LayUiResultData badRequest(){
		return new LayUiResultData(ResponseConstant.BAD_REQUEST_CODE,ResponseConstant.BAD_REQUEST_MESSAGE,null);
	}
	/**
	 * 401 Unauthorized 表示请求报文存在语法错误或参数错误，服务器不理解
	 * @return LayUiResultData
	 */
	public static LayUiResultData unauthorized(){
		return new LayUiResultData(ResponseConstant.UNAUTHORIZED_CODE,ResponseConstant.UNAUTHORIZED_MESSAGE,null);
	}

	/**
	 * 403 Forbidden 表示对请求资源的访问被服务器拒绝了
	 * @return LayUiResultData
	 */
	public static LayUiResultData forbidden(){
		return new LayUiResultData(ResponseConstant.FORBIDDEN_CODE,ResponseConstant.FORBIDDEN_MESSAGE,null);
	}

    /**
     * 100 请求数据错误,请修正 表示服务器找不到你请求的资源
     * 
     * @return LayUiResultData
     */
    public static LayUiResultData error() {
        return new LayUiResultData(ResponseConstant.ERROR_CODE, ResponseConstant.ERROR_MESSAGE, null);
    }
	/**
	 * 500 Internal Server Error 表示服务器执行请求的时候出错了
	 * @return LayUiResultData
	 */
	public static LayUiResultData serverError(){
		return new LayUiResultData(ResponseConstant.INTERNAL_SERVER_ERROR_CODE,ResponseConstant.INTERNAL_SERVER_ERROR_MESSAGE,null);
	}
	/**
	 * 200 OK 请求正常处理完毕
	 * @return LayUiResultData
	 */
	public static LayUiResultData ok(Object object){
		return new LayUiResultData(ResponseConstant.OK_CODE,ResponseConstant.OK_MESSAGE,object);
	}
}
