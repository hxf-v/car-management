package cn.edu.ahu.carmanagement.Utils;

/**
 * 请求数据异常返回400错误
 * Created by Administrator on 2017/4/21.
 */
public class RequestException extends ApplicationException {
    public RequestException(String message) {
        super(message);
    }
}
