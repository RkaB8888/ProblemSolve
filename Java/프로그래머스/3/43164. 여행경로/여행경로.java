import java.io.*;
import java.util.*;

/**
 * @description 한붓 그리기
 */
 
// Hierholzer 알고리즘 활용
class Solution {

    private void dfs(String start, Map<String, List<String>> graph, Deque<String> route){
        List<String> end = graph.get(start);
        while(end != null && !end.isEmpty()) {
            String next = end.remove(end.size()-1);
            dfs(next, graph, route);
        }
        route.addFirst(start);
    }
    public String[] solution(String[][] tickets) {
        // 공항과 연결된 다음 공항들 저장
        Map<String, List<String>> graph = new HashMap<>();
        for(String[] ticket : tickets) {
            graph.putIfAbsent(ticket[0],new ArrayList<>());
            graph.get(ticket[0]).add(ticket[1]);
        }

        for(List<String> list : graph.values()) {
            list.sort(Collections.reverseOrder());
        }

        Deque<String> route = new ArrayDeque<>();
        dfs("ICN", graph, route);

        return route.toArray(new String[0]);
    }
}