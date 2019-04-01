/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.view;

import com.model.App_Super;
import com.model.EmpModel;
import com.model.BaseInfo;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author F Vadim
 */
public class NQueens implements Runnable{
JLabel jl2=null;
    //行号，列号,对象信息，时间片号，结果
    public boolean Place(int r, int l, Vector<App_Super> Vx, int timepice, int res[][], int state[][]) {
//判断同一列是否有已选，同一时间片是否有已选，学生人数有没有超,其实只要和前面的信息比较就行
//测试每个实验室是位置够
//得到实验室编号
//        if (r <= 3) {
//说明这两周都申请了
if (state[r][0]==1&&state[r][2]==1) {
            int lab = l - timepice * 4;
            //把行号转化为Vector编号
            //int a = r * 20 + timepice;
            int a=0;
            for(int m=0;m<r;m++){
                a=a+state[m][0]+state[m][2];
            }
            a=a*10+timepice;

            if (Vx.get(a).getStuNum() <= Vx.get(a).getDeskNum(lab) && Vx.get(a).getHope().equals("√")) {
                //实验室够
                //判断是否和前面冲突
                for (int i = 0; i < r; i++) {
                    if (res[i][l] == 1) {
                        return false;
                    }
                }
                //搜索时间片
                for (int j = timepice * 4; j < l; j++) {
                    if (res[r][j] == 1) {
                        return false;
                    }
                }
                return true;
            }
            //只在第10周申请
        } else if (state[r][0]==0&&state[r][2]==1) {
           //第5组第9周不开课
            if (l <= 39) {
                return false;
            } else {
                int lab = l - timepice * 4;
                //把行号转化为Vector编号
//                int a = r * 20 + timepice;
//                a=a-10;
                int a = 0;
                for (int m = 0; m < r; m++) {
                    a = a + state[m][0] + state[m][2];
                }
                a = a * 10 + timepice;
                a=a-10;
                if (Vx.get(a).getStuNum() <= Vx.get(a).getDeskNum(lab) && (Vx.get(a).getHope().equals("√")||Vx.get(a).getHope().equals(""))) {
                    //实验室够
                    //判断是否和前面冲突
                    for (int i = 0; i < r; i++) {
                        if (res[i][l] == 1) {
                            return false;
                        }
                    }
                    //搜索时间片
                    for (int j = timepice * 4; j < l; j++) {
                        if (res[r][j] == 1) {
                            return false;
                        }
                    }
                    return true;
                }
            }
        }else if (state[r][0]==1&&state[r][2]==0) {
           //第5组第9周不开课
            if (l >= 40) {
                return false;
            } else {
                int lab = l - timepice * 4;
                //把行号转化为Vector编号
//                int a = r * 20 + timepice;
//                a=a-10;
                int a = 0;
                for (int m = 0; m < r; m++) {
                    a = a + state[m][0] + state[m][2];
                }
                a = a * 10 + timepice;
                if (Vx.get(a).getStuNum() <= Vx.get(a).getDeskNum(lab) && (Vx.get(a).getHope().equals("√")||Vx.get(a).getHope().equals(""))) {
                    //实验室够
                    //判断是否和前面冲突
                    for (int i = 0; i < r; i++) {
                        if (res[i][l] == 1) {
                            return false;
                        }
                    }
                    //搜索时间片
                    for (int j = timepice * 4; j < l; j++) {
                        if (res[r][j] == 1) {
                            return false;
                        }
                    }
                    return true;
                }
            }
        }

        return false;
    }
    //该标志位用于提前返回一组可行解
boolean flag=true;
    //n为定值，在后续程序中不会改变
    public int[][] NQueens1(int r, int n, Vector<App_Super> Vx, int[][] res, int first,int [][]state,int tea_num) {
        //遍历列
        int course = 0;
        int timepice = 0;
        for (int l = first; l < n; l++) {
            //计算所属的时间片,损失精度
            timepice = l / 4;
            //判断是否可行
            if (Place(r, l, Vx, timepice, res,state)) {
                //该时间片该实验室可行
                res[r][l] = 1;
                course++;
                //没有排完遍历下一个列
                if (course < 4) {
                    if (l != n-1) {
                        NQueens1(r, n, Vx, res, l + 1,state,tea_num);
                    }
                } else {
                    //最后一组排课完成,输出一组可行解
                    if (r == tea_num-1) {
                        flag=false;
                        
                        return res;
                    } else {
                        if(flag)
                        NQueens1(r + 1, n, Vx, res, 0,state,tea_num);
                    }
                }

            }

        }
        return res;
    }

