import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
	
	static final int POWER = 26;
	static final long MOD = 10_0000_0007;
	static final int MAX_NAME_LEN = 1000;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		
		String[] input = br.readLine().split(" ");
		int C = Integer.parseInt(input[0]);
		int N = Integer.parseInt(input[1]);
		
		Map<Integer, Set<Integer>> colorMap = new HashMap<>();
		Map<Integer, Set<Integer>> nameMap = new HashMap<>();
        
        setMap(colorMap, C, true);
		setMap(nameMap, N, false);
		
		List<Integer> colorLenList = new ArrayList<>(colorMap.keySet());
		Collections.sort(colorLenList);
		Set<Integer> nameSet = nameMap.keySet();
		Deque<Integer> nameStack = new ArrayDeque<>();

        int Q = Integer.parseInt(br.readLine());
		for (int i = 0; i < Q; i++) {
			boolean legend = false;
			String str = br.readLine();
			int len = str.length();
			int idx = 0;
			long colorHash = 0;
			for (int colorLen : colorLenList) {
				
				// colorLen이 팀명의 길이보다 작아야함
				if(colorLen >= len) break;
				
				// colorLen, nameLen이 맞지 않으면 continue
				int nameLen = len - colorLen;
				if(!nameSet.contains(nameLen)) continue;
				
				// color부분 hash값 구하기
				for (; idx < colorLen; idx++) {
					colorHash = (((colorHash * POWER) % MOD) + (str.charAt(idx) - 'a')) % MOD;
				}

                // color의 hash값과 일치하면 okArr에 추가
				if(colorMap.get(colorLen).contains((int) colorHash)) {
					nameStack.push(nameLen);
				}
			}

            idx = 0;
			long nameHash = 0;
			while(!nameStack.isEmpty()) {
				int nameLen = nameStack.pop();
				
				// name부분 hash값 구하기
				for (; idx < nameLen; idx++) {
					nameHash = (((nameHash * POWER) % MOD) + (str.charAt(len - idx - 1) - 'a')) % MOD;
				}
                
				if(nameMap.get(nameLen).contains((int) nameHash)) {
					legend = true;
					break;
				}
			}
			
			nameStack.clear();
			
			if(legend) sb.append("Yes");
			else sb.append("No");
			sb.append("\n");
		}
		
		br.close();
		System.out.println(sb);
	}

	private static void setMap(Map<Integer, Set<Integer>> map, int size, boolean tf) throws IOException {
		for (int i = 0; i < size; i++) {
			String str = br.readLine();
			int len = str.length();
			long hash = 0;
			if(tf) {
				for (char c : str.toCharArray()) {
					hash = (((hash * POWER) % MOD) + (c - 'a')) % MOD;
				}
			} else {
				for (int j = len - 1; j >= 0; j--) {
					hash = (((hash * POWER) % MOD) + (str.charAt(j) - 'a')) % MOD;
				}
			}
			map.compute(len, (k, v) -> v == null ? new HashSet<>() : v).add((int)hash);
		}
	}
}