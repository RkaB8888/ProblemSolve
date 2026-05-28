import java.io.*;
import java.util.*;

/**
 * @description 정렬 후 공통 접두사 계산
 */
 
// 주어진 words를 찾을 때 입력해야 하는 글자 수 총합
// 단어 수 N은 2이상 100,000이하
// 단어 길이 **총합** L은 2이상 1,000,000이하

// 방법1:
// trie 구조로 모든 단어를 저장하고, 각 노드에는 지나가는 단어 수를 카운팅함
// 모든 단어를 trie 구조에서 찾아갈 때 노드의 카운트가 1이면 단어를 찾은 것.
// 시간 복잡도는 L
// 공간 복잡도는 최대 N*L개의 노드 생성

// 방법2:
// words를 정렬한 후 인접 단어끼리의 공통접두사의 길이 누적합
// 이전 단어와 공통 길이 2, 다음 단어와 공통 길이 1 -> 둘 중에 큰 값 + 1을 answer에 더함
// 시간 복잡도는 L
// 공간 복잡도는 N

class Solution {
    int getLcp(String a, String b) {
        int len = Math.min(a.length(), b.length());
        int count = 0;
        for(int i = 0 ; i < len ; i++) {
            if(a.charAt(i)==b.charAt(i)) count++;
            else break;
        }
        return count;
    }
    public int solution(String[] words) {
        Arrays.sort(words);
        int n = words.length;
        int[] lcp = new int[n-1]; // 0-1, 1-2 사이의 공통 길이 저장
        for(int i = 0 ; i < n-1 ; i++) {
            lcp[i] = getLcp(words[i],words[i+1]);
        }

        int answer = 0;
        for(int i = 0 ; i < n ; i++) {
            int maxLcp = 0;
            if(i>0) maxLcp = Math.max(maxLcp, lcp[i-1]);
            if(i<n-1) maxLcp = Math.max(maxLcp, lcp[i]);
            answer += Math.min(words[i].length(),maxLcp+1);
        }
        return answer;
    }
}