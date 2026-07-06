import java.io.*;
import java.util.*;

/**
 * @description 이분 탐색
 */
 
// n: 입국심사를 기다리는 사람 수 
// 1이상 1,000,000,000이하
// times: 각 심사관이 한 명을 심사하는데 걸리는 시간이 담긴 배열 
// 1이상 1,000,000,000이하
// times 길이는 1이상 100,000이하

// 총 걸리는 시간을 기준으로 이분 탐색을 한다.
// 제한사항을 통해 걸릴 수 있는 최소 시간은 1분, 최대 시간은 1,000,000,000명 * 1,000,000,000분
// 이분 탐색의 기준 시간 내에 각 심사관이 심사할 수 있는 모든 인원을 합했을 때 n보다 크면 가능한 것으로 판단
class Solution {
    public long solution(int n, int[] times) {
        Arrays.sort(times);
        long left = 1L;
        long right = (long) times[times.length - 1] * n;
        long answer = right;
        while(left<=right) {
            long mid = (left+right)>>1;
            long cnt = 0;
            for(int time : times) {
                cnt+=mid/time;
                if(cnt>=n) break;
            }
            if(cnt>=n) {
                answer = mid;
                right = mid-1;
            } else {
                left = mid+1;
            }
        }
        return answer;
    }
}