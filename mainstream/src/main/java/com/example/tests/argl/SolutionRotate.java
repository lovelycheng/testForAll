package com.example.tests.argl;

/**
 * @author chengtong
 * @date 2020/7/5 06:59
 */
public class SolutionRotate {

    public void rotateViolence(int[] nums, int k) {
        if (nums.length < k) {
            return;
        }

        for (int j = 0; j < k; j++) {
            int tempValue = nums[nums.length - 1];
            for (int i = nums.length - 1; i >= 0; i--) {

                int next = i - 1;
                if (i == 0) {
                    nums[i] = tempValue;
                    continue;
                }
                nums[i] = nums[next];
            }
            System.out.println("");
        }
    }


    public void rotateCircle(int[] nums, int k) {


        for (int i = 0; i < k; i++) {

            int current = i;

            int start = current;
            int prev = nums[start];

            do{

                int next = current+k % nums.length;
                int temp = nums[next];
                nums[next] = prev;

                prev = temp;
                current = current + k;
            }while (start != current);



        }





    }


    public static void main(String[] args) {
        SolutionRotate sr = new SolutionRotate();

        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7};
//        sr.rotate(nums, 3);

    }


}
