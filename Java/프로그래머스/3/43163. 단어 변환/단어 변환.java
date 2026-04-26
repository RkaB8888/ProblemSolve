import java.io.*;
import java.util.*;

/**
 * @description 이 클래스에 대한 동작 설명
 * @performance 메모리: ? KB, 동작시간: ? ms
 */

// 단어 길이 최대 L: 10, 단어 개수 최대 N: 51
// 각 단어를 노드로 변환
// 글자 수 차이가 1개 일 경우 노드 연결
// 완성된 그래프에서 최소거리 BFS
// 시간 복잡도 연결: N^2*L, 탐색 N+E
class Solution {
    Node[] nodes;
    int size, len;
    static class Node{
        int n;
        char[] word;
        List<Node> next = new ArrayList<Node>();
        public Node(){

        }
        public Node(int n, String word) {
            this.n = n;
            this.word = word.toCharArray(); 
        }
        public void link(Node o, int len) {
            int diffCnt = 0;
            for(int i = 0 ; i < len ; i++) {
                if(this.word[i]!=o.word[i]) diffCnt++;
                if(diffCnt>1) return;
            }
            next.add(o);
        }
    }
    public int solution(String begin, String target, String[] words) {
        size = words.length;
        len = target.length();
        nodes = new Node[size+1];
        nodes[0] = new Node(0,begin);
        int end = 0;
        for(int i = 1 ; i <= size ; i++) {
            nodes[i] = new Node(i, words[i-1]);
            if(target.equals(words[i-1])) end = i;
        }
        for(int i = 0 ; i <= size ; i++) {
            for(int j = 0 ; j <= size ; j++) {
                nodes[i].link(nodes[j],len);
            }
        }
        
        Queue<Node> q = new ArrayDeque<Node>();
        q.add(nodes[0]);
        boolean[] visited = new boolean[size+1];
        visited[0] = true;
        int answer = 0;
        while(!q.isEmpty()) {
            int stack = q.size();
            answer++;
            while(stack-- >0) {
                Node cur = q.poll();
                for(Node next : cur.next) {
                    if(visited[next.n]) continue;
                    visited[next.n] = true;
                    if(next.n==end) {
                        return answer;
                    }
                    q.add(next);
                }
            }
        }
        return 0;
    }
}