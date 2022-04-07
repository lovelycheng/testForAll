package com.example.tests.argl;

import static java.lang.System.err;

/**
 * @author chengtong
 * @date 2020/6/28 17:15
 */
public class Solution {

    //对实参做了拷贝
    public int removeDuplicatesFailure(int[] nums) {
        int temp;
        int tempLength = 0;
        int index = 0;
        int length = nums.length;
        for (int i = 0; i < length - 1; ) {

            if (length < 2) {
                return nums[i];
            }

            if (nums[i] == nums[i + 1]) {
                temp = nums[i];
                err.println("temp:" + temp);
                err.println("i:" + i);
                err.println("length:" + length);
                int j = i;
                while (nums[j] == temp) {
                    if (j == length - 1) {
                        int[] newint = new int[i];
                        System.arraycopy(nums, 0, newint, 0, i);
                        nums = newint;
                        return nums.length;
                    }
                    tempLength++;
                    j++;
                }
                int needReplace = tempLength - 1;
                int x = i;

                while (x < length - needReplace - 1) {
                    nums[x + 1] = nums[x + 1 + needReplace];
                    x++;
                }
                err.println("needReplace:" + needReplace);
                tempLength = 0;
                index++;
                i = index;

            } else {
                index++;
                i = index;
            }
            for (int num : nums) {
                err.print(num);
            }
            err.println("");
        }

        return nums.length;
    }


    public int removeDuplicates1(int[] nums) {
        if (nums.length == 0) return 0;
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }


    public int removeDuplicates(int[] nums){
        int i = 0;
        int j = 1;
        //考虑相同的情况需要一个另外的循环；快慢指针反一下;慢指针在里面、快指针在外 - 一次循环；
        for(;j<nums.length;j++){
            if (nums[i] != nums[j]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return 1;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] a = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        solution.removeDuplicatesFailure(a);


        a = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4,4,5};
        solution.removeDuplicates1(a);

        for (int num : a) {
            err.print(num);
        }

    }


}
