import java.io.*;
import java.util.*;

/**
 * @description 정렬 그리디
 */
 
// A와 B를 합치고 정렬한 후 (O(2Nlog2N))
// 작은 것부터 A와 B를 카운팅 하며 B가 나왔을 때 A가 더 많으면 +1
// AABABBAABAABBABABB
class Solution {

    public int solution(int[] A, int[] B) {
        int N = A.length;
        int[] total = new int[N*2];
        int answer = 0;

        for(int i = 0 ; i < N ; i++) {
            total[i*2] = (A[i]<<1)|1;
            total[i*2+1] = (B[i]<<1)|0;
        }
        Arrays.sort(total);

        int aCnt = 0;
        for(int i = 0 ; i < total.length ; i++){
            if((total[i]&1)==1) {
                aCnt++;
            }else {
                if(aCnt>0) {
                    answer++;
                    aCnt--;
                }
            }
        }
        return answer;
    }
}