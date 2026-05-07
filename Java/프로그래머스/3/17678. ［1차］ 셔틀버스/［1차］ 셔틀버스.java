import java.io.*;
import java.util.*;

/**
 * @description 그리디
 */
 
// n: 셔틀 운행 횟수 0 초과 10 이하
// t: 셔틀 운행 간격 0 초과 60 이하 [분]
// m: 셔틀 당 인원 수 0 초과 45 이하
// timetable: 크루 도착 시간 00:01~23:59

// 최대한 늦게 출근하기 위한 도착 시간 출력
// 즉, 마지막 셔틀에 탑승할 수 있는 마지막 시간을 구한다.
class Solution {

    private int parseTime(String time) {
        return (time.charAt(0) - '0') * 600 +
               (time.charAt(1) - '0') * 60 +
               (time.charAt(3) - '0') * 10 +
               (time.charAt(4) - '0');
    }

    private String formatTime(int time) {
        return String.format("%02d:%02d", time / 60, time % 60);
    }

    public String solution(int n, int t, int m, String[] timetable) {
        
        int[] times = new int[timetable.length];
        for(int i = 0 ; i < timetable.length ; i++) {
            times[i] = parseTime(timetable[i]);
        }
        Arrays.sort(times);

        int busTime = parseTime("09:00");
        int timeIdx = 0;
        int endTime = 0;

        for(int i = 0 ; i < n ; i++) {
            int cnt = 0;
            while(cnt < m && timeIdx < times.length && times[timeIdx] <= busTime) {
                endTime = times[timeIdx];
                timeIdx++;
                cnt++;
            }
            if(i==n-1) {
                if(cnt==m) return formatTime(endTime-1);
                else return formatTime(busTime);
            }
            busTime+=t;
        }
        return "";
    }
}