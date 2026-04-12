import java.io.*;
import java.util.*;

/**
 * @description FenwickTree
 * @performance 메모리: ? KB, 동작시간: ? ms
 * @author python98
 */
public class Main {

	static class FenWickTree{
		int len;
		int[] tree;
		FenWickTree(int len){
			this.len = len;
			this.tree = new int[len];
		}
		void init(){
			for(int i = 1 ; i < len ; i++) {
				int next = i+(i&-i);
				if(next<len){
					tree[next]+=tree[i];
				}
			}
		}
		void update(int idx, int val){
			while(idx<len) {
				tree[idx]+=val;
				idx += idx&-idx;
			}
		}
		int sumAll(int idx){
			int result = 0;
			while(idx>0) {
				result+=tree[idx];
				idx-=idx&-idx;
			}
			return result;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t = 0 ; t < T ; t++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int maxIdx = n+m;
			int[] pos = new int[n+1];
			FenWickTree tree = new FenWickTree(maxIdx+1);
			for(int i = 1 ; i <= n ; i++) {
				pos[i] = m+i;
				tree.tree[m+i] = 1;
			}
			tree.init();
			st = new StringTokenizer(br.readLine());
			for(int i = m ; i > 0 ; i--) {
				int a = Integer.parseInt(st.nextToken());
				sb.append(tree.sumAll(pos[a]-1)).append(' ');
				tree.update(pos[a],-1);
				tree.update(i,1);
				pos[a] = i;
			}
			sb.append('\n');
		}
		System.out.print(sb);
	}
}