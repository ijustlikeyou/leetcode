package medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LeetCode_384 {


    /**
     * 384. 打乱数组
     * 给你一个整数数组 nums ，设计算法来打乱一个没有重复元素的数组。
     *
     * 实现 Solution class:
     *
     * Solution(int[] nums) 使用整数数组 nums 初始化对象
     * int[] reset() 重设数组到它的初始状态并返回
     * int[] shuffle() 返回数组随机打乱后的结果
     *
     *
     * 示例：
     *
     * 输入
     * ["Solution", "shuffle", "reset", "shuffle"]
     * [[[1, 2, 3]], [], [], []]
     * 输出
     * [null, [3, 1, 2], [1, 2, 3], [1, 3, 2]]
     *
     * 解释
     * Solution solution = new Solution([1, 2, 3]);
     * solution.shuffle();    // 打乱数组 [1,2,3] 并返回结果。任何 [1,2,3]的排列返回的概率应该相同。例如，返回 [3, 1, 2]
     * solution.reset();      // 重设数组到它的初始状态 [1, 2, 3] 。返回 [1, 2, 3]
     * solution.shuffle();    // 随机返回数组 [1, 2, 3] 打乱后的结果。例如，返回 [1, 3, 2]
     *
     *
     * 提示：
     *
     * 1 <= nums.length <= 200
     * -106 <= nums[i] <= 106
     * nums 中的所有元素都是 唯一的
     * 最多可以调用 5 * 104 次 reset 和 shuffle
     *
     *
     * 已通过
     * 执行用时：
     * 75 ms
     * , 在所有 Java 提交中击败了
     * 99.32%
     * 的用户
     * 内存消耗：
     * 46.2 MB
     * , 在所有 Java 提交中击败了
     * 93.92%
     * 的用户
     */
    class Solution {

        private int[] lty=null;
        private List<Integer> shuffleArray=null;
        private int[] a=null;
        public Solution(int[] nums) {
            this.lty=nums;
            shuffleArray=new ArrayList<>(nums.length);
            for(int i :nums){
                shuffleArray.add(i);
            }
            a=new int[nums.length];
        }

        public int[] reset() {
            return this.lty;
        }

        public int[] shuffle() {
            Collections.shuffle(shuffleArray);
            for (int i=0;i< lty.length;i++){
                a[i]=shuffleArray.get(i);
            }
            return  a;
        }
    }

}
