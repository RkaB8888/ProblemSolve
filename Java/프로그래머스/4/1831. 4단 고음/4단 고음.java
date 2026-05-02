import java.io.*;
import java.util.*;

/**
 * @description 재귀 DFS
 */
class Solution {
    public int dfs(int n, int cnt) {
        if(n<3 || Math.pow(3, cnt>>1)>n) return 0;
        if(n==3) {
            if(cnt==2) return 1;
            return 0; // cnt가 1인 경우
        }
        int result = 0;
        int last = n%3;
        if(last==0) {
            if(cnt>=2) {
                result+=dfs(n/3,cnt-2);
            }
            result+=dfs(n-3,cnt+3);
        } else {
            result+=dfs(n-last,cnt+last);
        }
        return result;
    }
    // n은 최소 5, 최대 2^31-1
    public int solution(int n) {
        return dfs(n,0);
    }
    //  public static void main(String[] args) throws Exception{
    //     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    //     Solution sol = new Solution();

    //     int input = Integer.parseInt(br.readLine());
    //     int result = sol.solution(input);

    //     System.out.println(result);
    //  }
}