package utils;

public class LeetCodeUtils {
    public static void main(String[] args) {
        Integer [][] a=new Integer[1][1];

    }

    public static void showTwoArray(int[][] lty){
        System.out.println("----------------------");
        for (int i = 0; i < lty.length; i++) {
            for (int j = 0; j < lty[i].length; j++) {
                if (j == lty[i].length-1){
                    System.out.print(lty[i][j]);
                }else
                System.out.print(lty[i][j]+",");
            }
            System.out.println();
        }
        System.out.println("----------------------");
    }
}
