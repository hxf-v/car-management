package cn.edu.ahu.carmanagement.Utils;


import java.util.List;

/**
 * 参数检测
 * Created by moran on 2017/3/20.
 */
public class RequestParamCheck {
    /**
     * List 检测
     *
     * @param list List
     * @param <T>  List类型
     */
    public static <T> void checkList(List<T> list, String exceptionName) {
        if (list == null)
            throw new RequestException(exceptionName + " 为 null");
        if (list.isEmpty())
            throw new RequestException(exceptionName + " 为 empty");
    }

    /**
     * null 检测
     *
     * @param parameter 参数
     * @param <T>       参数类型
     */
    public static <T> void checkNull(T parameter, String exceptionName) {
        if (parameter == null)
            throw new RequestException(exceptionName + " 为 null");
    }
}