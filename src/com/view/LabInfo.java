package com.view;
//这是人事管理界面

import com.mytools.*;
import com.model.*;
import static com.mytools.MyFont.f0;
import static com.view.AppInfo.p3_l1;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

public class LabInfo extends JPanel implements ActionListener {
    //定义组件

    JPanel p1, p2, p3, p4, p5;
    JLabel p1_l1;
    static JLabel p3_l1;
    JTextField p1_jtf;
    JButton p1_jb, p4_jb1, p4_jb2, p4_jb3, p4_jb4;
    JTable jtable;
    JScrollPane jsp;
    EmpModel em = null;
    static int sum;

    public LabInfo() {
        //创建组件

        //北
        p1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        p1_l1 = new JLabel("请输入id或实验室地址:");
        p1_l1.setFont(MyFont.f1);
        p1_jtf = new JTextField(20);
        p1_jb = new JButton("查询");
        p1_jb.addActionListener(this);
        p1_jb.setToolTipText("键入为空时显示所有记录");
        p1_jb.setFont(MyFont.f4);
        p1_jb.setContentAreaFilled(false);  //只须加上此句
        java.net.URL imageUrl1=Windows1.class.getResource("/com/image/center_image/查询.png");
        p1_jb.setIcon(new ImageIcon(imageUrl1));
        p1.add(p1_l1);
        p1.add(p1_jtf);
        p1.add(p1_jb);

        //中间
        p2 = new JPanel(new BorderLayout());
        String[] params = {"1"};
        String sql = "select * from lab_info where 1=?";
        em = new EmpModel();
        em.query(sql, params);
        jtable = new JTable(em);
//		jtable.setModel(em);
        jsp = new JScrollPane(jtable);

        p2.add(jsp);

        //南
        p5 = new JPanel(new BorderLayout());
        p3 = new JPanel(new FlowLayout(FlowLayout.LEFT));

        sql = "select count(*) from lab_info";
        em = new EmpModel();
        sum = em.getNum(sql);

//		jtable.setModel(em);
        p3_l1 = new JLabel("总记录是" + sum + "条");
        p3_l1.setFont(f0);
        //jsp=new JScrollPane(jtable);
        p3.add(p3_l1);
        p4 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        p4_jb1 = new JButton("详细信息");
        p4_jb1.setContentAreaFilled(false);  //只须加上此句
        p4_jb1.addActionListener(this);
        p4_jb1.setToolTipText("显示选中行的详细信息");
        p4_jb1.setFont(MyFont.f4);
        p4_jb2 = new JButton("添加");
        p4_jb2.setContentAreaFilled(false);  //只须加上此句
        java.net.URL imageUrl2=Windows1.class.getResource("/com/image/center_image/添加.png");
        p4_jb2.setIcon(new ImageIcon(imageUrl2));
        p4_jb2.setToolTipText("添加一条记录");
        p4_jb2.addActionListener(this);
        p4_jb2.setFont(MyFont.f4);
        p4_jb3 = new JButton("修改");
        p4_jb3.setContentAreaFilled(false);  //只须加上此句
        java.net.URL imageUrl3=Windows1.class.getResource("/com/image/center_image/修改.png");
        p4_jb3.setIcon(new ImageIcon(imageUrl3));
        p4_jb3.setToolTipText("修改选中的一行");
        p4_jb3.addActionListener(this);
        p4_jb3.setFont(MyFont.f4);
        p4_jb4 = new JButton("删除");
        p4_jb4.setContentAreaFilled(false);  //只须加上此句
         java.net.URL imageUrl4=Windows1.class.getResource("/com/image/center_image/delete.png");
        p4_jb4.setIcon(new ImageIcon(imageUrl4));
        p4_jb4.setToolTipText("删除选中的一行");
        p4_jb4.addActionListener(this);
        p4_jb4.setFont(MyFont.f4);
        p4.add(p4_jb1);
        p4.add(p4_jb2);
        p4.add(p4_jb3);
        p4.add(p4_jb4);
        p5.add(p3, "West");
        p5.add(p4, "East");
        //this.setBackground(Color.blue);
        this.setLayout(new BorderLayout());
        this.add(p1, "North");
        this.add(p2, "Center");
        this.add(p5, "South");
        this.setVisible(true);
        DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
        cr.setHorizontalAlignment(JLabel.CENTER);
        jtable.setDefaultRenderer(Object.class, cr);

    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO 自动生成的方法存根

        if (arg0.getSource().equals(p1_jb)) {

            if (p1_jtf.getText().trim().equals("")) {
//				String[]params={"1"};
//				String sql="select * from login where 1=?";
//				em=new EmpModel();
//				em.query(sql, params);
//				jtable.setModel(em);
                showAll();
            } else {
                String params[] = {p1_jtf.getText().trim(), p1_jtf.getText().trim()};
                String sql = "select * from lab_info where lab_id=? or address=?";
                em = new EmpModel();
                em.query(sql, params);

                jtable.setModel(em);
            }
        } else if (arg0.getSource().equals(p4_jb1)) {
            int rowNum = this.jtable.getSelectedRow();
            //System.out.println(rowNum);
            if (rowNum == -1) {
                JOptionPane.showMessageDialog(this, "请选择一行！");
            }

            String empId = (String) this.jtable.getValueAt(rowNum, 0);
            String sql = "select * from lab_info where lab_id=?";
            String[] params = {empId};
            em = new EmpModel();
            em.query(sql, params);
            jtable.setModel(em);

        } else if (arg0.getSource().equals(p4_jb2)) {
            if (true) {
                AddLabDialog A = new AddLabDialog(this, "添加", true, jtable);
                A.showAll(jtable);
            }

//			String sql="select * from login where 1=?";
//			String[]params={"1"};
//			em=new EmpModel();
//			em.query(sql, params);
//			jtable.setModel(em);
        } else if (arg0.getSource().equals(p4_jb3)) {

            int rowNum = this.jtable.getSelectedRow();
            if (rowNum == -1) {
                JOptionPane.showMessageDialog(this, "请选择一行！");
            } //			String empId=(String)this.jtable.getValueAt(rowNum, 0);
            //			String []params={empId};
            else {
                String[] params = {"1"};
                String sql = "select * from lab_info where 1=?";
                em = new EmpModel();
                em.query(sql, params);
                jtable.setModel(em);
                new UpdLabDialog(this, "修改", true, em, rowNum, jtable);
            }

//			String[]params={"1"};
//			String sql="select * from login where 1=?";
//			em=new EmpModel();
//			em.query(sql, params);
//			jtable.setModel(em);
        } else if (arg0.getSource().equals(p4_jb4)) {
            int rowNum = this.jtable.getSelectedRow();

            if (rowNum == -1) {
                JOptionPane.showMessageDialog(this, "请选择一行！");
            } else {
                int n = JOptionPane.showConfirmDialog(null, "确定要删除这条记录吗", "删除记录", JOptionPane.YES_NO_OPTION); //返回值为0或1
                if (n == 0) {
                String empId = (String) this.jtable.getValueAt(rowNum, 0);
                String sql = "delete from lab_info where lab_id=?";
                String[] params = {empId};
                JOptionPane.showMessageDialog(null, "恭喜！删除成功！");
                LabInfo.sum--;
                LabInfo.p3_l1.setText("总记录是" + LabInfo.sum + "条");
                em = new EmpModel();
                em.UpdateModel(sql, params);
                //更新sum
                sum = em.getNum("select count(*) from lab_info");

                //showAll();
                }
            }

            String sql = "select * from lab_info where 1=?";
            String[] params = {"1"};
            em = new EmpModel();
            em.query(sql, params);
            jtable.setModel(em);
        }
    }
    //显示所有记录，用于更新表单和记录数

    public void showAll() {
        String[] params = {"1"};
        String sql = "select * from lab_info where 1=?";
        em = new EmpModel();
        em.query(sql, params);
        jtable.setModel(em);
    }

}
