package com.view;
import java.awt.*;
import javax.swing.*;
import javax.swing.JDialog;
import com.model.*;
import com.mytools.*;
import java.awt.event.*;
//修改界面

public class UpdEmpDialog extends JDialog implements ActionListener{

	JPanel p1,p2,p3;
	JLabel jl1,jl2,jl3,jl4,usernameJLabel;
	JTextField jtf1,jtf2,jtf3,jtf4;
	JButton jb1,jb2;
        JTable jtable1;
        //EmpInfo empInfo1=null;//有一个bug，管理员在jtalbe修改自己数据后没有同步到JLabel username
	public UpdEmpDialog(EmpInfo empInfo,String title,boolean model,EmpModel em,int rowNum,JTable jtable,JLabel username)
	{
        
		super();
                this.jtable1=jtable;
                if(username!=null){
                this.usernameJLabel=username;
                }
		p1=new JPanel(new GridLayout(4,1));
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
		jtf1.setText(em.getValueAt(rowNum, 0)+"");
		jtf1.setEditable(false);
		//System.out.println(em.getValueAt(rowNum, 0)+"");
		jtf2=new JTextField();
		jtf2.setText( em.getValueAt(rowNum, 1)+"");
		jtf3=new JTextField();
		jtf3.setText( em.getValueAt(rowNum, 2)+"");
		jtf4=new JTextField();
		jtf4.setText( em.getValueAt(rowNum, 3)+"");
		p2.add(jtf1);
		p2.add(jtf2);
		p2.add(jtf3);
		p2.add(jtf4);
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
                this.setLocation(800, 400);
		this.setSize(400,250);

		this.setTitle(title);
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO 自动生成的方法存根
		if(arg0.getSource()==jb1&&!jtf4.getText().equals("gm"))
		{
			String sql="update login set username=? ,password=? , job=? where id=?";
			String []params={jtf2.getText(),jtf3.getText(),jtf4.getText(),jtf1.getText()};
			EmpModel em=new EmpModel();
			if(!em.UpdateModel(sql, params))
			{
				JOptionPane.showMessageDialog(null, "修改失败，请输入正确数据类型!");
			}else{
			JOptionPane.showMessageDialog(null, "恭喜！修改成功！");
                        if(usernameJLabel!=null){
                        usernameJLabel.setText(jtf2.getText());
                        }
                        this.showAll(jtable1);
			this.dispose();
                        }
		}
		else if(arg0.getSource()==jb2)
		{
			this.dispose();
		}else{
                    JOptionPane.showMessageDialog(null, "不可以修改管理员用户!");
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
