package com.view;

import com.model.EmpModel;
import com.mytools.MyFont;
import com.mytools.*;
import static com.mytools.MyFont.f0;
import static com.mytools.MyFont.f1;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.*;
import java.io.*;
import javax.swing.plaf.FontUIResource;

public class Windows1 extends JFrame implements ActionListener, MouseListener,Runnable,MouseMotionListener {
    //定义需要的组件

    Image titleIcon, timeBg, p1_bg, p3Icon, chart;
    ImagePanel p1_bgImage, jp3Image, ct;
    BackgroundMenuBar jmb;
    JSplitPane jsp;
    JMenu jm1, jm2, jm3, jm4, jm5, jm6;
    JMenuItem jmi1, jmi2, jmi3, jmi4, jmi5, jmi6, jmi7, jmi8, jmi9, jmi10, jmi11, jmi12;
    ImageIcon jmi1_icon1, jmi2_icon2, jmi3_icon3, jmi4_icon4, jmi5_icon5, jmi6_icon6, jmi7_icon7, jmi8_icon8, jmi9_icon9, jmi10_icon10, jmi11_icon11, jmi12_icon12;
    JToolBar jtb;
    JButton jb1, jb2, jb3, jb4, jb5, jb6, jb7, jb8, jb9, jb10;
    JPanel jp1, jp2, jp3, jp4, jp5;
    JLabel showTime;//显示时间
    JLabel p2_jl1, p2_jl2,jl1,jl2,username;
    JLabel p1_jl1, p1_jl2, p1_jl3, p1_jl4, p1_jl5, p1_jl6, p1_jl7, p1_jl8;
    CardLayout myCard, myCard1;
    Timer t;//可定时触发Action事件
    Vector<String> ss=null;
    EmpInfo ei=null;
    int bright=0;
    showTimeTable st=null;
    JTable showTable=null;
    SearchTimeTable st1=null;
    public static void main(String[] args) {
               //管理员测试登录
        Vector<String> s=new Vector<String>();
        s.add("1");
        s.add("gm");
        s.add("gm");
        Windows1 w1 = new Windows1(s);
                 
    }
    //菜单

