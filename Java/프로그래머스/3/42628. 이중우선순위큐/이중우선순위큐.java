import java.io.*;
import java.util.*;

/**
 * @description TreeMap
 */
 
class Solution {
    public int[] solution(String[] operations) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(String s : operations) {
            char[] arr = s.toCharArray();
            if(arr[0]=='I') {
                int i = 2;
                int num = 0;
                int sign = 1;
                if(arr[i]=='-') {
                    sign = -1;
                    i++;
                }
                for(; i < arr.length ; i++) {
                    num*=10;
                    num+=arr[i]-'0';
                }
                num = sign*num;
                map.put(num, map.getOrDefault(num,0)+1);
            } else{
                if(map.isEmpty()) continue;
                int val;
                if(arr[2]=='-') {
                    val = map.firstKey();
                }else {
                    val = map.lastKey();
                }
                int cnt = map.get(val);
                if(cnt==1){
                    map.remove(val);
                }else {
                    map.put(val,cnt-1);
                }
            }
        }
        if(map.isEmpty()) return new int[] {0,0};
        return new int[] {map.lastKey(),map.firstKey()};
    }
}