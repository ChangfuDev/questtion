package cn.edu.dlnu.question.result;

import cn.edu.dlnu.question.constant.ResponseConstant;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * @program: e3
 * @description: ${description}
 * @author: 张家豪
 * @create: 2018-11-08 23:03
 **/
@Data
public class LayUiResultDataList implements Serializable {
	private String code;
	private String msg;
	private List data;
	private Long count;

	public LayUiResultDataList(String code, String msg, List data, Long count) {
		this.code = code;
		this.msg = msg;
		this.data = data;
		this.count = count;
	}

	public LayUiResultDataList(String code, String msg, List data, Integer count) {
		this.code = code;
		this.msg = msg;
		this.data = data;
		this.count = Integer.toUnsignedLong(count);
	}

	/**
	 * 204  No Content 表示请求已成功处理，但是没有内容返回（就应该没有内容返回的状况）
	 * @return LayUiResultDataList
	 */
	public static LayUiResultDataList noData(){
		return new LayUiResultDataList(ResponseConstant.NO_CONTENT_CODE,ResponseConstant.NO_CONTENT_MESSAGE,null,0L);
	}

	/**
	 * 206 Partial Content 表示服务器已经完成了部分GET请求（客户端进行了范围请求）
	 * @return LayUiResultDataList
	 */
	public static LayUiResultDataList doGetOk(List data){
		return new LayUiResultDataList(ResponseConstant.PARTIAL_CONTENT_CODE,ResponseConstant.PARTIAL_CONTENT_MESSAGE,data,data.size());
	}

	/**
	 * 301 Moved Permanently 永久重定向，表示请求的资源已经永久的搬到了其他位置
	 * @return LayUiResultDataList
	 */
	public static LayUiResultDataList noUrl(){
		return new LayUiResultDataList(ResponseConstant.MOVED_PERMANENTLY_CODE,ResponseConstant.MOVED_PERMANENTLY_MESSAGE,null,0);
	}

	/**
	 * 400 Bad Request 表示请求报文存在语法错误或参数错误，服务器不理解
	 * @return LayUiResultDataList
	 */
	public static LayUiResultDataList badRequest(){
		return new LayUiResultDataList(ResponseConstant.BAD_REQUEST_CODE,ResponseConstant.BAD_REQUEST_MESSAGE,null,0);
	}
	/**
	 * 401 Unauthorized 表示请求报文存在语法错误或参数错误，服务器不理解
	 * @return LayUiResultDataList
	 */
	public static LayUiResultDataList unauthorized(){
		return new LayUiResultDataList(ResponseConstant.UNAUTHORIZED_CODE,ResponseConstant.UNAUTHORIZED_MESSAGE,null,0);
	}

	/**
	 * 403 Forbidden 表示对请求资源的访问被服务器拒绝了
	 * @return LayUiResultDataList
	 */
	public static LayUiResultDataList forbidden(){
		return new LayUiResultDataList(ResponseConstant.FORBIDDEN_CODE,ResponseConstant.FORBIDDEN_MESSAGE,null,0);
	}

	/**
	 * 404 Not Found 表示服务器找不到你请求的资源
	 * @return LayUiResultDataList
	 */
	public static LayUiResultDataList error(){
        return new LayUiResultDataList(ResponseConstant.ERROR_CODE, ResponseConstant.ERROR_MESSAGE, null, 0);
    }
    
    /**
     * 0 Message
     * 
     * @return LayUiResultDataList
     */
    public static LayUiResultDataList fail(String message) {
        return new LayUiResultDataList(ResponseConstant.FAIL_CODE, ResponseConstant.FAIL_MESSAGE, null, 0);
    }
    
	/**
	 * 500 Internal Server Error 表示服务器执行请求的时候出错了
	 * @return LayUiResultDataList
	 */
	public static LayUiResultDataList serverError(){
		return new LayUiResultDataList(ResponseConstant.INTERNAL_SERVER_ERROR_CODE,ResponseConstant.INTERNAL_SERVER_ERROR_MESSAGE,null,0);
	}
	/**
	 * 200 OK 请求正常处理完毕
	 * @return LayUiResultDataList
	 */
	public static LayUiResultDataList ok(List data){
		return new LayUiResultDataList(ResponseConstant.OK_CODE,ResponseConstant.OK_MESSAGE,data,data.size());
	}
	public static LayUiResultDataList ok(List data,Long total){
		return new LayUiResultDataList(ResponseConstant.OK_CODE,ResponseConstant.OK_MESSAGE,data,total);
	}

	@Override
	public String toString() {
		return "LayUiResultDataList{" +
				"code='" + code + '\'' +
				", msg='" + msg + '\'' +
				", data=" + data +
				", count=" + count +
				'}';
	}
}
