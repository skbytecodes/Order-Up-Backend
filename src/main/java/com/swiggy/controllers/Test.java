package com.swiggy.controllers;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        List<Integer> res = new ArrayList<>();
        for(int i=0; i<nums.length; i++){
            if(nums[i] > target){
                continue;
            }else{
                for(int j=i+1; j<nums.length; j++){
                    if(nums[i]+nums[j] == target){
                        res.add(i);
                        res.add(j);
                        break;
                    }
                }
            }

        }
//        int[] finalArr = res.stream().mapToInt(i -> i).toArray();
        int[] finalArr = {res.get(0),res.get(1)};
        return finalArr;
    }
}
public class Test {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] res =  s.twoSum(new int[]{3,2,4}, 6);
        for(int i=0; i<res.length; i++){
                System.out.println(i);
        }
    }
}
