package com.scaler.advanced.sr_015_trees.sr_06_problems.hw_day69_apr8.Q3;

import java.util.ArrayList;

public class Q3{
    public static void main(String[] args) {
        //int[] eg = { 19, 10, 11, 13, 14, 19, 20, 6, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        int[] eg = {1,2,3,4,5,6,7,8,-1,-1,-1,-1,-1,-1,-1,-1,-1};
        ArrayList<Integer> arr = new ArrayList<>();
        for(int ele: eg) {
            arr.add(ele);
        }
        TreeNode root = DeserializeTree.solve(arr);
        System.out.println(Solution.solve(root));
    }
}