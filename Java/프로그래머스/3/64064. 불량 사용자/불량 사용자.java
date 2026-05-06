import java.io.*;
import java.util.*;

/**
 * @description DFS + HashSet
 */
// user_id와 banned_id는 최대 8, 각 길이는 최대 8
// 비교 아이디 뽑는데 N*M, 대조하는데 Len
// 중복 아이디 경우의 수 N! 따라서 시간 복잡도: O(N*M*Len + N!)
// banned_id의 각 요소가 user_id에서 어떤 것이 가능한지 List에 담고, 마지막에 dfs로 가능한 경우를 탐색함
// 순서는 상관없기 때문에 8자리 비트를 visited로 사용하여 중복되는 경우를 걸러냄
class Solution {
    private boolean check(String A, String B){ // A에 *가 있음
        if(A.length()!=B.length()) return false;
        for (int i = 0; i < A.length(); i++) {
            if (A.charAt(i) == '*') continue;
            if (A.charAt(i) != B.charAt(i)) return false;
        }
        return true;
    }
    private int calc(List<List<Integer>> total, int depth, int len, Set<Integer> visited, int bit){
        if(depth == len) {
            if(!visited.contains(bit)) {
                visited.add(bit);
                return 1;
            }
            return 0;
        }
        int result = 0;
        for(Integer i : total.get(depth)) {
            if(((bit>>i)&1)==1) continue;
            result+=calc(total, depth+1, len, visited, bit|(1<<i));
        }
        return result;
    }
    public int solution(String[] user_id, String[] banned_id) {
        List<List<Integer>> list = new ArrayList<>();
        
        for(int i = 0 ; i < banned_id.length ; i++) {
            List<Integer> temp = new ArrayList<>();
            for(int j = 0 ; j < user_id.length ; j++) {
                if(check(banned_id[i],user_id[j])) { // 가능한지 확인
                    temp.add(j);
                }
            }
            list.add(temp);
        }
        return calc(list, 0,list.size(), new HashSet<>(), 0); // 경우의 수 계산
    }
}