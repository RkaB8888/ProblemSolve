import java.io.*;
import java.util.*;

/**
 * @description 이 클래스에 대한 동작 설명
 */
// 제재 아이디가 user_id가 될 수 있는 경우의 수 끼리 곱해서 반환
// user_id와 banned_id는 최대 8, 각 길이는 최대 8
// 비교 아이디 뽑는데 N^2, 대조하는데 Len
// 즉, 시간 복잡도는 O(N^2*len) -> 최대 512
// banned_id의 각 요소가 user_id에서 어떤 것이 가능한지 List에 담고, 마지막에 dfs로 가능한 경우를 탐색함
// 순서는 상관없기 때문에 8자리 비트를 visited로 사용하여 중복되는 경우를 걸러냄
class Solution {
    private boolean check(String A, String B){ // A에 *가 있음
        if(A.length()!=B.length()) return false;
        char[] a = A.toCharArray();
        char[] b = B.toCharArray();
        for(int i = 0 ; i < a.length ; i++) {
            if(a[i]=='*') continue;
            if(a[i]!=b[i]) return false;
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
        List<Integer> list = total.get(depth);
        int result = 0;
        for(Integer i : list) {
            if(((bit>>i)&1)==1) continue;
            result+=calc(total, depth+1, len, visited, bit|(1<<i));
        }
        return result;
    }
    public int solution(String[] user_id, String[] banned_id) {
        int len = banned_id.length;
        List<List<Integer>> list = new ArrayList<>();
        
        for(int i = 0 ; i < len ; i++) {
            List<Integer> temp = new ArrayList<>();
            boolean added = false;
            for(int j = 0 ; j < user_id.length ; j++) {
                if(check(banned_id[i],user_id[j])) { // 가능한체 확인
                    added = true;
                    temp.add(j);
                }
            }
            if(added) {
                list.add(temp);
            }
        }
        Set<Integer> visited = new HashSet<>();
        return calc(list, 0,list.size(), visited, 0); // 경우의 수 계산
    }
}