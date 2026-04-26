import java.io.*;
import java.util.*;

/**
 * @description 사칙연산
 */
 
// n은 최대 10,000 s는 최대 100,000,000
// answer의 길이는 n, 각 요소는 s/n이 들어있고 s%n개의 요소에 1씩 더해주면 됨
class Solution {
    public int[] solution(int n, int s) {
        if(n>s) return new int[] {-1};
        int val = s/n;
        int cnt = s%n;
        int[] answer = new int[n];
        Arrays.fill(answer, val);
        if(cnt!=0)
            Arrays.fill(answer,n-cnt,n,val+1);
        return answer;
    }
}