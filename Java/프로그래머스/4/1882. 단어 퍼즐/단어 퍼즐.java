import java.io.*;
import java.util.*;

/**
 * @description Trie + Queue
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
// 1. Queue에 들어간 사용 횟수가 갱신된 최소 횟수보다 크면 종료하도록
// 2. t의 인덱스 위치마다 도달할 수 있는 최소 횟수를 갱신/저장하며 가지치기
class Solution {
    static class Point{
        int idx;
        int cnt;
        Point(int idx, int cnt){
            this.idx = idx;
            this.cnt = cnt;
        }
    }
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

        Queue<Point> q = new ArrayDeque<>();
        q.add(new Point(0,0));

        int n = t.length();
        char[] chars = t.toCharArray();
        int[] cnts = new int[n];
        Arrays.fill(cnts,20001);
        cnts[0] = 0;
        int answer = 20001;

        while(!q.isEmpty()){
            Point p = q.poll(); // 가장 마지막에 끝나는 것이 제일 뒤에 있음
            int curCnt = p.cnt;
            int curTidx = p.idx;

            if(curCnt>=answer) continue;

            Node curNode = trie.root;
            for(int i = curTidx ; i < n ; i++) {
                curNode = curNode.next[chars[i]-'a'];
                if(curNode == null) break;
                if(curNode.isEnd) {
                    int nextTidx = i+1;
                    int nextCnt = curCnt+1;
                    if(nextTidx == n) {
                        answer = nextCnt;
                    } else{
                        if(cnts[nextTidx]>nextCnt) {
                            cnts[nextTidx] = nextCnt;
                            q.add(new Point(nextTidx,nextCnt));
                        }
                    }
                }
            }
        }
        if(answer==20001) answer = -1;
        return answer;
    }
}