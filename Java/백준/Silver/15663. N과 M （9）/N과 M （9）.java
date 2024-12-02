import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 
 * 순열 메모리 ? KB 시간 ? ms
 * 
 * @author python98
 *
 */
public class Main {
	static int N, M, arr1[], arr2[];
	static Set<String> set;
	static boolean[] selected;	
	static StringBuilder sb1;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr1 = new int[N];
		arr2 = new int[M];
		set = new HashSet<>();
		selected = new boolean[N];
		sb1 = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i++) {
			arr1[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr1);
		process(0);
		System.out.print(sb1);
	}
	
	private static void process(int depth) {
		if(depth == M) {
			StringBuilder sb2 = new StringBuilder();
			for(int i = 0 ; i < M ; i++) {
				sb2.append(arr2[i]).append(' ');
			}
			if(!set.contains(sb2.toString())) {
				set.add(sb2.toString());
				sb1.append(sb2).append('\n');
			}
			return;
		}
		
		for(int i = 0 ; i < N ; i++) {
			if(selected[i]) continue;
			selected[i] = true;
			arr2[depth] = arr1[i];
			process(depth+1);
			selected[i] = false;
		}
	}
}