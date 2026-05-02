import java.io.*;
import java.util.*;

/**
 * @description 재귀 DFS
 */
// x3 또는 +1을 하여 정수 n이 되는 경우의 수를 반환하면 된다.
// 단, x3과 +1을 할 수 있는 조건이 따로 존재한다.
// *가 들어가면 이후에 무조건 +2가 존재해야 함. 따라서 *의 갯수에 따라 +의 갯수가 정해짐.
// 임의의 숫자 x에
// 1. (*3을 한다.) 2. (+를 할 수 있다면 +1을 한다.)
// 위 두 선택지 중 하나를 선택하고 큐에 담는다. -> BFS
class Solution {
    public int dfs(int n, int cnt) {
        if(n<3 || Math.pow(3, cnt>>1)>n) return 0;
        if(n==3) {
            if(cnt==2) return 1;
            return 0; // cnt가 1인 경우
        }
        int result = 0;
        if(n%3==0 && cnt >= 2) {
            result+=dfs(n/3,cnt-2);
        }
        result+=dfs(n-1,cnt+1);
        return result;
    }
    // n은 최소 5, 최대 2^31-1
    public int solution(int n) {
        return dfs(n,0);
    }
     
}