import java.io.*;
import java.util.*;

/**
 * @description DP
 */

// arr은 짝수 인덱스에 "숫자", 홀수 인덱스에 "+ or -"가 들어 있는 홀수 길이의 배열
// arr의 길이는 3이상 201이하
// arr에 들어가는 숫자(String)은 1이상 1000이하의 자연수
 
// DP를 이용해서 구간의 최대값 구하기?
// 기호가 사용될 순서 정하는 횟수는 최대 100!
// 괄호가 적용될 때 제일 앞의 기호가 -라면 괄호가 최소가 되어야 함
// 즉 수식에서 '-' 뒤에 있는 영역은 무조건 최소가 되어야 하고 '+' 뒤에 있는 영역은 최대가 되어야 함
// 최소 DP와 최대 DP를 나누고 구간별 연산에서 최대와 최소를 갱신하는 것?
class Solution {
    public int solution(String arr[]) {
        int len = (arr.length>>1)+1;
        int[] nums = new int[len];
        int[] p = new int[len-1];

        nums[0] = Integer.parseInt(arr[0]);
        for(int i = 1 ; i < arr.length ; i+=2) {
            p[i>>1] = arr[i].equals("-")?0:1;
            nums[(i>>1)+1] = Integer.parseInt(arr[i+1]);
        }

        int[][] minDP = new int[len][len];
        int[][] maxDP = new int[len][len];

        for(int i = 0 ; i < len ; i++) {
            Arrays.fill(minDP[i],1000000);
            Arrays.fill(maxDP[i],-1000000);
            minDP[i][i] = nums[i];
            maxDP[i][i] = nums[i];
        }

        for(int k = 1 ; k < len ; k++) { // 구간 길이
            for(int i = 0 ; i < len-k ; i++){ // 행
                int end = i+k;
                for(int j = i ; j < end ; j++){ // 구간을 둘로 나누는 기준
                    if(p[j]==0) { // 빼기
                        minDP[i][end] = Math.min(minDP[i][end],minDP[i][j]-maxDP[j+1][end]);
                        maxDP[i][end] = Math.max(maxDP[i][end],maxDP[i][j]-minDP[j+1][end]);
                    } else { // 더하기
                        minDP[i][end] = Math.min(minDP[i][end],minDP[i][j]+minDP[j+1][end]);
                        maxDP[i][end] = Math.max(maxDP[i][end],maxDP[i][j]+maxDP[j+1][end]);
                    }
                }
            }
        }
        return maxDP[0][len-1];
    }
}