import java.io.*;
import java.util.*;

/**
 * @description 시뮬 + 그리디
 */
 
// 그래프 생성 후 시뮬레이션 했을 떄, 각 리프 노드에 쌓인 값의 합이 target과 같아야 함(최소, 사전 정렬 가장 빠른)
// edge는 최대 100이므로 떨어지는 순서를 찾기에는 다소 시간이 걸릴 것 같음
// 하지만 문제 조건에서 1~3의 값만 넣으므로 반복구간을 찾기 보다 순서만 담아두는 게 좋을 듯
// 모든 leaf에 3을 넣었을 때 target 이상이 되어야 함. 근데 유독 큰 target 때문에 다른 leaf가 1만 넣어도 불가능한 경우도 있음

class Solution {
    int leafCnt;
    Tree tree;

    static class Node{
        int num;
        List<Integer> childList;
        int road;
        boolean havC;

        Node(int num) {
            this.num = num;
            childList = new ArrayList<>();
            this.road = 0;
            this.havC = false;
        }

        void addChild(int c){
            childList.add(c);
            road = 0;
            havC = true;
        }
        void nextRoad(){
            road++;
            if(road==childList.size()) {
                road = 0;
            }
        }
    }
    static class Tree{
        Node[] nodes;

        void setNode(int size){
            nodes = new Node[size];
            for(int i = 0 ; i < size ; i++) {
                nodes[i] = new Node(i);
            }
        }
        void addEdge(int p, int c){
            nodes[p].addChild(c);
        }
        int nextStep(){
            int curNode = 0;
            while(nodes[curNode].havC) {
                int idx = nodes[curNode].road;
                nodes[curNode].nextRoad();
                curNode = nodes[curNode].childList.get(idx);
            }
            return curNode;
        }
    }

    public int[] solution(int[][] edges, int[] target) {
        int n = target.length;

        Arrays.sort(edges, (a,b)-> { // 각 노드의 childList를 순서대로 넣기 위함
            if(a[0]==b[0]) {
                return Integer.compare(a[1],b[1]);
            } else return Integer.compare(a[0],b[0]);
        });

        tree = new Tree();
        tree.setNode(n);
        for(int[] edge : edges) {
            tree.addEdge(edge[0]-1,edge[1]-1);
        }
        
        int[] cnt = new int[n];
        List<Integer> order = new ArrayList<>();

        while(true) {
            boolean isImpossible = false;
            boolean isSatisfied = true;
            for(int i = 0 ; i < n ; i++) {

                if(cnt[i]>target[i]) { // 구조적으로 가능한지
                    isImpossible = true;
                    break;
                }

                if(cnt[i]*3<target[i]) { // while 더 돌아야 하는지
                    isSatisfied = false;
                }
            }

            if(isImpossible) return new int[] {-1};
            if(isSatisfied) break;

            int leafNode = tree.nextStep();
            cnt[leafNode]++;
            order.add(leafNode);
        }

        int[] answer = new int[order.size()];

        for(int i = 0 ; i < order.size() ; i++) {
            int leaf = order.get(i);
            cnt[leaf]--;

            for(int val = 1 ; val <= 3 ; val++) {
                if(target[leaf] - val <= cnt[leaf]*3) {
                    answer[i] = val;
                    target[leaf] -= val;
                    break;
                }
            }
        }

        return answer;
    }
}