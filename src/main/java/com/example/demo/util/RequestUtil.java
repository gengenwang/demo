package com.example.demo.util;

import com.google.common.base.Charsets;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * <p></p>
 *
 * @author gengen.wang
 * @version $$ Id: RequestUtil.java, V 0.1 2019/5/31 下午5:14 wanggengen Exp $$
 **/
public class RequestUtil {

    public static String encodeFileName(String fileName, HttpServletRequest request)
            throws UnsupportedEncodingException {

        String agent = request.getHeader("USER-AGENT");
        if (StringUtils.isBlank(agent) || agent.contains("Trident")) {
            // IE浏览器，这里不考虑IE6文件名不能超过150的问题
            String newFileName = URLEncoder.encode(fileName, "UTF-8");
            return StringUtils.replace(newFileName, "+", "%20");
        }
        return new String(fileName.getBytes(Charsets.UTF_8), Charsets.ISO_8859_1);
    }

}
