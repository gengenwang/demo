package com.example.demo.util;

import com.example.demo.exception.ExcpUtil;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.NetworkInterface;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p></p>
 *
 * @author gengen.wang
 * @version $$ Id: CommonUtil.java, V 0.1 2019/5/31 下午5:12 wanggengen Exp $$
 **/
@Slf4j
public class CommonUtil {

    public static String getString(@NonNull InputStream inputStream) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"))) {
            String s; // 依次循环，至到读的值为空
            while ((s = reader.readLine()) != null) {
                sb.append(s);
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

    public static byte[] getBytes(@NonNull InputStream input) throws IOException {
        int total = input.available();
        byte[] data = new byte[total];
        if (total != input.read(data)) {
            throw ExcpUtil.genServiceException("可读量与已读量不符!");
        }
        return data;
    }

    // 导出文件
    public static void exportResponse(@NonNull HttpServletResponse response, @NonNull byte[] data,
                                      @NonNull String fileName) throws IOException {
        // 将输出流写出到servlet
        response.setContentLength(data.length);
        // url编码
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
        response.setContentType("application/octet-stream");
        OutputStream servletOutPutStream = response.getOutputStream();
        servletOutPutStream.write(data);
        // 刷新servlet输出流
        servletOutPutStream.flush();
        servletOutPutStream.close();
    }

    public static void exportResponse(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                                      @NonNull byte[] data, @NonNull String fileName) throws IOException {

        // 将输出流写出到servlet
        response.setContentLength(data.length);
        // url编码
        response.setHeader("Content-Disposition",
                "attachment; filename=\"" + RequestUtil.encodeFileName(fileName, request) + "\"");
        response.setContentType("application/octet-stream");
        OutputStream servletOutPutStream = response.getOutputStream();
        servletOutPutStream.write(data);
        // 刷新servlet输出流
        servletOutPutStream.flush();
        servletOutPutStream.close();
    }

    /**
     * @param response
     * @param data
     * @throws IOException
     */
    public static void exportPDF(@NonNull HttpServletResponse response, @NonNull byte[] data) throws IOException {
        // 将输出流写出到servlet
        response.setContentLength(data.length);
        // url编码
        response.setContentType("application/pdf");
        OutputStream servletOutPutStream = response.getOutputStream();
        servletOutPutStream.write(data);
        // 刷新servlet输出流
        servletOutPutStream.flush();
        servletOutPutStream.close();
    }

    /**
     * 输出HTML
     *
     * @param response
     * @param data
     * @throws IOException
     */
    public static void exportResponse(@NonNull HttpServletResponse response, @NonNull byte[] data) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        OutputStream servletOutPutStream = response.getOutputStream();
        servletOutPutStream.write(data);
        // 刷新servlet输出流
        servletOutPutStream.flush();
        servletOutPutStream.close();
    }

    private static String[] units = {"", "十", "百", "千", "万", "十万", "百万", "千万", "亿", "十亿", "百亿", "千亿", "万亿"};
    private static char[] numArray = {'零', '一', '二', '三', '四', '五', '六', '七', '八', '九'};

    /**
     * 格式化输出中文数字
     *
     * @param num
     * @return
     */
    public static String formatInt(int num) {
        char[] val = String.valueOf(num).toCharArray();
        int len = val.length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            String m = val[i] + "";
            int n = Integer.valueOf(m);
            boolean isZero = n == 0;
            String unit = units[(len - 1) - i];
            if (isZero) {
                if ('0' == val[i - 1]) {
                    // 当前val[i]的下一个值val[i-1]为0则不输出零
                    continue;
                } else {
                    // 只有当当前val[i]的下一个值val[i-1]不为0才输出零
                    sb.append(numArray[n]);
                }
            } else {
                sb.append(numArray[n]);
                sb.append(unit);
            }
        }
        return StringUtils.strip(sb.toString(), "零");
    }

    /**
     * 获取classpath下资源
     *
     * @param relativeClassPath
     * @return
     */
    public static byte[] getClassPathResource(@NonNull String relativeClassPath) {
        byte[] data = null;
        try {
            ClassPathResource classPathResource = new ClassPathResource(relativeClassPath);
            data = getBytes(classPathResource.getInputStream());
            classPathResource.getInputStream().close();
        } catch (FileNotFoundException e) {
            throw ExcpUtil.genServiceException(relativeClassPath + " doesn't existName");
        } catch (IOException e) {
            return data;
        } catch (Exception e) {
            throw ExcpUtil.genServiceException("获取classpath资源错误", e);
        }
        return data;
    }

    public static String localMac() {
        StringBuilder sb = new StringBuilder();
        try {
            Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces();
            while (e.hasMoreElements()) {
                NetworkInterface ni = e.nextElement();
                byte[] mac = ni.getHardwareAddress();
                if (mac != null) {
                    sb.append(ni.toString());
                    for (int i = 0; i < mac.length; i++) {
                        if (i != 0) {
                            sb.append("-");
                        }
                        // 字节转换为整数
                        // 每8位一个数字
                        String str = Integer.toHexString(mac[i] & 0xff);
                        if (str.length() == 1) {
                            sb.append("0");
                        }
                        sb.append(str);
                    }
                    sb.append("|");
                }
            }
        } catch (Exception e) {
            log.error("get mac error:", e);
        }
        return sb.toString();
    }

    public final static Pattern PATTERN_ZIPCODE = Pattern.compile("^\\d{6}$");

    /**
     * 校验邮编合法性
     *
     * @param zipcode
     * @return
     */
    public static boolean validateZipcode(String zipcode) {
        if (null == zipcode) {
            return false;
        }

        Matcher matcher = PATTERN_ZIPCODE.matcher(zipcode);
        return matcher.find();
    }

}
