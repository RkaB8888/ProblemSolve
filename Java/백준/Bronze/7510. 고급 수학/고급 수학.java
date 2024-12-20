import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
/**
 * 수학 메모리 ? KB 시간 ? ms
 * 
 * @author python98
 */
public class Main {
	static double[] nums = new double[3];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < 3 ; i++) {
				nums[i] = Math.pow(Integer.parseInt(st.nextToken()),2);
			}
			Arrays.sort(nums);
			if(nums[2]==nums[0]+nums[1]) {
				sb.append("Scenario #").append(tc).append(":\nyes\n\n");
			} else {
				sb.append("Scenario #").append(tc).append(":\nno\n\n");
			}
		}
		System.out.print(sb);
	}
}