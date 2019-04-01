/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * 课表表格界面，展现想要的内容
 */
package com.view;

import com.model.EmpModel;
import com.model.Zuhe;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.util.Vector;
import javax.swing.table.TableCellRenderer;

public class SearchTimeTable extends JPanel {

//    public static void main(String[] args) throws SQLException {
//        String tname = null;
//        new SearchTimeTable(tname);
//    }

    public void Zuhe() {
        //从数据库里面找组合成teacher-class-time1-lab1-time2-lab2-time3-lab3-time4-lab4
        //连接数据库取出数据
        Vector<Zuhe> s = new Vector<>();
        String sql = "select count(*) from general_table";
        EmpModel em = new EmpModel();
        int sum = em.getNum(sql);

        sql = "select * from general_table where 1=?";
        String[] params = {"1"};
        em = new EmpModel();
        em.query(sql, params);
        for (int i = 0; i < sum; i++) {
            if (em.getValueAt(i, 2) != null) {
                Zuhe a = new Zuhe();
                a.setTeacher(em.getValueAt(i, 1) + "");
                a.setClass(em.getValueAt(i, 2) + "");
                a.setTime1(em.getValueAt(i, 4) + "");
                a.setLab1(em.getValueAt(i, 5) + "");
                a.setTime2(em.getValueAt(i, 6) + "");
                a.setLab2(em.getValueAt(i, 7) + "");
                a.setTime3(em.getValueAt(i, 8) + "");
                a.setLab3(em.getValueAt(i, 9) + "");
                a.setTime4(em.getValueAt(i, 10) + "");
                a.setLab4(em.getValueAt(i, 11) + "");
                s.add(a);
            }

            if (em.getValueAt(i, 3) != null) {
                Zuhe a = new Zuhe();
                a.setTeacher(em.getValueAt(i, 1) + "");
                a.setClass(em.getValueAt(i, 3) + "");
                a.setTime1(em.getValueAt(i, 12) + "");
                a.setLab1(em.getValueAt(i, 13) + "");
                a.setTime2(em.getValueAt(i, 14) + "");
                a.setLab2(em.getValueAt(i, 15) + "");
                a.setTime3(em.getValueAt(i, 16) + "");
                a.setLab3(em.getValueAt(i, 17) + "");
                a.setTime4(em.getValueAt(i, 18) + "");
                a.setLab4(em.getValueAt(i, 19) + "");
                s.add(a);
            }
        }
        for (int i = 0;i< s.size(); i++) {
            //插入
            sql = "insert into final_table(teacher,Class,time1,lab1,time2,lab2,time3,lab3,time4,lab4) values(?,?,?,?,?,?,?,?,?,?)";
            String []param={s.get(i).getTeacher(),s.get(i).getclass(),s.get(i).getTime1(),s.get(i).getLab1(),s.get(i).getTime2(),s.get(i).getLab2(),s.get(i).getTime3(),s.get(i).getLab3(),s.get(i).getTime4(),s.get(i).getLab4()};
            em = new EmpModel();
            em.UpdateModel(sql, param);
        }
    }

    public SearchTimeTable(String s) throws SQLException {
        
        //删除旧表
        //全部删除以前的表
            String sql = "delete from final_table";
            EmpModel em = new EmpModel();
            em.deleteModel(sql);
        //先搬库
        Zuhe();
        //初始化面板，显示所有课表信息
        intiComponent();
        //this.setSize(700,400);
    }
    public void up(String s){
        //传入一个关键字
//        String tname = s;
//        int id = 0;
//        ResultSet rs = findid(s, "final_table");
//        while (rs.next()) {
//            id = rs.getInt(1);
//        }
//        //System.out.println(id);
//        String roomid = null;
//        int t1 = 0, t2 = 0, t3 = 0, t4 = 0, t5 = 0, t6 = 0;
//        ResultSet rs1 = find(id, "final_table");
//        while (rs1.next()) {
//            roomid = rs1.getString(3);
//            t1 = rs1.getInt(6);
//            t2 = rs1.getInt(7);
//            t3 = rs1.getInt(8);
//            t4 = rs1.getInt(9);
//            t5 = rs1.getInt(10);
//            t6 = rs1.getInt(11);
//        }
        //System.out.println(t1);
        //System.out.println(t2);
        //System.out.println(t3);
    }

