import java.io.*;
import java.util.*;

/**
 * @description 이 클래스에 대한 동작 설명
 */
 
// 연속으로 돈을 가져오면 안됨
// DP로 [0]: 안훔치는 경우 [1]: 이전 집을 안훔치고 훔치는 경우
// 집의 배치가 원형임을 주의해야 함!!!
// 0번 집을 터는 경우과 털지 않는 경우로 나눠서 계산

// money의 길이는 3이상 1,000,000이하
// money는 0이상 1,000이하 정수
class Solution {
    public int solution(int[] money) {
        int pre0 = 0, pre1 = money[0];
        // 0번 집을 훔치는 경우 -> 마지막 집은 훔칠 수 없음
        for(int i = 1 ; i < money.length - 1 ; i++) {
            int cur = Math.max(pre0,pre1);
            pre1 = pre0+money[i];
            pre0 = cur;
        }
        int max1 = Math.max(pre0, pre1);

        pre0 = 0; pre1 = 0;
        // 0번 집을 안 훔치는 경우 -> 마지막 집은 훔칠 수 있음
        for(int i = 1 ; i < money.length ; i++) {
            int cur = Math.max(pre0,pre1);
            pre1 = pre0+money[i];
            pre0 = cur;
        }
        int max2 = Math.max(pre0,pre1);

        return Math.max(max1,max2);
    }
}