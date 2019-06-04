package com.example.demo.service.excel;

import org.apache.poi.ss.usermodel.Row;

import java.util.List;

/**
 * <p></p>
 *
 * @author gengen.wang
 * @version $$ Id: UserExcelService.java, V 0.1 2019/5/31 下午4:13 wanggengen Exp $$
 **/
public interface UserExcelService {

    Boolean ExcelDoing(List<Row> rows, int sta, int end);

}
