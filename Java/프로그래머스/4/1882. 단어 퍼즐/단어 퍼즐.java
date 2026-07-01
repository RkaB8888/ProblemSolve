import java.io.*;
import java.util.*;

/**
 * @description Trie + DP
 */
 
// 사용가능한 문자열을 사용하여 주어진 단어 t를 완성할 수 있는 최소 횟수.
// trie 구조를 통해 strs를 모두 담고, 주어진 단어 t를 따라가면서 문자열이 끝나는 순간 큐에 담아서 확인
// 1. strs의 모든 문자열을 trie에 저장
// 2. t의 각 단어를 trie에 대입하면서 문자열 종료, 마땅한 문자열이 없는지 확인
// 3-1. trie 구조를 확인하면서 문자열 종료시 Queue에 t의 인덱스 번호, 사용 횟수를 담음
// 3-2. 마땅한 문자열이 없다면 trie의 root로 돌아가 Queue에서 하나 꺼내서 다시 읽음
// 4-1. t가 끝나지도 않았는데 Queue가 비었다면 불가능한 경우
// 4-2. t가 끝났을 때 최소 횟수 갱신 후 Queue가 빌 때까지 반복

// 최적화
// 1. Queue 대신 DP를 사용하여 모든 t의 인덱스를 확인 -> dp의 값을 통해 도달한 적 없으면 넘길 수 있어서 약간의 차이만 있음
// 2. 하지만 Queue와 Point 오버헤드가 확실히 줆
// 3. t의 인덱스 위치마다 도달할 수 있는 최소 횟수를 갱신/저장하며 가지치기
class Solution {
    static class Node{
        boolean isEnd;
        Node[] next;
        Node(){
            this.isEnd = false;
            this.next = new Node[26];
        }
    }
    static class Trie{
        Node root;
        Trie(){
            root = new Node();
        }
        void add(String str){
            Node curNode = root;
            for(int i = 0 ; i < str.length() ; i++) {
                int idx = str.charAt(i)-'a';
                if(curNode.next[idx] == null) {
                    curNode.next[idx] = new Node();
                }
                curNode = curNode.next[idx];
            }
            curNode.isEnd = true;
        }
    }
    public int solution(String[] strs, String t) {
        Trie trie = new Trie();
        for(String str : strs) {
            trie.add(str);
        }

        int n = t.length();
        int[] dp = new int[n+1];
        final int INF = 20001;
        Arrays.fill(dp,INF);
        dp[0] = 0;

        for(int i = 0 ; i < n ; i++) {
            if(dp[i]==INF) continue; // 도달한적 없는 t 인덱스

            Node curNode = trie.root;

            for(int j = i ; j < n ; j++) {
                int charIdx = t.charAt(j)-'a';
                curNode = curNode.next[charIdx];

                if(curNode == null) break;

                if(curNode.isEnd) {
                    if(dp[i]+1 < dp[j+1]) {
                        dp[j+1] = dp[i]+1;
                    }
                }
            }
        }
        return dp[n]==INF?-1:dp[n];
    }
}