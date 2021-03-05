package simpleness;

public class LeetCode_67 {


    /**
     * 67. 二进制求和
     * 给你两个二进制字符串，返回它们的和（用二进制表示）。
     *
     * 输入为 非空 字符串且只包含数字 1 和 0。
     *
     *
     *
     * 示例 1:
     *
     * 输入: a = "11", b = "1"
     * 输出: "100"
     * 示例 2:
     *
     * 输入: a = "1010", b = "1011"
     * 输出: "10101"
     *
     *
     * 提示：
     *
     * 每个字符串仅由字符 '0' 或 '1' 组成。
     * 1 <= a.length, b.length <= 10^4
     * 字符串如果不是 "0" ，就都不含前导零。
     *
     * "1010"
     * "1011"
     * @param a
     * @param b
     * @return
     */
    public static String addBinary(String a, String b) {

        return Long.toBinaryString(binaryStrToInt(a)+binaryStrToInt(b));
    }
    public static int binaryStrToInt(String a){
        if(a.length()==1){
            return Integer.parseInt(a);
        }
        int x=0;
        for (int i=a.length()-1,y=0;i>=0;i--,y++){
            x+=y+(a.charAt(i)=='0'?0:y);
        }
        System.out.println(x);
        return x;
    }

    public static int oneToInt(String digit){
        if(digit.length()==1){
            return 1;
        }

       // Math.pow(2, digit-1);
        return 0;
    }

    public static void main(String[] args) {
        int i=10;
        int y=10;
      /*  String b=addBinary(Long.toBinaryString(i), Long.toBinaryString(y));
        System.out.println(Long.parseLong(b, 2));
        System.out.println(Long.toBinaryString(i)+" | "+Long.toBinaryString(y));
        System.out.println(Long.parseLong(Long.toBinaryString(i), 2)+" | "+Long.parseLong(Long.toBinaryString(y), 2));
        for (int x=0;x<100;x++){
            System.out.println(x+" | "+Long.toBinaryString(x));
        }*/
        int a=23;
        int b=6;
        System.out.println(Long.toBinaryString(a));
        System.out.println(a>>b);
        System.out.println(Long.toBinaryString(a>>b));
    }
}
