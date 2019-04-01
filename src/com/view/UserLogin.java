package com.view;

import com.mytools.*;
import com.model.*;
import static com.mytools.MyFont.f0;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.plaf.FontUIResource;

public class UserLogin extends JDialog implements ActionListener, KeyListener,MouseListener{

    /**
     * @param args
     */
    JLabel jl1, jlclose, jl3;
    JTextField jname;
    JPasswordField jpass;
    JButton  jconfirm;//, jcancel;
    //Font f1=new Font("宋体", Font.PLAIN, 16);
    //Font f2=new Font("黑体", Font.ITALIC, 12);

    public static void main(String[] args) {
        // TODO 自动生成的方法存根
        UserLogin ul = new UserLogin();
    }
    int xOld = 0;
    int yOld = 0;

    public UserLogin() {

// 设置文本显示效果
UIManager.put("OptionPane.messageFont", new FontUIResource(f0));

        Container ct = this.getContentPane();
        this.setLayout(null);
        java.net.URL imageUrl=UserLogin.class.getResource("/com/image/index/用户名.png");
        //jl1 = new JLabel("", new ImageIcon("image/index/用户名.png"), 0);
        jl1 = new JLabel("", new ImageIcon(imageUrl), 0);
        jl1.setFont(MyFont.f1);
        jl1.setBounds(40, 188, 150, 30);
        ct.add(jl1);
        jname = new JTextField(20);
        jname.setFont(MyFont.f1);
        jname.setBounds(150, 190, 150, 30);
        jname.setBorder(BorderFactory.createEtchedBorder());
        //jname.setBorder(BorderFactory.createLoweredBevelBorder());
        ct.add(jname);

        //jname.enableInputMethods(false);
//		jl2=new JLabel("(用户Id)");
//		jl2.setFont(MyFont.f2);
//		jl2.setForeground(Color.red);
//		jl2.setBounds(100, 210, 100, 30);
//		ct.add(jl2);
        jlclose = new JLabel("", new ImageIcon("image/999.png"), 0);
        jlclose.setBounds(398,7,25,25);
        jlclose.addMouseListener(this);
        jlclose.addMouseListener(this);
        jlclose.setToolTipText("关闭");
        
        ct.add(jlclose);
        java.net.URL imageUrl1=UserLogin.class.getResource("/com/image/index/密码.png");
        jl3 = new JLabel("", new ImageIcon(imageUrl1), 0);
        jl3.setFont(MyFont.f1);
        jl3.setBounds(40, 240, 150, 30);
        ct.add(jl3);
        jpass = new JPasswordField(20);
        jpass.setFont(MyFont.f1);
        jpass.setBounds(150, 240, 150, 30);
        jpass.setBorder(BorderFactory.createEtchedBorder());
        ct.add(jpass);

        jconfirm = new JButton("登  录");
        jconfirm.addActionListener(this);
        jconfirm.setFont(MyFont.f1);
        Color confirmColor=new Color(0,163,255);
        jconfirm.setBackground(confirmColor);
        jconfirm.setForeground(Color.WHITE);
        jconfirm.setBounds(140, 285, 160, 30);
        ct.add(jconfirm);

//        jcancel = new JButton("取消");
//        jcancel.addActionListener(this);
//        jcancel.setFont(MyFont.f1);
//        jcancel.setBounds(230, 285, 70, 30);
//        ct.add(jcancel);
        jpass.addKeyListener(this);

        BackImage bi = new BackImage();
        bi.setBounds(0, 0, 429, 331);
        //this.add(bi);
        ct.add(bi);
        this.setUndecorated(true);
        this.setSize(429, 331);
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setLocation(width / 2 - 200, height / 2 - 200);
        this.setVisible(true);
        //清除输入法控制
        
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                xOld = e.getX();//记录鼠标按下时的坐标
                yOld = e.getY();
            }
        });

        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int xOnScreen = e.getXOnScreen();
                int yOnScreen = e.getYOnScreen();
                int xx = xOnScreen - xOld;
                int yy = yOnScreen - yOld;
                UserLogin.this.setLocation(xx, yy);//设置拖拽后，窗口的位置
            }
        });

    }

    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
