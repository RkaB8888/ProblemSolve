import java.io.*;
import java.util.*;

/**
 * @description 이진 트리 구성 및 나열
 * @performance 메모리: ? KB, 동작시간: ? ms
 */
 
// 자신의 부모 노드 판별
// 이진 트리 구성
// 순회하며 리스트에 담기
class Solution {
    static class Node implements Comparable<Node>{
        int n, x, y;
		Node left, right;
        
		public Node(int n, int x, int y) {
			this.n = n;
			this.x = x;
			this.y = y;
			this.left = null;
			this.right = null;
		}

        @Override
		public int compareTo(Node o) {
			if(this.y == o.y) {
				return Integer.compare(this.x, o.x);
			}
			return Integer.compare(o.y, this.y);
		}
    }

	Node[] pos;

	private void insert(Node p, Node c){
		if(p.x>c.x) { // left에 들어갈 노드
			if(p.left == null) {
				p.left = c;
			} else {
				insert(p.left, c);
			}
		} else if(p.x < c.x) { // right에 들어갈 노드
			if(p.right == null) {
				p.right = c;
			} else {
				insert(p.right,c);
			}
		} else return; // 이상한 것
	}

	private int pre(Node n, int[] arr, int idx){ // 일단 담고 다음으로 넘어감
		if(n==null) return idx;
		arr[idx++] = n.n;
		idx = pre(n.left, arr, idx);
		idx = pre(n.right, arr, idx);
		return idx;
	}
	private int post(Node n, int[] arr, int idx){ // 다음으로 넘어가고 끝날 때 담음
		if(n==null) return idx;
		idx = post(n.left, arr, idx);
		idx = post(n.right, arr, idx);
		arr[idx++] = n.n;
		return idx;
	}
    public int[][] solution(int[][] nodeinfo) {
		pos = new Node[nodeinfo.length];
        for(int i = 0 ; i < nodeinfo.length ; i++) {
			pos[i] = new Node(i+1,nodeinfo[i][0],nodeinfo[i][1]);
		}

		Arrays.sort(pos);
		Node root = pos[0];
		for(int i = 1 ; i < nodeinfo.length ; i++) {
			insert(root, pos[i]);
		}
        int[][] answer = new int[2][nodeinfo.length];
		pre(root, answer[0], 0);
		post(root, answer[1], 0);
        return answer;
    }
}