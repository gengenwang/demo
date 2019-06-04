package com.example.demo.service.excel.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.domain.UserDomain;
import com.example.demo.service.excel.UserExcelService;
import com.example.demo.util.ExcelUtils;
import com.example.demo.util.IdGenerator;
import com.google.common.collect.Lists;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p></p>
 *
 * @author gengen.wang
 * @version $$ Id: UserExcelServiceImpl.java, V 0.1 2019/5/31 下午4:12 wanggengen Exp $$
 **/
@Service
public class UserExcelServiceImpl implements UserExcelService {

    @Resource
    private UserDao userDao;

    @Override
    public Boolean ExcelDoing(List<Row> rows, int sta, int end) {
        UserDomain user;
        List<UserDomain> userList = Lists.newArrayList();
        for (int y = sta; y < end; y++) {
            Row row = rows.get(y);
            if (row != null) {
                user = new UserDomain();
                user.setId(IdGenerator.get().toLong());//id
                user.setName(ExcelUtils.getValueOfCell(row.getCell(0)));
                user.setPassword(ExcelUtils.getValueOfCell(row.getCell(1)));
                user.setPhone(ExcelUtils.getValueOfCell(row.getCell(2)));
                userList.add(user);
            }

        }
        userDao.insertBatch(userList);

        return Boolean.TRUE;
    }

    @Override
    //不加锁报错
    //java.lang.RuntimeException: cell row is already empty
    public void createRows(HSSFSheet hSSFSheet, int startRow, int endRow, List<UserDomain> userDomains) {
        HSSFRow hssfRows;
        for (int i = startRow; i < endRow; i++) {
            UserDomain userDomain = userDomains.get(i);
            hssfRows = getRows(hSSFSheet, i);

            HSSFCell hSSFCells1 = hssfRows.createCell(0);//第1列
            hSSFCells1.setCellValue(userDomain.getName());
            HSSFCell hSSFCells2 = hssfRows.createCell(1);
            hSSFCells2.setCellValue(userDomain.getPassword());//第2列
            HSSFCell hSSFCells3 = hssfRows.createCell(2);
            hSSFCells3.setCellValue(userDomain.getPhone());//第3列
        }
    }

    /**
     * 创建行
     *
     * @param hSSFSheet
     * @param row
     * @return
     */
    private static HSSFRow getRows(HSSFSheet hSSFSheet, int row) {
        synchronized (UserExcelServiceImpl.class) {
            return hSSFSheet.createRow(row);
        }
    }


}