    public NQueens(JLabel jl) {
       jl2=jl;
             
    }

//    public static void main(String[] args) {
//
//        NQueens n = new NQueens();
//    }

    public BaseInfo Init(EmpModel em2,EmpModel em_lab) {
        //连接数据库取出数据
        Vector<App_Super> s = new Vector<>();
        //重写预处理部分,选出不重复的老师
        String sql = "select distinct teacher from application where 1=?";
        String[] params12 = {"1"};
        EmpModel em1 = new EmpModel();
        em1.query(sql, params12);//em1得到的是一列不重复的老师姓名
        int tea_num=em1.getRowCount();//老师个数 
        //System.out.println(tea_num);
        
        //整合，前提是每个老师最多只能申请4次，按名字第一次出现的顺序排布
        //取出老师的申请状态生成01矩阵，0表示在该时段没有申请
        int[][] state=new int[tea_num][4];
        for (int i = 0; i < tea_num; i++) {
            for (int j = 0; j < 4; j++) {
                state[i][j] = 0;
            }
        }
        
        for(int i=0;i<tea_num;i++){  
        sql = "select * from application where teacher=? and week=? and time=?";
        String[] param = {em1.getValueAt(i, 0)+"","9","2-5节"};
        EmpModel em3 = new EmpModel();
        em3.query(sql, param);
        if(em3.getRowCount()==1){
           state[i][0]=1;
           //得到状态后直接按名字加入Vector
           for (int k = 0; k < 5; k++) {
                App_Super a = new App_Super();
                a.setClassid(em3.getValueAt(0, 1) + "");
                a.setHope(em3.getValueAt(0, 7 + k) + "");
                a.setEm(em2);
                a.setEm_lab(em_lab);
                s.add(a);
            }
        }
        
        sql = "select * from application where teacher=? and week=? and time=?";
        String[] param1 = {em1.getValueAt(i, 0)+"","9","6-9节"};
        em3 = new EmpModel();
        em3.query(sql, param1);
        if(em3.getRowCount()==1){
           state[i][1]=1;
           for (int k = 0; k < 5; k++) {
                App_Super a = new App_Super();
                a.setClassid(em3.getValueAt(0, 1) + "");
                a.setHope(em3.getValueAt(0, 7 + k) + "");
                a.setEm(em2);
                a.setEm_lab(em_lab);
                s.add(a);
            }
        }
        
        sql = "select * from application where teacher=? and week=? and time=?";
        String[] param2 = {em1.getValueAt(i, 0)+"","10","2-5节"};
        em3 = new EmpModel();
        em3.query(sql, param2);
        if(em3.getRowCount()==1){
           state[i][2]=1;
           for (int k = 0; k < 5; k++) {
                App_Super a = new App_Super();
                a.setClassid(em3.getValueAt(0, 1) + "");
                a.setHope(em3.getValueAt(0, 7 + k) + "");
                a.setEm(em2);
                a.setEm_lab(em_lab);
                s.add(a);
            }
        }
        
        sql = "select * from application where teacher=? and week=? and time=?";
        String[] param3 = {em1.getValueAt(i, 0)+"","10","6-9节"};
        em3 = new EmpModel();
        em3.query(sql, param3);
        if(em3.getRowCount()==1){
           state[i][3]=1;
           for (int k = 0; k < 5; k++) {
                App_Super a = new App_Super();
                a.setClassid(em3.getValueAt(0, 1) + "");
                a.setHope(em3.getValueAt(0, 7 + k) + "");
                a.setEm(em2);
                a.setEm_lab(em_lab);
                s.add(a);
            }
        }
        }
        BaseInfo bi=new BaseInfo();
        bi.setS(s);
        bi.setState(state);

        return bi;
    }
    //老师不会同时上课
//    public boolean PlaceTea(Vector<App_Super> x,int timepice,int res[][]){
//        if()
//    }
    //同一个班的学生不会同时上课
//    public boolean PlaceStu(Vector<App_Super> x,int timepice,int res[][]){
//        if()
//        return true;
//    }
    //t为实验室编号，r为行数
//    public boolean PlaceClass(int r,Vector<App_Super> x,int timepice,int res[][]){
//        
//    }
//  

