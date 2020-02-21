import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main{
	static int N, M, ans;;
	static int[][] map;
	static int[][] p;
	static List<int[]> list;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt(); // 남길치킨집수
		map = new int[N][N];
		list = new ArrayList<int[]>();
		ans = Integer.MAX_VALUE;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt(); // 0빈칸 1집 2치킨집
				if (map[i][j] == 2) {
					list.add(new int[] { i, j }); // 치킨집이면 리스트에넣기
				}
			}
		}

		int peup = list.size() - M; // 페업시킬 치킨집수 (조합돌릴갯수)
		p = new int[peup][2];
	//	System.out.println(peup);
		choose(peup, -1, 0); // 페업할치킨집수, 포문시작인덱스, 몇번째까지골랏나
		System.out.println(ans);
	}

	private static void choose(int num, int index, int cnt) {
		if (num == 0 || cnt >= num) {
			solve();
			return;
		}
		for (int i = index + 1; i < list.size(); i++) {
			p[cnt][0] = list.get(i)[0];
			p[cnt][1] = list.get(i)[1];
			choose(num, i, cnt + 1);
		}
	}

	private static void solve() {
		int dsum = 0;
		int[][] newmap = new int[N][N];
		for (int i = 0; i < N; i++) { // 뉴맵복사떠서
			for (int j = 0; j < N; j++) {
				newmap[i][j] = map[i][j];
			}
		}

		for (int i = 0; i < p.length; i++) { // 페업시킨다
			newmap[p[i][0]][p[i][1]] = 0;
		}

    //	for (int[] a : newmap)
	//	System.out.println(Arrays.toString(a));
	//	System.out.println();
		
		ArrayList<int[]> chick= new ArrayList<int[]>();
		for(int i=0; i<N; i++){
			for(int j=0; j<N; j++){
				if(newmap[i][j]==2){
					chick.add(new int[]{i,j});
				}
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (newmap[i][j] == 1) { // 집이면 치킨집부터 거리잼
					//System.out.println("하이");
					int min = Integer.MAX_VALUE;
					for (int k = 0; k < chick.size(); k++) {
						int a = chick.get(k)[0];
						int b = chick.get(k)[1];
						int dist = Math.abs(a - i) + Math.abs(b - j);
						min = dist < min ? dist : min;
					}
					dsum+=min;
				}
			}
		}
		ans=dsum<ans?dsum:ans;
	}
}