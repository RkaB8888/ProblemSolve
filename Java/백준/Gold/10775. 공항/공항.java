import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 메모리 ? KB 시간 ? ms
 * 
 * @author python98
 */
public class Main {

	static int G, P, possibleP[], result;
	static int[] group;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		G = Integer.parseInt(br.readLine());
		P = Integer.parseInt(br.readLine());
		possibleP = new int[P + 1]; // 들어갈 수 있는 게이트 번호 저장
		result = 0;
		for (int pNum = 1; pNum <= P; pNum++) {
			possibleP[pNum] = Integer.parseInt(br.readLine());
		}
		makeSet();
		for (int pNum = 1; pNum <= P; pNum++) {
			int gNum = find(possibleP[pNum]);
			if(find(gNum)!=0) {
				union(gNum,gNum-1);
				result++;
			} else {
				break;
			}
		}
		System.out.println(result);
	}
	private static void makeSet() {
		group = new int[G+1];
		for(int i = 0 ; i <= G ; i++) {
			group[i] = i;
		}
	}
	private static int find(int a) {
		if(group[a]==a) return a;
		return group[a] = find(group[a]);
	}
	private static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		
		if(rootA<rootB) {
			group[rootB] = rootA;
		} else {
			group[rootA] = rootB;
		}
	}
}