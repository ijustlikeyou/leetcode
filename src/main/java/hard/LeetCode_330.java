package hard;

import java.util.Map;

public class LeetCode_330 {

    /**
     * 解题思路
     *
     * 可以这么理解，以[1,5,10]的例子为例: 我们从1开始遍历,并且维护一个指向nums的下标.一开始是1，而我们看到当前nums数组的第一个元素就是1,所以不需要其他操作.直接跳到2，并且让pos指向nums的第二个元素；
     *
     * 现在,我们的目标数是2,但是当前pos指向的数却是5,显然我们只能自己填充一个2,所以让res+1;既然我们已经填过2了,而在2之前可以被覆盖的最长区间长度是1,所以当前可以遍历到的最大区间长度变成了3(即2 + 1);
     *
     * 然后,我们可以忽略3,直接跳到4(因为上一步已经知道3在最大覆盖范围内了)。我们发现4同样比当前pos所指向的nums元素小,所以我们得填入4，即让res+1;既然已经填入4了,而我们知道在4之前可以覆盖的连续区间是(1-3),所以当前可以覆盖的最大区间被扩展到了7(即4 + 3)。
     *
     * 接下来我们可以直接跳过5、6、7来到8,而当前pos所指向的元素是5,所以当前可覆盖的区间大小又可以加上5了(7+5 = 12),并让pos指向下一个元素
     *
     * 最后我们跳过了7-12，从13开始遍历，这时候pos所指的元素是10,所以覆盖范围变成了12 + 10 = 22 >20，说明可以完全覆盖指定区间了！
     *
     * 到这里大概能够看出端倪 ：我们不断维持一个从1开始的可以被完全覆盖的区间,举个例子,当前可以完全覆盖区间是[1,k]，而当前pos所指向的nums中的元素为B,说明在B之前(因为是升序，所以都比B小)的所有元素之和可以映射到1-----k，而当我们把B也加入进去后，显然，可映射范围一定向右扩展了B个，也就是变成了1---k+B，这就是解题的思路
     */

    /**
     * 330. 按要求补齐数组
     * 给定一个已排序的正整数数组 nums，和一个正整数 n 。从 [1, n] 区间内选取任意个数字补充到 nums 中，使得 [1, n] 区间内的任何数字都可以用 nums 中某几个数字的和来表示。请输出满足上述要求的最少需要补充的数字个数。
     *
     * 示例 1:
     *
     * 输入: nums = [1,3], n = 6
     * 输出: 1
     * 解释:
     * 根据 nums 里现有的组合 [1], [3], [1,3]，可以得出 1, 3, 4。
     * 现在如果我们将 2 添加到 nums 中， 组合变为: [1], [2], [3], [1,3], [2,3], [1,2,3]。
     * 其和可以表示数字 1, 2, 3, 4, 5, 6，能够覆盖 [1, 6] 区间里所有的数。
     * 所以我们最少需要添加一个数字。
     * 示例 2:
     *
     * 输入: nums = [1,5,10], n = 20
     * 输出: 2
     * 解释: 我们需要添加 [2, 4]。
     * 示例 3:
     *
     * 输入: nums = [1,2,2], n = 5
     * 输出: 0
     */
    public static int minPatches(int[] nums, int n) {
        int count = 0;
        long sum = 0;
        for (int i = 0; i < nums.length && nums[i] <= n; i++) {
            while (sum + 1 < nums[i]) {
                sum = sum * 2 + 1;
                count ++;
            }
            sum += nums[i];
            if (sum >= n) break;
        }
        while (sum < n) {
            sum = sum * 2 + 1;
            count ++;
        }
        return count;
    }


    public static void main(String[] args) {
        int count=minPatches(new int[]{1,3},6);
        System.out.println(count);
    }
}
