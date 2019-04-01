package com.view;

import com.excel.ExcelOperationUtil;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.JDialog;

import com.model.*;
import com.mytools.*;
import java.io.File;
import java.awt.event.*;
import java.util.Locale;
//添加界面

public class AddExcelDialog extends JFrame {

    public AddExcelDialog(AppInfo appInfo, String title, boolean model, JTable jtable) {
        this.setTitle(title);
        JFileChooser jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        //jfc.showDialog(new JLabel(), "选择");
        int result = jfc.showOpenDialog(null);
        
        if(result == JFileChooser.APPROVE_OPTION){
            File file = jfc.getSelectedFile();
        if (file.isDirectory()) {
            System.out.println("文件夹需要重新选择:" + file.getAbsolutePath());
        } else if (file.isFile()) {
            //System.out.println("文件:"+file.getAbsolutePath()); 
            List<GetApp> list = new ArrayList<GetApp>();
            list = new ExcelOperationUtil().readExcelData(file.getAbsolutePath());

            for (int tmp = 0; tmp <= list.size() - 1; tmp++) {
                String[] params = null;
                String sql = "insert into application(class,stu_num,address,teacher,week,time,Monday,Tuesday,Wednesday,Thurday,Friday) values(?,?,?,?,?,?,?,?,?,?,?)";
                String[] tmplist = {list.get(tmp).getClasses(), list.get(tmp).getStu_num(), list.get(tmp).getAddress() + "", list.get(tmp).getTeacher(), list.get(tmp).getWeek(), list.get(tmp).getTime(), list.get(tmp).getMonday(), list.get(tmp).getTuesday(), list.get(tmp).getWednseday(), list.get(tmp).getThurday(), list.get(tmp).getFriday()};
                params = tmplist;
                //判断空值
//                if (list.get(tmp).equals(list.get(tmp).setAddress(null))){
//                    list.
//                }
//                    
//                switch(flag){
//                    case 0:
//                        
//                }
                
                //插入数据
                EmpModel em = new EmpModel();
                boolean b = em.UpdateModel(sql, params);
                if (!b && tmp == list.size() - 1) {
                    JOptionPane.showMessageDialog(null, "添加失败，请输入正确数据类型！");
                } else if (b && tmp == list.size() - 1) {
                    JOptionPane.showMessageDialog(null, "恭喜！添加成功！");
                    AppInfo.sum += list.size();
                    AppInfo.p3_l1.setText("总记录是" + AppInfo.sum + "条");
                    //EmpInfo tmpEmpInfo=new EmpInfo();
                    this.showAll(jtable);
                    this.dispose();
                }
                //System.out.println(tmp); 
            }

        }
        }
        //System.out.println(jfc.getSelectedFile().getName());
    }

    public void showAll(JTable jtable) {
        String sql = "select * from application where 1=?";
        String[] params = {"1"};
        EmpModel em = new EmpModel();
        em.query(sql, params);
        //jtable=new JTable(em);
        jtable.setModel(em);
    }

}
