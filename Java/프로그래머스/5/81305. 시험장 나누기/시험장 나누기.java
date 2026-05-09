import java.io.*;
import java.util.*;

/**
 * @description 이 클래스에 대한 동작 설명
 */

// k는 1이상 10,000이하
// num의 길이는 k이상 10,000이하 / num의 값은 1이상 10,000이하
// link의 길이는 num의 길이와 같다

// 이진 트리를 k-1번 끊어서 k개의 그룹으로 고르게 분할하기
// root에서의 시험장 최대 인원수->최소 인원, 총 인원수->최대 인원의 이분 탐색을 한다.
// 부모에 더했을 때 기준을 안 넘으면 합치고, 기준을 넘는다면 분리한다.
// 양쪽 자식을 모두 더했을 때만 기준을 넘으면, 작은 쪽을 부모랑 합하고 큰 쪽은 분리한다.
// 분리된 노드는 seperated에 체크한다.
// 분리된 갯수가 k보다 커지면 실패 -> 기준을 높인다. 
// k보다 작으면 성공 -> 기준을 낮춘다.
 
class Solution {
    int[] num;
    int[][] links;
    int cuts;

    // line을 기준으로 몇번 잘라내는지 계산
    private int dfs(int node, int line) {
        if(node==-1) return 0;
        int leftSum = dfs(links[node][0], line);
        int rightSum = dfs(links[node][1], line);

        // 둘 두 합쳐도 통과 가능한 경우
        if(num[node]+leftSum+rightSum <= line) {
            return num[node]+leftSum+rightSum;
        }

        // 더 작은 쪽을 합쳐서 통과 가능한 경우
        if(num[node]+Math.min(leftSum,rightSum) <= line) {
            cuts++;
            return num[node]+Math.min(leftSum,rightSum);
        }

        // 양쪽 다 끊어야 하는 경우
        cuts+=2;
        return num[node];
    }

    public int solution(int k, int[] num, int[][] links) {
        this.num = num;
        this.links = links;
        
        int left = 0;
        int right = 0;
        int[] parent = new int[num.length];
        Arrays.fill(parent, -1);

        for(int i = 0 ; i < num.length ; i++) {
            left = Math.max(num[i],left);
            right += num[i];
            if(links[i][0] != -1) parent[links[i][0]] = i;
            if(links[i][1] != -1) parent[links[i][1]] = i;
        }

        int root = 0;
        for(int i = 0 ; i < num.length ; i++) {
            if(parent[i]==-1) {
                root = i;
                break;
            }
        }

        while(left<right){
            int mid = (left+right) >>> 1;
            cuts = 0;
            dfs(root, mid);
            if(cuts < k) {
                right = mid;
            } else {
                left = mid+1;
            }
        }
        return left;
    }
}