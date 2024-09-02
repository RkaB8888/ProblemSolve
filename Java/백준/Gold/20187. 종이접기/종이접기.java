import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int K;
	static char[] orders;
	static int h;
	static int[][] map;
	static final int[] LR = { 1, 0, 3, 2 };
	static final int[] UD = { 2, 3, 0, 1 };
	static int mapR, mapC;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		orders = new char[2*K];
		for(int i = 0, iEnd = 2*K ; i < iEnd ; i++) {
			orders[i] = st.nextToken().charAt(0);
		}
		h = Integer.parseInt(br.readLine());
		
		mapR = 1; mapC = 1;
		map = new int[mapR][mapC];
		map[0][0] = h;
		process();
		for(int i = 0 ; i < mapR ; i++) {
			for(int j = 0 ; j < mapC ; j++) {
				sb.append(map[i][j]).append(' ');
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}

	public static void process() {
		for (int i = 2 * K - 1; i >= 0; i--) {
			char order = orders[i];
			switch (order) {
			case 'R':
				genMapR();
				break;
			case 'L':
				genMapL();
				break;
			case 'U':
				genMapU();
				break;
			case 'D':
				genMapD();
				break;
			}
		}
	}
	public static void genMapR() {
		int[][] halfMap = new int[mapR][mapC];
		for(int i = 0 ; i < mapR ; i++) {
			for(int j = 0 ; j < mapC ; j++) {
				halfMap[i][j] = LR[map[i][mapC-j-1]];
			}
		}
		int[][] newMap = new int[mapR][2*mapC];
		for(int i = 0 ; i < mapR ; i++) {
			for(int j = 0 ; j < mapC ; j++) {
				newMap[i][j] = halfMap[i][j];
				newMap[i][j+mapC] = map[i][j];
			}
		}
		map = newMap;
		mapC *= 2;
//		printMap(newMap);
		
	}
	public static void genMapL() {
		int[][] halfMap = new int[mapR][mapC];
		for(int i = 0 ; i < mapR ; i++) {
			for(int j = 0 ; j < mapC ; j++) {
				halfMap[i][j] = LR[map[i][mapC-j-1]];
			}
		}
		int[][] newMap = new int[mapR][2*mapC];
		for(int i = 0 ; i < mapR ; i++) {
			for(int j = 0 ; j < mapC ; j++) {
				newMap[i][j+mapC] = halfMap[i][j];
				newMap[i][j] = map[i][j];
			}
		}
		map = newMap;
		mapC *= 2;
//		printMap(newMap);
	}
	public static void genMapU() {
		int[][] halfMap = new int[mapR][mapC];
		for(int i = 0 ; i < mapR ; i++) {
			for(int j = 0 ; j < mapC ; j++) {
				halfMap[i][j] = UD[map[mapR-i-1][j]];
			}
		}
		int[][] newMap = new int[mapR*2][mapC];
		for(int i = 0 ; i < mapR ; i++) {
			for(int j = 0 ; j < mapC ; j++) {
				newMap[i+mapR][j] = halfMap[i][j];
				newMap[i][j] = map[i][j];
			}
		}
		map = newMap;
		mapR *= 2;
//		printMap(newMap);
	}
	public static void genMapD() {
		int[][] halfMap = new int[mapR][mapC];
		for(int i = 0 ; i < mapR ; i++) {
			for(int j = 0 ; j < mapC ; j++) {
				halfMap[i][j] = UD[map[mapR-i-1][j]];
			}
		}
		int[][] newMap = new int[mapR*2][mapC];
		for(int i = 0 ; i < mapR ; i++) {
			for(int j = 0 ; j < mapC ; j++) {
				newMap[i][j] = halfMap[i][j];
				newMap[i+mapR][j] = map[i][j];
			}
		}
		map = newMap;
		mapR *= 2;
//		printMap(newMap);
	}
	public static void printMap(int[][] newMap) {
		StringBuilder sbtemp = new StringBuilder();
		for(int i = 0 ; i < mapR ; i++) {
			for(int j = 0 ; j < mapC ; j++) {
				sbtemp.append(newMap[i][j]).append(' ');
			}
			sbtemp.append('\n');
		}
		sbtemp.append('\n').append("/////////////////////").append('\n');
		System.out.println(sbtemp);
	}
}