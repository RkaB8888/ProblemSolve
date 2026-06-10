import java.io.*;
import java.util.*;

/**
 * @description 정렬 투포인터
 */
 
// A와 B를 합치고 정렬한 후 (O(2Nlog2N))
// 작은 것부터 A와 B를 카운팅 하며 B가 나왔을 때 A가 더 많으면 +1
// AABABBAABAABBABABB
class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;

        Arrays.sort(A);
        Arrays.sort(B);
        
        int aIdx = 0;
        int bIdx = 0;
        
        while (bIdx < B.length) {
            if (B[bIdx] > A[aIdx]) {
                answer++;
                aIdx++;
                bIdx++;
            } 
            else {
                bIdx++;
            }
        }
        
        return answer;
    }
}