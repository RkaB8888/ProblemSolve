import java.io.*;
import java.util.*;

/**
 * @description 이 클래스에 대한 동작 설명
 */
 
// n: 셔틀 운행 횟수 0 초과 10 이하
// t: 셔틀 운행 간격 0 초과 60 이하 [분]
// m: 셔틀 당 인원 수 0 초과 45 이하
// timetable: 크루 도착 시간 00:01~23:59

// 최대한 늦게 출근하기 위한 도착 시간 출력
// 즉, 마지막 셔틀에 탑승할 수 있는 마지막 시간을 구한다.
class Solution {
    String[] TIME;
    private void makeTimeArray(int n, int t){
        this.TIME = new String[n];
        int HH = 9;
        int MM = 0;
        
        for(int i = 0 ; i < n ; i++) {
            StringBuilder sb = new StringBuilder();
            TIME[i] = sb.append(HH/10).append(HH%10).append(':').append(MM/10).append(MM%10).toString();
            MM+=t;
            while(MM>59) {
                MM-=60;
                HH++;
            }
        }
    }
    private String isSmall(String s) {
        char[] tmp = s.toCharArray();
        int m = 0;
        m+=(tmp[0]-'0')*600;
        m+=(tmp[1]-'0')*60;
        m+=(tmp[3]-'0')*10;
        m+=(tmp[4]-'0')-1;
        return String.format("%02d:%02d",m/60,m%60);
    }
    public String solution(int n, int t, int m, String[] timetable) {
        makeTimeArray(n,t);
        Arrays.sort(timetable);

        int startIdx = 0, endIdx = 0;
        for(int i = 0 ; i < n ; i++) {
            startIdx = endIdx;
            String curTime = TIME[i];
            for(; endIdx-startIdx < m && endIdx < timetable.length && timetable[endIdx].compareTo(curTime)<=0 ; endIdx++);
        }
        if(endIdx - startIdx < m) return TIME[n-1]; // 막차는 인원 부족
        endIdx--;
        return isSmall(timetable[endIdx]);
    }
    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 2;
        int t = 10;
        int m = 2;
        String[] timetable = {"09:10", "09:09", "08:00"};
        System.out.println(sol.solution(n,t,m,timetable));
    }
}