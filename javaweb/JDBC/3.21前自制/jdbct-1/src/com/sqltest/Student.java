package com.sqltest;

public class Student {
    private int flowID;
    private int type;
    private String IDCard;
    private String examCard;
    private String name;
    private String location;
    private int grade;

    public Student() {
    }

    public Student(int flowID, int type, String IDCard, String examCard, String name, String location, int grade) {
        this.flowID = flowID;
        this.type = type;
        this.IDCard = IDCard;
        this.examCard = examCard;
        this.name = name;
        this.location = location;
        this.grade = grade;
    }

    public int getFlowID() {
        return flowID;
    }

    public int getType() {
        return type;
    }

    public String getIDCard() {
        return IDCard;
    }

    public String getExamCard() {
        return examCard;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public int getGrade() {
        return grade;
    }

    public void setFlowID(int flowID) {
        this.flowID = flowID;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setIDCard(String IDCard) {
        this.IDCard = IDCard;
    }

    public void setExamCard(String examCard) {
        this.examCard = examCard;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        System.out.println("===============查询结果==================");
        return  "flowID=  \t" + flowID +
                "\ntype=  \t" + type +
                "\nIDCard=  \t" + IDCard + '\'' +
                "\n examCard=  " + examCard + '\'' +
                "\nname=  " + name + '\'' +
                "\n location=  " + location + '\'' +
                "\ngrade=  " + grade;
    }


}
