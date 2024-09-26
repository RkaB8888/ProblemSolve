import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 
 * @author python98
 * 이 프로그램은 주어진 격자(map) 내에서 1로 표시된 구역을 찾아 연결된 모든 1들을 그룹화하고,
 * 이 그룹의 개수를 세는 기능을 합니다. 
 * DFS(깊이 우선 탐색) 알고리즘을 사용하여 그룹을 찾고 "floodfill" 기법으로 연결된 구역을 모두 탐색합니다.
 * 
 * 전체적으로 프로그램은 여러 테스트 케이스를 처리하며, 각 테스트 케이스마다 새로운 격자(map)가 주어집니다.
 * 
 * 메모리 사용량: 13656 KB
 * 실행 시간: 100 ms
 *
 */

public class Main {
    static int T, R, C, K, map[][], cnt, drdc[][] = {{-1,0},{1,0},{0,-1},{0,1}};
    static List<int[]> list;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        // T는 테스트 케이스의 수입니다.
        T = Integer.parseInt(br.readLine());
        
        // 각각의 테스트 케이스를 처리합니다.
        for(int tc = 1 ; tc <= T ; tc++) {
        	
        	// 격자의 열(C)과 행(R) 크기, 1로 표시된 구역의 수(K)를 입력받습니다.
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	C = Integer.parseInt(st.nextToken());
        	R = Integer.parseInt(st.nextToken());
        	K = Integer.parseInt(st.nextToken());
        	
        	// 격자를 초기화합니다. 모든 값은 0으로 설정됩니다.
        	map = new int[R][C];
        	
        	// 1로 표시된 구역의 좌표를 저장할 리스트를 초기화합니다.
        	list = new ArrayList<>(K);
        	
        	// 구역을 구분하기 위해 사용하는 초기 값을 2로 설정합니다.
        	cnt = 2;
        	
        	// K개의 1로 표시된 구역의 좌표를 입력받아 격자에 1로 표시하고 리스트에 추가합니다.
        	for(int i = 0 ; i < K ; i++) {
        		st = new StringTokenizer(br.readLine());
        		int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken());
        		map[y][x] = 1;
        		list.add(new int[] {y,x});
        	}
        	
        	// 리스트에 저장된 좌표를 하나씩 처리하면서, 
        	// 연결된 1의 그룹을 찾아내고 그 그룹을 구분합니다.
        	for(int[] d : list) {
        		// 아직 방문하지 않은 1로 표시된 구역을 발견하면 floodfill을 호출하여 연결된 모든 구역을 탐색합니다.
        		if(map[d[0]][d[1]]==1) {
                    map[d[0]][d[1]] = cnt;
        			floodfill(d[0],d[1]);
        			cnt++; // 다음 그룹을 위한 값을 증가시킵니다.
        		}
        	}
        	
        	// 최종적으로 그룹의 수를 저장합니다. (cnt는 2에서 시작하므로 2를 빼서 결과에 저장합니다)
        	sb.append(cnt-2).append('\n');
        }
        
        // 모든 테스트 케이스의 결과를 출력합니다.
        System.out.println(sb);
    }

    // floodfill 함수는 DFS를 사용하여 연결된 모든 1의 구역을 탐색하고, 그 구역을 하나의 그룹으로 표시합니다.
    private static void floodfill(int row, int col) {
    	int r, c;
    	
    	// 현재 위치에서 상하좌우로 이동하여 연결된 1의 구역을 탐색합니다.
    	for(int i = 0 ; i < 4 ; i++) {
    		r = row + drdc[i][0]; // 새로운 행 위치
    		c = col + drdc[i][1]; // 새로운 열 위치
    		
    		// 새로운 위치가 격자 범위 내에 있고, 그 위치가 1로 표시되어 있으면,
    		// 해당 위치를 현재 그룹으로 표시하고, 재귀적으로 floodfill을 호출하여 계속 탐색합니다.
    		if(r<0 || c<0 || r>=R || c>=C || map[r][c]!=1) continue;
    		
    		map[r][c] = cnt; // 현재 그룹 번호로 표시합니다.
    		floodfill(r,c);  // 재귀적으로 연결된 구역을 탐색합니다.
    	}
    }
}