    public void initMenu() {
        //一级菜单
java.net.URL imageUrl1=Windows1.class.getResource("/com/image/toolBar_image/form1.png");
java.net.URL imageUrl2=Windows1.class.getResource("/com/image/toolBar_image/profile1.png");
java.net.URL imageUrl3=Windows1.class.getResource("/com/image/toolBar_image/settings1.png");
java.net.URL imageUrl4=Windows1.class.getResource("/com/image/toolBar_image/same1.png");
java.net.URL imageUrl5=Windows1.class.getResource("/com/image/toolBar_image/exit1.png");
        jmi1_icon1 = new ImageIcon(imageUrl1);
        jmi2_icon2 = new ImageIcon(imageUrl2);
        jmi3_icon3 = new ImageIcon(imageUrl3);
        jmi4_icon4 = new ImageIcon(imageUrl4);
        jmi5_icon5 = new ImageIcon(imageUrl5);
        jm1 = new JMenu("系统管理");
        jm1.setFont(MyFont.f3);
        //创建其二级菜单
//				jmi1=new JMenuItem("切换用户",jmi1_icon1);
//				jmi1.setFont(MyFont.f2);
        jmi2 = new JMenuItem("个人管理", jmi2_icon2);
        jmi2.addActionListener(this);
        jmi2.setActionCommand("me");
        jmi2.setFont(MyFont.f2);
        jmi3 = new JMenuItem("系统设置", jmi3_icon3);
        jmi3.addActionListener(this);
        jmi3.setActionCommand("tools");
        jmi3.setFont(MyFont.f2);
//				jmi4=new JMenuItem("NULL",jmi4_icon4);
//				jmi4.setFont(MyFont.f2);
        jmi5 = new JMenuItem("安全退出", jmi5_icon5);
        jmi5.addActionListener(this);
        jmi5.setActionCommand("Exit");
        jmi5.setFont(MyFont.f2);

        //jm1.add(jmi1);
        jm1.add(jmi2);
        jm1.add(jmi3);
        //jm1.add(jmi4);
        jm1.add(jmi5);
java.net.URL imageUrl6=Windows1.class.getResource("/com/image/toolBar_image/group1.png");
        jmi6_icon6 = new ImageIcon(imageUrl6);

        jm2 = new JMenu("人事记录");
        jm2.setFont(MyFont.f3);
        jmi6 = new JMenuItem("人事管理", jmi6_icon6);
        jmi6.setFont(MyFont.f2);
        jmi6.addActionListener(this);
        jmi6.setActionCommand("Emp");
        jm2.add(jmi6);
   java.net.URL imageUrl7=Windows1.class.getResource("/com/image/toolBar_image/sort1.png");     
        jmi7_icon7 = new ImageIcon(imageUrl7);
        jm3 = new JMenu("录入信息");
        jm3.setFont(MyFont.f3);
        jmi7 = new JMenuItem("录入实验室", jmi7_icon7);
        jmi7.setFont(MyFont.f2);
        jmi7.addActionListener(this);
        jmi7.setActionCommand("Addlab");
        jm3.add(jmi7);
        jmi8 = new JMenuItem("录入申请", jmi1_icon1);
        jmi8.setFont(MyFont.f2);
        jmi8.addActionListener(this);
        jmi8.setActionCommand("Addapp");
        jm3.add(jmi8);
  java.net.URL imageUrl8=Windows1.class.getResource("/com/image/toolBar_image/punch1.png");  
        jmi8_icon8 = new ImageIcon(imageUrl8);
        jm4 = new JMenu("课表服务");
        jm4.setFont(MyFont.f3);
        jmi9 = new JMenuItem("课表生成", jmi8_icon8);
        jmi9.setFont(MyFont.f2);
        jm4.add(jmi9);
        jmi9.addActionListener(this);
        jmi9.setActionCommand("create");
//        jmi9.addActionListener( e-> {
//            new Thread(() -> {
//                try {
//                   // Thread.sleep(3000);
//                   SwingUtilities.invokeLater(() -> new NQueens());
//                   //Thread.sleep(3000);
//                    SwingUtilities.invokeLater(() -> jl2.setText("课表生成成功，快去查看课表吧！"));
//                  // SwingUtilities.invokeLater(() -> jl2.setText("1.检查数据合法性..."));
//                   //Thread.sleep(3000);//模仿检测数据合法性
//                   //SwingUtilities.invokeLater(()-> jl2.setText("2.正在导入数据..."));
//                    //Thread.sleep(4000);//模仿导入数据
//                   //SwingUtilities.invokeLater(() -> jl2.setText("3.导入成功!"));
//                } catch (Exception e1) {
//                    e1.printStackTrace();
//                }
//            }).start();
//        });
   
        jmi10 = new JMenuItem("课表查询", jmi4_icon4);
        jmi10.setFont(MyFont.f2);
        jm4.add(jmi10);
        jmi10.addActionListener(this);
        jmi10.setActionCommand("timetable");
          java.net.URL imageUrl10=Windows1.class.getResource("/com/image/toolBar_image/info1.png");  
            //java.net.URL imageUrl11=Windows1.class.getResource("/com/image/toolBar_image/jb10.jpg");  
              java.net.URL imageUrl12=Windows1.class.getResource("/com/image/toolBar_image/emoji1.png");  
        jmi10_icon10 = new ImageIcon(imageUrl10);
        //jmi11_icon11 = new ImageIcon(imageUrl11);
        jmi12_icon12 = new ImageIcon(imageUrl12);
        jm6 = new JMenu("帮助");
        jm6.setFont(MyFont.f3);
        jmi11 = new JMenuItem("文本帮助", jmi10_icon10);
        jmi11.setFont(MyFont.f2);
        jmi12 = new JMenuItem("关于我们", jmi12_icon12);
        jmi12.setFont(MyFont.f2);
        jmi11.setFont(MyFont.f2);
        jm6.add(jmi11);
        jm6.add(jmi12);
        jmi11.addActionListener(this);
        jmi11.setActionCommand("info");
        jmi12.addActionListener(this);
        jmi12.setActionCommand("About");
        jmb = new BackgroundMenuBar();
        jmb.add(jm1);
        jmb.add(jm2);
        jmb.add(jm3);
        jmb.add(jm4);
        jmb.add(jm6);
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setJMenuBar(jmb);
    }
    //工具栏1111
//	public void initToolBar()
//	{
//		jtb=new JToolBar();
//		jtb.setFloatable(false);
//		jb1=new JButton(new ImageIcon("image/jm1_icon1.jpg"));
//		jb2=new JButton(new ImageIcon("image/jm1_icon2.jpg"));
//		jb3=new JButton(new ImageIcon("image/jm1_icon3.jpg"));
//		jb4=new JButton(new ImageIcon("image/jm1_icon4.jpg"));
//		jb5=new JButton(new ImageIcon("image/toolBar_image/jb5.jpg"));
//		jb6=new JButton(new ImageIcon("image/toolBar_image/jb6.jpg"));
//		jb7=new JButton(new ImageIcon("image/toolBar_image/jb7.jpg"));
//		jb8=new JButton(new ImageIcon("image/toolBar_image/jb8.jpg"));
//		jb9=new JButton(new ImageIcon("image/toolBar_image/jb9.jpg"));
//		jb10=new JButton(new ImageIcon("image/toolBar_image/jb10.jpg"));
//		jtb.add(jb1);
//		jtb.add(jb2);
//		jtb.add(jb3);
//		jtb.add(jb4);
//		jtb.add(jb5);
//		jtb.add(jb6);
//		jtb.add(jb7);
//		jtb.add(jb8);
//		jtb.add(jb9);
//		jtb.add(jb10);
//	
//	}

