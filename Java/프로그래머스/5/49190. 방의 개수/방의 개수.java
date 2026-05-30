import java.io.*;
import java.util.*;

/**
 * @description 이 클래스에 대한 동작 설명
 */
 
// arrows의 크기는 1이상 100,000이하
// arrows의 원소는 0이상 7이하
// 12시 방향을 0으로 시계방향으로 7까지 존재

// 격자의 크기를 *3하면 오른쪽 대각 -> 왼쪽 -> 아래 로 방을 만들었을 때 빈칸 하나가 나올 수 있음
// 화살의 크기를 3으로 하여 방향만큼의 칸을 1로 채움
// 그림을 전부 그린 뒤 모든 격자의 0 영역을 flood fill로 채워서 방의 갯수 반환
// 요구되는 격자의 크기는 arrows 크기가 최대 100,000이기 때문에 시작을 정중앙에 뒀을 때 상하좌우 30만씩 총 60만*60만의 크기
// 시간복잡도 3n + (6n)^2 -> 360,000,000,000으로 시간초과

// 각 노드에서 화살표 방향으로 노드 연결하는 그래프 생성
// 순환 사이클 발견시 방+1
// -> 그래프를 타고 가다가 이미 한번 지나쳐온 노드라면 방+1
// -> 외부순환, 내부순환 고려할 필요 없이 방의 갯수만 반환하면 됨
// -> 모래시계 모양으로 방이 형성될 수 있으니 화살의 크기를 *2로 하여 중간에 노드 추가
// 시간복잡도 2n
// 공간복잡도 2n
class Solution {
    private static final int[][] DIR = {{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1},{-1,0},{-1,1}};
    private static class Node{
        int x, y;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
        @Override
        public boolean equals(Object o) {
            if(this == o) return true;
            if(o==null || this.getClass() != o.getClass()) return false;
            Node other = (Node)o;
            return this.x==other.x&&this.y==other.y;
        }
        @Override
        public int hashCode(){
            return Objects.hash(this.x,this.y);
        }
    }
    public int solution(int[] arrows) {
        Node cur = new Node(0,0);
        HashMap<Node,HashSet<Node>> visited = new HashMap<>();
        visited.put(cur,new HashSet<>());
        int answer = 0;
        for(int arrow : arrows) {
            for(int i = 0 ; i < 2 ; i++) {
                Node next = new Node(cur.x + DIR[arrow][0],cur.y + DIR[arrow][1]);
                if(!visited.containsKey(next)) { // 새롭게 가는 노드
                    visited.put(next,new HashSet<>());
                    visited.get(cur).add(next);
                    visited.get(next).add(cur);
                } else if(!visited.get(cur).contains(next)) { // 간선만 새로 생기는 노드
                    visited.get(cur).add(next);
                    visited.get(next).add(cur);
                    answer++;
                }
                cur = next;
            }
        }
        return answer;
    }
}