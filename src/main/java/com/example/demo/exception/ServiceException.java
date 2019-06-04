package com.example.demo.exception;

import java.util.Map;

/**
 * <p></p>
 *
 * @author gengen.wang
 * @version $$ Id: ServiceException.java, V 0.1 2019/5/31 下午3:53 wanggengen Exp $$
 **/
public class ServiceException extends UnifiedException{

    private static final long serialVersionUID = 1L;

    public ServiceException(String module, ErrorDefinition errorDefinition) {

        super(module, errorDefinition);
    }

    public ServiceException(String module, ErrorDefinition errorDefinition, Throwable cause) {

        super(module, errorDefinition, cause);
    }

    public ServiceException(String module, String message) {

        this(ExceptionLevel.SLIGHT, module, 400, message, null);
    }

    public ServiceException(String module, String message, Map<String, Object> context) {

        this(ExceptionLevel.SLIGHT, module, 400, message, context);
    }

    public ServiceException(String module, String message, Throwable cause) {

        this(ExceptionLevel.SLIGHT, module, 400, message, null, cause);
    }

    public ServiceException(ExceptionLevel level, String module, int code, String message,
                            Map<String, Object> context) {

        super(level, module, code, message, context);
    }

    public ServiceException(ExceptionLevel level, String module, int code, String message, Map<String, Object> context,
                            Throwable cause) {

        super(level, module, code, message, context, cause);
    }

}
