package com.example.demo.exception;

/**
 * <p>异常等级 1： 严重 2：一般 3: 非重要异常 值越大表示级别越低,默认是非重要异常</p>
 *
 * @author gengen.wang
 * @version $$ Id: ExceptionLevel.java, V 0.1 2019/5/31 下午3:58 wanggengen Exp $$
 **/
public enum ExceptionLevel {

    SERIOUS(1, "严重"), COMMON(2, "一般"), SLIGHT(3, "轻微");

    private final int value;

    private final String description;

    ExceptionLevel(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

}
