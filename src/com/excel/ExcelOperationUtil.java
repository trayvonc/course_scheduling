package com.excel;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;

import com.model.GetApp;
import javax.swing.JOptionPane;

public class ExcelOperationUtil {

    public List<GetApp> readExcelData(String filePath) {
        List<GetApp> list = new ArrayList<GetApp>();
        try {
            String path = filePath;
            //String path = "C:\\Users\\F Vadim\\Desktop\\查询结果.xls";
            File xlsFile = new File(path);
            FileInputStream fs = new FileInputStream(xlsFile);
            Workbook book = Workbook.getWorkbook(fs);//获取工作簿对象
            Sheet sheet = book.getSheet(0);//获取工作表对象,第一个sheet
            int rows = sheet.getRows();//获取工作表中的数据行数

            for (int i = 1; i <= rows - 1; i++) {//循环Excel工作表的行，并读取单元格数据
                GetApp g = new GetApp();

                int application_id = Integer.parseInt(sheet.getCell(0, i).getContents());
                String classes = sheet.getCell(1, i).getContents();
                String stu_num = sheet.getCell(2, i).getContents();
                //空值判断
                if (sheet.getCell(3, i).getContents().equals(null)) {
                    //不知道怎么处理，暂时先告诉程序无穷大就是空值
                    g.setAddress(null);
                } else {
                    String address = sheet.getCell(3, i).getContents();
                    g.setAddress(address);
                }

                String teacher = sheet.getCell(4, i).getContents();
                String week = sheet.getCell(5, i).getContents();
                String time = sheet.getCell(6, i).getContents();
                
                if (sheet.getCell(7, i).getContents().equals(null)) {
                    //不知道怎么处理，暂时先告诉程序无穷大就是空值
                    g.setMonday(null);
                } else {
                    String Monday = sheet.getCell(7, i).getContents();
                    g.setMonday(Monday);
                }

                if (sheet.getCell(8, i).getContents().equals(null)) {
                    //不知道怎么处理，暂时先告诉程序无穷大就是空值
                    g.setTuesday(null);
                } else {
                    String Thuesday = sheet.getCell(8, i).getContents();
                    g.setTuesday(Thuesday);
                }

                if (sheet.getCell(9, i).getContents().equals(null)) {
                    //不知道怎么处理，暂时先告诉程序无穷大就是空值
                    g.setWednseday(null);
                } else {
                    String Wednesday = sheet.getCell(9, i).getContents();
                    g.setWednseday(Wednesday);
                }

                if (sheet.getCell(10, i).getContents().equals(null)) {
                    //不知道怎么处理，暂时先告诉程序无穷大就是空值
                    g.setThurday(null);
                } else {
                    String Thurday = sheet.getCell(10, i).getContents();
                    g.setThurday(Thurday);
                }

                if (sheet.getCell(11, i).getContents().equals(null)) {
                    //不知道怎么处理，暂时先告诉程序无穷大就是空值
                    g.setFriday(null);
                } else {
                    String Friday = sheet.getCell(11, i).getContents();
                    g.setFriday(Friday);
                }

                g.setApplication_id(application_id);
                g.setClasses(classes);
                g.setStu_num(stu_num);
                g.setTeacher(teacher);
                g.setWeek(week);
                g.setTime(time);

                list.add(g);
            }
            System.out.println("------获取Excel中的数据【成功】------");
            return list;
        } catch (Exception e) {
            System.out.println("获取Excel中的数据【异常】，异常信息：" + e.getMessage());
            JOptionPane.showMessageDialog(null, "获取Excel中的数据【异常】，异常信息：" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
