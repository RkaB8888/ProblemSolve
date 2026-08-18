import java.io.*;
import java.util.*;

/**
 * @description 이 클래스에 대한 동작 설명
 */
 
// 인기 있는 장르의 가장 많이 재생된 노래 2곡 수록 (Tie. 고유번호 오름차순)
// i: 노래 고유번호
// genres[i]: i의 장르
// plays[i]: i 재생 횟수
// 노래는 1이상 10000이하
// 장르는 100개 미만
// !장르가 속한 곡이 1개만 1개만 수록

// HashMap<String,ArrayList<int[]>> seperate
// 장르 이름을 통해 담겨있는 노래의 고유번호와 재생횟수 관리
// HashMap<String,Integer> genreIdx
// 장르의 고유 인덱스 번호 관리
// int[][] sum
// 장르 인덱스 번호를 통해 장르 누적 횟수 저장 (장르 번호, 횟수 합)
// sum을 내림차순 정렬하여 [0]의 장르 번호를 가져오고
// 장르 이름을 통해 담겨있는 노래의 합계 관리
// 전부 분류하고 seperate의 리스트를 하나씩 
class Solution {
    public int[] solution(String[] genres, int[] plays) {
        List<List<int[]>> seperate = new ArrayList<>();
        Map<String,Integer> genreIdx = new HashMap<>(); // 장르의 sum배열에 접근하기 위한 고유번호
        int[][] sum = new int[100][2]; // 장르 고유번호, 합계
        int genreLen = 0;
        for(int i = 0 ; i < genres.length ; i++) {
            String genre = genres[i];
            int play = plays[i];

            if(!genreIdx.containsKey(genre)) {
                genreIdx.put(genre,genreLen);
                genreLen++;
            }
            int gIdx = genreIdx.get(genre);

            if(seperate.size() < genreLen) { // 새로운 장르 생성
                seperate.add(new ArrayList<>());
            }
            List<int[]> list = seperate.get(gIdx);
            list.add(new int[]{i,play});

            sum[gIdx][0] = gIdx;
            sum[gIdx][1] += play;
        }

        for(int i = 0 ; i < genreLen ; i++) {
            List<int[]> list = seperate.get(i);
            list.sort((a,b)-> {
                if(a[1]==b[1]) return Integer.compare(a[0],b[0]); 
                else return Integer.compare(b[1],a[1]);
            });
        }
        Arrays.sort(sum, (a,b) -> Integer.compare(b[1],a[1]));

        List<Integer> result = new ArrayList<>();
        for(int i = 0 ; i < genreLen ; i++) {
            int gIdx = sum[i][0];
            List<int[]> sep = seperate.get(gIdx);
            result.add(sep.get(0)[0]);
            if(sep.size()>1) {
                result.add(sep.get(1)[0]);
            }
        }
        int[] answer = new int[result.size()];
        for(int i = 0 ; i < result.size() ; i++) {
            answer[i] = result.get(i);
        }
        return answer;
    }
}