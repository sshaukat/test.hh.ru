package com.company;


public class Main {

    public static void main(String[] args) {

        System.out.println("\n=== Minimum Convex Hull =====\n");
        MinimumConvexHull mch = new MinimumConvexHull();
        mch.run();

        System.out.println("\n=== Balance Weights =====\n");
        BalanceWeights bw = new BalanceWeights();
        bw.run();

    }
}
