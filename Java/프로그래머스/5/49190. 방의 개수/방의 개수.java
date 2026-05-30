import java.io.*;
import java.util.*;

import org.w3c.dom.Node;

/**
 * @description 오일러 다면체 공식
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

// 오일러 다면체 정리 R = E - V + 1
// 간선과 노드의 해시셋을 따로 관리하여 갯수만 관리
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
            return this.x*31+this.y;
        }
    }
    private static class Edge{
        int x1, y1, x2, y2;
        public Edge(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
        @Override
        public boolean equals(Object o) {
            if(this == o) return true;
            if(o==null || this.getClass() != o.getClass()) return false;
            Edge other = (Edge)o;
            return (this.x1==other.x1&&this.y1==other.y1&&this.x2==other.x2&&this.y2==other.y2)||
            (this.x1==other.x2&&this.y1==other.y2&&this.x2==other.x1&&this.y2==other.y1);
        }
        @Override
        public int hashCode(){
            int hash1 = this.x1*31+this.y1;
            int hash2 = this.x2*31+this.y2;
            return hash1+hash2;
        }
    }
    public int solution(int[] arrows) {
        Node curN = new Node(0,0);
        HashSet<Node> visitedNode = new HashSet<>();
        HashSet<Edge> visitedEdge = new HashSet<>();
        visitedNode.add(curN);
        for(int arrow : arrows) {
            for(int i = 0 ; i < 2 ; i++) {
                Node nextN = new Node(curN.x + DIR[arrow][0],curN.y + DIR[arrow][1]);
                Edge nextE = new Edge(curN.x, curN.y, nextN.x, nextN.y);
                visitedNode.add(nextN);
                visitedEdge.add(nextE);
                curN = nextN;
            }
        }
        return visitedEdge.size() - visitedNode.size() + 1;
    }
}