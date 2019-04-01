package com.mytools;

import java.awt.*;
import com.view.*;
import javax.swing.*;
import javax.swing.JDialog;
import java.util.List;
import com.model.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Locale;
//批量添加

public class addExcel extends JDialog{

    JTable jtable;
    List<GetApp> list = null;
    
        public void doAddExcle(boolean model, JTable jtable,List<GetApp> list,int tmp){
        this.jtable = jtable;
        this.list=list;
        String[] params = null;
        String sql = "insert into application(class,stu_num,address,teacher,week,time,Monday,Tuesday,Wednesday,Thurday,Friday) values(?,?,?,?,?,?,?,?,?,?,?)";

        String[] tmplist = {list.get(tmp).getClasses(),list.get(tmp).getStu_num(),list.get(tmp).getAddress(),list.get(tmp).getTeacher(),list.get(tmp).getWeek(),list.get(tmp).getTime(),list.get(tmp).getMonday(),list.get(tmp).getTuesday(),list.get(tmp).getWednseday(),list.get(tmp).getThurday(),list.get(tmp).getFriday()};
        params = tmplist;

        EmpModel em = new EmpModel();
        boolean b=em.UpdateModel(sql, params);
        if (!b&&tmp==list.size()-1) {
            JOptionPane.showMessageDialog(null, "添加失败，请输入正确数据类型！");
        } else if(b&&tmp==list.size()-1) {
            JOptionPane.showMessageDialog(null, "恭喜！添加成功！");
            AppInfo.sum+=list.size();
            AppInfo. p3_l1.setText("总记录是" + AppInfo.sum + "条");
            
            //EmpInfo tmpEmpInfo=new EmpInfo();
            this.showAll(jtable);
            this.dispose();
        }
        //this.dispose();
    }

        public void showAll(JTable jtable){
                        String sql="select * from application where 1=?";
			String[]params={"1"};
			EmpModel em=new EmpModel();
			em.query(sql, params);
			//jtable=new JTable(em);
                        jtable.setModel(em);
        }
	
	
	
}
