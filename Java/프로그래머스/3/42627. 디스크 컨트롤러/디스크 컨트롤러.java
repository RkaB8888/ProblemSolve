import java.io.*;
import java.util.*;

/**
 * @description 이 클래스에 대한 동작 설명
 */
 
// 우선 순위에 따라 각 작업들을 수행할 때 요청부터 작업 종료까지의 걸린 시간 평균 반환
// jobs의 길이는 1이상 500이하
// 작업이 요청되는 시간은 0이상 1000이하
// 작업 소요 시간은 1이상 1000이하
// 우선순위는 작업의 소요시간이 짧은 것 > 작업의 요청 시각이 빠른 것 > 작업의 번호가 작은 것

// 우선순위 큐를 활용한다.
// 우선 jobs를 요청시간 오름차순으로 정렬한 뒤에 (nlogn)
// 시작이 0인 것만 우선순위 큐에 넣는다. (nlogn)
// 큐에서 뽑은 작업이 종료되는 시간을 기준으로 정렬된 jabs를 더 뽑아넣는다.
// 다시 큐에서 작업하나를 뽑고 반복하며 각 작업의 걸린 시간을 누적한다.
// 누적값을 jabs의 길이로 나눠서 반환한다.

// 시간복잡도는 nlogn으로 보임
class Solution {
    public int solution(int[][] jobs) {
        // 0:작업번호, 1:요청시간, 2:소요시간
        int n = jobs.length;
        int[][] newJobs = new int[n][3];
        for(int i = 0 ; i < n ; i++) {
            newJobs[i][0] = i;
            newJobs[i][1] = jobs[i][0];
            newJobs[i][2] = jobs[i][1];
        }
        Arrays.sort(newJobs, (a,b)->{
            if (a[1]==b[1]) {
                if (a[2]==b[2]) return Integer.compare(a[0], b[0]);
                return Integer.compare(a[2], b[2]);
            }
            return Integer.compare(a[1], b[1]);
        });
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->{
            if(a[2]==b[2]) {
                if(a[1]==b[1]) return Integer.compare(a[0], b[0]);
                else return Integer.compare(a[1], b[1]);
            }
            return Integer.compare(a[2], b[2]);
        });
        int sum = 0;
        int idx = 0;
        int cnt = 0;
        int curTime = newJobs[0][1];
        while(cnt < n) {
            while(idx<n && newJobs[idx][1] <= curTime) {
                pq.add(newJobs[idx]);
                idx++;
            }
            if(pq.isEmpty()) {
                curTime = newJobs[idx][1];
            }
            else {
                int[] curJob = pq.poll();
                curTime += curJob[2];
                sum += curTime - curJob[1];
                cnt++;
            }
        }
        
        return sum/n;
    }
}