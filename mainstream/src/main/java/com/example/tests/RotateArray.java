package com.example.tests;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author chengtong
 * @date 2020/7/22 14:29
 */
public class RotateArray {

    public void rotate(int[] nums, int k) {
        k %= nums.length;
        reverse(nums,0,nums.length-1);
        reverse(nums,0,k);
        reverse(nums,k,nums.length-1);
    }


    public void reverse(int[] nums,int start, int end){
        while(start < end){
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;

        }
    }


    public static void main(String[] args) {
//        RotateArray rotateArray = new RotateArray();
//
//        int[] nums = {1,2,3,4,5,6,7};
//
//        rotateArray.rotate(nums,3);

        PriorityQueue<Integer> p = new PriorityQueue<>();

        p.add(7);
        p.add(6);
        p.add(10);
        p.add(11);
        p.add(12);
        p.add(122);
        p.add(11);
        p.add(1);
        for (Object in:p.toArray()){
            System.out.print(in+" ");
        }


    }
}
