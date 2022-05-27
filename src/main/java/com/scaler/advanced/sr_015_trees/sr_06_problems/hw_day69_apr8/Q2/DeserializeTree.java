package com.scaler.advanced.sr_015_trees.sr_06_problems.hw_day69_apr8.Q2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class DeserializeTree{
    public static TreeNode solve(ArrayList<Integer> A) {
        return generate(A);
    }

    public static TreeNode generate(ArrayList<Integer> A){
        int level=0;
        int nextLevelCount=0;
        TreeNode root = null;
        Queue<TreeNode> q = new LinkedList<>();
        if(A.size()==-1){
            return null;
        }
        else{
            root = new TreeNode(A.get(0));
            nextLevelCount = 2;
            q.offer(root);
        }
        level = nextLevelCount;
        TreeNode node = null;
        int index = 1;
        while(index<A.size()){
            node = q.poll();
            node.left=null;
            node.right=null;
            if(A.get(index)!=-1){
                TreeNode nodel = new TreeNode(A.get(index));
                node.left = nodel;
                q.offer(nodel);
                nextLevelCount+=2;
            }
            level--;
            index++;
            if(A.get(index)!=-1){
                TreeNode noder = new TreeNode(A.get(index));
                node.right = noder;
                q.offer(noder);
                nextLevelCount+=2;
            }
            level--;
            index++;
            if(level==0){
                level=nextLevelCount;
            }
        }
        return root;
    }
}
