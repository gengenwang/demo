package com.example.demo.exception;

/**
 * <p></p>
 *
 * @author gengen.wang
 * @version $$ Id: ExcpUtil.java, V 0.1 2019/5/31 下午3:52 wanggengen Exp $$
 **/
public class ExcpUtil {

    public static ServiceException genServiceException(String message) {
        return new ServiceException("业务异常", message);
    }

    public static ServiceException genServiceException(String message, Object... params) {
        return new ServiceException("业务异常", String.format(message, params));
    }

    public static ServiceException genServiceException(String message, Throwable exception) {
        return new ServiceException("业务异常", message, exception);
    }

    public static ServiceException genServiceException(ErrorDefinition errorDefinition) {
        return new ServiceException("业务异常", errorDefinition);
    }

    public static ServiceException genNotRollBackServiceException(String message) {
        return new NotRollBackServiceException("业务异常", message);
    }

    private ExcpUtil() {
    }

}
