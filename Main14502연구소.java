package com.example.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main{
    static int N, M, ans;
    static int[][] map;
    static int[][] wall;
    static ArrayList<int[]> arr;
    static int[] di = { -1, 0, 1, 0 };
    static int[] dj = { 0, 1, 0, -1 };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        wall = new int[3][2];
        map = new int[N][M];
        arr = new ArrayList<>();
        ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == 0)
                    arr.add(new int[] { i, j });
            }
        }
        for (int i = 0; i < arr.size(); i++) {
            wall[0][0] = arr.get(i)[0];
            wall[0][1] = arr.get(i)[1];
            for (int j = i + 1; j < arr.size(); j++) {
                wall[1][0] = arr.get(j)[0];
                wall[1][1] = arr.get(j)[1];
                for (int k = j + 1; k < arr.size(); k++) {
                    wall[2][0] = arr.get(k)[0];
                    wall[2][1] = arr.get(k)[1];

                    for (int a = 0; a < wall.length; a++) {
                        map[wall[a][0]][wall[a][1]] = 1;
                    }
                    bfs();
                    for (int a = 0; a < wall.length; a++) {
                        map[wall[a][0]][wall[a][1]] = 0;
                    }
                }
            }
        }
        System.out.println(ans);
    }
    private static void bfs() {
        int[][] newmap = new int[N][M];
        boolean[][] vi = new boolean[N][M];
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                newmap[i][j] = map[i][j];
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (newmap[i][j] == 2) {
                    vi[i][j] = true;
                    q.offer(new int[] { i, j });
                }
            }
        }
        while (!q.isEmpty()) {
            int[] hi = q.poll();
            int x = hi[0];
            int y = hi[1];
            for (int i = 0; i < di.length; i++) {
                int xx = x + di[i];
                int yy = y + dj[i];
                if (xx >= 0 && xx < N && yy >= 0 && yy < M && newmap[xx][yy] == 0 && vi[xx][yy] == false) {
                    newmap[xx][yy] = 2;
                    vi[xx][yy] = true;
                    q.offer(new int[] { xx, yy });
                }
            }
        }
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (newmap[i][j] == 0) {
                    cnt++;
                }
            }
        }
        ans = ans < cnt ? cnt : ans;
    }
}