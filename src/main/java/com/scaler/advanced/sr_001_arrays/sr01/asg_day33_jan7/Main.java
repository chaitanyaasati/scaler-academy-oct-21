package com.scaler.advanced.sr_001_arrays.sr01.asg_day33_jan7;

import java.util.ArrayList;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        System.out.println("I ma just changing  here");
        int[][] A = new int[][]
//                {
//                        {1, 0, 0, 1, 0, 1, 1, 0, 1, 1},
//                        {0, 0, 0, 0, 1, 1, 1, 1, 0, 1},
//                        {0, 1, 0, 0, 1, 0, 1, 1, 0, 1},
//                        {1, 1, 0 , 1, 1, 1, 1, 1, 0, 0},
//                        {0, 0, 1, 1, 1, 1, 1, 0, 0, 1},
//                        {0, 1, 0, 1, 1, 0, 1, 1, 1, 0},
//                        {1, 1, 0, 0, 0, 0, 1, 0, 0, 0},
//                        {0, 0, 0, 1, 1, 0, 0, 0, 0, 1},
//        };

        {
            {0, 1, 1},
        {1, 0, 0},
    {1, 0, 0},
        {1, 0, 0},
        {1, 0, 0},
        {1, 1, 1},
        {0, 1, 0}
        };
//                {
//        {0, 0, 1, 1, 1, 0, 1, 0, 1, 1},
//        {1, 1, 1, 1, 0, 0, 0, 1, 0, 1},
//        {1, 0, 0, 0, 1, 1, 1, 1, 1, 0},
//        {0, 1, 1, 1, 1, 0, 1, 0, 0, 1},
//        {1, 0, 1, 0, 0, 1, 0, 1, 1, 1},
//        };
        ArrayList<ArrayList<Integer>> AA = new ArrayList<>();
        for(int[] i: A){
            ArrayList<Integer> temp = new ArrayList<Integer>();
            for(int ii: i) {
                temp.add(ii);
            }
            AA.add(temp);
        }
        Solution s = new Solution();
        System.out.println("finall "+s.solve(AA));
    }
}


class Solution {
    public int solve(ArrayList<ArrayList<Integer>> A) {
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
                System.out.println(width);
                System.out.println("row "+row);
                System.out.println("col "+col);
                System.out.println( (cellList.get(row).get(col).rstindex-cellList.get(row).get(col).lstindex ));
                System.out.println(cellList.get(row).get(col));
                System.out.println("vhhhhh");
                arear=Math.max(arear, width*(cellList.get(row).get(col).rstindex-cellList.get(row).get(col).lstindex-1 ));
            }
        }

        return arear;
    }
}


class CellInfo{
    int left;
    int right;
    int top;
    int bottom;
    int lstindex;
    int rstindex;

    @Override
    public String toString() {
        return "CellInfo{" +
                "left=" + left +
                ", right=" + right +
                ", top=" + top +
                ", bottom=" + bottom +
                ", lstindex=" + lstindex +
                ", rstindex=" + rstindex +
                '}';
    }

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
                    cellList.get(row).get(column).lstindex = -1;
                }
                else {
                    cellList.get(row).get(column).lstindex = indexStack.peek();
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
                    cellList.get(row).get(column).rstindex = A.size();
                }
                else {
                    cellList.get(row).get(column).rstindex = indexStack.peek();
                }
                indexStack.push(row);
            }
        }
    }
}

//[
//        [1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 0, 1]
//        [1, 1, 1, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 1, 0, 1]
//        [1, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0]
//        [1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 0, 0]
//        [1, 0, 1, 0, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1]
//        [0, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0]
//        [1, 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1]
//        [1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1]
//        [0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0]
//        [1, 0, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
//        [1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0]
//        [1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0, 1, 1]
//        [0, 0, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 0]
//        [1, 0, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 0]
//        [1, 0, 1, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 0, 0]
//        [0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1]
//        [1, 1, 1, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1]
//        [1, 0, 1, 1, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 1, 0]
//        [0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1]
//        [0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 1]
//        [1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0]
//        [1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 1, 0]
//        [0, 0, 1, 1, 0, 1, 1, 0, 1, 0, 1, 0, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1]
//        [0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 1]
//        [0, 0, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 1, 1, 1, 0, 0]
//        [1, 0, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 1, 1, 0]
//        [1, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 1, 1, 0, 0, 1, 0, 1, 1, 1, 1]
//        [1, 1, 1, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 1, 1, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0, 0, 1, 1]
//        [0, 0, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 0]
//        ]
