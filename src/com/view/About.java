package com.view;

import static com.mytools.MyFont.f0;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;

public class About extends JDialog implements ActionListener,MouseListener{

    /**
     * @param args
     */
    JLabel  jlclose;

//    public static void main(String[] args) {
//        // TODO 自动生成的方法存根
//        About ul = new About();
//    }
    int xOld = 0;
    int yOld = 0;

    public About() {

// 设置文本显示效果
UIManager.put("OptionPane.messageFont", new FontUIResource(f0));

        Container ct = this.getContentPane();
        this.setLayout(null);
        
        jlclose = new JLabel("", new ImageIcon("image/999.png"), 0);
        jlclose.setBounds(455,4,25,25);
        jlclose.addMouseListener(this);
        jlclose.addMouseListener(this);
        jlclose.setToolTipText("关闭");
        ct.add(jlclose);
        
       

        BackImage bi = new BackImage();
        bi.setBounds(0, 0, 484,341);
        //this.add(bi);
        ct.add(bi);
        this.setUndecorated(true);
        this.setSize(484,341);
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
                About.this.setLocation(xx, yy);//设置拖拽后，窗口的位置
            }
        });

    }

//	内部类 放图片

    @Override
    public void mouseClicked(MouseEvent e) {
       if (e.getSource() == jlclose) {
            this.dispose();
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
            java.net.URL imageUrl1=Windows1.class.getResource("/com/image/close-square.png");
            this.jlclose.setIcon( new ImageIcon(imageUrl1));
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
                java.net.URL imageUrl2=Windows1.class.getResource("/com/image/About.png");
                img = ImageIO.read(imageUrl2);
            } catch (IOException e) {
                // TODO 自动生成的 catch 块
                e.printStackTrace();
            }
        }

        public void paintComponent(Graphics g) {
            g.drawImage(img, 0, 0,484,341, this);
        }
    }
    //响应登录请求

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO 自动生成的方法存根
//         else if (e.getSource() == jcancel) {
//
//            //当点击取消按钮时，关闭登陆框，退出系统
//            this.dispose();
//        }

    }

}
