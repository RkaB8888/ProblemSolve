import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/**
 * 
 * ? 메모리 ? KB 시간 ? ms
 * @author python98
 *
 */
public class Main {
	static int N;
	static int[] house, time;
	static Place[] place;
	static long result;
	
	static class Place implements Comparable<Place>{
		int dist, wait;

		public Place(int dist, int wait) {
			this.dist = dist;
			this.wait = wait;
		}

		@Override
		public int compareTo(Place o) {
			return o.dist - this.dist;
		}
		
	}
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        house = new int[N];
        time = new int[N];
        place = new Place[N];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++) {
        	house[i] = Integer.parseInt(st.nextToken());
        }
        
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++) {
        	time[i] = Integer.parseInt(st.nextToken());
        }
        
        for(int i = 0 ; i < N ; i++) {
        	place[i] = new Place(house[i],time[i]);
        }
        
        Arrays.sort(place);
        result = place[0].dist;
        int curDist = place[0].dist;
        for(int i = 0 ; i < N ; i++) {
        	result += curDist - place[i].dist; // 이동 시간 계산
        	curDist = place[i].dist;
        	if(result < place[i].wait) {
        		result = place[i].wait;
        	}
        }
        result += curDist;
        System.out.print(result);
    }

}