import java.io.*;
import java.util.*;

/**
 * @description 재귀
 * @performance 메모리: ? KB, 동작시간: ? ms
 */
 
// 하노이의 탑은 시작 원판의 갯수에 따라 옮기는 방법이 정해져 있음
// 재귀 방식으로 원판 하나씩 목적지까지 옮김
class Solution {
    
    private List<int[]> answerList = new ArrayList<int[]>();
    
    private void hanoi(int n, int s, int g, int m){ // 크기, 시작, 골, 중간
        if(n == 1) {
            answerList.add(new int[] {s, g});
            return;
        }
        hanoi(n-1,s, m, g);
        answerList.add(new int[] {s, g});
        hanoi(n-1, m, g, s);
    }
    public int[][] solution(int n) {
        hanoi(n,1,3,2);
        int[][] answer = new int[answerList.size()][2];
        for(int i = 0 ; i < answerList.size() ; i++) {
            answer[i] = answerList.get(i);
        }
        return answer;
    }
}