import java.io.*;
import java.util.*;

/**
 * @description 정렬
 */
 
// A와 B를 합치고 정렬한 후 (O(2Nlog2N))
// 작은 것부터 A와 B를 카운팅 하며 B가 나왔을 때 A가 더 많으면 +1
// AABABBAABAABBABABB
class Solution {
    static class Node{
        boolean isA;
        int val;
        public Node(boolean isA, int val){
            this.isA = isA;
            this.val = val;
        }
    }
    public int solution(int[] A, int[] B) {
        Node[] total = new Node[A.length*2];
        int answer = 0;
        for(int i = 0 ; i < A.length ; i++) {
            int idx = i<<1;
            total[idx] = new Node(true,A[i]);
            total[idx+1] = new Node(false,B[i]);
        }
        Arrays.sort(total, (a,b) ->{
            if(a.val == b.val) { // A를 뒤로 보내야 함
                if (a.isA && !b.isA) return 1;
                if (!a.isA && b.isA) return -1;
                return 0;
            } else {
                return Integer.compare(a.val, b.val);
            }
        });
        int aCnt = 0;
        for(int i = 0 ; i < total.length ; i++){
            if(total[i].isA) {
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