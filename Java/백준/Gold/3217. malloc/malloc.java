import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * PQ, Map, Greedy 메모리 247,176 KB 시간 2,596 ms
 * 
 * @author python98
 */
public class Main {
	static int N;
	static String cmd;
	static StringBuilder sb;
	static Map<String, int[]> vars; // 이름, 주소
	static PriorityQueue<int[]> freeBlocks;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		vars = new HashMap<>();
		N = Integer.parseInt(br.readLine());
		freeBlocks = new PriorityQueue<>((a, b) -> a[0] - b[0]);
		freeBlocks.add(new int[] { 1, 100000 });
		String var;
		for (int i = 0; i < N; i++) {
			cmd = br.readLine();
			switch (cmdCheck()) {
			case 0: // free
				var = cmd.substring(5, 9);
				if (!vars.containsKey(var)) {
					vars.put(var, new int[] { 0, 0 });
				} else {
					int[] block = vars.get(var);
					if (block[0] != 0) {
						free(block);
						vars.put(var, new int[] { 0, 0 });
					}
				}
				break;

			case 1: // print
				var = cmd.substring(6, 10);

				if (!vars.containsKey(var)) {
					vars.put(var, new int[] { 0, 0 });
				}
				sb.append(vars.get(var)[0]).append('\n');
				break;

			case 2: // malloc
				var = cmd.substring(0, 4);
				int size = Integer.parseInt(cmd.substring(12, cmd.length() - 2));
				if (!vars.containsKey(var)) {
					vars.put(var, new int[] { 0, 0 });
				}
				malloc(size, vars.get(var));
				break;
			}
		}
		System.out.print(sb);
	}

	private static void malloc(int size, int[] block) {
		block[0] = 0;
		block[1] = 0;
		List<int[]> temp = new ArrayList<>();

		while (!freeBlocks.isEmpty()) {
			int[] curBlock = freeBlocks.poll();

			if (curBlock[1] - curBlock[0] + 1 >= size) {
				block[0] = curBlock[0];
				block[1] = curBlock[0] + size - 1;

				if (block[1] < curBlock[1]) {
					temp.add(new int[] { block[1] + 1, curBlock[1] });
				}
				break;
			} else {
				temp.add(curBlock);
			}
		}

		freeBlocks.addAll(temp);
	}

	public static void free(int[] block) {
		List<int[]> temp = new ArrayList<>();
		boolean merged = false;
		
		while (!freeBlocks.isEmpty()) {
			int[] curBlock = freeBlocks.poll();

			if (curBlock[1] + 1 == block[0]) {
				block[0] = curBlock[0];
				merged = true;
			} else if (curBlock[0] - 1 == block[1]) {
				block[1] = curBlock[1];
				break;
			} else {
				temp.add(curBlock);
				if (merged)
					break;
			}
		}

		temp.add(block);

		freeBlocks.addAll(temp);
	}

	private static int cmdCheck() {
		if (cmd.startsWith("free(")) {
			return 0;
		} else if (cmd.startsWith("print(")) {
			return 1;
		} else {
			return 2;
		}
	}
}