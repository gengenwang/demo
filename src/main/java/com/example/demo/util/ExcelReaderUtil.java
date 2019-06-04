package com.example.demo.util;

import com.example.demo.exception.ExcpUtil;
import com.google.common.collect.Lists;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

/**
 * <p>注释</p>
 *
 * @author wanggengen
 * @version $ Id: ExcelReaderUtil.java, v 0.1 2017/6/20 16:04 wanggengen Exp $$
 **/
public class ExcelReaderUtil {
    /**
     * 从流中读取信息
     *
     * @param is2003Version 是否是excel2003或以下版本
     */
    public static List<Row> readContent(InputStream is, boolean is2003Version) {
        Workbook wb = null;
        List<Row> rows = null;
        try {
            wb = is2003Version ? new HSSFWorkbook(is) : new XSSFWorkbook(is);
            //默认读取第一个sheet
            Sheet sheet = wb.getSheetAt(0);
            rows = Lists.newArrayList();
            Iterator<Row> iter = sheet.rowIterator();
            while (iter.hasNext()) {
                Row row = iter.next();
                boolean isEmpty = ExcelUtils.isRowEmpty(row);
                if (!isEmpty) {//去除空行
                    rows.add(row);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw ExcpUtil.genServiceException("读取Excel异常");
        } catch (NoClassDefFoundError e) {
            e.printStackTrace();
            throw ExcpUtil.genServiceException("文件损坏");
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return rows;
    }

    /**
     * 读取Excel文件
     */
    public static List<Row> readContent(String filePath) throws Exception {
        return readContent(new File(filePath));
    }

    /**
     * 判断是否是excel2003或以下版本
     *
     * @param file
     * @return
     * @throws Exception
     */
    public static List<Row> readContent(File file) throws Exception {
        boolean is2003Version = file.getName().endsWith(".xls");
        return readContent(new FileInputStream(file), is2003Version);
    }
}
