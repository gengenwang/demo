package com.example.demo.exception;

import java.util.Map;

/**
 * <p></p>
 *
 * @author gengen.wang
 * @version $$ Id: NotRollBackServiceException.java, V 0.1 2019/5/31 下午4:01 wanggengen Exp $$
 **/
public class NotRollBackServiceException extends ServiceException{

    public NotRollBackServiceException(String module, ErrorDefinition errorDefinition) {
        super(module, errorDefinition);
    }

    public NotRollBackServiceException(String module, ErrorDefinition errorDefinition, Throwable cause) {
        super(module, errorDefinition, cause);
    }

    public NotRollBackServiceException(String module, String message) {
        super(module, message);
    }

    public NotRollBackServiceException(String module, String message, Map<String, Object> context) {
        super(module, message, context);
    }

    public NotRollBackServiceException(String module, String message, Throwable cause) {
        super(module, message, cause);
    }

    public NotRollBackServiceException(ExceptionLevel level, String module, int code,
                                       String message, Map<String, Object> context) {
        super(level, module, code, message, context);
    }

    public NotRollBackServiceException(ExceptionLevel level, String module, int code, String message,
                                       Map<String, Object> context, Throwable cause) {
        super(level, module, code, message, context, cause);
    }

}
