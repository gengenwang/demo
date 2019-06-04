package com.example.demo.controller.excel;

import com.example.demo.exception.ExcpUtil;
import com.example.demo.model.excel.ExcelDownloadModel;
import com.example.demo.service.excel.ExcelReadService;
import com.example.demo.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.InputStream;

/**
 * <p></p>
 *
 * @author gengen.wang
 * @version $$ Id: ExcelReadController.java, V 0.1 2019/5/31 下午3:30 wanggengen Exp $$
 **/
@RestController
@RequestMapping("/excelRead")
@Slf4j
@Validated
public class ExcelReadController {

    @Resource
    private ExcelReadService excelReadService;

    /**
     * 上传Excel
     *
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/userUpload", method = RequestMethod.POST)
    public Boolean userUpload(@NotNull @RequestParam("file") MultipartFile file) throws IOException {
        if (file.getSize() <= 0) {
            throw ExcpUtil.genServiceException("未上传文件");
        }
        InputStream inputStream = file.getInputStream();

        return excelReadService.userUpload(inputStream, file.getName().endsWith("xls"));
    }

    /**
     * 上传Excel
     */
    @RequestMapping(value = "/userDownload", method = RequestMethod.GET)
    public void userDownload(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ExcelDownloadModel excelDownloadModel = excelReadService.userDownload();
        CommonUtil.exportResponse(request, response, excelDownloadModel.getData(), excelDownloadModel.getFileName());
    }

    /**
     * Excel上传主页
     */
    @RequestMapping(value = "/userHtml", method = RequestMethod.GET)
    public void userHtml(HttpServletResponse response) throws IOException {

        StringBuilder result = new StringBuilder();
        result.append("<html>");
        result.append("<head>");
        result.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"/>");
        result.append("<title>用户导入</title>");
        result.append("</head>");
        result.append("<body>");
        String s = "<form action=\"/excelRead/userUpload\" method=\"post\" enctype=\"multipart/form-data\">\n" +
                "<p>用户导入:<input type=\"file\" name=\"file\"/>" + getBlank(5) + "<input type=\"submit\" value=\"上传\"/></p>\n" +
                "</form>";
        result.append(s);
        String s2 = "<a href=\"/excelRead/userDownload\" >用户下载</a>";
        result.append(s2);
        result.append("</body>");
        result.append("</html>");
        CommonUtil.exportResponse(response, result.toString().getBytes("utf-8"));
    }

    /**
     * 获取空格
     *
     * @param count
     * @return
     */
    private static String getBlank(int count) {
        StringBuffer s = new StringBuffer("");
        for (int i = 0; i < count; i++) {
            s.append("&nbsp;");
        }
        return s.toString();
    }


}
