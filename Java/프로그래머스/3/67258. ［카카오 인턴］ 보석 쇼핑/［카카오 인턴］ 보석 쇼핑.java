import java.io.*;
import java.util.*;

/**
 * @description 투포인터
 */
 
// 슬라이딩 범위는 최소 보석 종류, 최대 진열대 길이
// gems 길이 1이상 100,000이하
// gems 요소는 길이 1이상 10이하 알파벳 대문자

// gems에 있는 보석이 몇번 인덱스인지 저장하는 HashMap
// check 배열에 값이 0이면 1로 바꾸고 새로운 보석 종류 +1
// 보석 종류가 전부 채워지면 가능한 것으로 보고 구간 줄이기

// 구간 분할 log N * 해당 구간으로 슬라이딩 탐색 N
class Solution {

    public int[] solution(String[] gems) {

        Set<String> gemSet = new HashSet<>(Arrays.asList(gems));
        int totalGem = gemSet.size();

        Map<String, Integer> gemCount = new HashMap<>();
        int left = 0;
        int minLen = Integer.MAX_VALUE;
        int start = 0;

        for (int right = 0; right < gems.length; right++) {
            gemCount.put(gems[right], gemCount.getOrDefault(gems[right], 0) + 1);

            while (gemCount.get(gems[left]) > 1) {
                gemCount.put(gems[left], gemCount.get(gems[left]) - 1);
                left++;
            }

            if (gemCount.size() == totalGem && right - left < minLen) {
                minLen = right - left;
                start = left;
            }
        }

        return new int[]{start + 1, start + minLen + 1};
        
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] result = sol.solution(new String[]{"A", "B", "A", "A", "A", "C", "A", "B"});
        System.out.println(result[0]+", "+result[1]);
    }
}