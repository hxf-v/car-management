package cn.edu.ahu.carmanagement.Utils;

/**
 * 自定义应用层异常
 * Created by Administrator on 2017/4/21.
 */
public class ApplicationException extends RuntimeException {
    public ApplicationException(String message) {
        super("应用层-" + message);
    }
}
