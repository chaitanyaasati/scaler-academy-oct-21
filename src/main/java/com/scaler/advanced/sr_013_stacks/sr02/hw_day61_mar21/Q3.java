package com.scaler.advanced.sr_013_stacks.sr02.hw_day61_mar21;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class Q3 {
    public static void main(String[] args) {
        int[] ss = { 7569780, 6509094, 9066928, 9978215, 9990289, 500953, 5829073, 2862108, 2983492, 8356802 };
        Solution s = new Solution();
        ArrayList<Integer> A = new ArrayList<Integer>();
        for(int ii : ss){
            A.add(ii);
        }
        s.solve(A);
    }
}

class Solution {
    public int solve(ArrayList<Integer> A) {
        Stack<Integer> stack = new Stack<Integer>();
        ArrayList<Integer> left = new ArrayList<Integer>();
        ArrayList<Integer> right = new ArrayList<Integer>();
        stack.push(A.get(0));
        left.add(A.get(0));
        for(int i=1; i<A.size(); i++){
            if(stack.peek()>A.get(i)){
                left.add(stack.peek());
                stack.push(A.get(i));
            }
            else{
                while(!stack.isEmpty() && stack.peek()<=A.get(i)){
                    stack.pop();
                }
                if(stack.isEmpty()){
                    left.add(A.get(i));
                }
                else{
                    left.add(stack.peek());
                }
                stack.push(A.get(i));
            }
        }
        stack.clear();
        stack.push(A.get(A.size()-1));
        right.add(A.get(A.size()-1));
        for(int i=A.size()-2; i>=0; i--){
            if(stack.peek()>A.get(i)){
                right.add(stack.peek());
                stack.push(A.get(i));
            }
            else{
                while(!stack.isEmpty() && stack.peek()<=A.get(i)){
                    stack.pop();
                }
                if(stack.isEmpty()){
                    right.add(A.get(i));
                }
                else{
                    right.add(stack.peek());
                }
                stack.push(A.get(i));
            }
        }
        System.out.println(A);
        System.out.println(left);
        Collections.reverse(right);
        System.out.println(right);
        int xorVal = 0;
        for(int i=0; i<A.size() ; i++){
            xorVal = Math.max(xorVal,left.get(i)^A.get(i));
            xorVal = Math.max(xorVal,right.get(i)^A.get(i));
            if(i!=(A.size()-1)){
                xorVal = Math.max(xorVal, A.get(i) ^ A.get(i + 1));
            }
        }
        return xorVal;
    }
}
