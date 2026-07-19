import java.io.*;
import java.util.*;

/**
 * @description 구현
 */
 
// 명령어에 따라 각 행의 데이터를 삭제하거나 복구해야 함.
// 행을 가리키는 포인터와 값이 들어 있는 링크드 리스트로 분리하여
// 삭제 시 해당 행 번호가 다음에 오는 데이터와 연결되도록 한다.
// 복구 하는 방법은 스택에 담겨 있던 삭제된 노드를 다시 꺼내고, 기존에 있던 행번호의 노드 사이에 끼워 넣는 식으로
// ex) 이전에 지운 행번호가 5번이었고, 지금은 3번을 가리키고 있다면
// 링크드 리스트 상으로 뒤로 두번 간 노드 앞에 다시 넣는다.
// 다만 5번이 마지막이어서 4번 노드가 최대라면 그 뒤에 추가하는 형태로 한다.

// n은 5이상 1000이하
// 처음 시작하는 행 번호 k는 0이상 n미만
// cmd 길이는 1이상 1000이하
// cmd의 원소는 "U 숫자", "D 숫자", "C", "Z" 중 하나
// "숫자"는 1이상 300000이하
class Solution {

    public String solution(int n, int k, String[] cmd) {
        int[] pre = new int[n];
        int[] next = new int[n];
        for(int i = 0 ; i < n ; i++) {
            pre[i] = i-1;
            next[i] = i+1;
        }
        next[n-1] = -1;

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int cur = k;
        
        for(String s : cmd) {
            char c = s.charAt(0);
            if(c=='U') {
                int num = Integer.parseInt(s.substring(2));
                while(num-- > 0) {
                    cur = pre[cur];
                }
            }else if(c=='D') {
                int num = Integer.parseInt(s.substring(2));
                while(num-- > 0) {
                    cur = next[cur];
                }
            }else if(c=='C') {
                stack.addLast(cur); // 지워진 데이터를 스택에 넣고
                if(pre[cur]!=-1) {
                    next[pre[cur]] = next[cur];
                }
                if(next[cur]!=-1) {
                    pre[next[cur]] = pre[cur];
                }
                if(next[cur]!=-1) { // 마지막 행이 아니라면
                    cur = next[cur];
                } else {
                    cur = pre[cur];
                }
            }else if(c=='Z') {
                int re = stack.pollLast();
                if(pre[re]!=-1) {
                    next[pre[re]] = re;
                }
                if(next[re]!=-1) {
                    pre[next[re]] = re;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < n ; i++) {
            sb.append('O');
        }
        while(!stack.isEmpty()) {
            sb.setCharAt(stack.pollLast(),'X');
        }
        return sb.toString();
    }
}