import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
/**
 * 구현 메모리 ? KB 시간 ? ms
 * 
 * @author python98
 */
public class Main {
	static int N;
	static String cmd;
	static StringBuilder sb;
	static Map<String, Integer> vars; // 이름, 시작주소
	static PriorityQueue<int[]> useMemoryRange;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		vars = new HashMap<>();
		useMemoryRange = new PriorityQueue<>((a, b) -> a[0] - b[0]);
		N = Integer.parseInt(br.readLine());
		String var;
		for (int i = 0; i < N; i++) {
			cmd = br.readLine();
			switch (cmdCheck()) {
			case 0: // free
				var = cmd.substring(5, 9);
//				System.out.println("free("+var+")");
				if (!vars.containsKey(var))
					vars.put(var, 0);
				else
					free(var);

				break;

			case 1: // print
				var = cmd.substring(6, 10);
				
				if (!vars.containsKey(var))
					vars.put(var, 0);
//				System.out.println("print("+var+") : "+vars.get(var));
				sb.append(vars.get(var)).append('\n');
				break;

			case 2: // malloc
				var = cmd.substring(0, 4);
				int size = Integer.parseInt(cmd.substring(12, cmd.length() - 2));

				if (!vars.containsKey(var))
					vars.put(var, 0);
				
				int startAddrs = malloc(size);
				vars.put(var, startAddrs);
//				System.out.println(var+"=malloc("+size+") -> "+startAddrs);
				break;
			}
		}
		System.out.print(sb);
	}

	private static int malloc(int size) {
		List<int[]> temp = new ArrayList<>();
		int preEndAddr = 0;
		while (!useMemoryRange.isEmpty()) {
			
			// 메모리 초과하는 경우
			if (preEndAddr + size > 100000) {
				useMemoryRange.addAll(temp);
				return 0;
			}

			int[] curRange = useMemoryRange.poll();
			int emptyMemorySize = curRange[0] - preEndAddr - 1;
			
			// 빈공간 유무
			if (emptyMemorySize < size) { 
				preEndAddr = curRange[1];
				temp.add(curRange);
				continue;
			} else {
				useMemoryRange.add(new int[] { preEndAddr + 1, preEndAddr + size });
				useMemoryRange.add(curRange);
				useMemoryRange.addAll(temp);
				return preEndAddr + 1;
			}
			
		}
		useMemoryRange.addAll(temp);
		if(preEndAddr+size<=100000) {
			useMemoryRange.add(new int[] {preEndAddr+1,preEndAddr+size});
			return preEndAddr+1;
		}
		return 0;
	}

	private static void free(String var) throws Exception {
		int startAddr = vars.get(var);
		if (startAddr != 0) {
			List<int[]> temp = new ArrayList<>();
			while (!useMemoryRange.isEmpty()) {
				int[] curRange = useMemoryRange.poll();
				if (startAddr == curRange[0]) {
					useMemoryRange.addAll(temp);
					vars.put(var, 0);
					return;
				} else if (startAddr > curRange[0]) {
					temp.add(curRange);
				} else {
					Exception e = new Exception("존재하지 않는 메모리 시작 주소");
					throw e;
				}
			}
		}
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