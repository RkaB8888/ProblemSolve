import java.io.*;
import java.util.*;

/**
 * @description Sorting + HashMap
 */
 
// 인기 있는 장르의 가장 많이 재생된 노래 2곡 수록 (Tie. 고유번호 오름차순)
// i: 노래 고유번호
// genres[i]: i의 장르
// plays[i]: i 재생 횟수
// 노래는 1이상 10000이하
// 장르는 100개 미만
// !장르가 속한 곡이 1개만 1개만 수록

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        Map<String,Integer> genreIdx = new HashMap<>(); // 장르의 sum배열에 접근하기 위한 고유번호
        int[][] sum = new int[100][2]; // 장르 고유번호, 합계
        int[][] top1 = new int[100][2];
        int[][] top2 = new int[100][2];
        int genreLen = 0;

        for (int i = 0; i < 100; i++) {
            top1[i][0] = -1;
            top1[i][1] = -1;
            top2[i][0] = -1;
            top2[i][1] = -1;
        }

        int n = genres.length;
        for(int i = 0 ; i < n ; i++) {
            String genre = genres[i];
            int play = plays[i];

            Integer gIdx = genreIdx.get(genre);
            if(gIdx == null) {
                gIdx = genreLen++;
                genreIdx.put(genre,gIdx);
                sum[gIdx][0] = gIdx;
            }
            
            sum[gIdx][1] += play;

            if(play > top1[gIdx][1]) {
                top2[gIdx][0] = top1[gIdx][0];
                top2[gIdx][1] = top1[gIdx][1];
                top1[gIdx][0] = i;
                top1[gIdx][1] = play;
            } else if(play > top2[gIdx][1]) {
                top2[gIdx][0] = i;
                top2[gIdx][1] = play;
            }
        }

        Arrays.sort(sum,0,genreLen,(a,b)->Integer.compare(b[1],a[1]));
        int[] result = new int[genreLen*2];
        int cnt = 0;

        for(int i = 0 ; i < genreLen ; i++) {
            int gIdx = sum[i][0];
            if(top1[gIdx][0]!=-1) result[cnt++] = top1[gIdx][0];
            if(top2[gIdx][0]!=-1) result[cnt++] = top2[gIdx][0];
        }
        return Arrays.copyOf(result,cnt);
    }
}