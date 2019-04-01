package com.view;
import java.awt.*;

import javax.swing.*;
import javax.swing.JDialog;

import com.model.*;
import com.mytools.*;

import java.awt.event.*;
//修改界面

public class UpdLabDialog extends JDialog implements ActionListener{

	JPanel p1,p2,p3;
	JLabel jl1,jl2,jl3;
	JTextField jtf1,jtf2,jtf3;
	JButton jb1,jb2;
        JTable jtable;
	public UpdLabDialog(LabInfo labInfo,String title,boolean model,EmpModel em,int rowNum,JTable jtable)
	{
        
		super();
                this.jtable=jtable;
		p1=new JPanel(new GridLayout(3,1));
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
		jtf1.setText(em.getValueAt(rowNum, 0)+"");
		jtf1.setEditable(false);
		//System.out.println(em.getValueAt(rowNum, 0)+"");
		jtf2=new JTextField();
		jtf2.setText( em.getValueAt(rowNum, 1)+"");
		jtf3=new JTextField();
		jtf3.setText( em.getValueAt(rowNum, 2)+"");
		//jtf4=new JTextField();
		//jtf4.setText( em.getValueAt(rowNum, 3)+"");
		p2.add(jtf1);
		p2.add(jtf2);
		p2.add(jtf3);
		//p2.add(jtf4);
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
                this.setLocation(800, 600);
		this.setSize(400,250);

		this.setTitle("修改信息");
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO 自动生成的方法存根
		if(arg0.getSource()==jb1)
		{
			String sql="update lab_info set address=? , desk=? where lab_id=?";
			String []params={jtf2.getText(),jtf3.getText(),jtf1.getText()};
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
                        String sql="select * from lab_info where 1=?";
			String[]params={"1"};
			EmpModel em=new EmpModel();
			em.query(sql, params);
			//jtable=new JTable(em);
                        jtable.setModel(em);
        }
	
	
	
}
