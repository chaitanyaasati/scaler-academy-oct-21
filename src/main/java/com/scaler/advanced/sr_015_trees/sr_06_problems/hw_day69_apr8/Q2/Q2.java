package com.scaler.advanced.sr_015_trees.sr_06_problems.hw_day69_apr8.Q2;


import java.util.ArrayList;

public class Q2 {
    public static void main(String[] args) {
        int[] input = { 19, 10, 11, 13, 14, 19, 20, 6, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        ArrayList<Integer> arr = new ArrayList<>();
        for(int ele: input) {
            arr.add(ele);
        }
        System.out.println("Serialize Tree: " + Solution.solve(DeserializeTree.solve(arr)));
    }
}

