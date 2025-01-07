/**
 * @description 조합 최대 90,345,024번
 * @performance 메모리: 12,416 KB, 동작시간: 596 ms
 * @author python98
 */
public class Main {
	static int[] cubes = new int[101];
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		
		for (int i = 2; i <= 100; i++) {
            cubes[i] = i * i * i;
        }
		for (int a = 2; a <= 100; a++) {
            int aCube = cubes[a];
            findTriples(a, aCube);
        }
		System.out.print(sb);
	}

	private static void findTriples(int a, int aCube) {
        for (int b = 2; b < a; b++) {
            for (int c = b+1; c < a; c++) {
                for (int d = c+1; d < a; d++) {
                    int sum = cubes[b] + cubes[c] + cubes[d];
                    if (sum == aCube) {
                        sb.append("Cube = ").append(a).append(", Triple = (")
                          .append(b).append(",").append(c).append(",").append(d).append(")\n");
                    } else if (sum > aCube) break; // 가지치기
                }
            }
        }
    }
}