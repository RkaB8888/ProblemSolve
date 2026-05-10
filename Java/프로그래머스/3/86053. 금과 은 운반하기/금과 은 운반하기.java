import java.io.*;
import java.util.*;

/**
 * @description Priority Queue
 */
// a,b는 10^9 이하 (요구 금, 은)
// 배열의 길이 g, s, w, t는 1 이상 10^5 이하 (금, 은, 질량, 편도 시간)
// g와 s는 0 이상 10^9 이하
// w는 1 이상 10^2 이하
// t는 1 이상 10^5 이하
// a와 b는 무조건 다 채울 수 있다는 보장

// PQ로 각 도시에서 전달하는 트럭을 도착 시간 오름차순으로 정렬. {출발 도시, 트럭 질량, 도착 시간}
// PQ를 2개로 해서 금과 은의 도착을 따로 할까? 
// 필요한 금과 은의 비율로 한번에 전달할까?
// 트럭을 받을 때 금과 은의 비율을 정할까? -> 트럭이 도착했을 때, 출발 도시의 금과 은을 원하는 만큼 차감하는 게 좋을 듯.
// -> 트럭이 도착했을 때 어떤 것을 먼저 차감하는지에 따라 결과가 달라짐 -> 그래서 안 될 듯.

// 이분탐색으로 특정 시간 내에 금과 은을 모두 차감할 수 있는지 확인하는 방법
// 1. 도시를 순회하면서 해당 도시에서 전달 가능한 질량
// 2. 모든 도시에서 금만 전달할 경우 특정 시간 내에 가능한지
// 3. 모든 도시에서 은만 전달할 경우 특정 시간 내에 가능한지
// 위 세 경우가 모두 참이면 어떤 방법으로 가능한 듯?
// 최대 시간은 트럭 질량이 1이고 a+b가 2*10^9일 때 걸리는 시간이 2*10^5 -> 4*10^14
// 최소 시간은 0으로 하면 될 듯
class Solution {
    private boolean isPossible(long time, int a, int b, int[] g, int[] s, int[] w, int[] t){
        long gSum = 0;
        long sSum = 0;
        long wSum = 0;
        for(int i = 0 ; i < t.length ; i++) {
            long gi = g[i];
            long si = s[i];
            long wi = w[i];
            long ti = 2L*t[i];
            long cnt = time/ti;
            if(time%ti>=t[i]) cnt++;
            wSum += Math.min(wi*cnt,gi+si);
            gSum += Math.min(wi*cnt,gi);
            sSum += Math.min(wi*cnt,si);
        }
        if(gSum >= a && sSum >= b && wSum >= a+b) return true;
        return false;
    }

    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long left = 0;
        long right = (long)(4*Math.pow(10,14));
        long answer = -1;
        while(left<=right) {
            long mid = (left+right)>>>1;
            if(isPossible(mid, a, b, g, s, w, t)){ // 가능하면 시간을 더 줄여본다.
                right = mid-1;
                answer = mid;
            }else { // 불가능하면 시간을 더 늘여본다.
                left = mid+1;
            }
        }
        return answer;
    }
}