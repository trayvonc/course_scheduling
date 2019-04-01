package com.view;
import java.awt.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.JDialog;

import com.model.*;
import com.mytools.*;
import static com.view.EmpInfo.sum;

import java.awt.event.*;
import java.util.Locale;
//添加界面
public class AddEmpDialog extends JDialog implements ActionListener{

	JPanel p1,p2,p3,p4;
	JLabel jl1,jl2,jl3,jl4,jl5,jl6;
	JTextField jtf1,jtf2,jtf3,jtf4,jtf5,jtf6;
	JButton jb1,jb2;
	JTable jtable;
	public AddEmpDialog(EmpInfo empInfo,String title,boolean model,JTable jtable)
	{
                this.jtable=jtable;
		//super(empInfo,title,model);
		p1=new JPanel(new GridLayout(6,1));
		jl1=new JLabel("id");
		jl2=new JLabel("username");
		jl3=new JLabel("password");
		jl4=new JLabel("job");
		p1.add(jl1);
		p1.add(jl2);
		p1.add(jl3);
		p1.add(jl4);
		p2=new JPanel(new GridLayout(4,1));
		jtf1=new JTextField();
		jtf2=new JTextField();
		jtf3=new JTextField();
		jtf4=new JTextField();
		p2.add(jtf1);
		p2.add(jtf2);
		p2.add(jtf3);
		p2.add(jtf4);
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
                p1.setBounds(20, 40, 100, 200);
                p2.setBounds(200, 45, 200, 120);
                p3.setBounds(200, 170, 100, 200);
                p4.setBounds(300, 170, 100, 200);
		this.setSize(450,250);
                this.setLocation(800,600);
		this.setTitle("添加成员");
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO 自动生成的方法存根
		if(arg0.getSource()==jb1&&!jtf4.getText().equals("gm"))
		{
			String sql="insert into login(id,username,password,job) values(?,?,?,?)";
			String []params={jtf1.getText(),jtf2.getText(),jtf3.getText(),jtf4.getText()};
			EmpModel em=new EmpModel();
			if(!em.UpdateModel(sql, params))
			{
				JOptionPane.showMessageDialog(null, "添加失败，请输入正确数据类型！");
			}else{
			JOptionPane.showMessageDialog(null, "恭喜！添加成功！");
                        EmpInfo.sum++;
                        EmpInfo.p3_l1.setText("总记录是"+EmpInfo.sum+"条");
                        //EmpInfo tmpEmpInfo=new EmpInfo();
                        this.showAll(jtable);
                        this.dispose();
                        }
			//this.dispose();
		}
		else if(arg0.getSource()==jb2)
		{
			this.dispose();
		}else{
                    JOptionPane.showMessageDialog(null, "不可以添加管理员用户!");
                }
	}
	public void showAll(JTable jtable){
                        String sql="select id,username,job from login where 1=?";
			String[]params={"1"};
			EmpModel em=new EmpModel();
			em.query(sql, params);
			//jtable=new JTable(em);
                        jtable.setModel(em);
        }
	
	
	
}
