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
        int[][] total = new int[A.length*2][2];
        int answer = 0;
        for(int i = 0 ; i < A.length ; i++) {
            int idx = i<<1;
            total[idx][0] = 0;
            total[idx][1] = A[i];
            total[idx+1][0] = 1;
            total[idx+1][1] = B[i];
        }
        Arrays.sort(total, (a,b) ->{
            if(a[1] == b[1]) { // A를 뒤로 보내야 함
                if (a[0]==0 && b[0]==1) return 1;
                if (a[0]==1 && b[0]==0) return -1;
                return 0;
            } else {
                return Integer.compare(a[1], b[1]);
            }
        });
        int aCnt = 0;
        for(int i = 0 ; i < total.length ; i++){
            if(total[i][0]==0) {
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