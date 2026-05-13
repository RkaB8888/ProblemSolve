import java.io.*;
import java.util.*;

/**
 * @description Sliding Window + Deque
 */

// stones 배열의 길이는 1이상 200,000이하
// stones의 값은 1이상 200,000,000이하 자연수
// k는 1이상 stones 배열의 길이 이하 자연수

// stones 중 최소값만큼은 통과 가능
// k길이의 윈도우 내의 최대값을 추적하며 모든 구간에서 가장 작은 최대값이 정답
class Solution {
    public int solution(int[] stones, int k) {
        Deque<Integer> deq = new ArrayDeque<>();
        int answer = Integer.MAX_VALUE;
        for(int i = 0 ; i < stones.length ; i++) {
            if(!deq.isEmpty()&&deq.peekFirst()<=i-k){
                deq.pollFirst();
            }
            while(!deq.isEmpty()&&stones[deq.peekLast()]<stones[i]) {
                deq.pollLast();
            }
            deq.addLast(i);

            if(i>=k-1) answer = Math.min(stones[deq.peekFirst()], answer);
        }
        return answer;
    }
}