    private void UpdComponent(String tname, String roomid, int t1, int t2, int t3, int t4, int t5, int t6) {
        String[] columnNames
                = {"课节数", "星期一", "星期二", "星期三", "星期四", "星期五"};

        Object[][] obj = new Object[4][6];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                obj[i][j] = "";
            }
        }
        obj[0][0] = "第一周上午";
        obj[1][0] = "第一周下午";
        obj[2][0] = "第二周上午";
        obj[3][0] = "第二周下午";
        int t[] = new int[6];
        t[0] = t1;
        t[1] = t2;
        t[2] = t3;
        t[3] = t4;
        t[4] = t5;
        t[5] = t6;
        for (int i = 0; i < 6; i++) {
            switch (t[i]) {
                case 1:
                    obj[0][1] = roomid + "上课";
                    break;
                case 2:
                    obj[1][1] = roomid + "上课";
                    break;
                case 3:
                    obj[0][2] = roomid + "上课";
                    break;
                case 4:
                    obj[1][2] = roomid + "上课";
                    break;
                case 5:
                    obj[0][3] = roomid + "上课";
                    break;
                case 6:
                    obj[1][3] = roomid + "上课";
                    break;
                case 7:
                    obj[0][4] = roomid + "上课";
                    break;
                case 8:
                    obj[1][4] = roomid + "上课";
                    break;
                case 9:
                    obj[0][5] = roomid + "上课";
                    break;
                case 10:
                    obj[1][5] = roomid + "上课";
                    break;
                case 11:
                    obj[2][1] = roomid + "上课";
                    break;
                case 12:
                    obj[3][1] = roomid + "上课";
                    break;
                case 13:
                    obj[2][2] = roomid + "上课";
                    break;
                case 14:
                    obj[3][2] = roomid + "上课";
                    break;
                case 15:
                    obj[2][3] = roomid + "上课";
                    break;
                case 16:
                    obj[3][3] = roomid + "上课";
                    break;
                case 17:
                    obj[3][4] = roomid + "上课";
                    break;
                case 18:
                    obj[3][4] = roomid + "上课";
                    break;
                case 19:
                    obj[2][5] = roomid + "上课";
                    break;
                case 20:
                    obj[3][5] = roomid + "上课";
                    break;
            }
        }

        JTable table = new JTable(obj, columnNames);
        TableColumn column = null;
        int colunms = table.getColumnCount();
        for (int i = 0; i < colunms; i++) {
            column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth(75);
        }
        this.setSize(700, 400);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setRowHeight(100);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setSize(300, 50);

        this.add(scroll);

        this.setLocation(0, 200);

        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        //this.pack();  
    }

    private void intiComponent() {
        String[] columnNames
                = {"课节数", "星期一", "星期二", "星期三", "星期四", "星期五"};

        Object[][] obj = new Object[4][6];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                obj[i][j] = "";
            }
        }
        obj[0][0] = "第九周上午";
        obj[1][0] = "第九周下午";
        obj[2][0] = "第十周上午";
        obj[3][0] = "第十周下午";
        String sql = "select count(*) from final_table";
        EmpModel em = new EmpModel();
        int sum = em.getNum(sql);
        
        sql = "select * from final_table where 1=?";
        String[] params = {"1"};
        em = new EmpModel();
        em.query(sql, params);
        String timepice=null;
        
        for(int i=0;i<sum;i++){
            int k=3;
            for(int j=0;j<4;j++){
            timepice=em.getValueAt(i, k)+"";
            switch (Integer.parseInt(timepice)) {
                case 0:
                    obj[0][1] = obj[0][1]+""+em.getValueAt(i, 1)+"-"+em.getValueAt(i, 2)+"-"+em.getValueAt(i, k+1)+"\n";
                    break;
                case 1:
                    obj[0][2] = obj[0][2]+""+em.getValueAt(i, 1)+"-"+em.getValueAt(i, 2)+"-"+em.getValueAt(i, k+1)+"\n";
                    break;
                case 2:
                    obj[0][3] = obj[0][3]+""+em.getValueAt(i, 1)+"-"+em.getValueAt(i, 2)+"-"+em.getValueAt(i, k+1)+"\n";
                    break;
                case 3:
                    obj[0][4] = obj[0][4]+""+em.getValueAt(i, 1)+"-"+em.getValueAt(i, 2)+"-"+em.getValueAt(i, k+1)+"\n";
                    break;
                case 4:
                    obj[0][5] = obj[0][5]+""+em.getValueAt(i, 1)+"-"+em.getValueAt(i, 2)+"-"+em.getValueAt(i, k+1)+"\n";
                    break;
                case 5:
                    obj[1][1] = obj[1][1]+""+em.getValueAt(i, 1)+"-"+em.getValueAt(i, 2)+"-"+em.getValueAt(i, k+1)+"\n";
                    break;
                case 6:
                    obj[1][2] = obj[1][2]+""+em.getValueAt(i, 1)+"-"+em.getValueAt(i, 2)+"-"+em.getValueAt(i, k+1)+"\n";
                    break;
                case 7:
                    obj[1][3] = obj[1][3]+""+em.getValueAt(i, 1)+"-"+em.getValueAt(i, 2)+"-"+em.getValueAt(i, k+1)+"\n";
                    break;
                case 8:
                    obj[1][4] = obj[1][4]+""+em.getValueAt(i, 1)+"-"+em.getValueAt(i, 2)+"-"+em.getValueAt(i, k+1)+"\n";
                    break;
                case 9:
                    obj[1][5] = obj[1][5]+""+em.getValueAt(i, 1)+"-"+em.getValueAt(i, 2)+"-"+em.getValueAt(i, k+1)+"\n";
                    break;
                case 10:
                    obj[2][1] = obj[2][1]+""+em.getValueAt(i, 1)+"-"+em.getValueAt(i, 2)+"-"+em.getValueAt(i, k+1)+"\n";
                    break;
                case 11:
                    obj[2][2] = obj[2][2]+""+em.getValueAt(i, 1)+"-"+em.getValueAt(i, 2)+"-"+em.getValueAt(i, k+1)+"\n";
                    break;
                case 12:
                    obj[2][3] = obj[2][3]+""+em.getValueAt(i, 1)+"-"+em.getValueAt(i, 2)+"-"+em.getValueAt(i, k+1)+"\n";
                    break;
                case 13:
                    obj[2][4] = obj[2][4]+""+em.getValueAt(i, 1)+"-"+em.getValueAt(i, 2)+"-"+em.getValueAt(i, k+1)+"\n";
                    break;
                case 14:
                    obj[2][5] = obj[2][5]+""+em.getValueAt(i, 1)+"-"+em.getValueAt(i, 2)+"-"+em.getValueAt(i, k+1)+"\n";
                    break;
                case 15:
                    obj[3][1] = obj[3][1]+""+em.getValueAt(i, 1)+"-"+em.getValueAt(i, 2)+"-"+em.getValueAt(i, k+1)+"\n";
                    break;
                case 16:
                    obj[3][2] = obj[3][2]+""+em.getValueAt(i, 1)+"-"+em.getValueAt(i, 2)+"-"+em.getValueAt(i, k+1)+"\n";
                    break;
                case 17:
                    obj[3][3] = obj[3][3]+""+em.getValueAt(i, 1)+"-"+em.getValueAt(i, 2)+"-"+em.getValueAt(i, k+1)+"\n";
                    break;
                case 18:
                    obj[3][4] = obj[3][4]+""+em.getValueAt(i, 1)+"-"+em.getValueAt(i, 2)+"-"+em.getValueAt(i, k+1)+"\n";
                    break;
                case 19:
                    obj[3][5] = obj[3][5]+""+em.getValueAt(i, 1)+"-"+em.getValueAt(i, 2)+"-"+em.getValueAt(i, k+1)+"\n";
                    break;
            }
            //右移两位
            k=k+2;
        }
        }
       

        JTable table = new JTable(obj, columnNames);
