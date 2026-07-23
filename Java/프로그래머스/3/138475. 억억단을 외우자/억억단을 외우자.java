import java.io.*;
import java.util.*;

/**
 * @description 에라스토체네스의 채 + DP
 */
 
// 1~1억 구구단 표에서 e 이상 starts[i]이하의 수 중에서 가장 많이 등장하는 수를 result[i]에 담아서 반환
// e는 1이상 5000000이하
// starts의 길이는 1이상 (e와 100000 중에 작은 수)이하
// starts의 요소는 1이상 e이하

// 어떤 숫자 n이 표에 등장하는 횟수는 n의 약수의 갯수라고 할 수 있나?
// 4는 1 2 4 -> 3번, 8은 1 2 4 8 -> 4번 ...
// n의 약수를 구하는 방법은 i의 제곱이 n보다 작거나 같을 때 까지 카운팅하는 방법 O(root(n))
// 최악의 경우 e가 5,000,000이고 starts의 길이는 100,000이고 starts의 원소는 전부 1인 경우
// 500,000*root(5,000,000) = 약 111억으로 시간 초과 가능성 있음
// 여기에 memoization으로 각 숫자의 약수 갯수를 담아두면 괜찮지 않을까?

// 에라토스체네스의 채를 활용하면 5000000*root(5000000) -> 5000000*log(5000000) = 1억1만으로 크게 줄음
// s~e까지를 반복해서 구하면 1000000*5000000이 되기 때문에 DP를 통해 미리 최고 값을 담아 둔다.
class Solution {
    public int[] solution(int e, int[] starts) {
        int[] memo = new int[5000001];
        for(int i = 1 ; i <= 5000000 ; i++) {
            for(int j = i ; j <= 5000000 ; j+=i) {
                memo[j]++;
            }
        }
        int[] best = new int[5000001];
        best[e] = e;
        int num = e;
        for(int i = e ; i > 0 ; i--) {
            if(memo[i]>=memo[num]) {
                best[i] = i;
                num = i;
            } else {
                best[i] = num;
            }
        }
        int[] answer = new int[starts.length];
        for(int i = 0 ; i < starts.length ; i++) {
            answer[i] = best[starts[i]];
        }
        return answer;
    }
}