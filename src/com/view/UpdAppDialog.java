package com.view;
import java.awt.*;

import javax.swing.*;
import javax.swing.JDialog;

import com.model.*;
import com.mytools.*;

import java.awt.event.*;
//修改界面

public class UpdAppDialog extends JDialog implements ActionListener{

	JPanel p1,p2,p3;
	JLabel jl1,jl2,jl3,jl4,jl5,jl6,jl7,jl8,jl9,jl10,jl11,jl12;
	JTextField jtf1,jtf2,jtf3,jtf4,jtf5,jtf6,jtf7,jtf8,jtf9,jtf10,jtf11,jtf12;
	JButton jb1,jb2;
        JTable jtable;
	public UpdAppDialog(AppInfo appInfo,String title,boolean model,EmpModel em,int rowNum,JTable jtable)
	{
        
		super();
                this.jtable=jtable;
		p1=new JPanel(new GridLayout(12,1));
                jl1=new JLabel("application_id");
		jl2=new JLabel("class");
                jl3=new JLabel("stu_num");
		jl4=new JLabel("address");
		jl5=new JLabel("teacher");
                jl6=new JLabel("week");
		jl7=new JLabel("time");
		jl8=new JLabel("Monday");
		jl9=new JLabel("Tuesday");
                jl10=new JLabel("Wednesday");
		jl11=new JLabel("Thurday");
		jl12=new JLabel("Friday");
		p1.add(jl1);
		p1.add(jl2);
		p1.add(jl3);
		p1.add(jl4);
                p1.add(jl5);
		p1.add(jl6);
		p1.add(jl7);
		p1.add(jl8);
                p1.add(jl9);
		p1.add(jl10);
		p1.add(jl11);
                p1.add(jl12);
		p2=new JPanel(new GridLayout(12,1));
		jtf1=new JTextField();
		jtf1.setText(em.getValueAt(rowNum, 0)+"");
		jtf1.setEditable(false);
		//System.out.println(em.getValueAt(rowNum, 0)+"");
		jtf2=new JTextField();
		jtf2.setText( em.getValueAt(rowNum, 1)+"");
		jtf3=new JTextField();
		jtf3.setText( em.getValueAt(rowNum, 2)+"");
		jtf4=new JTextField();
		jtf4.setText( em.getValueAt(rowNum, 3)+"");
		jtf5=new JTextField();
                jtf5.setText( em.getValueAt(rowNum, 4)+"");
		jtf6=new JTextField();
                jtf6.setText( em.getValueAt(rowNum, 5)+"");
		jtf7=new JTextField();
                jtf7.setText( em.getValueAt(rowNum, 6)+"");
		jtf8=new JTextField();
                jtf8.setText( em.getValueAt(rowNum, 7)+"");
                jtf9=new JTextField();
                jtf9.setText( em.getValueAt(rowNum, 8)+"");
		jtf10=new JTextField();
                jtf10.setText( em.getValueAt(rowNum, 9)+"");
		jtf11=new JTextField();
                jtf11.setText( em.getValueAt(rowNum, 10)+"");
                jtf12=new JTextField();
                jtf12.setText( em.getValueAt(rowNum, 11)+"");
		p2.add(jtf1);
		p2.add(jtf2);
		p2.add(jtf3);
		p2.add(jtf4);
                p2.add(jtf5);
		p2.add(jtf6);
		p2.add(jtf7);
		p2.add(jtf8);
                p2.add(jtf9);
		p2.add(jtf10);
		p2.add(jtf11);
                p2.add(jtf12);
		p3=new JPanel(new FlowLayout(FlowLayout.CENTER));
		jb1=new JButton("修改");
		jb1.setFont(MyFont.f4);
		jb1.addActionListener(this);
		jb2=new JButton("取消");
		jb2.setFont(MyFont.f4);
		jb2.addActionListener(this);
		p3.add(jb1);
		p3.add(jb2);
		this.setLayout(new BorderLayout());
		this.add(p1,"West");
		this.add(p2,"Center");
		this.add(p3,"South");
                this.setLocation(800, 300);
		this.setSize(400,500);

		this.setTitle("修改信息");
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO 自动生成的方法存根
		if(arg0.getSource()==jb1)
		{
                    String []params=null;
			String sql="update application set class=?,stu_num=?,address=?,teacher=?,week=?,time=?,Monday=?,Tuesday=?,Wednesday=?,Thurday=?,Friday=? where application_id=?";
			if(jtf4.getText().equals("")||jtf4.getText().equals("null")){
                        String []tmp={jtf2.getText(),jtf3.getText(),null,jtf5.getText(),jtf6.getText(),jtf7.getText(),jtf8.getText(),jtf9.getText(),jtf10.getText(),jtf11.getText(),jtf12.getText(),jtf1.getText()};
                        params=tmp;
                        }
                        else{
                        String []tmp={jtf2.getText(),jtf3.getText(),jtf4.getText(),jtf5.getText(),jtf6.getText(),jtf7.getText(),jtf8.getText(),jtf9.getText(),jtf10.getText(),jtf11.getText(),jtf12.getText(),jtf1.getText()};
                        params=tmp;
                        }
                        EmpModel em=new EmpModel();
			if(!em.UpdateModel(sql, params))
			{
				JOptionPane.showMessageDialog(null, "修改失败，请输入正确数据类型!");
			}else{
			JOptionPane.showMessageDialog(null, "恭喜！修改成功！");
                        this.showAll(jtable);
			this.dispose();
                        }
		}
		else if(arg0.getSource()==jb2)
		{
			this.dispose();
		}
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
