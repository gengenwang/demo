package com.example.demo.model.excel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p></p>
 *
 * @author gengen.wang
 * @version $$ Id: ExcelDownloadModel.java, V 0.1 2019/6/4 下午5:23 wanggengen Exp $$
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExcelDownloadModel implements Serializable {

    /**
     * excel数据
     */
    private byte[] data;
    /**
     * excel名称
     */
    private String fileName;

}
