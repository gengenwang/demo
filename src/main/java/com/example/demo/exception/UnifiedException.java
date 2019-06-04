package com.example.demo.exception;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>统一的异常定义,默认异常级别是轻微</p>
 *
 * @author gengen.wang
 * @version $$ Id: UnifiedException.java, V 0.1 2019/5/31 下午3:55 wanggengen Exp $$
 **/
@Data
public class UnifiedException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    /**
     * 异常级别
     */
    private ExceptionLevel level = ExceptionLevel.COMMON;

    /**
     * 业务模块
     */
    private String module;

    /**
     * 自定义code,可根据这个code做异常的筛选、特殊处理
     */
    private int code;

    /**
     * 自定义信息回现
     */
    private String message;

    /**
     * 异常上下文，可以设置一些关键业务参数
     */
    private Map<String, Object> context;

    public UnifiedException(String module, ErrorDefinition errorDefinition) {

        super(errorDefinition.getErrorMessage());
        this.message = errorDefinition.getErrorMessage();
        this.module = module;
        this.code = errorDefinition.getErrorCode();
    }

    public UnifiedException(String module, ErrorDefinition errorDefinition, Throwable cause) {

        super(errorDefinition.getErrorMessage(), cause);
        this.message = errorDefinition.getErrorMessage();
        this.module = module;
        this.code = errorDefinition.getErrorCode();
    }

    public UnifiedException(ExceptionLevel level, String module, ErrorDefinition errorDefinition,
                            Map<String, Object> context, Throwable cause) {

        super(errorDefinition.getErrorMessage(), cause);
        this.message = errorDefinition.getErrorMessage();
        this.level = level;
        this.module = module;
        this.code = errorDefinition.getErrorCode();
        this.context = context;
    }

    protected UnifiedException(ExceptionLevel level, String module, int code, String message,
                               Map<String, Object> context) {

        super(message);
        this.message = message;
        this.level = level;
        this.module = module;
        this.code = code;
        this.context = context;
    }

    protected UnifiedException(ExceptionLevel level, String module, int code, String message,
                               Map<String, Object> context, Throwable cause) {

        super(message, cause);
        this.message = message;
        this.level = level;
        this.module = module;
        this.code = code;
        this.context = context;
    }

    public UnifiedException addContext(String key, Object value) {

        if (context == null) {
            context = new HashMap<>();
        }
        context.put(key, value);
        return this;
    }


}
