import java.io.*;
import java.util.*;

/**
 * @description 구현
 */

// 주어진 조건을 만족하는 행렬 변환을 구현
// 실제 행렬을 옮기는 과정을 직접 구현하면 시간복잡도는 최대 (100,000(행이동) or 100004(테두리 회전)) * 10000 = 10억번

// 실제로 옮기지 않고 마지막에 한번에 옮기는 과정이 필요할 듯?
// 결국 각 요소마다 이동횟수 관리는 시간복잡도가 같음

// 행이동은 직접 움직이지 않고 이동된 행이동 횟수만 기억하고 테두리 회전이 발생하면 그 때 한번에 처리하는 방법?
// 회전을 하면 행이동의 규칙이 바뀌고, 행이동을 하면 회전의 규칙이 바뀜

// 왼쪽 테두리 (1개의 덱)
// -> 행 이동 시 한 칸 아래로 이동
// -> 회전 시 제일 윗 칸을 1행 2열로 보내고 제일 아랫 칸에 막행 2열이 들어옴
// 오른쪽 테두리 (1개의 덱)
// -> 행 이동 시 한 칸 아래로 이동
// -> 회전 시 제일 윗 칸에 1행 막열-1이 들어오고 제일 아랫 칸을 막행 막열-1로 보냄
// 중간 영역 (행 개수의 덱을 담는 덱)
// -> 행 이동 시 모든 열의 행이 아래로 이동하고, 제일 막열을 제일 위로 올림
// -> 회전 시 제일 윗열의 마지막 요소를 오른쪽 테두리로 보내고 왼쪽 테두리에서 온 요소를 처음 요소에 넣어줌
// -> 제일 아랫열의 마지막 요소에 오른쪽 테두리에서 온 요소를 넣고 처음 요소를 왼쪽 테두리에 넘겨줌
class Solution {
    private void shift(ArrayDeque<Integer> leftArea,ArrayDeque<ArrayDeque<Integer>> middleArea,ArrayDeque<Integer> rightArea){
        int leftLast = leftArea.pollLast();
        leftArea.addFirst(leftLast);
        int rightLast = rightArea.pollLast();
        rightArea.addFirst(rightLast);
        ArrayDeque<Integer> middleLast = middleArea.pollLast();
        middleArea.addFirst(middleLast);
    }
    private void rotate(ArrayDeque<Integer> leftArea,ArrayDeque<ArrayDeque<Integer>> middleArea,ArrayDeque<Integer> rightArea){
        ArrayDeque<Integer> middleFirstRow = middleArea.pollFirst();
        ArrayDeque<Integer> middleLastRow = middleArea.pollLast();

        // 1을 2자리에
        int leftFirst = leftArea.pollFirst();
        middleFirstRow.addFirst(leftFirst);

        // 2를 3자리에
        int middleFirstRowLast = middleFirstRow.pollLast();
        rightArea.addFirst(middleFirstRowLast);

        // 9를 8자리에
        int rightLast = rightArea.pollLast();
        middleLastRow.addLast(rightLast);

        // 8을 7자리에
        int middleLastRowFirst = middleLastRow.pollFirst();
        leftArea.addLast(middleLastRowFirst);

        middleArea.addFirst(middleFirstRow);
        middleArea.addLast(middleLastRow);
    }
    private int[][] convert(ArrayDeque<Integer> leftArea,ArrayDeque<ArrayDeque<Integer>> middleArea,ArrayDeque<Integer> rightArea,int rlen, int clen){
        int[][] result = new int[rlen][clen];
        for(int i = 0 ; i < rlen ; i++) {
            result[i][0] = leftArea.pollFirst();
            result[i][clen-1] = rightArea.pollFirst();
            ArrayDeque<Integer> row = middleArea.pollFirst();
            for(int j = 1 ; j < clen-1 ; j++) {
                result[i][j] = row.pollFirst();
            }
        }
        return result;
    }
    public int[][] solution(int[][] rc, String[] operations) {
        int rlen = rc.length;
        int clen = rc[0].length;

        // 위쪽, 왼쪽이 덱의 첫번째에 오게끔 통일
        ArrayDeque<Integer> leftArea = new ArrayDeque<>();
        ArrayDeque<ArrayDeque<Integer>> middleArea = new ArrayDeque<>();
        ArrayDeque<Integer> rightArea = new ArrayDeque<>();

        for(int i = 0 ; i < rlen ; i++) {
            // 왼쪽 테두리 처리
            leftArea.addLast(rc[i][0]);
            // 오른쪽 테두리 처리
            rightArea.addLast(rc[i][clen-1]);
            // 중간 영역 처리
            ArrayDeque<Integer> row = new ArrayDeque<>();
            for(int j = 1 ; j < clen-1 ; j++) {
                row.addLast(rc[i][j]);
            }
            middleArea.addLast(row);
        }

        for(String oper : operations) {
            if(oper.equals("Rotate")){
                rotate(leftArea,middleArea,rightArea);
            } else if(oper.equals("ShiftRow")) {
                shift(leftArea,middleArea,rightArea);
            }
        }
        return convert(leftArea,middleArea,rightArea,rlen,clen);
    }
}