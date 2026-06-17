import java.io.*;
import java.util.*;

/**
 * @description 윈도우 슬라이딩 + 이분 탐색
 */
 
// 슬라이딩 범위는 최소 보석 종류, 최대 진열대 길이
// gems 길이 1이상 100,000이하
// gems 요소는 길이 1이상 10이하 알파벳 대문자

// gems에 있는 보석이 몇번 인덱스인지 저장하는 HashMap
// check 배열에 값이 0이면 1로 바꾸고 새로운 보석 종류 +1
// 보석 종류가 전부 채워지면 가능한 것으로 보고 구간 줄이기

// 구간 분할 log N * 해당 구간으로 슬라이딩 탐색 N
class Solution {
    int totalGem;
    String[] gems;
    Map<String, Integer> gemIdx;

    private int find(int size){
        int idx = 0;
        int total = 0;
        int[] cnt = new int[totalGem];
        
        while(idx<size) {
            int gem = gemIdx.get(gems[idx]);
            if(cnt[gem]==0) {
                total++;
            }
            cnt[gem]++;
            idx++;
        }

        if(total==totalGem) return size;

        while(idx<gems.length) {
            int addgem = gemIdx.get(gems[idx]);
            int absgem = gemIdx.get(gems[idx-size]);

            if(cnt[addgem]==0) {
                total++;
            }
            cnt[addgem]++;

            if(cnt[absgem]==1) {
                total--;
            }
            cnt[absgem]--;

            idx++;

            if(total==totalGem) return idx;
        }

        return 0;
    }
    public int[] solution(String[] gems) {
        this.gems = gems;
        gemIdx = new HashMap<>();
        totalGem = 0;
        for(int i = 0 ; i < gems.length ; i++) {
            if(!gemIdx.containsKey(gems[i])) {
                gemIdx.put(gems[i],totalGem);
                totalGem++;
            }
        }

        int[] answer = new int[]{-1,-1};
        int left = totalGem, right = gems.length;
        while(left<=right) {
            int mid = (left+right)>>1;
            int endIdx = find(mid);
            if(endIdx!=0) { // 가능
                answer[0] = endIdx-mid+1;
                answer[1] = endIdx;
                right = mid-1;
            } else { // 불가능
                left = mid+1;
            }
        }
        return answer;
    }
}