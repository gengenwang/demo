package com.example.demo.service.excel;

import com.example.demo.model.excel.ExcelDownloadModel;

import java.io.InputStream;

/**
 * <p></p>
 *
 * @author gengen.wang
 * @version $$ Id: ExcelReadService.java, V 0.1 2019/5/31 下午3:31 wanggengen Exp $$
 **/
public interface ExcelReadService {

    Boolean userUpload(InputStream is, boolean if2003Version);

    ExcelDownloadModel userDownload();

}
