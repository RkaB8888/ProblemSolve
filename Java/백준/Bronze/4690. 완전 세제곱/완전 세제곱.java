/**
 * @description 조합 최대 90,345,024번
 * @performance 메모리: ? KB, 동작시간: ? ms
 * @author python98
 */
public class Main {
	static int[] nums;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		nums = new int[4];
		combination(0, 2, 101, 0);
		System.out.print(sb);
	}

	private static void combination(int depth, int str, int max, double product) {
		if (depth == 4) {
			if (Math.pow(nums[0], 3) == product) {
				sb.append("Cube = ").append(nums[0]).append(", Triple = (").append(nums[1]).append(",").append(nums[2])
						.append(",").append(nums[3]).append(")\n");
			}
			return;
		}
		if (depth == 0) {
			for (int i = 2; i < max; i++) {
				nums[depth] = i;
				combination(depth + 1, 2, i, product);
			}
		} else {
			for (int i = str; i < max; i++) {
				nums[depth] = i;
				combination(depth + 1, i+1, max, product + Math.pow(i, 3));
			}
		}

	}
}