package com.example.demo.exception;

/**
 * <p>错误信息抽象,需要自己定义这个接口的实现，用于管理错误的errorCode和errorMessage。比如直接用一个Enum类管理</p>
 *
 * @author gengen.wang
 * @version $$ Id: ErrorDefinition.java, V 0.1 2019/5/31 下午3:54 wanggengen Exp $$
 **/
public interface ErrorDefinition {

    int getErrorCode();

    String getErrorMessage();

}
