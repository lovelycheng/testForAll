package com.example.tests;


import java.lang.*;

/**
 * @author chengtong
 * @date 2020/8/14 15:08
 */
public class ZSkipList {

    ZSkipListNode header;

    ZSkipListNode tail;

    int length;

    int level;

    ZSkipList(int level){
        this.header = new ZSkipListNode();
        this.header.setEle(null);
        this.header.setBackword(null);
        this.header.setListLevels(new ZSkipListNode.ZSkipListLevel[level]);
        this.header.setScore(0);
        this.tail = this.header;

        for(int i=0;i<level;i++){
            this.header.getListLevels()[i].forward = null;
            this.header.getListLevels()[i].span = 0;
        }
        this.level = level;
        this.length = 0;
    }



    public void add(String key){

    }

}
