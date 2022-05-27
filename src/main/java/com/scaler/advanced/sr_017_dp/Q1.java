package com.scaler.advanced.sr_017_dp;

import java.util.HashMap;

public class Q1 {

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solve(3000));
        //for smaller inputs it is giving correct ans. but after 1970
    }
}

class Solution {
    //HashMap<String,Long> mapi = new HashMap<String,Long>();
    public int solve(int A) {
        HashMap<String,Long> map = new HashMap<String,Long>();
        return (int)(generate(0, 'S', 'S', A, map)%1000000007);
    }

    public long generate(long index, char prev, char prevprev, int size, HashMap<String,Long> map){
        System.out.println("Index is: "+index);
        if(index==size){
            return 1;
        }
        if(size==1) return 3;
        if(size>=2 && index==0) {
            return (
                    generate(2,'S','S',size, map)+
                            generate(2,'P','S',size, map)+
                            generate(2,'T','S',size, map)+
                            generate(2,'S','P',size, map)+
                            generate(2,'T','P',size, map)+
                            generate(2,'S','T',size, map)+
                            generate(2,'P','T',size, map)
            );
        }
        long mod = 1_000_000_007;
        long count = 0;
        if(prev=='S'){
            if(prevprev == 'S'){
                long psIndex = 0;
                long ssIndex = 0;
                long tsIndex = 0;
                if(!map.containsKey(index+1+"S"+"S")){
                    map.put(index+1+"S"+"S",generate(index+1, 'S', 'S', size, map)%mod);
                }
                ssIndex = map.get(index+1+"S"+"S");
                if(!map.containsKey(index+1+"P"+"S")){
                    map.put(index+1+"P"+"S",generate(index+1, 'P', 'S', size, map)%mod);
                }
                psIndex = map.get(index+1+"P"+"S");
                if(!map.containsKey(index+1+"T"+"S")){
                    map.put(index+1+"T"+"S",generate(index+1, 'T', 'S', size, map)%mod);
                }
                tsIndex = map.get(index+1+"T"+"S");
                count = (count +  ssIndex)%mod;
                count = (count +  psIndex)%mod;
                count = (count +  tsIndex)%mod;
            }
            else if(prevprev == 'T'){
                long ssIndex = 0;
                long psIndex = 0;
                if(!map.containsKey(index+1+"S"+"S")){
                    map.put(index+1+"S"+"S",generate(index+1, 'S', 'S', size, map)%mod);
                }
                ssIndex = map.get(index+1+"S"+"S");
                if(!map.containsKey(index+1+"P"+"S")){
                    map.put(index+1+"P"+"S",generate(index+1, 'P', 'S', size, map)%mod);
                }
                psIndex = map.get(index+1+"P"+"S");
                count = (count +  ssIndex)%mod;
                count = (count +  psIndex)%mod;
            }
            else if(prevprev == 'P'){
                long psIndex = 0;
                long ssIndex = 0;
                long tsIndex = 0;
                if(!map.containsKey(index+1+"P"+"S")){
                    map.put(index+1+"P"+"S",generate(index+1, 'P', 'S', size, map)%mod);
                }
                psIndex = map.get(index+1+"P"+"S");
                if(!map.containsKey(index+1+"S"+"S")){
                    map.put(index+1+"S"+"S",generate(index+1, 'S', 'S', size, map)%mod);
                }
                ssIndex = map.get(index+1+"S"+"S");
                if(!map.containsKey(index+1+"T"+"S")){
                    map.put(index+1+"T"+"S",generate(index+1, 'T', 'S', size, map)%mod);
                }
                tsIndex = map.get(index+1+"T"+"S");
                count = (count +  psIndex)%mod;
                count = (count +  ssIndex)%mod;
                count = (count +  tsIndex)%mod;
            }

        }
        else if(prev == 'T'){
            if(prevprev == 'S'){
                long stIndex = 0;
                long ptIndex = 0;
                if(!map.containsKey(index+1+"S"+"T")){
                    map.put(index+1+"S"+"T",generate(index+1, 'S', 'T', size, map)%mod);
                }
                stIndex = map.get(index+1+"S"+"T");
                if(!map.containsKey(index+1+"P"+"T")){
                    map.put(index+1+"P"+"T",generate(index+1, 'P', 'T', size, map)%mod);
                }
                ptIndex = map.get(index+1+"P"+"T");
                count = (count +  stIndex)%mod;
                count = (count +  ptIndex)%mod;
            }
            else if(prevprev == 'P'){
                long ptIndex = 0;
                long stIndex = 0;
                String pt = index+1+"P"+"T";
                if(!map.containsKey(pt)){
                    map.put(pt,generate(index+1, 'P', 'T', size, map)%mod);
                }
                ptIndex = map.get(pt);
                String st = index+1+"S"+"T";
                if(!map.containsKey(st)){
                    map.put(st,generate(index+1, 'S', 'T', size, map)%mod);
                }
                stIndex = map.get(st);
                count = (count +  ptIndex)%mod;
                count = (count +  stIndex)%mod;
            }
        }
        else if(prev == 'P'){
            if(prevprev == 'T'){
                long spIndex = 0;
                String sp = index+1+"S"+"P";
                if(!map.containsKey(sp)){
                    map.put(sp,generate(index+1, 'S', 'P', size,  map)%mod);
                }
                spIndex = map.get(sp);
                count = (count +  spIndex)%mod;
            }
            else if(prevprev == 'S'){
                long spIndex = 0;
                long tpIndex = 0;
                String sp = index+1+"S"+"P";
                if(!map.containsKey(sp)){
                    map.put(sp,generate(index+1, 'S', 'P', size, map)%mod);
                }
                spIndex = map.get(sp);
                String tp = index+1+"T"+"P";
                if(!map.containsKey(tp)){
                    map.put(tp,generate(index+1, 'T', 'P', size, map)%mod);
                }
                tpIndex = map.get(tp);
                count = (count  + spIndex)%mod;
                count = (count  + tpIndex)%mod;
            }
        }
        return count%mod;
    }
}
