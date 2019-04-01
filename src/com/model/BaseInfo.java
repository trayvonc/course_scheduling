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
public class BaseInfo {
    public Vector<App_Super> s;
    public int [][] state;

    public Vector<App_Super> getS() {
        return s;
    }

    public void setS(Vector<App_Super> s) {
        this.s = s;
    }

    public int[][] getState() {
        return state;
    }

    public void setState(int[][] state) {
        this.state = state;
    }
    
}