    public void initCenter(Vector<String> s) {
        //存放头像和昵称
        JPanel jp_user=new JPanel();
        //jp1
        jp1 = new JPanel(new BorderLayout());
        try {
            java.net.URL imageUrl13=Windows1.class.getResource("/com/image/center_image/jp1_bg.jpg");
            p1_bg = ImageIO.read(imageUrl13);
        } catch (IOException e1) {
            // TODO 自动生成的 catch 块
            e1.printStackTrace();
        }
        Cursor myCursor = new Cursor(HAND_CURSOR);
        p1_bgImage = new ImagePanel(p1_bg);
        p1_bgImage.setLayout(new GridLayout(8, 1));
        if(s.get(2).equals("gm")){
            java.net.URL imageUrl14=Windows1.class.getResource("/com/image/center_image/gm.png");
        p1_jl1 = new JLabel(new ImageIcon(imageUrl14));
        }else if(s.get(2).equals("student")){
             java.net.URL imageUrl15=Windows1.class.getResource("/com/image/center_image/student.png");
        p1_jl1 = new JLabel(new ImageIcon(imageUrl15));
        }else if(s.get(2).equals("teacher")){
             java.net.URL imageUrl16=Windows1.class.getResource("/com/image/center_image/teacher.png");
        p1_jl1 = new JLabel(new ImageIcon(imageUrl16));
        }   
        jp_user.setLayout(null);
        jp_user.setOpaque(false); 
        jp_user.add(p1_jl1);
        p1_jl1.setBounds(10,20,64,64);
        username = new JLabel(s.get(1)+"");
        username.setFont(MyFont.f3);
        username.setForeground(Color.lightGray);
        username.setBounds(80,40,64,20);
        jp_user.add(username);
        
        p1_bgImage.add(jp_user);
        
         java.net.URL imageUrl17=Windows1.class.getResource("/com/image/center_image/group.png");
        p1_jl2 = new JLabel("人 事 管 理", new ImageIcon(imageUrl17), 0);
        p1_jl2.setFont(MyFont.f3);
        p1_jl2.setForeground(Color.lightGray);
        //让label 初始不可用
        p1_jl2.setCursor(myCursor);
        //p1_jl2.setEnabled(false);
        p1_jl2.addMouseListener(this);
        p1_bgImage.add(p1_jl2);
//		p1_jl3=new JLabel("登 陆 管 理",new ImageIcon("image/center_image/label_3.jpg"),0);
//		p1_jl3.setFont(MyFont.f4);
//		p1_jl3.setCursor(myCursor);
//		p1_jl3.setEnabled(false);
//		p1_jl3.addMouseListener(this);
//		p1_bgImage.add(p1_jl3);
java.net.URL imageUrl18=Windows1.class.getResource("/com/image/center_image/sort.png");
        p1_jl4 = new JLabel("录入 实验室", new ImageIcon(imageUrl18), 0);
        p1_jl4.setFont(MyFont.f3);
        p1_jl4.setForeground(Color.lightGray);
        p1_jl4.setCursor(myCursor);
        //p1_jl4.setEnabled(false);
        p1_jl4.addMouseListener(this);
        p1_bgImage.add(p1_jl4);
        java.net.URL imageUrl19=Windows1.class.getResource("/com/image/center_image/form.png");
        p1_jl5 = new JLabel("录 入 申 请", new ImageIcon(imageUrl19), 0);
        p1_jl5.setFont(MyFont.f3);
        p1_jl5.setForeground(Color.lightGray);
        p1_jl5.setCursor(myCursor);
        //p1_jl5.setEnabled(false);
        p1_jl5.addMouseListener(this);
        p1_bgImage.add(p1_jl5);
        java.net.URL imageUrl20=Windows1.class.getResource("/com/image/center_image/punch.png");
        p1_jl6 = new JLabel("课 表 生 成", new ImageIcon(imageUrl20), 0);
        p1_jl6.setFont(MyFont.f3);
        p1_jl6.setForeground(Color.lightGray);
        p1_jl6.setCursor(myCursor);
        //p1_jl6.setEnabled(false);
        p1_jl6.addMouseListener(this);
        p1_bgImage.add(p1_jl6);
        java.net.URL imageUrl21=Windows1.class.getResource("/com/image/center_image/same.png");
        p1_jl7 = new JLabel("课 表 查 询", new ImageIcon(imageUrl21), 0);
        p1_jl7.setFont(MyFont.f3);
        p1_jl7.setForeground(Color.lightGray);
        p1_jl7.setCursor(myCursor);
       // p1_jl7.setEnabled(false);
        p1_jl7.addMouseListener(this);
        p1_bgImage.add(p1_jl7);
         java.net.URL imageUrl22=Windows1.class.getResource("/com/image/center_image/settings.png");
        p1_jl8 = new JLabel("系 统 设 置", new ImageIcon(imageUrl22), 0);
        p1_jl8.setFont(MyFont.f3);
        p1_jl8.setForeground(Color.lightGray);
        p1_jl8.setCursor(myCursor);
        //p1_jl8.setEnabled(false);
        p1_jl8.addMouseListener(this);
        p1_bgImage.add(p1_jl8);
        jp1.add(p1_bgImage);

        //jp4,jp2,jp3
        myCard = new CardLayout();
        myCard1 = new CardLayout();
        jp4 = new JPanel(new BorderLayout());
        jp2 = new JPanel(myCard1);
         java.net.URL imageUrl23=Windows1.class.getResource("/com/image/center_image/jp2_left.jpg");
        p2_jl1 = new JLabel(new ImageIcon(imageUrl23));
        p2_jl1.setToolTipText("折叠");
        //图标切换
        p2_jl1.addMouseListener(this);
        java.net.URL imageUrl24=Windows1.class.getResource("/com/image/center_image/jp2_right.jpg");
        p2_jl2 = new JLabel(new ImageIcon(imageUrl24));
        p2_jl2.setToolTipText("展开");
        p2_jl2.addMouseListener(this);
        jp2.add(p2_jl1, "0");
        jp2.add(p2_jl2, "1");

        jp3 = new JPanel(myCard);
        //先给jp3加入主界面卡片
        try {
             java.net.URL imageUrl25=Windows1.class.getResource("/com/image/center_image/jp3_bg.jpg");
            p3Icon = ImageIO.read(imageUrl25);
        } catch (IOException e1) {
            // TODO 自动生成的 catch 块
            e1.printStackTrace();
        }
        jp3Image = new ImagePanel(p3Icon);

        jp3.add(jp3Image, "0");
        //人事管理
        ei = new EmpInfo(s);
        jp3.add(ei, "1");

        //实验室界面
        LabInfo li = new LabInfo();
        jp3.add(li, "3");

        //申请界面
        AppInfo ai = new AppInfo();
        jp3.add(ai, "4");
        //线程查询太耗费资源了
//        Thread t=new Thread(ai);
//        t.start(); 
        //生成表界面
        //ImageIcon p3Icon1 =new ImageIcon("image/center_image/loading.gif");
        java.net.URL imageUrl26=Windows1.class.getResource("/com/image/center_image/loading.gif");
        jl1=new JLabel(new ImageIcon(imageUrl26));
        jl2=new JLabel("哈哈哈");
        jl2.setFont(f1);
        JPanel jp=new JPanel();
        jp.setLayout(null);
        jl1.setBounds(430,100, 256,256);
        jl2.setBounds(430,250, 256,256);
        jp.add(jl1);
        jp.add(jl2);
        jp3.add(jp, "5");
        //课表查询界面
        st=new showTimeTable();
//         String tname=null;
//         try{
//        st1=new SearchTimeTable(tname);
//         }catch(Exception e){
//             e.printStackTrace();
//         }
        jp3.add(st, "6");
        
        //系统设置
        Image p3Icon1=null;
        try {
            java.net.URL imageUrl27=Windows1.class.getResource("/com/image/center_image/jp3_bg.jpg");
            p3Icon1 = ImageIO.read(imageUrl27);
        } catch (IOException e1) {
            // TODO 自动生成的 catch 块
            e1.printStackTrace();
        }
        ImagePanel jp3Image1 = new ImagePanel(p3Icon1);
        jp3.add(jp3Image1, "7");


//		 //报表统计
//		 try {
//			 chart=ImageIO.read(new File("image/chart.jpg"));
//			} catch (IOException e) {
//				// TODO 自动生成的 catch 块
//				e.printStackTrace();
//			}
//			ct=new ImagePanel(chart);
//			//ct.setBounds(0, 0, this.getWidth()-50, this.getHeight()-10);
//			jp3.add(ct,"4");
        //系统设置
//		 OperatChoose oc=new OperatChoose();
//		 jp3.add(oc,"6");
        jp4.add(jp2, "West");
        jp4.add(jp3, "Center");
        jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, jp1, jp4);
        jsp.setDividerLocation(150);
        jsp.setDividerSize(0);
    }
    //状态栏
    public void initEnd() {
        jp5 = new JPanel(new BorderLayout());
        //t = new Timer(1000, this);//每隔一秒触发ActonEvent
        Thread t = new Thread(this);
        showTime = new JLabel(Calendar.getInstance().getTime().toLocaleString() + "   ");
         java.net.URL imageUrl28=Windows1.class.getResource("/com/image/时间.png");
        showTime.setIcon(new ImageIcon(imageUrl28));
        showTime.setFont(MyFont.f1);
        showTime.setForeground(Color.PINK);
        t.start();
        try {
            java.net.URL imageUrl29=Windows1.class.getResource("/com/image/time_bg.jpg");
            timeBg = ImageIO.read(imageUrl29);
        } catch (IOException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        ImagePanel ip1 = new ImagePanel(timeBg);
        ip1.setLayout(new BorderLayout());
        ip1.add(showTime, "East");
        jp5.add(ip1);

    }

    public Windows1(Vector<String> s) {    
      
// 设置文本显示效果
UIManager.put("OptionPane.messageFont", new FontUIResource(f0));

        try {
             java.net.URL imageUrl30=Windows1.class.getResource("/com/image/center_image/"+s.get(2)+".png");
            titleIcon = ImageIO.read(imageUrl30);
        } catch (IOException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
       this.ss=s;
        //菜单
        this.initMenu();
        //工具栏
        //this.initToolBar();

        //中间
        this.initCenter(s);

        //状态栏
        this.initEnd();

        Container ct = this.getContentPane();
//		ct.add(jtb,"North");
        ct.add(jp5, "South");
        ct.add(jsp, "Center");

        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width - 600, height - 300);
        this.setLocation(400, 200);
        this.setIconImage(titleIcon);
        this.setTitle("实验室安排系统");
        
        //this.getContentPane().add(new GetFilePathSingle().getPanel());
        //测试读取
        //List<GetApp> list = new ArrayList<GetApp>();
        //list=new ExcelOperationUtil().readExcelData();
        //去掉上边框
//        this.addMouseListener(this);
//        this.addMouseMotionListener(this);
//        this.setUndecorated(true);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO 自动生成的方法存根
        if (e.getActionCommand().equals("Emp")) {
            this.myCard.show(jp3, "1");
            this.setTitle("人事管理-实验室安排系统");
            //颜色控制
            this.p1_jl2.setForeground(Color.white);
            this.p1_jl4.setForeground(Color.lightGray);
            this.p1_jl5.setForeground(Color.lightGray);
            this.p1_jl6.setForeground(Color.lightGray);
            this.p1_jl7.setForeground(Color.lightGray);
            this.p1_jl8.setForeground(Color.lightGray);
        }else if (e.getActionCommand().equals("Addlab")) {
            this.myCard.show(jp3, "3");
            this.setTitle("录入实验室-实验室安排系统");
            bright=2;
            this.p1_jl2.setForeground(Color.lightGray);
            this.p1_jl4.setForeground(Color.white);
            this.p1_jl5.setForeground(Color.lightGray);
            this.p1_jl6.setForeground(Color.lightGray);
            this.p1_jl7.setForeground(Color.lightGray);
            this.p1_jl8.setForeground(Color.lightGray);
        }else if (e.getActionCommand().equals("Addapp")) {
            this.myCard.show(jp3, "4");
            this.setTitle("录入申请-实验室安排系统");
            bright=3;
            this.p1_jl2.setForeground(Color.lightGray);
            this.p1_jl4.setForeground(Color.lightGray);
            this.p1_jl5.setForeground(Color.white);
            this.p1_jl6.setForeground(Color.lightGray);
            this.p1_jl7.setForeground(Color.lightGray);
            this.p1_jl8.setForeground(Color.lightGray);
        }else if (e.getActionCommand().equals("Exit")) {
            System.exit(0);
        }else if (e.getActionCommand().equals("create")) {
            this.myCard.show(jp3, "5");
            this.setTitle("课表生成-实验室安排系统");
            jl2.setText("  正在为您生成课表，请稍后。。。");

            bright=4;
            this.p1_jl2.setForeground(Color.lightGray);
            this.p1_jl4.setForeground(Color.lightGray);
            this.p1_jl5.setForeground(Color.lightGray);
            this.p1_jl6.setForeground(Color.white);
            this.p1_jl7.setForeground(Color.lightGray);
            this.p1_jl8.setForeground(Color.lightGray);
            //运行排课程序
             NQueens nQueens=new NQueens(jl2);
             Thread t=new Thread(nQueens);
             t.start();
             showTable=st.returnjJTable();
            //全部删除以前的表
            String sql = "delete from general_table";
            EmpModel em = new EmpModel();
            em.deleteModel(sql);
                
        }else if (e.getActionCommand().equals("me")) {
            //更改个人信息
                String empId =ss.get(0);
                String sql = "select * from login where id=?";
                String[] params = {empId};
                EmpModel emme = new EmpModel();
                emme.query(sql, params);
                new UpdEmpDialog(ei, "修改个人信息", true, emme, 0, ei.jtable,username);
                //下面两句执行的时间过早
//                System.out.println(emme.getValueAt(0, 1)+"");
//                username.setText(emme.getValueAt(0, 1)+"");
        }else if (e.getActionCommand().equals("timetable")) {
            this.myCard.show(jp3, "6");
            this.setTitle("课表查询-实验室安排系统");
            
            bright=5;
            this.p1_jl2.setForeground(Color.lightGray);
            this.p1_jl4.setForeground(Color.lightGray);
            this.p1_jl5.setForeground(Color.lightGray);
            this.p1_jl6.setForeground(Color.lightGray);
            this.p1_jl7.setForeground(Color.white);
            this.p1_jl8.setForeground(Color.lightGray);
            //刷新课表
            st.flashTable();
            
        }else if (e.getActionCommand().equals("About")) {
            About about = new About();
            
        }else if (e.getActionCommand().equals("info")) {
            final JDialog dialog = new JDialog(this, "提示", true);
            Font f1 = new Font("宋体", Font.BOLD, 18);
            JPanel jp = new JPanel();
            jp.setLayout(new GridLayout(1, 1));
            JTextArea jta = new JTextArea("*人事管理可以增加人员，只有管理员可以删改\r\n*修改个人信息请使用左上角个人管理\r\n*可以录入实验室地址和座位数\r\n*录入申请支持增删改查\r\n*录入申请支持批量导入和拖拽导入\r\n*录入申请支持一键清空\r\n*课表生成尽量满足老师的上课选择期望\r\n*课表生成按照先申请优先满足的原则\r\n*课表查询支持按老师查询和按班级查询\r\n*此软件纯属娱乐，版权问题概不负责", 20, 10);
            jta.setFont(f1);
            jp.add(jta);
            dialog.add(jp);
            dialog.setSize(410, 240);
            dialog.setResizable(false);
            dialog.setLocation(800, 430);
            dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
            
        }else if (e.getActionCommand().equals("tools")) {
            this.myCard.show(jp3, "7");
            this.setTitle("系统设置-实验室安排系统");
            
            bright=6;
            this.p1_jl2.setForeground(Color.lightGray);
            this.p1_jl4.setForeground(Color.lightGray);
            this.p1_jl5.setForeground(Color.lightGray);
            this.p1_jl6.setForeground(Color.lightGray);
            this.p1_jl7.setForeground(Color.lightGray);
            this.p1_jl8.setForeground(Color.white);
        }

    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
        // TODO 自动生成的方法存根
        if (arg0.getSource() == p1_jl2) {
            this.myCard.show(jp3, "1");
            this.setTitle("人事管理-实验室安排系统");
            //颜色控制
            bright=1;
            this.p1_jl4.setForeground(Color.lightGray);
            this.p1_jl5.setForeground(Color.lightGray);
            this.p1_jl6.setForeground(Color.lightGray);
            this.p1_jl7.setForeground(Color.lightGray);
            this.p1_jl8.setForeground(Color.lightGray);
        } else if (arg0.getSource() == p1_jl4) {
            this.myCard.show(jp3, "3");
            this.setTitle("录入实验室-实验室安排系统");
            bright=2;
            this.p1_jl2.setForeground(Color.lightGray);
            this.p1_jl5.setForeground(Color.lightGray);
            this.p1_jl6.setForeground(Color.lightGray);
            this.p1_jl7.setForeground(Color.lightGray);
            this.p1_jl8.setForeground(Color.lightGray);
        } else if (arg0.getSource() == p1_jl5) {
            this.myCard.show(jp3, "4");
            this.setTitle("录入申请-实验室安排系统");
            bright=3;
            this.p1_jl2.setForeground(Color.lightGray);
            this.p1_jl4.setForeground(Color.lightGray);
            this.p1_jl6.setForeground(Color.lightGray);
            this.p1_jl7.setForeground(Color.lightGray);
            this.p1_jl8.setForeground(Color.lightGray);
        } else if (arg0.getSource() == p1_jl6) {
            this.myCard.show(jp3, "5");
            this.setTitle("课表生成-实验室安排系统");
            jl2.setText("  正在为您生成课表，请稍后。。。");
            bright=4;
            this.p1_jl2.setForeground(Color.lightGray);
            this.p1_jl4.setForeground(Color.lightGray);
            this.p1_jl5.setForeground(Color.lightGray);
            this.p1_jl7.setForeground(Color.lightGray);
            this.p1_jl8.setForeground(Color.lightGray);
            
            //全部删除以前的表
            String sql = "delete from general_table";
            EmpModel em = new EmpModel();
            em.deleteModel(sql);
            //不能在这里运行排课程序，要调用线程
             NQueens nQueens=new NQueens(jl2);
             Thread t=new Thread(nQueens);
             t.start();
        } else if (arg0.getSource() == p1_jl7) {
            this.myCard.show(jp3, "6");
            this.setTitle("课表查询-实验室安排系统");
            bright=5;
            this.p1_jl2.setForeground(Color.lightGray);
            this.p1_jl4.setForeground(Color.lightGray);
            this.p1_jl5.setForeground(Color.lightGray);
            this.p1_jl6.setForeground(Color.lightGray);
            this.p1_jl8.setForeground(Color.lightGray);
            st.flashTable();
        } else if (arg0.getSource() == p1_jl8) {
            //MediaHelp mh=new MediaHelp();
            this.myCard.show(jp3, "7");
            this.setTitle("系统设置-实验室安排系统");
            bright=6;
            this.p1_jl2.setForeground(Color.lightGray);
            this.p1_jl4.setForeground(Color.lightGray);
            this.p1_jl5.setForeground(Color.lightGray);
            this.p1_jl6.setForeground(Color.lightGray);
            this.p1_jl7.setForeground(Color.lightGray);
        } else if (arg0.getSource() == p2_jl1) {
            //把显示各种操作的界面隐藏起来（jp1)，同时显示jp2卡片布局中的jp2_lab2(向右的箭头)
            //把拆分面板的左边隐藏起来，即隐藏jp1
            this.jsp.setDividerLocation(0);//设置拆分面板的左边面板的大小为0像素，即不可见
            //同时显示向右的箭头
            this.myCard1.show(jp2, "1");
        } else if (arg0.getSource() == p2_jl2) {
            //System.out.println("111111111");
            //把隐藏的jp1面板展开，即设置左边面板拆分大小
            this.jsp.setDividerLocation(150);//由于前面定义拆分面板时，左边占150像素，此时应显示150像素
            //同时显示向左的箭头
            this.myCard1.show(jp2, "0");
//this.jsp.setDividerLocation(Toolkit.getDefaultToolkit().getScreenSize().width);
        }else  if (arg0.getSource() == jm2) {
            this.myCard.show(jp3, "1");
        } else  if (arg0.getSource() == jmi7) {
            this.myCard.show(jp3, "2");
        } else  if (arg0.getSource() == jmi8) {
            this.myCard.show(jp3, "3");
        } else  if (arg0.getSource() == jmi5) {
           System.exit(0);
        } 
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
        // TODO 自动生成的方法存根
        if (arg0.getSource() == p1_jl2) {
            this.p1_jl2.setForeground(Color.white);
        } else if (arg0.getSource() == p1_jl4) {
            this.p1_jl4.setForeground(Color.white);
        } else if (arg0.getSource() == p1_jl5) {
            this.p1_jl5.setForeground(Color.white);
        } else if (arg0.getSource() == p1_jl6) {
            this.p1_jl6.setForeground(Color.white);
        } else if (arg0.getSource() == p1_jl7) {
            this.p1_jl7.setForeground(Color.white);
        } else if (arg0.getSource() == p1_jl8) {
            this.p1_jl8.setForeground(Color.white);
        }
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        // TODO 自动生成的方法存根
        if (arg0.getSource() == p1_jl2) {
            if(bright!=1)
            this.p1_jl2.setForeground(Color.lightGray);
        }  else if (arg0.getSource() == p1_jl4) {
            if(bright!=2)
            this.p1_jl4.setForeground(Color.lightGray);
        } else if (arg0.getSource() == p1_jl5) {
            if(bright!=3)
            this.p1_jl5.setForeground(Color.lightGray);
        } else if (arg0.getSource() == p1_jl6) {
            if(bright!=4)
            this.p1_jl6.setForeground(Color.lightGray);
        } else if (arg0.getSource() == p1_jl7) {
            if(bright!=5)
            this.p1_jl7.setForeground(Color.lightGray);
        } else if (arg0.getSource() == p1_jl8) {
            if(bright!=6)
            this.p1_jl8.setForeground(Color.lightGray);
        }
    }
// int xOld = 0;
// int yOld = 0;
    @Override
    public void mousePressed(MouseEvent arg0) {
        // TODO 自动生成的方法存根
//        xOld = arg0.getX();//记录鼠标按下时的坐标
//        yOld = arg0.getY();

    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        // TODO 自动生成的方法存根

    }

     @Override
    public void run() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        while (true) {
            //休眠
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //重画时间
        this.showTime.setText(Calendar.getInstance().getTime().toLocaleString()+"   ");

        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        int xOnScreen = e.getXOnScreen();
//    int yOnScreen = e.getYOnScreen();
//    int xx = xOnScreen - xOld;
//    int yy = yOnScreen - yOld;
//    Windows1.this.setLocation(xx, yy);//设置拖拽后，窗口的位置
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
   
 

}


//创建JMenuBar对象修改内容
class BackgroundMenuBar extends JMenuBar
{
    Color bgColor=Color.lightGray;
    public void setColor(Color color)
    {
        bgColor=color;
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(bgColor);
        g2d.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
        //Image img = new Image("image/toolBar_image/label_7.jpg");
//        BufferedImage image = new BufferedImage(150, 150,BufferedImage.TYPE_3BYTE_BGR);
//         g2d.drawImage(img, 20, 0, 20, 20, this);
    }
}
