/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import java.util.Vector;

/**
 *
 * @author F Vadim
 */
public class App_Super {

    private String classid;
    private String hope;
    private  EmpModel em;
    private  EmpModel em_lab;

    public EmpModel getEm_lab() {
        return em_lab;
    }

    public void setEm_lab(EmpModel em_lab) {
        this.em_lab = em_lab;
    }

    public EmpModel getEm() {
        return em;
    }

    public void setEm(EmpModel em) {
        this.em = em;
    }

    public String getClassid() {
        return classid;
    }
    public int getDeskNum(int id){
            return Integer.parseInt(em_lab.getValueAt(id, 2)+"");
    }
    
    public int getStuNum(){
        
        for(int i=0;i<em.getRowCount();i++){
        if(classid.equals(em.getValueAt(i, 0)+""))
            return Integer.parseInt(em.getValueAt(i, 1)+"");
    }
        return -1;
    }

    public void setClassid(String classid) {
        this.classid = classid;
    }

    public String getHope() {
        return hope;
    }

    public void setHope(String hope) {
        this.hope = hope;
    }
    
}
