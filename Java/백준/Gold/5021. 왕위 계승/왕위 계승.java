import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Map을 이용한 BFS 메모리 12,080 KB 시간 68 ms
 */
public class Main {
	static int N, M;
	static String root, result;
	static String[][] name;//name, parent, parent
	static String[] confirmName;
	static Map<String,Double> nameMap;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		root = br.readLine();
		name = new String[N][3];//이름 담는 배열
		nameMap = new HashMap<>();//이름과 값을 담는 맵
		confirmName = new String[M];//비교할 이름 담는 배열
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			name[i][0] = st.nextToken();
			name[i][1] = st.nextToken();
			name[i][2] = st.nextToken();
		}
		for(int i = 0 ; i < M ; i++) {
			confirmName[i] = br.readLine();
		}
		
		double rootValue = 2L<<(N-1);
		nameMap.put(root, rootValue);
		bfs();
		double val = 0;
		
		for(String s : confirmName) {
			double temp = 0;
			if(nameMap.containsKey(s)) {
				temp = nameMap.get(s);
			}
			if(val<temp) {
				val = temp;
				result = s;
			}
		}
		System.out.print(result);
		
	}
	private static void bfs() {
		Queue<String> q = new ArrayDeque<>();	
		q.add(root);
		while(!q.isEmpty()) {
			String curName = q.poll();
			double curVal = nameMap.get(curName);
			for(int i = 0 ; i < N ; i++) {
				if(name[i][2].equals(curName)) {//뒤쪽 이름이 지금 비교할 이름과 같다면
					double val = 0;
					if(nameMap.containsKey(name[i][1])) {
						val = nameMap.get(name[i][1]);//다른 쪽 부모의 가치를 저장
					}
					nameMap.put(name[i][0], (curVal+val)/2);//부모 둘의 가치를 더하고 2로 나눈 것이 자식의 가치
					q.add(name[i][0]);
				} else if(name[i][1].equals(curName)) {//뒤쪽 이름이 지금 비교할 이름과 같다면
					double val = 0;
					if(nameMap.containsKey(name[i][2])) {
						val = nameMap.get(name[i][2]);//다른 쪽 부모의 가치를 저장
					}
					nameMap.put(name[i][0], (curVal+val)/2);//부모 둘의 가치를 더하고 2로 나눈 것이 자식의 가치
					q.add(name[i][0]);
				}
			}
		}
	}
}