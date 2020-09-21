import org.apache.commons.lang3.ArrayUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
public class LeetCode {

    public static void main(String[] args) {
      System.out.println( isOneBitCharacter(new int[]{1, 1, 1, 0}));
//        int[] a = new int[]{4,1,2,1,2};
//        System.out.println(2&3);
//        System.out.println(singleNumber(a));
//        for (int x : a) {
//            System.out.print(x + ",");
//        }

    }


    /**
     * 717. 1比特与2比特字符
     * 题目描述
     * 评论 (198)
     * 题解(89)
     * 提交记录
     *
     * 有两种特殊字符。第一种字符可以用一比特0来表示。第二种字符可以用两比特(10 或 11)来表示。
     *
     * 现给一个由若干比特组成的字符串。问最后一个字符是否必定为一个一比特字符。给定的字符串总是由0结束。
     *
     * 示例 1:
     *
     * 输入:
     * bits = [1, 0, 0]
     * 输出: True
     * 解释:
     * 唯一的编码方式是一个两比特字符和一个一比特字符。所以最后一个字符是一比特字符。
     *
     * 示例 2:
     *
     * 输入:
     * bits = [1, 1, 1, 0]
     * 输出: False
     * 解释:
     * 唯一的编码方式是两比特字符和两比特字符。所以最后一个字符不是一比特字符。
     *
     * 注意:
     *
     *     1 <= len(bits) <= 1000.
     *     bits[i] 总是0 或 1.
     *  [1,0,1,0,1,1,1,0,0,1,0,0,1,0,0]
     *
     * @param bits
     * @return
     */
    public static boolean isOneBitCharacter(int[] bits) {
          if(bits.length==1||bits[bits.length-2]==0){
              return true;
          }
          int i=0;
          for(;i<bits.length-1;i++){
              if(bits[i]==0){
                  continue;
              }
              i++;
          }
        return i!=bits.length;
    }


    /**
     * 3. 无重复字符的最长子串
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     *
     * 示例 1:
     *
     * 输入: "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     * 示例 2:
     *
     * 输入: "bbbbb"
     * 输出: 1
     * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
     * 示例 3:
     *
     * 输入: "pwwkew"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
         char [] chars=s.toCharArray();
         int length=0;
         StringBuilder sbd=new StringBuilder();
         for(int i=0,count=0;i<chars.length;i++){
             if(sbd.indexOf(chars[i]+"")!=-1){
                 sbd.delete(0, sbd.length());
                 count++;
                  i=count-1;
                  continue;
             }else{
                 sbd.append(chars[i]);
             }
         }
        int strLength=sbd.length();
        length=strLength>length?strLength:length;
        return length;
    }

    public static Boolean isTrue(StringBuilder sbd,char str){

        return  false;
    }

    /**
     *     136 只出现一次的数字
     *     给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     *
     *     说明：
     *
     *     你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
     *
     *     示例 1:
     *
     *     输入: [2,2,1]
     *     输出: 1
     *     示例 2:
     *
     *     输入: [4,1,2,1,2]
     *     输出: 4
     * @param nums
     * @return
     */
    public static int singleNumber(int[] nums) {
        //此方法使用按位异或 ^
        //只要对位不同 则为1
        //相同的数字两两匹配都为0
        //所以剩下的就只有单独的数字了
        int ans = nums[0];
        if (nums.length > 1) {
            for (int i = 1; i < nums.length; i++) {
                System.out.println(""+toBinary(ans,8)+"  "+ans+"\n"+toBinary(nums[i],8)+"  "+nums[i]);
                ans = ans ^ nums[i];
                System.out.println("转换后: "+toBinary(ans,8)+"  "+ans);
            }
        }
        return ans;
    }

