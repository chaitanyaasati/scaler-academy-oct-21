package com.scaler.advanced.sr_013_stacks.sr01.hw_day60_mar18;

/*
Q4. Maximum Rectangle
Given a 2D binary matrix of integers A containing 0's and 1's of size N x M.
Find the largest rectangle containing only 1's and return its area.
Note: Rows are numbered from top to bottom and columns are numbered from left to right.

Input Format
The only argument given is the integer matrix A.

Output Format
Return the area of the largest rectangle containing only 1's.

Constraints
1 <= N, M <= 1000
0 <= A[i] <= 1

For Example
Input 1:
    A = [   [0, 0, 1]
            [0, 1, 1]
            [1, 1, 1]   ]
Output 1:
    4

Input 2:
    A = [   [0, 1, 0, 1]
            [1, 0, 1, 0]    ]
Output 2:
    1
 */


import java.util.ArrayList;
import java.util.Stack;

public class Q4_MaximumRectangle {
    public static void main(String[] args) {
        int[][] input = new int[][]{
                {0, 0, 1},
                {0, 1, 1},
                {1, 1, 1}
        };
        ArrayList<ArrayList<Integer>> arr = new ArrayList<ArrayList<Integer>>();
        for(int[] row: input){
            ArrayList<Integer> list = new ArrayList<>();
            for(int value: row){
                list.add(value);
            }
            arr.add(list);
        }
        System.out.println("ANSWER=" + Solution.solve(arr));
    }
}


class Solution {
    public static int solve(ArrayList<ArrayList<Integer>> A) {
        ArrayList<ArrayList<CellInfo>> cellList = new ArrayList<>();
        for(int row=0; row<A.size(); row++){
            ArrayList<CellInfo> list = new ArrayList<CellInfo>();
            for(int column=0; column<A.get(0).size(); column++){
                CellInfo cell = new CellInfo();
                list.add(cell);
            }
            cellList.add(list);
        }

        for(int row=0; row<A.size(); row++){
            int right = A.get(0).size();
            for(int column=A.get(0).size()-1; column>=0; column--){
                CellInfo cell = cellList.get(row).get(column);
                if(A.get(row).get(column).equals(0)){
                    right = column;
                    cell.right = right;
                }
                else{
                    cell.right = right;
                }
            }
        }

        CellInfo.LSE(cellList,A);
        int arear=0;
        for(int row=0; row<A.size(); row++){
            for(int col=0; col<A.get(0).size(); col++){
                int width = cellList.get(row).get(col).right-col;
                arear=Math.max(arear, width*(cellList.get(row).get(col).bottomSmallestIndex-cellList.get(row).get(col).topSmallestIndex-1 ));
            }
        }
        return arear;
    }
}

class CellInfo{
    int right;
    int topSmallestIndex;
    int bottomSmallestIndex;

    public static void LSE(ArrayList<ArrayList<CellInfo>> cellList, ArrayList<ArrayList<Integer>> A ) {
        Stack<Integer> indexStack = new Stack<Integer>();
        for(int column = 0; column<A.get(0).size(); column++){
            for(int row=0; row<A.size(); row++){
                int ele = A.get(row).get(column);
                int width = cellList.get(row).get(column).right - column;
                if(ele==0){
                    indexStack.clear();
                    indexStack.push(row);
                    continue;
                }
                while(!indexStack.empty() && (width <= ( cellList.get(indexStack.peek()).get(column).right-column ) )){
                    indexStack.pop();
                }
                if(indexStack.empty()){
                    cellList.get(row).get(column).topSmallestIndex = -1;
                }
                else {
                    cellList.get(row).get(column).topSmallestIndex = indexStack.peek();
                }
                indexStack.push(row);
            }

            indexStack.clear();
            for(int row=A.size()-1; row>=0; row--){
                int ele = A.get(row).get(column);
                int width = cellList.get(row).get(column).right - column;
                if(ele==0){
                    indexStack.clear();
                    indexStack.push(row);
                    continue;
                }
                while(!indexStack.empty() && (width <= ( cellList.get(indexStack.peek()).get(column).right-column ) ) ){
                    indexStack.pop();
                }
                if(indexStack.empty()){
                    cellList.get(row).get(column).bottomSmallestIndex = A.size();
                }
                else {
                    cellList.get(row).get(column).bottomSmallestIndex = indexStack.peek();
                }
                indexStack.push(row);
            }
        }
    }
}

/*
Solution provided
public class Solution {
	public int maximalRectangle(ArrayList<ArrayList<Integer>> A) {

	    if (A == null || A.size() == 0)
	        return 0;

	    int m, n;
	    int i, j;

	    m = A.size();
	    n = A.get(0).size();

	    int count[][];
	    count = new int[m][n];

	    for (i = 0; i < m; i++) {
	        for (j = 0; j < n; j++) {
	            if (i == 0) {
	                count[i][j] = A.get(i).get(j);
	            } else {
	                count[i][j] = A.get(i).get(j) == 0 ? 0 : count[i - 1][j] + 1;
	            }
	        }
	    }

	    int res = 0;

	    for (i = 0; i < m; i++) {
	        res = Math.max(res, maxRect(count[i]));
	    }

	    return res;

	}

	public int maxRect(int [] histogram) {
	    int n;
	    int res = 0;
	    int prevMin[];
	    int nextMin[];
	    int num;
	    Stack<Integer> stack;

	    n = histogram.length;
	    prevMin = new int[n];
	    nextMin = new int[n];

	    stack = new Stack<Integer>();

	    prevMin[0] = -1;
	    stack.push(0);
	    for (int i = 1; i < n; i++) {
	        num = histogram[i];
	        while (!stack.isEmpty() && num <= histogram[stack.peek()]) {
	            stack.pop();
	        }
	        prevMin[i] = -1;
	        if (!stack.isEmpty()) {
	            prevMin[i] = stack.peek();
	        }
	        stack.push(i);
	    }


	    nextMin[n - 1] = n;
	    stack.clear();
	    stack.push(n - 1);
	    for (int i = n - 2; i >= 0; i--) {
	        num = histogram[i];
	        while (!stack.isEmpty() && num <= histogram[stack.peek()]) {
	            stack.pop();
	        }
	        nextMin[i] = n;
	        if (!stack.isEmpty())
	            nextMin[i] = stack.peek();
	        stack.push(i);
	    }

	    for (int i = 0; i < n; i++) {
	        int left = prevMin[i] + 1;
	        int right = nextMin[i] - 1;
	        res = Math.max(res, histogram[i] * (right - left + 1));
	    }

	    return res;
	}

}
 */