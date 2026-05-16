import java.io.*;
import java.util.*;

/**
 * @description 이분 탐색 + PQ
 */
 
// works의 길이는 1이상 20,000이하
// works의 값은 50,000이하 자연수
// n은 1,000,000이하 자연수

// n시간 동안 작업량 1씩 처리하며 남은 각 작업량을 제곱해서 합했을 때 최소가 되어야 함.
// 즉, 작업량 그래프에서 윗 부분을 잘라냈을 때 결과값이 최소가 되어야 함
// 이분탐색으로 모든 작업량이 mid 이하로 맞출 수 있는지 검토하고 가능하다면 answer에 갱신
class Solution {

    private boolean possible(int limit, int n, int[] works) {
        int req = 0;
        for(int work : works) {
            if(work>limit) {
                req+=(work-limit);
            }
        }
        return req<=n;
    }

    public long solution(int n, int[] works) {
        long sum = 0;
        int maxWork = 0;
        for(int work : works) {
            sum+=work;
            if(maxWork<work) maxWork = work;
        }
        if(sum<=n) return 0L;

        int left = 1, right = maxWork, limit = maxWork;
        while(left<=right) {
            int mid = (left+right)>>>1;
            if(possible(mid, n, works)) {
                limit = mid;
                right = mid - 1;
            }else {
                left = mid + 1;
            }
        }
        for(int i = 0 ; i < works.length ; i++) {
            if(works[i] > limit) {
                n -= (works[i]-limit);
                works[i] = limit;
            }
        }
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int work : works) {
            pq.add(work);
        }
        while(n>0) {
            int cur = pq.poll();
            pq.add(cur-1);
            n--;
        }

        long result = 0;
        for(long work : pq) {
            result += work*work;
        }
        return result;
    }
    // public static void main(String[] args) {
    //     Solution sol = new Solution();
    //     System.out.println(sol.solution(4, new int[] {4,3,3}));
    // }
}