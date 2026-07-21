import java.io.*;
import java.util.*;

/**
 * @description 누적합
 */
 
// 구간의 크기가 주어졌을 때 겹치는 구간을 전부 더했을 때 가장 큰 값을 반환
// 모든 시간은 00:00:01 이상 99:59:59 이하이다. -> 초로 환산하면 360,000초
// 각 재생구간 logs는 1이상 300,000 이하의 길이

// 배열의 길이를 360000으로 잡고 logs에 해당하는 시간에 +1과 -1을 넣고 누적합을 한다면?
// 그리고 한번 더 누적합해서 360000배열을 한번 순회하며 adv_time의 합계를 계산?
// 그러면 갯수만큼 곱해져셔 더해진 시간(초)가 나오는데 이를 HH:MM:SS로 변환해사 반환한다?
class Solution {
    private int conv2sec(String time){
        int result = 0;

        result+=Integer.parseInt(time.substring(0,2))*3600;
        result+=Integer.parseInt(time.substring(3,5))*60;
        result+=Integer.parseInt(time.substring(6,8));

        return result;
    }
    private String conv2time(int sec){
        StringBuilder result = new StringBuilder();

        int h = sec/3600;
        sec%=3600;
        int m = sec/60;
        sec%=60;
        int s = sec;

        if(h<10) result.append('0').append(h).append(':');
        else result.append(h).append(':');
        if(m<10) result.append('0').append(m).append(':');
        else result.append(m).append(':');
        if(s<10) result.append('0').append(s);
        else result.append(s);

        return result.toString();
    }
    public String solution(String play_time, String adv_time, String[] logs) {
        int pt = conv2sec(play_time);
        long[] sum = new long[pt+1];
        for(String log : logs) {
            int start = conv2sec(log.substring(0,8));
            int end = conv2sec(log.substring(9,17));
            sum[start]++;
            sum[end]--;
        }
        for(int i = 1 ; i <= pt ; i++) { // 구간 별 시청자 수가 됨
            sum[i] += sum[i-1];
        }
        for(int i = 1 ; i <= pt ; i++) { // 구간 별 누적 시청 시간이 됨
            sum[i] += sum[i-1];
        }
        int partSec = conv2sec(adv_time);
        long big = sum[partSec-1];
        int answer = 0;
        for(int i = 1 ; i <= pt-partSec ; i++) {
            long time = sum[partSec+i-1] - sum[i-1];
            if(time > big) {
                answer = i;
                big = time;
            }
        }
        return conv2time(answer);
    }
    public static void main(String[] args) {
        Solution sol = new Solution();
        String play_time = "02:03:55";
        String adv_time = "00:14:15";
        String[] logs = {"01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"};
        System.out.println(sol.solution(play_time, adv_time, logs));
    }
}