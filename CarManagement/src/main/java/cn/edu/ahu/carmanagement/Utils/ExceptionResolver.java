package cn.edu.ahu.carmanagement.Utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常异常解析器
 * 设定错误码并返回错误信息
 * 1、解析RequestException
 * 2、解析ApplicationExceptionHandler
 * Created by Administrator on 2017/4/21.
 */
@ControllerAdvice
@ResponseBody
public class ExceptionResolver {
    /**
     * 请求参数异常解析为400错误
     *
     * @param request   请求
     * @param exception 错误
     * @return 400 Json
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = RequestException.class)
    public Response RequestExceptionHandler(HttpServletRequest request, RequestException exception) {
        return new Response().failure(exception.getMessage());
    }

    /**
     * 资源不存在异常解析为404错误
     *
     * @param request   请求
     * @param exception 错误
     * @return 404 Json
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = NotFoundException.class)
    public Response NotFoundExceptionHandler(HttpServletRequest request, NotFoundException exception) {
        return new Response().failure(exception.getMessage());
    }

    /**
     * 请求内部异常解析为500错误
     *
     * @param request   请求
     * @param exception 错误
     * @return 500 Json
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = ApplicationException.class)
    public Response ExceptionHandler(HttpServletRequest request, Exception exception) {
        return new Response().failure(exception.getMessage());
    }
}
