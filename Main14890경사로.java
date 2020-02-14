import java.util.Scanner;

public class Main{
    static int N, L, ans;
    static int[][] map;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
            N = sc.nextInt();
            L = sc.nextInt();
            map = new int[N][N];
            ans = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    map[i][j] = sc.nextInt();
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (i == 0) {
                        check(i, j, 1, 0, 1); // 밑으로보면서 체크하기
                    }
                    if (j == 0) {
                        check(i, j, 0, 1, 1); // 오른쪽으로보면서 체크하기
                    }
                }
            }
            System.out.println(ans);
    }

    private static void check(int X, int Y, int di, int dj, int cnt) {
        int xx = X + di;
        int yy = Y + dj;

        if (xx == N || yy == N) { // 끝까지다다르면 가능한거이므로 앤서증가하고 마무리
            ans++;
            return;
        }

        if (xx >= 0 && xx < N && yy >= 0 && yy < N) {
            if (map[xx][yy] == map[X][Y]) {
                check(xx, yy, di, dj, cnt + 1); // 지금이랑 같은높이면 계속탐색
            } else if (map[X][Y]-map[xx][yy]==1) {// 지금높이보다 낮으면 한칸더봐서 같은높이 연속2개면
                boolean f = false;
                for (int k = 1; k < L; k++) {
                    int xxx = xx + di * k;
                    int yyy = yy + dj * k;
                    if (xxx >= 0 && xxx < N && yyy >= 0 && yyy < N
                            && map[xxx][yyy] == map[xx][yy]) {
                        continue;
                    } else {
                        f = true;
                    }
                }
                if (f == false)
                    check(xx + di * (L - 1), yy + dj * (L - 1), di, dj,0);

            } else if (map[xx][yy] - map[X][Y] == 1) { // 지금높이보다 높을경우 왼쪽을보거나 위를본다.
                boolean f = false;
                for (int k = 1; k < L; k++) {
                    int xxx = X - (di * k); // 0,1=>0,-1 , 1,0=>-1,0
                    int yyy = Y - (dj * k);
                    if (xxx >= 0 && xxx < N && yyy >= 0 && yyy < N
                            && map[xxx][yyy] == map[X][Y]) {
                        continue;
                    } else {
                        f = true;
                    }
                }
                if (f == false && cnt>= L)
                    check(xx, yy, di, dj,1);
            }
        }
    }
}