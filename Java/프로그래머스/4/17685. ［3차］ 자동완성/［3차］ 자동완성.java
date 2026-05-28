import java.io.*;
import java.util.*;

/**
 * @description 이 클래스에 대한 동작 설명
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
    static class Node{
        int cnt;
        Node[] next;
        public Node(){
            this.cnt = 0;
            this.next = new Node[26];
        }
    }
    static class Trie{
        Node root;
        public Trie(){
            this.root = new Node();
        }
        void add(String word){
            Node cur = root;
            for(int i = 0 ; i < word.length() ; i++) {
                int idx = word.charAt(i)-'a';
                if(cur.next[idx]==null) cur.next[idx] = new Node();
                cur = cur.next[idx];
                cur.cnt++;
            }
        }
        int getCnt(String word){
            Node cur = root;
            for(int i = 0 ; i < word.length() ; i++) {
                int idx = word.charAt(i)-'a';
                cur = cur.next[idx];
                if(cur.cnt==1) {
                    return i+1;
                }
            }
            return word.length();
        }
    }
    public int solution(String[] words) {
        Trie trie = new Trie();
        for(String word : words) {
            trie.add(word);
        }
        int answer = 0;
        for(String word : words) {
            answer+=trie.getCnt(word);
        }
        return answer;
    }
}