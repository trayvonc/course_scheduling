/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

public class GetApp {
	/**
	 * id
	 */
	private int application_id;
	/**
	 * ѧ��
	 */
	private String Class;
	/**
	 * ����
	 */
	private String stu_num;
	/**
	 * ѧԺ
	 */
	private String age;
	/**
	 * �ɼ�
	 */
	private String address;
        private String teacher;
        private String week;
        private String time;
        private String Monday;
        private String Tuesday;
        private String Wednseday;
        private String Thurday;
        private String Friday;

    public int getApplication_id() {
        return application_id;
    }

    public String getStu_num() {
        return stu_num;
    }

    public String getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getWeek() {
        return week;
    }

    public String getTime() {
        return time;
    }

    public String getMonday() {
        return Monday;
    }

    public String getTuesday() {
        return Tuesday;
    }

    public String getWednseday() {
        return Wednseday;
    }

    public String getFriday() {
        return Friday;
    }

    public void setApplication_id(int application_id) {
        this.application_id = application_id;
    }

    public void setStu_num(String stu_num) {
        this.stu_num = stu_num;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setMonday(String Monday) {
        this.Monday = Monday;
    }

    public void setTuesday(String Tuesday) {
        this.Tuesday = Tuesday;
    }

    public void setWednseday(String Wednseday) {
        this.Wednseday = Wednseday;
    }

    public void setFriday(String Friday) {
        this.Friday = Friday;
    }

    public String getClasses() {
        return Class;
    }

    public void setClasses(String Class) {
        this.Class = Class;
    }

    public String getThurday() {
        return Thurday;
    }

    public void setThurday(String Thurday) {
        this.Thurday = Thurday;
    }
	
}

