package com.company;

import java.util.ArrayList;
import java.util.Collections;

public class MinimumConvexHull {

    private static Point[] points;

    public MinimumConvexHull() {
        initArray();
    }

    /** Алгоритм Грэхема
     */

    public void run() {

        int n = points.length;

        //забиваем массив индексов
        int[] idx = new int[n];
        for (int i = 0; i < n; ++i) idx[i] = i+1;

        //1. самая первая по X точка в 0-й элемент
        for (int i = 0; i < n; ++i) {
            if (points[i].x < points[0].x) {
                int t = points[i].x;
                points[i].x = points[0].x;
                points[0].x = t;
            }
        }

        //2. сортируем все точки кроме 0-й по положению относительно 0-й
        for (int i = 2; i < n; ++i) {
            int j = i;
            while (j > 1 && rotate(points[idx[0]-1], points[idx[j - 1]-1], points[idx[j]-1]) < 0) {
                int t = idx[j];
                idx[j] = idx[j - 1];
                idx[j - 1] = t;
                --j;
            }
        }

        ArrayList<Integer> res = new ArrayList<Integer>(); //результат

        res.add(idx[0]);
        res.add(idx[1]);

        for (int i = 2; i < n; ++i) {
            if (rotate(points[res.get(res.size() - 2)-1], points[res.get(res.size() - 1)-1], points[idx[i]-1]) < 0) {
                res.remove(res.size() - 1);
            }
            res.add(idx[i]);
        }

        Collections.sort(res);

        System.out.println(String.format("%s", res.toString()));

    }

    /**
     * возвращает отрицательное значение, если вектор от B к C перемещается по часовой стрелки
     * возвращает положительное значение, если вектор от B к C перемещается против часовой стрелки
     */
    private int rotate(Point A, Point B, Point C) {
        int res = (B.x - A.x) * (C.y - B.y) - (B.y - A.y) * (C.x - B.x);
        return res;
    }

    private void initArray() {
        points = new Point[]{
                new Point(2, 3),
                new Point(4, 4),
                new Point(3, 7),
                new Point(6, 5),
                new Point(7, 2),
        };
    }

    private class Point {
        private int x, y;

        public Point(int X, int Y) {
            x = X;
            y = Y;
        }
    }
}