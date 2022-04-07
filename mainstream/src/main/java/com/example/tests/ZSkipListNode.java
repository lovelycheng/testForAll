package com.example.tests;


/**
 * @author chengtong
 * @date 2020/8/14 15:02
 */
public class ZSkipListNode {

    private String ele;

    private double score;//order

    private ZSkipListNode backword;//简单的单向链表

    private ZSkipListLevel[] listLevels;

    static class ZSkipListLevel { //层级信息

        ZSkipListNode forward;

        int span;

    }

    public String getEle() {
        return ele;
    }

    public void setEle(String ele) {
        this.ele = ele;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public ZSkipListNode getBackword() {
        return backword;
    }

    public void setBackword(ZSkipListNode backword) {
        this.backword = backword;
    }

    public ZSkipListLevel[] getListLevels() {
        return listLevels;
    }

    public void setListLevels(ZSkipListLevel[] listLevels) {
        this.listLevels = listLevels;
    }
}
