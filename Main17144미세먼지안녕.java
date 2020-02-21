import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main{
	static int R, C, T,machinex1,machinex2,ans;
	static int[][] map;
	static int[][] windgo;
	static Queue<int[]> q;
	static int[] di = {0, -1, 0, 1, 0 }; //제자리 상 우 하 좌
	static int[] dj = {0,  0, 1, 0,-1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		T = sc.nextInt();
		map = new int[R][C];
		windgo= new int[R][C];
		q = new LinkedList<>();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] > 0)
					q.offer(new int[] {i,j,map[i][j]}); // 지금 미세먼지가 있는칸
			}
		}
		
		for(int i=0; i<R; i++) {
			if(map[i][0]==-1) {
				machinex1=i;     //청정기위치1
				machinex2=i+1;   //청정기위치2
				break;
			}
		}
		
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if (i==0 && j>0 || i==R-1 && j>0) {
					windgo[i][j]=4;     //좌
				}else if((i==machinex1 || i==machinex2) && j>0 && j<C-1) {
					windgo[i][j]=2;       //우
				}else if((j==C-1 && i<=machinex1 && i>0) || 
						j==0 && i>machinex2) {   //상
					windgo[i][j]=1; 
				}else if(j==0 && i<machinex1 ||
						j==C-1 && i>=machinex2 && i<R-1) {  //하
					windgo[i][j]=3;
				}
			}
		}
		for(int i=0; i<T; i++) {
			spread();
			wind();
		}
		ans=0;
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				ans+=map[i][j];
			}
		}
		System.out.println(ans+2);
	}

	private static void wind() {
		map= new int[R][C];
		map[machinex1][0]=-1;
		map[machinex2][0]=-1;
		int size=q.size();
		for(int n=0; n<size; n++) {
			int[] item= q.poll();
			int x = item[0];
			int y = item[1];
			int dust = item[2];
			int ii= x+di[windgo[x][y]];
			int jj= y+dj[windgo[x][y]];
			if(map[ii][jj]==-1) {
				continue;
			}
			map[ii][jj]=dust;
			q.offer(new int[] {ii,jj,dust});
		}
	}

	private static void spread() {
		int size = q.size();
		for (int n = 0; n < size; n++) {
			int[] item = q.poll();
			int x = item[0];
			int y = item[1];
			int dust= item[2]/5;
			for (int i = 0; i < di.length; i++) {
				int xx = x + di[i];
				int yy = y + dj[i];
				if (dust>0 && xx >= 0 && xx < R && yy >= 0 && yy < C 
						&& map[xx][yy] != -1 ) {
					map[xx][yy] = map[xx][yy]+dust;
					map[x][y] = map[x][y]-dust;
				} else continue;
			}
		}
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(map[i][j]>0) {
					q.offer(new int[] {i,j,map[i][j]});
				}
			}
		}
	}
}