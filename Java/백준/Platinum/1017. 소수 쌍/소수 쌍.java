import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 메모리 33,096 KB 시간 552 ms
 * 
 */
public class Main {

	static boolean[] isPrime = new boolean[2000], isChecked = new boolean[2000]; // 소수 판별

	static int N, numList[], pair[];
	static List<Integer> adjList[], result = new ArrayList<>();
	static boolean[] isSelect;

	public static void main(String[] args) throws IOException {

		process();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		numList = new int[N]; // 숫자를 담아둠

		adjList = new List[N];// 두 요소의 합이 소수가 되는 경우를 간선으로 담아두는 리스트
		for (int i = 0; i < N; i++) {
			adjList[i] = new ArrayList<>();
		}

		st = new StringTokenizer(br.readLine());
		List<Integer> evenList = new ArrayList<>();
		List<Integer> oddList = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			int temp = Integer.parseInt(st.nextToken());
			numList[i] = temp;
			if (temp % 2 == 0) {
				evenList.add(i);
			} else {
				oddList.add(i);
			}
		}

		for (int i = 0; i < N - 1; i++) {// 소수면 간선 생성
			for (int j = i + 1; j < N; j++) {
				if (isPrime[numList[i] + numList[j]]) {
					adjList[i].add(j);
					adjList[j].add(i);
				}
			}
		}
		if (evenList.size() != oddList.size()) {
			System.out.print(-1);
			return;
		}

		List<Integer> list = (numList[0] % 2 == 0) ? evenList : oddList;

		pair = new int[N];
		isSelect = new boolean[N];
		a: for (int i : adjList[0]) {// 0번 숫자와 연결 가능한 간선을 따로 지정
			Arrays.fill(pair, -1);

			pair[0] = i;
			pair[i] = 0;

			for (int j : list) {
				if (j == 0)
					continue;
				Arrays.fill(isSelect, false);
				isSelect[0] = true;
				isSelect[i] = true;// 변경 불가
				if (!dfs(j)) {
					continue a;
				}
			}
			result.add(numList[i]);
		}
		if (result.isEmpty()) {
			sb.append(-1);
		} else {
			result.sort(null);
			for (int i : result) {
				sb.append(i).append(' ');
			}
		}

		System.out.print(sb);
	}

	public static boolean dfs(int a) {
		if (isSelect[a])
			return false;
		isSelect[a] = true;
		for (int b : adjList[a]) {
			if (pair[b] == -1 || dfs(pair[b])) {
				pair[a] = b;
				pair[b] = a;
				return true;
			}
		}
		return false;
	}

	public static void process() {
		check(2);
		check(3);
		for (int i = 1; i <= 400; i++) {
			if (i * 6 - 1 < 2000 && !isChecked[i * 6 - 1])
				check(i * 6 - 1);
			if (i * 6 + 1 < 2000 && !isChecked[i * 6 + 1])
				check(i * 6 + 1);
		}
	}

	public static void check(int num) {
		isChecked[num] = true;
		isPrime[num] = true;
		int n = num * 2;
		while (n < 2000) {
			isChecked[n] = true;
			isPrime[n] = false;
			n += num;
		}
	}
}