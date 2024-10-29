import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int x1, y1, x2, y2;
    static int x3, y3, x4, y4;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 첫 번째 선분의 두 점 (x1, y1) - (x2, y2)
        x1 = Integer.parseInt(st.nextToken());
        y1 = Integer.parseInt(st.nextToken());
        x2 = Integer.parseInt(st.nextToken());
        y2 = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        // 두 번째 선분의 두 점 (x3, y3) - (x4, y4)
        x3 = Integer.parseInt(st.nextToken());
        y3 = Integer.parseInt(st.nextToken());
        x4 = Integer.parseInt(st.nextToken());
        y4 = Integer.parseInt(st.nextToken());

        // 두 선분이 교차하는지 여부 출력
        System.out.println(isIntersect() ? 1 : 0);
    }

    // 두 선분이 교차하는지 확인하는 메서드
    private static boolean isIntersect() {
        int ccw1 = ccw(x1, y1, x2, y2, x3, y3);
        int ccw2 = ccw(x1, y1, x2, y2, x4, y4);
        int ccw3 = ccw(x3, y3, x4, y4, x1, y1);
        int ccw4 = ccw(x3, y3, x4, y4, x2, y2);

        // 두 선분이 서로 다른 방향으로 지나가는 경우 (교차)
        if (ccw1 * ccw2 < 0 && ccw3 * ccw4 < 0) {
            return true;
        }

        // 끝점이 일치하거나 접하는 경우 처리 (겹치는 경우)
        if (ccw1 == 0 && onSegment(x1, y1, x2, y2, x3, y3)) return true;
        if (ccw2 == 0 && onSegment(x1, y1, x2, y2, x4, y4)) return true;
        if (ccw3 == 0 && onSegment(x3, y3, x4, y4, x1, y1)) return true;
        if (ccw4 == 0 && onSegment(x3, y3, x4, y4, x2, y2)) return true;

        return false;
    }

    // CCW 계산: (x1, y1) → (x2, y2) → (x3, y3)의 방향
    private static int ccw(int x1, int y1, int x2, int y2, int x3, int y3) {
        long crossProduct = (long) (x2 - x1) * (y3 - y1) - (long) (y2 - y1) * (x3 - x1);
        return Long.compare(crossProduct, 0); // 양수: 반시계, 음수: 시계, 0: 일직선
    }

    // (x3, y3)가 (x1, y1) - (x2, y2) 선분 위에 있는지 확인
    private static boolean onSegment(int x1, int y1, int x2, int y2, int x3, int y3) {
        return Math.min(x1, x2) <= x3 && x3 <= Math.max(x1, x2) &&
               Math.min(y1, y2) <= y3 && y3 <= Math.max(y1, y2);
    }
}