import java.util.Arrays;
import java.util.Scanner;

public class Main{
	static int N, M, rx, ry, rd, cnt;
	static int[][] map;
	static int[][] vi;
	static int[] di = { -1, 0, 1, 0 }; // 북 동 남 서
	static int[] dj = { 0, 1, 0, -1 }; // 0,1,2,3

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		vi = new int[N][M];
		rx = sc.nextInt();
		ry = sc.nextInt();
		rd = sc.nextInt();
		cnt = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		vi[rx][ry] = 1; // 현재위치청소
		solve(0); // 0은 방향 한번도 안바꿨단뜻

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (vi[i][j] == 1)
					cnt++;
			}
		}

//		for (int[] a : vi)
//			System.out.println(Arrays.toString(a));

		System.out.println(cnt);
	}

	private static void solve(int cnt) {
		if (cnt >= 4) { // 네방향다봤으면
			/*
			 * if (rx == 9 && ry == 3) System.out.println("노답");
			 */
			// 바라보는방향대로 한칸후진이 가능한지 봄 0->2 1->3 2->0 3->1
			int xx = rx + di[(rd + 2) % 4];
			int yy = ry + dj[(rd + 2) % 4];
			/*
			 * if (rx == 9 && ry == 3) System.out.println("노답이 후진하면 "+xx+" "+yy);
			 */
			if (xx >= 0 && xx < N && yy >= 0 && yy < M && (map[xx][yy] != 1) /* && (vi[xx][yy] == 0) */) {
				rx = xx;
				ry = yy; // 후진하고
			//	System.out.println("후진 "+rx + " " + ry);
				// 그방향기준으로 다시탐색
				solve(0);
				return;
			} else { // 후진도 못할 상황이면
				return;
			}
		}
		// 0->3 1->0 2->1 3->2
		int newx = rx + di[(rd + 3) % 4];
		int newy = ry + dj[(rd + 3) % 4];
		if (newx >= 0 && newx < N && newy >= 0 && newy < M && map[newx][newy] != 1  && (vi[newx][newy] == 0) ) {
			// 지금방향에서 왼쪽방향이 갈수잇고 청소하지 않았으면
			rd = (rd + 3) % 4; // 그방향으로 회전하고
			rx = rx + di[rd];
			ry = ry + dj[rd]; // 그방향으로 한칸전진
			vi[rx][ry] = 1;
			//System.out.println(rx + " " + ry);
			solve(0); // 청소하고 그칸기준으로 다시 진행
		} else {
			rd = (rd + 3) % 4; // 그방향으로 회전만하고
			solve(cnt + 1); // 다른방향탐색
		}
	}
}
