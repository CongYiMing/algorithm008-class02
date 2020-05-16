package com.yim.jike;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class HW4 {
    static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int _val) {
            val = _val;
        }

        public TreeNode(int _val, TreeNode l, TreeNode r) {
            val = _val;
            left = l;
            right = r;
        }
    }

    public static void main(String[] args) {
//        lemonadeChange_main();
//        maxProfit_main();
        findContentChildren_main();
    }

    //<editor-fold desc="">


    //</editor-fold>

    //<editor-fold desc="findContentChildren">
    private static void robotSim_main() {
        int[] commands = new int[]{4, -1, 3};
        int[][] obstacles = new int[][]{};
        int i = robotSim(commands, obstacles);
        p(i);
    }

    private static int robotSim(int[] commands, int[][] obstacles) {
        int[] dx = new int[]{0, 1, 0, -1};
        int[] dy = new int[]{1, 0, -1, 0};
        int x = 0, y = 0, di = 0;

        // Encode obstacles (x, y) as (x+30000) * (2^16) + (y+30000)
        Set<Long> obstacleSet = new HashSet();
        for (int[] obstacle : obstacles) {
            long ox = (long) obstacle[0] + 30000;
            long oy = (long) obstacle[1] + 30000;
            obstacleSet.add((ox << 16) + oy);
        }

        int ans = 0;
        for (int cmd : commands) {
            if (cmd == -2)  //left
                di = (di + 3) % 4;
            else if (cmd == -1)  //right
                di = (di + 1) % 4;
            else {
                for (int k = 0; k < cmd; ++k) {
                    int nx = x + dx[di];
                    int ny = y + dy[di];
                    long code = (((long) nx + 30000) << 16) + ((long) ny + 30000);
                    if (!obstacleSet.contains(code)) {
                        x = nx;
                        y = ny;
                        ans = Math.max(ans, x * x + y * y);
                    }
                }
            }
        }

        return ans;
    }

    //</editor-fold>

    //<editor-fold desc="findContentChildren">
    private static void findContentChildren_main() {
        int[] g = new int[]{1, 2, 3};
        int[] s = new int[]{1, 1};
        int i = findContentChildren(g, s);
        p(i);
    }

    private static int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int child = 0;
        int statj = (s.length - 1);
        for (int i = (g.length - 1); i >= 0; i--) {
            int i1 = g[i];
            lable2:
            for (int j = statj; j >= 0; j--) {
                if (s[j] >= i1) {
                    statj = j - 1;
                    child++;
                    break lable2;
                }
            }
        }
        return child;
    }

    //</editor-fold>
    //<editor-fold desc="maxProfit">
    private static void maxProfit_main() {
        int[] array = new int[]{7, 1, 5, 3, 6, 4};
        int i = maxProfit(array);
        p(i);
    }

    private static int maxProfit(int[] prices) {
        int max = 0;
        int i = 0;
        int low;
        int height;
        while (i < (prices.length - 1)) {
            while (i < prices.length - 1 && prices[i] >= prices[i + 1]) {
                i++;
            }
            low = prices[i];
            while (i < prices.length - 1 && prices[i] <= prices[i + 1]) {
                i++;
            }
            height = prices[i];
            max = max + height - low;
        }
        return max;
    }

    private static int maxProfit1(int[] prices) {
        int max = calculate(prices, 0);
        return max;
    }

    private static int calculate(int prices[], int s) {
        if (s >= prices.length)
            return 0;
        int max = 0;
        for (int start = s; start < prices.length; start++) {
            int maxprofit = 0;
            for (int i = start + 1; i < prices.length; i++) {
                if (prices[start] < prices[i]) {
                    int profit = calculate(prices, i + 1) + prices[i] - prices[start];
                    if (profit > maxprofit) {
                        maxprofit = profit;
                    }
                }
            }
            if (maxprofit > max)
                max = maxprofit;
        }
        return max;
    }
    //</editor-fold>

    //<editor-fold desc="lemonadeChange">
    private static void lemonadeChange_main() {
//        int[] bills = new int[]{5, 5, 5, 10, 20};
        int[] bills = new int[]{10, 10};
        boolean b = lemonadeChange(bills);
        p(b);
    }

    private static boolean lemonadeChange(int[] bills) {
        int five = 0, ten = 0;
        for (int bill : bills) {
            if (bill == 5) {
                five++;
            } else if (bill == 10) {
                if (five < 0) {
                    return false;
                }
                five--;
                ten++;
            } else {
                if (five > 0 && ten > 0) {
                    five--;
                    ten--;
                } else if (five >= 3) {
                    five = five - 3;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
    //</editor-fold>


    private static void p(Object param) {
        p(String.valueOf(param));
    }

    private static void p(String param) {
        System.out.println("rtn:" + param);
    }
}
