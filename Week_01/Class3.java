package com.yim;

import java.util.Arrays;

public class Class3 {
    public static void main(String[] args) {
        main_removeDuplicates();
        main_rotate();
    }

    public static void main_removeDuplicates() {
        int[] array = new int[]{1, 1, 2};
//        int i = removeDuplicates(array);
        int i = removeDuplicates2(array);
        System.out.println("rtn:" + i);
    }

    public static int removeDuplicates(int[] nums) {
        int index = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[index] != nums[i]) {
                index++;
                nums[index] = nums[i];
            }
        }
        return index + 1;
    }

    public static int removeDuplicates2(int[] nums) {
        int dupCount = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                dupCount++;
            } else {
                nums[i - 1] = nums[i];
            }
        }
        return nums.length - dupCount;
    }

    public static void main_rotate() {
        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7};
        int k = 3;
        rotate2(array, k);
        System.out.println("rtn:" + Arrays.toString(array));
    }

    public static void rotate(int[] nums, int k) {
        int[] nnums = new int[nums.length];
        int index = nums.length - k;
        for (int i = 0; i < k; i++) {
            nnums[i] = nums[index];
            index++;
        }
        int index2 = 0;
        for (int i = k; i < nums.length; i++) {
            nnums[i] = nums[index2];
            index2++;
        }
        System.arraycopy(nnums, 0, nums, 0, nums.length);
    }

    public static void rotate2(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    public static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
