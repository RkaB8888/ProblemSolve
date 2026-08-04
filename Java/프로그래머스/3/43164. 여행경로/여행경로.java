import java.io.*;
import java.util.*;

/**
 * @description 이 클래스에 대한 동작 설명
 */
 
// 모든 간선을 사용해서 거쳐가는 노드 반환
class Solution {
    int n;
    int[] link, next, startNode, endNode;
    boolean[] used;
    int[] stack;

    private boolean dfs(int nodeIdx, int step){
        if(step == n) return true;

        for(int edge = link[nodeIdx] ; edge != -1 ; edge = next[edge]) {
            if(!used[edge]){
                used[edge] = true;
                stack[step] = edge;
                if(dfs(endNode[edge], step+1)) {
                    return true;
                }
                used[edge] = false;
            }
        }
        return false;
    }
    public String[] solution(String[][] tickets) {
        // 티켓 이름 오름차순 정렬
        Arrays.sort(tickets,(a,b)->{
            if(a[0].compareTo(b[0])==0) {
                return a[1].compareTo(b[1]);
            }
            return a[0].compareTo(b[0]);
        });

        n = tickets.length;
        link = new int[n+1]; // 공항의 갯수는 n+1이내임
        Arrays.fill(link,-1);
        next = new int[n];
        startNode = new int[n];
        endNode = new int[n]; // 도착 공항의 갯수는 n이내임

        Map<String,Integer> index = new HashMap<>();
        String[] name = new String[n+1]; // 티켓이 n개라면 지역은 최대 n+1개

        int cnt = 0;
        // 각 공항의 인덱스 저장
        for(int i = 0 ; i < n ; i++) {
            String from = tickets[i][0];
            String to = tickets[i][1];

            if(!index.containsKey(from)) {
                index.put(from,cnt);
                name[cnt] = from;
                cnt++;
            }
            if(!index.containsKey(to)) {
                index.put(to,cnt);
                name[cnt] = to;
                cnt++;
            }
        }

        for(int i = n-1 ; i >= 0 ; i--) { // 인접 리스트 역순으로 연결
            int fromIdx = index.get(tickets[i][0]);
            int toIdx = index.get(tickets[i][1]);

            next[i] = link[fromIdx];
            link[fromIdx] = i;
            startNode[i] = fromIdx;
            endNode[i] = toIdx;
        }

        used = new boolean[n];
        stack = new int[n];

        dfs(index.get("ICN"), 0);

        String[] answer = new String[n+1];
        answer[0] = "ICN";
        for(int i = 1 ; i <= n ; i++) {
            answer[i] = name[endNode[stack[i-1]]];
        }

        return answer;
    }
}