//        TableColumn column = null;
//        int colunms = table.getColumnCount();
//        for (int i = 0; i < colunms; i++) {
//            column = table.getColumnModel().getColumn(i);
//            column.setPreferredWidth(150);
//        }
        //this.setSize(700, 400);
        this.setLayout(new BorderLayout());
        //table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setDefaultRenderer(Object.class, new TableCellTextAreaRenderer()); 
        this.add(new JScrollPane(table)); 
        //table.setRowHeight(200);
//        JScrollPane scroll = new JScrollPane(table);
//        scroll.setSize(300, 50);

//        this.add(scroll);

        this.setLocation(0, 200);

        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        //this.pack();  
    }
    
//    public static ResultSet find(int i, String s) throws SQLException {
//        Connection con;
//        String driver = "com.mysql.jdbc.Driver";
//        String url = "jdbc:mysql://localhost:3306/time_table?characterEncoding=utf8&useSSL=false";
//        String user = "root";
//        String password = "123123";
//        ResultSet rs = null;
//        try {
//
//            Class.forName(driver);
//            con = (Connection) DriverManager.getConnection(url, user, password);
//            //if(!con.isClosed())
//            //System.out.println("Succeeded connecting to the Database!");
//            PreparedStatement stmt = null;
//            String sql = "select * from " + s + " where application_id = " + String.valueOf(i);
//            stmt = (PreparedStatement) con.prepareStatement(sql);
//            rs = stmt.executeQuery();
//        } catch (ClassNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//        return rs;
//    }

//    public static ResultSet findid(String name, String s) throws SQLException {
//        Connection con;
//        String driver = "com.mysql.jdbc.Driver";
//        String url = "jdbc:mysql://localhost:3306/time_table?characterEncoding=utf8&useSSL=false";
//        String user = "root";
//        String password = "123123";
//        ResultSet rs = null;
//        try {
//
//            Class.forName(driver);
//            con = (Connection) DriverManager.getConnection(url, user, password);
//            PreparedStatement stmt = null;
//            String sql = "select * from " + s + " where teacher = '" + name + "'";
//            stmt = (PreparedStatement) con.prepareStatement(sql);
//            rs = stmt.executeQuery();
//        } catch (ClassNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//        return rs;
//    }

}


class TableCellTextAreaRenderer extends JTextArea implements TableCellRenderer {

    public TableCellTextAreaRenderer() {
        setLineWrap(true);
        setWrapStyleWord(true);
        //setAlignmentX(0.5f);
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
// 计算当下行的最佳高度 
        int maxPreferredHeight = 0;
        for (int i = 0; i < table.getColumnCount(); i++) {
            setText("" + table.getValueAt(row, i));
            setSize(table.getColumnModel().getColumn(column).getWidth(), 0);
            maxPreferredHeight = Math.max(maxPreferredHeight, getPreferredSize().height);
        }

        if (table.getRowHeight(row) != maxPreferredHeight) // 少了这行则处理器瞎忙 
        {
            table.setRowHeight(row, maxPreferredHeight);
        }

        setText(value == null ? "" : value.toString());
        return this;
    }
}
