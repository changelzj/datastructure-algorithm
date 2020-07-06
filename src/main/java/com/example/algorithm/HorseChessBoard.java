package com.example.algorithm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HorseChessBoard {
    
    private static int X = 6;
    private static int Y = 6;

    private static int[][] chessBoard = new int[X][Y]; //棋盘
    private static boolean[] visited = new boolean[X * Y]; //记录某个位置是否走过

    private static boolean finished = false;// 是否遍历完成
    
    public static void main(String[] args) {
        
        int row = 6;
        int col = 1;
        
        traver(chessBoard, row - 1, col - 1, 1);
        
        // 输出棋盘
        for (int[] rows : chessBoard) {
            for (int step : rows) {
                System.out.print("第" + (step-1)+"步"+ "\t");
            }
            System.out.print("\n");
        }
        
        
        
    }
    
    public static void traver(int[][] board, int row, int col, int step) {
        board[row][col] = step;
        visited[row * X + col] = true;
        List<Point> points = nextSteps(new Point(col, row));
        sort(points);
        while (!points.isEmpty()) {
            Point point = points.remove(0);
            if (!visited[point.y * X + point.x]) {
                traver(board, point.y, point.x, step + 1);
            }
        }
        
        if (step < X*Y && !finished) {
            board[row][col] = 0;
            visited[row * X + col] = false;
        } else {
            finished = true;
        }
    }
    
    
    public static void sort(List<Point> points) {
        points.sort(new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                return nextSteps(p1).size() - nextSteps(p2).size();
            }
        });
    }
    
    public static List<Point> nextSteps(Point point) {
        List<Point> points = new ArrayList<>();
        
        if (point.x - 2 >= 0 && point.y - 1 >= 0) {
            points.add(new Point(point.x - 2, point.y - 1));
        }
        if (point.x - 1 >= 0 && point.y - 2 >= 0) {
            points.add(new Point(point.x - 1, point.y - 2));
        }
        if (point.x - 2 >= 0 && point.y + 1 < Y) {
            points.add(new Point(point.x - 2, point.y + 1));
        }
        if (point.x - 1 >= 0 && point.y + 2 < Y) {
            points.add(new Point(point.x - 1, point.y + 2));
        }

        if (point.x + 1 < X && point.y - 2 >= 0) {
            points.add(new Point(point.x + 1, point.y - 2));
        }
        if (point.x + 2 < X && point.y - 1 >= 0) {
            points.add(new Point(point.x + 2, point.y - 1));
        }
        if (point.x + 2 < X && point.y + 1 < Y) {
            points.add(new Point(point.x + 2, point.y + 1));
        }
        if (point.x + 1 < X && point.y + 2 < Y) {
            points.add(new Point(point.x +1, point.y +2));
        }
        return points;
    }
    
    
    public static class Point {
        public int x;
        public int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
