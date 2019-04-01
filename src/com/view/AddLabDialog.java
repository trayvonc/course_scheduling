package com.view;
import java.awt.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.JDialog;

import com.model.*;
import com.mytools.*;

import java.awt.event.*;
import java.util.Locale;
//添加界面
public class AddLabDialog extends JDialog implements ActionListener{

	JPanel p1,p2,p3,p4;
	JLabel jl1,jl2,jl3,jl4,jl5,jl6;
	JTextField jtf1,jtf2,jtf3,jtf4,jtf5,jtf6;
	JButton jb1,jb2;
	JTable jtable;
	public AddLabDialog(LabInfo labInfo,String title,boolean model,JTable jtable)
	{
                this.jtable=jtable;
		//super(empInfo,title,model);
		p1=new JPanel(new GridLayout(6,1));
		jl1=new JLabel("id");
		jl2=new JLabel("address");
		jl3=new JLabel("desk_num");
		//jl4=new JLabel("job");
		p1.add(jl1);
		p1.add(jl2);
		p1.add(jl3);
		//p1.add(jl4);
		p2=new JPanel(new GridLayout(3,1));
		jtf1=new JTextField();
		jtf2=new JTextField();
		jtf3=new JTextField();
		//jtf4=new JTextField();
		p2.add(jtf1);
		p2.add(jtf2);
		p2.add(jtf3);
		//p2.add(jtf4);
		p3=new JPanel(new FlowLayout(FlowLayout.CENTER));
                p4=new JPanel(new FlowLayout(FlowLayout.CENTER));
		jb1=new JButton("添加");
		jb1.setFont(MyFont.f4);
		jb1.addActionListener(this);
		jb2=new JButton("取消");
		jb2.setFont(MyFont.f4);
		jb2.addActionListener(this);
		p3.add(jb1);
		p4.add(jb2);
		//this.setLayout(new BorderLayout());
                this.setLayout(null);
//		this.add(p1,"West");
//		this.add(p2,"Center");
//		this.add(p3,"South");
		this.add(p1);
		this.add(p2);
		this.add(p3);
                this.add(p4);
                p1.setBounds(20, 20, 100, 200);
                p2.setBounds(200, 25, 200, 90);
                p3.setBounds(200, 118, 100, 200);
                p4.setBounds(300, 118, 100, 200);
		this.setSize(450,200);
                this.setLocation(800,600);
		this.setTitle("添加");
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO 自动生成的方法存根
		if(arg0.getSource()==jb1)
		{
			String sql="insert into lab_info(lab_id,address,desk) values(?,?,?)";
			String []params={jtf1.getText(),jtf2.getText(),jtf3.getText()};
			EmpModel em=new EmpModel();
			if(!em.UpdateModel(sql, params))
			{
				JOptionPane.showMessageDialog(null, "添加失败，请输入正确数据类型！");
			}else{
			JOptionPane.showMessageDialog(null, "恭喜！添加成功！");
                        LabInfo.sum++;
                        LabInfo.p3_l1.setText("总记录是"+LabInfo.sum+"条");
                        //EmpInfo tmpEmpInfo=new EmpInfo();
                        this.showAll(jtable);
                        this.dispose();
                        }
			//this.dispose();
		}
		else if(arg0.getSource()==jb2)
		{
			this.dispose();
		}
	}
	public void showAll(JTable jtable){
                        String sql="select * from lab_info where 1=?";
			String[]params={"1"};
			EmpModel em=new EmpModel();
			em.query(sql, params);
			//jtable=new JTable(em);
                        jtable.setModel(em);
        }
	
	
	
}