    public static String toBinary(int num, int digits) {
        int value = 1 << digits | num;
        String bs = Integer.toBinaryString(value); //0x20 | 这个是为了保证这个string长度是6位数
        return  bs.substring(1);
    }

//    int index = nums.length - k - 1;
//        for (; index > -1; index--) {
//        nums[index] = nums[index] + nums[index + k];
//        nums[index + k] = nums[index] - nums[index + k];
//        nums[index] = nums[index] - nums[index + k];
//    }
    /**[2147483647,-2147483648,33,219,0]
     4
     * 旋转数组
     * <p>
     * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
     * 1,2,3,4,5,6
     * [5,6,1,2,3,4]
     * <p>
     * 示例 1:
     * <p>
     * 输入: [1,2,3,4,5,6,7] 和 k = 3
     * 输出: [5,6,7,1,2,3,4]
     * 解释:
     * 向右旋转 1 步: [7,1,2,3,4,5,6]
     * 向右旋转 2 步: [6,7,1,2,3,4,5]
     * 向右旋转 3 步: [5,6,7,1,2,3,4]
     * 示例 2:
     * <p>
     * 输入: [-1,-100,3,99] 和 k = 2
     * 输出: [3,99,-1,-100]
     * 解释:
     * 向右旋转 1 步: [99,-1,-100,3]
     * 向右旋转 2 步: [3,99,-1,-100]
     *
     *
     * @param nums
     * @param k
     */
    public static void rotate(int[] nums, int k) {
        int n = nums.length;
        k %= n;
        reverse(0, n-1, nums);
        reverse(0, k-1, nums);
        reverse(k, n-1, nums);
    }

    private static void reverse(int start,int end,int[] nums){
        while (end > start) {
            int tmp = nums[end];
            nums[end--] = nums[start];
            nums[start++] = tmp;
        }
    }


    /**
     * 买卖股票的最佳时机 II
     * [7,1,5,3,6,4]
     * 输出: 7
     * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     * 随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
     * 输入: [1,2,3,4,5]
     * 输出: 4
     * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     * 注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
     * 因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
     * 输入: [7,6,4,3,1]
     * 输出: 0
     * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
     *
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {
        int profit = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int y = i + 1; y < prices.length; y++) {
                if (prices[y] <= prices[i]) {
                    break;
                }
                if (y != prices.length - 1 && prices[y + 1] > prices[y]) {
                    continue;
                }
                profit += prices[y] - prices[i];
                i = y;
                break;
            }
        }
        return profit;
    }

    public static void ttt() {
        Map<String, Object> map = new HashMap<String, Object>();
        String a = "4028abf46dd85760016dd86210b50254";

        System.out.println(a.hashCode());
    }

    /**
     * 给定数组 nums = [1,1,2],
     * <p>
     * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
     * 给定 nums = [0,0,1,1,1,2,2,3,3,4],
     * <p>
     * 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
     * 你不需要考虑数组中超出新长度后面的元素。
     * 从排序数组中删除重复项
     *
     * @param nums
     * @return
     */
    public static int removeDuplicates(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }
        int length = 1, index = 1;
        lty:
        for (int i = 0; i < nums.length; i++) {
            for (int y = index; y < nums.length; y++) {
                if (nums[y] > nums[i]) {
                    if (y != i + 1) {
                        index = y;
                        nums[y] = nums[y] + nums[i + 1];
                        nums[i + 1] = nums[y] - nums[i + 1];
                        nums[y] = nums[y] - nums[i + 1];
                    }
                    length++;
                    if (y == nums.length - 1 || nums[i + 1] == nums[nums.length - 1]) {
                        break lty;
                    }
                    break;
                }
            }
        }
        for (int x : nums) {
            System.out.print(x + ",");
        }
        System.out.println("\nlength:" + length);
        return length;
    }

    /**
     * "{[]}"
     * ( 40
     * ) 41
     * [ 91
     * ] 93
     * { 123
     * } 125
     * <p>
     * 20. 有效的括号
     * 输入: "()"
     * 输出: true
     * 示例 2:
     * <p>
     * 输入: "()[]{}"
     * 输出: true
     * 示例 3:
     * <p>
     * 输入: "(]"
     * 输出: false
     * 示例 4:
     * <p>
     * 输入: "([)]"
     * 输出: false
     *
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        int length = s.length();
        if (length == 0) {
            return true;
        }
        if (length % 2 != 0) {
            return false;
        }
        StringBuilder sbd = new StringBuilder(s);
        do {
            int index = sbd.indexOf("{}");
            if (index > -1) {
                sbd.delete(index, index + 2);
            }
            int index1 = sbd.indexOf("[]");
            if (index1 > -1) {
                sbd.delete(index1, index1 + 2);
            }
            int index2 = sbd.indexOf("()");
            if (index2 > -1) {
                sbd.delete(index2, index2 + 2);
            }
            if (index == -1 && index1 == -1 && index2 == -1 && sbd.length() > 0) {
                return false;
            }
        } while (sbd.length() != 0);
        return true;
    }


}
