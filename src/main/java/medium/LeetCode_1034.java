package medium;

import utils.LeetCodeUtils;

public class LeetCode_1034 {


    public static void main(String[] args) {
       int [][] a={{2,1,2,2,1,1},{1,2,2,2,1,1},{1,1,2,2,1,1},{2,2,2,1,1,2},{2,2,2,1,1,2},{1,2,2,1,1,1}};
        LeetCodeUtils.showTwoArray(a);
       colorBorder(a,3,2,1);
        LeetCodeUtils.showTwoArray(a);
    }


    /**
     * [[2,1,2,2,1,1],[1,2,2,2,1,1],[1,1,2,2,1,1],[2,2,2,1,2,1],[2,2,2,1,1,2],[1,2,2,1,1,1]]
     *
     * 2,1,2,2,1,1
     * 1,2,2,2,1,1
     * 1,1,2,2,1,1
     * 2,2,2,1,1,2
     * 2,2,2,1,1,2
     * 1,2,2,1,1,1
     *
     * 3
     * 2
     * 1
     *
     [[2,1,1,1,1,1],
     [1,1,2,1,1,1],
     [1,1,1,1,1,1],
     [1,1,1,1,2,1],
     [1,2,1,1,1,2],
     [1,1,1,1,1,1]]
     */


    /**
     * 1034. 边界着色
     * 给你一个大小为 m x n 的整数矩阵 grid ，表示一个网格。另给你三个整数 row、col 和 color 。网格中的每个值表示该位置处的网格块的颜色。
     *
     * 当两个网格块的颜色相同，而且在四个方向中任意一个方向上相邻时，它们属于同一 连通分量 。
     *
     * 连通分量的边界 是指连通分量中的所有与不在分量中的网格块相邻（四个方向上）的所有网格块，或者在网格的边界上（第一行/列或最后一行/列）的所有网格块。
     *
     * 请你使用指定颜色 color 为所有包含网格块 grid[row][col] 的 连通分量的边界 进行着色，并返回最终的网格 grid 。
     *
     *[[1,2,1],[1,2,2],[2,2,1]]
     * 1
     * 1
     * 2
     * [[1,2,1,2,1,2],
     * [2,2,2,2,1,2],
     * [1,2,2,2,1,2]]
     *
     *[[1,1,1,1,1,2],
     * [1,2,1,1,1,2],
     * [1,1,1,1,1,2]]
     *
     *
     * 示例 1：
     *
     * 输入：grid = [[1,1],[1,2]], row = 0, col = 0, color = 3
     * 输出：[[3,3],[3,2]]
     * 示例 2：
     *
     * [[1,2,2],
     * [2,3,2]]
     *
     * [[1,3,3],
     * [2,3,3]]
     * 输入：grid = [[1,2,2],[2,3,2]], row = 0, col = 1, color = 3
     * 输出：[[1,3,3],[2,3,3]]
     * 示例 3：
     *
     * [[1,1,1],
     * [1,1,1],
     * [1,1,1]]
     *
     * [[2,2,2],
     * [2,1,2],
     * [2,2,2]]
     * 输入：grid = [[1,1,1],[1,1,1],[1,1,1]], row = 1, col = 1, color = 2
     * 输出：[[2,2,2],[2,1,2],[2,2,2]]
     *
     *
     * 提示：
     *
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 50
     * 1 <= grid[i][j], color <= 1000
     * 0 <= row < m
     * 0 <= col < n
     *
     *
     * 通过次数12,411提交次数23,736
     * @param grid
     * @param row
     * @param col
     * @param color
     * @return
     */
    public static int[][] colorBorder(int[][] grid, int row, int col, int color) {
        if (color == grid[row][col]) {
            return grid;
        }
        setColor(grid,row,col,color,grid[row][col],new Integer[grid.length][grid[0].length]);
        return grid;
    }

    /**
     * 设置颜色
     * @param grid
     * @param row
     * @param col
     * @param color
     */
    public static void setColor(int[][] grid, int row, int col, int color,int a,Integer [][] old){
        if(old[row][col]!=null){
           return;
        }
        grid[row][col]=color;
        old[row][col]=1;
        //上
        if (row-1>=0&&grid[row-1][col]==a){
            setColor(grid, row-1, col, color,a,old);
        }
        //下
        if (row+1<grid.length&&grid[row+1][col]==a){
            setColor(grid, row+1, col, color,a,old);
        }
        //左
        if (col!=0&&grid[row][col-1]==a){
            setColor(grid, row, col-1, color,a,old);
        }
        //右
        if (col+1<grid[row].length&&grid[row][col+1]==a) {
            setColor(grid, row, col+1, color,a,old);
        }
        if(row-1>=0&&old[row-1][col]!=null&&row+1<grid.length&&old[row+1][col]!=null&&col!=0&&old[row][col-1]!=null&&col+1<grid[row].length&&old[row][col+1]!=null){
            grid[row][col]=a;
        }
    }

}
