package com.scaler.advanced.sr_017_dp.sr_02.hw_day77_may4.Q2;

public class Solution {
    int max=0;

    public int maximalRectangle(int[][] A) {
        Data[][] strg = new Data[A.length][A[0].length];
        strg[0][0]=calcMaxArea(A,0,0,strg);
        printStrg(strg);
        return this.max;
    }

    public void printStrg(Data[][] strg){
        for(int row=0; row<strg.length; row++){
            for(int column=0; column<strg[0].length; column++){
                System.out.print("||row: "+row+" column: "+column+" "+strg[row][column]+"||");
            }
            System.out.println(" ");
        }
    }

    public Data calcMaxArea(int[][] A, int row, int column, Data[][] strg){
        if((row+1)==A.length){
            return new Data(0,0);
        }
        if((column+1)==A[0].length){
            return new Data(0,0);
        }
        if(column>=A[0].length){
            return new Data(0,0);
        }
        if(row>=A.length){
            return new Data(0,0);
        }
        if(strg[row][column+1]==null){
            strg[row][column+1] = calcMaxArea(A, row, column+1, strg);
        }
        Data right = strg[row][column+1];
        if(strg[row+1][column]==null){
            strg[row+1][column] = calcMaxArea(A, row+1, column, strg);
        }
        Data down = strg[row+1][column];
        if(strg[row+1][column+1]==null){
            strg[row+1][column+1] = calcMaxArea(A, row+1, column+1, strg);
        }
        Data diag = strg[row+1][column+1];
        if(A[row][column]==0){
            return new Data(0,0);
        }
        int length = 1;
        int breadth = 1;
        int area = 1;
        if(area<(1+down.length)){
            breadth=1;
            length=1+down.length;
            area=1+down.length;
        }
        if(area<(1+right.breadth)){
            breadth=1+right.breadth;
            length=1;
            area=1+right.breadth;
        }
        int dlength = 1+Math.min(down.length,diag.length);
        int dbreadth = 1+Math.min(right.breadth,diag.breadth);
        int darea = dlength*dbreadth;
        if(darea>area){
            breadth = dbreadth;
            length = dlength;
            area = darea;
        }
        this.max=Math.max(this.max,area);
        return new Data(length,breadth);
    }
}

class Data{
    int length;
    int breadth;
    int max=0;

    @Override
    public String toString() {
        return "Data{" +
                "length=" + length +
                ", breadth=" + breadth +
                '}';
    }

    Data(int length, int breadth){
        this.breadth = breadth;
        this.length = length;
    }
}
