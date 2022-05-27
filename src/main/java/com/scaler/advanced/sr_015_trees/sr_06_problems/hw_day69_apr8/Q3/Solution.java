package com.scaler.advanced.sr_015_trees.sr_06_problems.hw_day69_apr8.Q3;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    static int solve(TreeNode A) {
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(A);
        int currentLevelSize = 1;
        boolean isEven = true;
        while(q.size()!=0){
            isEven = !isEven;
            int nextLevelSize = 0;
            for(int count=0; count<currentLevelSize; count++){
                TreeNode node = q.poll();
                if(node.left!=null){
                    q.offer(node.left);
                    nextLevelSize++;
                }
                if(node.right!=null){
                    q.offer(node.right);
                    nextLevelSize++;
                }
                if(isEven){
                    EvenOdd.even+=node.val;
                }
                else{
                    EvenOdd.odd+=node.val;
                }
            }
            currentLevelSize=nextLevelSize;
        }
        return (EvenOdd.odd - EvenOdd.even);
    }
}
