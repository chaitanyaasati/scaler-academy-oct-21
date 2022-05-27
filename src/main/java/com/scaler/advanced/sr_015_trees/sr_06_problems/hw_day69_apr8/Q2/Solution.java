package com.scaler.advanced.sr_015_trees.sr_06_problems.hw_day69_apr8.Q2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class Solution {
    public static ArrayList<Integer> solve(TreeNode A) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if(A==null){
            return result;
        }
        generate(A, result);
        return result;
    }

    public static void generate(TreeNode root, ArrayList<Integer> result){
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(root);
        while(q.size()!=0){
            TreeNode node = q.poll();
            if(node==null){
                result.add(-1);
                continue;
            }
            result.add(node.val);
            if(node.left==null){
                q.offer(null);
            }
            else{
                q.offer(node.left);
            }
            if(node.right==null){
                q.offer(null);
            }
            else{
                q.offer(node.right);
            }
        }
    }
}
