import java.io.*;
import java.util.*;

/**
 * @description 계산
 */
 
// N: 아파트의 개수, 200,000,000 이하 자연수
// stations: 기지국이 설치된 아파트 번호가 담긴 배열, 크기는 10,00이하, 오름차순 정렬, N 이하의 자연수
// W: 전파 도달 거리, 10,000 이하 자연수
// 최소한의 기지국 설치로 모든 아파트가 커버되는 경우 찾기

// 단순 아파트 순회는 2억번이기 때문에 시간 초과 가능성 높음
// N을 순회하는 것이 아닌 stations를 순회하는 방식이 적절할 듯
// stations를 각 구간별 분리 후 사이의 빈 공간을 어떻게 채울지 고민함
// 기지국 설치할 아파트를 W번부터 2*W만큼 건너뛰면서 확인함.
// -> 기지국 설치 시 구분 경계 이상이면 설치 X
// -> 구분 경계 이내면 설치 O
class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int startRange = 1;
        for(int i = 0 ; i < stations.length ; i++) {
            if(stations[i]-w > startRange) { // 기준 이전 전부 커버가 안되는, 1-base
                int start = startRange;
                int end = stations[i]-w;
                answer+=((end-start)+(2*w))/(2*w+1);
            }
            startRange = stations[i]+w+1;
        }
        if(n+1>startRange) {
            int start = startRange;
            int end = n+1;
            answer+=((end-start)+(2*w))/(2*w+1);
        }
        return answer;
    }
}