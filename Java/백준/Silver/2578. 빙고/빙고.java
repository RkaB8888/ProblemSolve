
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] map = new int[5][5];
		for(int i = 0 ; i < 5 ; i++) {
			String[] s = br.readLine().split(" ");
			for(int j = 0 ; j < 5 ; j++) {//빙고 작성
				map[i][j] = Integer.parseInt(s[j]);
			}
		}
		int result = 0, bingo = 0;
		int[] rowLine = new int[5];
		int[] colLine = new int[5];
		int[] crossLine = new int[2];
		out : for(int i = 0 ; i < 5 ; i++) {
			String[] s = br.readLine().split(" ");
			for(int j = 0 ; j < 5 ; j++) {// 숫자 부름
				int[] position = findPosition(map,Integer.parseInt(s[j]));//숫자 찾음
				
				rowLine[position[0]]++;
				colLine[position[1]]++;
				if(position[0]==position[1]) {
					crossLine[0]++;
					if(crossLine[0]==5) bingo++; 
				}
				if(position[0]==4-position[1]) {
					crossLine[1]++;
					if(crossLine[1]==5) bingo++; 
				}
				
				if(rowLine[position[0]]==5) bingo++;
				if(colLine[position[1]]==5) bingo++;
				
				if(bingo>=3) {
					result = 5*i+j+1;
					break out;
				}
			}
		}
		System.out.println(result);
	}
	
	public static int[] findPosition(int[][] map, int num) {
		int[] position = new int[2];
		out : for(int i = 0 ; i < 5 ; i++) {
			for(int j = 0 ; j < 5 ; j++) {
				if(map[i][j]==num) {
					position[0]=i;
					position[1]=j;
					break out;
				}
			}
		}
		return position;
		
	}

}
