package medium;

public class LeetCode_318{


    public static void main(String[] args) {
        String x="123";
       int a= maxProduct(new String[]{"abcw","baz","foo","bar","xtfn","abcdef"});
        System.out.println(a);
    }

    /**
     * 318. 最大单词长度乘积
     * 给定一个字符串数组 words，找到 length(word[i]) * length(word[j]) 的最大值，并且这两个单词不含有公共字母。你可以认为每个单词只包含小写字母。如果不存在这样的两个单词，返回 0。
     *
     *167 / 167 个通过测试用例
     * 状态：通过
     * 执行用时: 1551 ms
     * 内存消耗: 39 MB
     * 都超越8%的用户
     *
     * 示例 1:
     *
     * 输入: ["abcw","baz","foo","bar","xtfn","abcdef"]
     * 输出: 16
     * 解释: 这两个单词为 "abcw", "xtfn"。
     * 示例 2:
     *
     * 输入: ["a","ab","abc","d","cd","bcd","abcd"]
     * 输出: 4
     * 解释: 这两个单词为 "ab", "cd"。
     * 示例 3:
     *
     * 输入: ["a","aa","aaa","aaaa"]
     * 输出: 0
     * 解释: 不存在这样的两个单词。
     *
     *
     * 提示：
     *
     * 2 <= words.length <= 1000
     * 1 <= words[i].length <= 1000
     * words[i] 仅包含小写字母
     *
     *
     *
     * @param words
     * @return
     */
    public static int maxProduct(String[] words) {
        int len=words.length;
        if(len<=1){
            return 0;
        }
        int product=0;
        for (int i = 0; i < len-1; i++) {
            char [] x = words[i].toCharArray();
           lty:
           for (int j = i+1; j < len; j++) {
               char [] y = words[j].toCharArray();
               for (int k = 0; k < x.length; k++) {
                   for (int s = 0; s < y.length; s++) {
                       if (x[k] == y[s]) {
                           continue lty;
                       }
                   }
               }
               product=x.length*y.length>product?x.length*y.length:product;
           }
        }
        return product;
    }


    /**
     * 最优解
     * @param words
     * @return
     */
    public static int maxProduct1(String[] words) {
        /**
         全是小写字母, 可以用一个32为整数表示一个word中出现的字母,
         hash[i]存放第i个单词出现过的字母, a对应32位整数的最后一位,
         b对应整数的倒数第二位, 依次类推. 时间复杂度O(N^2)
         判断两两单词按位与的结果, 如果结果为0且长度积大于最大积则更新
         **/
        int n = words.length;
        int[] hash = new int[n];
        int max = 0;
        for(int i = 0; i < n; ++i) {
            for(char c : words[i].toCharArray())
                hash[i] |= 1 << (c-'a');
        }

        for(int i = 0; i < n-1; ++i) {
            for(int j = i+1; j < n; ++j) {
                if((hash[i] & hash[j]) == 0)
                    max = Math.max(words[i].length() * words[j].length(), max);
            }
        }
        return max;
    }



}
