/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.view;
//这是课表展示界面,提供搜索和字符串输入

import com.mytools.*;
import com.model.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

public class showTimeTable extends JPanel implements ActionListener {
    //定义组件

    JPanel p1, p2, p3, p4, p5;
    SearchTimeTable p21;
    JLabel p1_l1;
    static JLabel p3_l1;
    JTextField p1_jtf;
    JButton p1_jb,p1_jb1, p4_jb1, p4_jb2, p4_jb3, p4_jb4;
    JTable jtable;
    JScrollPane jsp;
    EmpModel em = null;

    public showTimeTable() {
        //job = s;
        //创建组件

        //北
        p1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        p1_l1 = new JLabel("请输入实验教室或班级或老师:");
        p1_l1.setFont(MyFont.f1);
        p1_jtf = new JTextField(20);
        p1_jb = new JButton("查询");
        p1_jb.addActionListener(this);
        p1_jb.setToolTipText("键入为空时显示所有记录");
        p1_jb.setFont(MyFont.f4);
        p1_jb.setContentAreaFilled(false);  //只须加上此句
         java.net.URL imageUrl1=Windows1.class.getResource("/com/image/center_image/查询.png");
        p1_jb.setIcon(new ImageIcon(imageUrl1));
        
        p1_jb1 = new JButton("查看全表");
        p1_jb1.addActionListener(this);
        p1_jb1.setToolTipText("查看整张课程表");
        p1_jb1.setFont(MyFont.f4);
        p1_jb1.setContentAreaFilled(false);  //只须加上此句
          java.net.URL imageUrl2=Windows1.class.getResource("/com/image/center_image/查询.png");
        p1_jb1.setIcon(new ImageIcon(imageUrl2));
        p1.add(p1_l1);
        p1.add(p1_jtf);
        p1.add(p1_jb);
        p1.add(p1_jb1);
        //中间显示完整课表
        p2 = new JPanel(new BorderLayout());
        String[] params = {"1"};
        String sql = "select * from final_table where 1=?";
        em = new EmpModel();
        em.query(sql, params);
        jtable = new JTable(em);
        
//		jtable.setModel(em);
        jsp = new JScrollPane(jtable);
        p2.add(jsp);



        //南
        //this.setBackground(Color.blue);
        this.setLayout(new BorderLayout());
        this.add(p1, "North");
        this.add(p2, "Center");
        //this.add(p5, "South");
        this.setVisible(true);
//        DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
//        cr.setHorizontalAlignment(JLabel.CENTER);
//        jtable.setDefaultRenderer(Object.class, cr);

    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO 自动生成的方法存根

        if (arg0.getSource().equals(p1_jb)) {
            if(p21!=null){
                this.remove(p21);
                this.add(p2, "Center");
                
            }
            //查询
            //System.out.println("按下");
            if (p1_jtf.getText().trim().equals("")) {
                String[] params = {"1"};
                String sql = "select * from final_table where 1=?";
                em = new EmpModel();
                em.query(sql, params);
                //exChange(em);
                jtable.setModel(em);
                
            } else {
                String params[] = {p1_jtf.getText().trim(), p1_jtf.getText().trim(),p1_jtf.getText().trim(), p1_jtf.getText().trim(), p1_jtf.getText().trim(), p1_jtf.getText().trim()};
                String sql = "select * from final_table where teacher=? or Class=? or lab1=? or lab2=? or lab3=? or lab4=?";
                em = new EmpModel();
                em.query(sql, params);
                //exChange(em);
                jtable.setModel(em);
                
            }

        }else if (arg0.getSource().equals(p1_jb1)) {
            String tname = new String();
            try {
                p21 = new SearchTimeTable(tname);
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.remove(p2);
            this.add(p21, "Center");
        } 
    }
    //显示所有记录，用于更新表单和记录数
//    public void showAll() {
//        String[] params = {"1"};
//        String sql = "select * from final_table where 1=?";
//        em = new EmpModel();
//        em.query(sql, params);
//        jtable.setModel(em);
//    }
    
    public void flashTable(){
        String[] params = {"1"};
        String sql = "select * from final_table where 1=?";
        em = new EmpModel();
        em.query(sql, params);
        jtable.setModel(em);
    }
public JTable returnjJTable(){
    return jtable;
}

//public void exChange(EmpModel em){
//    
//    for(int i=0;i<em.getRowCount();i++){
//        for(int j=3;j<em.getColumnCount();j++){
//    switch(Integer.parseInt(em.getValueAt(i,j)+"")){ 
//        case 0:
//           em.setValueAt("第九周一上午", i,j);
//           System.out.println("替换");
//           break;
//           case 1:
//           em.setValueAt("第九周二上午", i,j);
//           break;
//           case 2:
//           em.setValueAt("第九周三上午", i,j);
//           break;
//           case 3:
//           em.setValueAt("第九周四上午", i,j);
//           break;
//           case 4:
//           em.setValueAt("第九周五上午", i,j);
//           break;
//           case 5:
//           em.setValueAt("第九周一下午", i,j);
//           break;
//           case 6:
//           em.setValueAt("第九周二下午", i,j);
//           break;
//           case 7:
//           em.setValueAt("第九周三下午", i,j);
//           break;
//           case 8:
//           em.setValueAt("第九周四下午", i,j);
//           break;
//           case 9:
//           em.setValueAt("第九周五下午", i,j);
//           break;
//           case 10:
//           em.setValueAt("第十周一上午", i,j);
//           break;
//           case 11:
//           em.setValueAt("第十周二上午", i,j);
//           break;
//           case 12:
//           em.setValueAt("第十周三上午", i,j);
//           break;
//           case 13:
//           em.setValueAt("第十周四上午", i,j);
//           break;
//           case 14:
//           em.setValueAt("第十周五上午", i,j);
//           break;
//           case 15:
//           em.setValueAt("第十周一下午", i,j);
//           break;
//           case 16:
//           em.setValueAt("第十周二下午", i,j);
//           break;
//           case 17:
//           em.setValueAt("第十周三下午", i,j);
//           break;
//           case 18:
//           em.setValueAt("第十周四下午", i,j);
//           break;
//           case 19:
//           em.setValueAt("第十周五下午", i,j);
//           break;
//        }
//    }
//    
//        
//    }
//}
}