    public int Choose(int i,EmpModel em3) {
        
        return Integer.parseInt(em3.getValueAt(i, 1)+"");
    }

    @Override
    public void run() {
        
        String sql = "select distinct class,stu_num,teacher from application where 1=?";
        String[] params13 = {"1"};
        EmpModel em2 = new EmpModel();
        em2.query(sql, params13);//em2告诉我有哪些班级，班级有多少学生，该班级的老师是谁
        int class_num=em2.getRowCount();//班级个数
        
        sql = "select distinct teacher from application where 1=?";
        String[] params12 = {"1"};
        EmpModel em1 = new EmpModel();
        em1.query(sql, params12);//em1得到的是一列不重复的老师姓名
        int tea_num=em1.getRowCount();//老师个数 
        
        sql = "select * from lab_info where 1=?";
        String[] params14 = {"1"};
        EmpModel em3 = new EmpModel();
        em3.query(sql, params14);//em3得到的是实验室信息
        int lab_num=em3.getRowCount();//实验室个数 
        
        
        
        
        BaseInfo baseInfo=Init(em2,em3);
        Vector<App_Super> Vx = baseInfo.getS();
        
        int[][] res = new int[tea_num][20*lab_num];
        //初始化res，结果为1的地方要上课
        for (int i = 0; i < tea_num; i++) {
            for (int j = 0; j < 20*lab_num; j++) {
                res[i][j] = 0;
            }
        }
        
        int[] a = new int[40];
        int[] b = new int[40];
        String[]name=new String[tea_num];
        String[]Class=new String[tea_num*2];
        int[][] state=baseInfo.getState(); 
        
                //从第0行开始，总共80列，结果存在res中
        res = NQueens1(0, 20*lab_num, Vx, res, 0,state,tea_num);
        for(int i1=0;i1<=tea_num-1;i1++){
                        for(int tmp1=0;tmp1<80;tmp1++)
                        System.out.print(res[i1][tmp1]+","); 
                        System.out.println(""); 
        }
//初始化老师姓名//初始化班级号
for (int i = 0; i < tea_num; i++) {
            name[i] = em1.getValueAt(i, 0) + "";

            sql = "select class from application where teacher=?";
            String[] params15 = {name[i]};
            EmpModel em4 = new EmpModel();
            em4.query(sql, params15);

            if (em4.getRowCount() == 4) {
                Class[i * 2] = em4.getValueAt(0, 0) + "";
                Class[i * 2+1] = em4.getValueAt(2, 0) + "";
            } else if (em4.getRowCount() == 2) {
                    if (state[i][0]==1) {
                      //说明是第9周申请，第10周没有申请
                      Class[i * 2] = em4.getValueAt(0, 0) + "";
                      Class[i * 2+1] =null;
                    }else{
                        //说明是第9周没有申请，第10周申请
                        Class[i * 2] = null;
                        Class[i * 2+1] =em4.getValueAt(0, 0) + "";
                    }
            }

}
//for (int i = 0; i < tea_num*2; i++) {
//    System.out.println(Class[i]);
//}
//        Class[0]="B160406";
//        Class[1]="B160407";
//        Class[2]="B160408";
//        Class[3]="B160408";
//        Class[4]="B160409";
//        Class[5]="B160409";
//        Class[6]="B160410";
//        Class[7]="B160410";
//        Class[8]=null;
//        Class[9]="B160404";
        
        int f = 0;
        for (int i = 0; i <tea_num; i++) {
            //System.out.print("老师" + i + "号:");
            for (int tmp = 0; tmp < 20*lab_num; tmp++) {
                //System.out.print(res[i][tmp] + ",");
                if (res[i][tmp] == 1) {
                    //获取教室
                    a[f] = Choose(tmp % lab_num,em3);
                    b[f] = tmp / 4;
                    //获取
                    //System.out.print(b[f] + "号时间片在" + a[f] + "号教室上课，");
                    f++;
                }
            }
            if(state[i][0]==1&&state[i][2]==1){
            sql = "insert into general_table(teacher,Class1,Class2,time1,lab1,time2,lab2,time3,lab3,time4,lab4,time5,lab5,time6,lab6,time7,lab7,time8,lab8) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            String[] params = {name[i],Class[2*i],Class[2*i+1],b[0] + "", a[0] + "", b[1] + "", a[1] + "", b[2] + "", a[2] + "", b[3] + "", a[3] + "", b[4] + "", a[4] + "", b[5] + "", a[5] + "", b[6] + "", a[6] + "", b[7] + "", a[7] + ""};
            EmpModel em = new EmpModel();
            em.UpdateModel(sql, params);
            }else if(state[i][0]==0&&state[i][2]==1){
            sql = "insert into general_table(teacher,Class1,Class2,time5,lab5,time6,lab6,time7,lab7,time8,lab8) values(?,?,?,?,?,?,?,?,?,?,?)";
            String[] params = {name[i],Class[2*i],Class[2*i+1],b[0] + "", a[0] + "", b[1] + "", a[1] + "", b[2] + "", a[2] + "", b[3] + "", a[3] + ""};
            EmpModel em = new EmpModel();
            em.UpdateModel(sql, params);
            }else if(state[i][0]==1&&state[i][2]==0){
               sql = "insert into general_table(teacher,Class1,Class2,time1,lab1,time2,lab2,time3,lab3,time4,lab4) values(?,?,?,?,?,?,?,?,?,?,?)";
            String[] params = {name[i],Class[2*i],Class[2*i+1],b[0] + "", a[0] + "", b[1] + "", a[1] + "", b[2] + "", a[2] + "", b[3] + "", a[3] + ""};
            EmpModel em = new EmpModel();
            em.UpdateModel(sql, params); 
            }
            //System.out.println("");
            f = 0;
        }
//        int i=4;
//           //System.out.print("老师" + i + "号:");
//            for (int tmp = 0; tmp < 20*lab_num; tmp++) {
//                //System.out.print(res[i][tmp] + ",");
//                if (res[i][tmp] == 1) {
//                    //获取教室
//                    a[f] = Choose(tmp % 4,em3);
//                    b[f] = tmp / 4;
//                    //获取
//                    //System.out.print(b[f] + "号时间片在" + a[f] + "号教室上课，");
//                    f++;
//                    //System.out.println(f); 
//                    
//                }
//            }
//                           
//            sql = "insert into general_table(teacher,Class1,Class2,time5,lab5,time6,lab6,time7,lab7,time8,lab8) values(?,?,?,?,?,?,?,?,?,?,?)";
//            String[] params = {name[i],Class[2*i],Class[2*i+1],b[0] + "", a[0] + "", b[1] + "", a[1] + "", b[2] + "", a[2] + "", b[3] + "", a[3] + ""};
//            EmpModel em = new EmpModel();
//            em.UpdateModel(sql, params);
            jl2.setText("  课表生成成功，快去查看课表吧！");
    }


}