Vector<String> s=null;
    @Override
    public void keyPressed(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            String uid = this.jname.getText().trim();
            String p = new String(this.jpass.getPassword());
            UserModel um = new UserModel();
            String id = new String(um.checkUser(uid, p).get(0).trim());
            String username = new String(um.checkUser(uid, p).get(1).trim());
            String zhiwei = new String(um.checkUser(uid, p).get(2).trim());
            s=new Vector<String>();
            s.add(id);
            s.add(username);
            s.add(zhiwei);
            //String empname=um.getNameById(uid);
//			String b=zhiwei;

//			System.out.println(b);
//			System.out.println(a);
//			System.out.println((boolean)(a==b));
            if ("gm".equals(zhiwei)) {
                new Windows1(s);
                this.hide();
                String welcome = "主人，欢迎回来，嘤嘤嘤~~";
                JOptionPane.showMessageDialog(this, welcome);
                this.dispose();
            } else if (zhiwei.equals("student")) {
                new Windows1(s);
                this.hide();
                String welcome = "欢迎您，尊贵的"+username+"同学!";
                JOptionPane.showMessageDialog(this, welcome);
                this.dispose();
            } else if (zhiwei.equals("teacher")) {
                new Windows1(s);
                this.hide();
                String welcome = "欢迎您，尊贵的"+username+"老师!";
                JOptionPane.showMessageDialog(this, welcome);
                this.dispose();
            } else {
                System.out.println(zhiwei);
                JOptionPane.showMessageDialog(this, "权限不足或密码错误！");
            }

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
//	内部类 放图片

    @Override
    public void mouseClicked(MouseEvent e) {
       if (e.getSource() == jlclose) {
            System.exit(0);
        }
// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (e.getSource() == jlclose) {
            java.net.URL imageUrl2=UserLogin.class.getResource("/com/image/close-square.png");
            this.jlclose.setIcon( new ImageIcon(imageUrl2));
        }else if (e.getSource() == jconfirm) {
            Color confirmColor=new Color(60,195,245);
            this.jconfirm.setBackground(confirmColor);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == jlclose) {
            this.jlclose.setIcon( new ImageIcon("image/999.png"));
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

    public class BackImage extends JPanel {

        Image img;

        public BackImage() {
            try {
                //java.net.URL imageUrl3=UserLogin.class.getResource("/com/image/tim2.png");
                img = ImageIO.read(UserLogin.class.getResource("/com/image/tim2.png"));
            } catch (IOException e) {
                // TODO 自动生成的 catch 块
                e.printStackTrace();
            }
        }

        public void paintComponent(Graphics g) {
            g.drawImage(img, 0, 0, 429, 331, this);
        }
    }
    //响应登录请求

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO 自动生成的方法存根

        if (e.getSource() == jconfirm) {
            String uid = this.jname.getText().trim();
            String p = new String(this.jpass.getPassword());
            UserModel um = new UserModel();
            //String zhiwei = new String(um.checkUser(uid, p).trim());
            String id = new String(um.checkUser(uid, p).get(0).trim());
            String username = new String(um.checkUser(uid, p).get(1).trim());
            String zhiwei = new String(um.checkUser(uid, p).get(2).trim());
            s=new Vector<String>();
            s.add(id);
            s.add(username);
            s.add(zhiwei);
            //String empname=um.getNameById(uid);
//			String b=zhiwei;

//			System.out.println(b);
//			System.out.println(a);
//			System.out.println((boolean)(a==b));
            if ("gm".equals(zhiwei)) {
                new Windows1(s);
                this.hide();
                String welcome = "主人，欢迎回来，嘤嘤嘤~~";
                JOptionPane.showMessageDialog(this, welcome);
                this.dispose();
            } else if (zhiwei.equals("student")) {
                new Windows1(s);
                this.hide();
                String welcome = "欢迎您，尊贵的"+username+"同学!";;
                JOptionPane.showMessageDialog(this, welcome);
                this.dispose();
            } else if (zhiwei.equals("teacher")) {
                new Windows1(s);
                this.hide();
                String welcome = "欢迎您，尊贵的"+username+"老师!";;
                JOptionPane.showMessageDialog(this, welcome);
                this.dispose();
            } else {
                System.out.println(zhiwei);
                JOptionPane.showMessageDialog(this, "权限不足或密码错误！");
            }
        }
//         else if (e.getSource() == jcancel) {
//
//            //当点击取消按钮时，关闭登陆框，退出系统
//            this.dispose();
//        }

    }

}
