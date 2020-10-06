import com.alibaba.fastjson.JSON;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.junit.Test;

import java.util.*;

public class LeetCode1 {

    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
        TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
    }

    public static TreeNode createTreeNode(Integer [] val){
        TreeNode node=new TreeNode(val[0]);
        TreeNode a=node;
        TreeNode b=null;
        for (int i=1,count=1,tier=2,time=2;i<val.length&&i<=tier;i++,count++){
            insertNode(node,tier,time,i,val[i]);
            if(i==tier){
                count=1;
                tier+=tier;
                time++;
            }
        }
        return node;
    }

    public static void insertNode(TreeNode node,int tier,int time,int index,Integer value){
         if (time==2){
             if (index%2==1){
                 node.left=new TreeNode(value);
             }else{
                 node.right=new TreeNode(value);
             }
         }else {
             insertNode(tier/2>index?node.left:node.right,tier/2,--time,index,value);
         }
    }

    @Test
    public void test1223(){
        nm(20,3);
    }

    /**
     * n 个人排成一圈，从第一个人开始报数，从 1 开始报，报到 m 的人出圈，剩下的人继 续开始从 1 报数，
     * 直到所有的人都出圈为止。对于给定的 n,m，求出所有人的出圈顺 序
     */
    public void nm(int n,int m){
        for(int i=1,count=1;i<=n;count++){
            for(int c=0;c<m&&i<=n;c++,i++){
                System.out.println("第"+count+"次,淘汰:"+i);
            }
        }
    }


    @Test
    public void testa(){
        StringBuilder s=new StringBuilder();
        char [] x=new char[]{'a','b','a','c','d'};
        int a=3;
        //s.insert(0,x,a/2-a%2,a/2-a%2+a)

        System.out.println(s.toString());

        System.out.println(longestPalindrome("acbab"));
    }



    /**
     * 5. 最长回文子串
     *
     * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
     * 示例 1：
     *
     * 输入: "babad"
     * 输出: "bab"
     *
     * 注意: "aba" 也是一个有效答案。
     * 示例 2：
     *
     * 输入: "cbbd"
     * 输出: "bb"
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if(s.length()<2){
            return s;
        }
        char [] x=s.toCharArray();

        StringBuilder sbd=new StringBuilder();
        int len=0;
        for(int i=0;i<x.length-1;i++){
             int len1=lty(x,i,true);
             int len2=lty(x,i,false);
             int max=len1>len2?len1:len2;
             if(max>len){
                 sbd.delete(0,len);
                 sbd.insert(0,x,max%2==0?i-max/2+1:i-max/2,max);
                 len=max;
             }
        }
        return len==0?s.substring(0,1):sbd.toString();
    }

    public int lty(char [] x,int index,Boolean is){
        int len=0;
        if(is){
            for(int i=index+1,count=index;i<x.length&&count>=0;i++,count--,len+=2){
               if(x[count]!=x[i]){
                 break;
               }
            }
        }else{
            len++;
            for(int i=index+1,count=index-1;i<x.length&&count>=0;i++,count--,len+=2){
                if(x[count]!=x[i]){
                    break;
                }
            }
        }
        return len==1?0:len;
    }



    /**
     * [3,4,5,1,2,null,null,0]
     * [4,1,2]
     * 572. 另一个树的子树
     *
     * 给定两个非空二叉树 s 和 t，检验 s 中是否包含和 t 具有相同结构和节点值的子树。s 的一个子树包括 s 的一个节点和这个节点的所有子孙。s 也可以看做它自身的一棵子树。
     *
     * 示例 1:
     * 给定的树 s:
     *
     *      3
     *     / \
     *    4   5
     *   / \
     *  1   2
     *
     * 给定的树 t：
     *
     *    4
     *   / \
     *  1   2
     *
     * 返回 true，因为 t 与 s 的一个子树拥有相同的结构和节点值。
     *
     * 示例 2:
     * 给定的树 s：
     *
     *      3
     *     / \
     *    4   5
     *   / \
     *  1   2
     *     /
     *    0
     *
     * 给定的树 t：
     *
     *    4
     *   / \
     *  1   2
     *
     * 返回 false。
     * @param s
     * @param t
     * @return
     */
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (isSubtree2(s, t)){
            return true;
        }
        return  s!=null&&(isSubtree(s.left, t)||isSubtree(s.right, t))?true:false;
    }

    public boolean isSubtree2(TreeNode s, TreeNode t){
        if(s==null&&t==null){
            return true;
        }
        if((s==null||t==null)||(s.val!=t.val)){
            return false;
        }
        return isSubtree2(s.left,t.left)&&isSubtree2(s.right,t.right);
    }


    /**
     *
     * 45. 跳跃游戏 II
     *
     * 给定一个非负整数数组，你最初位于数组的第一个位置。
     *
     * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
     *
     * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
     * 示例:
     * 输入: [2,3,1,1,4]
     * 输出: 2
     * 解释: 跳到最后一个位置的最小跳跃数是 2。
     *
     * 从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
     *
     * 说明:
     * 假设你总是可以到达数组的最后一个位置。
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        if(nums.length<2){
            return 0;
        }
        int length=0;
        for(int i=0;i<nums.length;){
            int a=nums[i];
            int max=a+i;
            int maxIndex=i;
            length++;
            if(a+maxIndex>=nums.length-1){
                return length;
            }
            for(int y=1;y<=a;y++){
                if(i+y>=nums.length){
                    break;
                }
                int index=i+y;
                if(nums[index]+index>max){
                    max=nums[index]+index;
                    maxIndex=index;
                }
            }
            i=maxIndex;
        }
        return length;
    }
    /**
     * 53. 最大子序和
     *
     * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     *
     * 示例:
     *
     * 输入: [-2,1,-3,4,-1,2,1,-5,4],
     * 输出: 6
     * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
     *
     * 进阶:
     *
     * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int lty = nums[0];
        int sum = 0;
        for (int num : nums) {
            if (sum > 0) {
                sum += num;
            }else {
                sum = num;
            }
            lty =lty>sum?lty:sum;
        }
        return lty;
    }



    /**
     *3. 无重复字符的最长子串
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     *
     * 示例 1:
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
     * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if(s==null||s.length()==0){
            return 0;
        }
        char [] lty=s.toCharArray();
        int length=1;
        for(int i=0;i<lty.length-1;i++){
            for(int y=i+1,count=1;y<lty.length;y++){
                if(isTrue(lty,i,y,lty[y])){
                    break;
                }
                length=length<++count?count:length;
            }
        }
        return length;
    }

    public Boolean isTrue(char [] lty,int start,int end,char index){
         for(;start<end;start++){
             if(lty[start]==index){
                 return true;
             }
         }
        return false;
    }

    /**["time", "atime", "btime"]
     * 820. 单词的压缩编码
     * 给定一个单词列表，我们将这个列表编码成一个索引字符串 S 与一个索引列表 A。
     *
     * 例如，如果这个列表是 ["time", "me", "bell"]，我们就可以将其表示为 S = "time#bell#" 和 indexes = [0, 2, 5]。
     *
     * 对于每一个索引，我们可以通过从字符串 S 中索引的位置开始读取字符串，直到 "#" 结束，来恢复我们之前的单词列表。
     *
     * 那么成功对给定单词列表进行编码的最小字符串长度是多少呢？
     * 示例：
     *
     * 输入: words = ["time", "me", "bell"]
     * 输出: 10
     * 说明: S = "time#bell#" ， indexes = [0, 2, 5] 。
     * 提示：
     * 1 <= words.length <= 2000
     * 1 <= words[i].length <= 7
     * 每个单词都是小写字母 。
     * @param words
     * @return
     */
    public int minimumLengthEncoding(String[] words) {
        //先去重
        Set<String> set = new HashSet<String>(Arrays.asList(words));
        for (String a : words) {
            //将剩下的单词一个一个拆除遍历删除 最后剩下完全毫无关联的单词
            int len=a.length();
            for (int i = 1; i < len; i++) {
                set.remove(a.substring(i));
            }
        }
        int len = 0;
        for (String lty : set) {
            len += lty.length() + 1;
        }
        return len;
    }

    /**
     * 914. 卡牌分组
     * 给定一副牌，每张牌上都写着一个整数。
     *
     * 此时，你需要选定一个数字 X，使我们可以将整副牌按下述规则分成 1 组或更多组：
     *
     * 每组都有 X 张牌。
     * 组内所有的牌上都写着相同的整数。
     * 仅当你可选的 X >= 2 时返回 true。
     * 示例 1：
     *
     * 输入：[1,2,3,4,4,3,2,1]
     * 输出：true
     * 解释：可行的分组是 [1,1]，[2,2]，[3,3]，[4,4]
     * 示例 2：
     *
     * 输入：[1,1,1,2,2,2,3,3]
     * 输出：false
     * 解释：没有满足要求的分组。
     * 示例 3：
     *
     * 输入：[1]
     * 输出：false
     * 解释：没有满足要求的分组。
     * 示例 4：
     *
     * 输入：[1,1]
     * 输出：true
     * 解释：可行的分组是 [1,1]
     * 示例 5：
     *
     * 输入：[1,1,2,2,2,2]
     * 输出：true
     * 解释：可行的分组是 [1,1]，[2,2]，[2,2]
     * @param deck
     * @return
     */
    public boolean hasGroupsSizeX(int[] deck) {
        if(deck.length<2){
            return false;
        }
        Map<Integer,Integer> map=new HashMap<Integer, Integer>();
        for(int a:deck){
            Integer x=map.get(a);
            if(x==null){
                map.put(a,1);
            }else{
                map.put(a,++x);
            }
        }
        int count=2;
        int index=0;
        for(Integer a:map.keySet()){
            index++;
           int x=map.get(a);
           if(x<2){
               return false;
           }
           if(index==1){
               count=x;
               continue;
           }
           if(count!=x&&!minRemainder(count,x)){
               return false;
           }
            count=x;
        }
        return true;
    }
    private Boolean minRemainder(int a,int b) {
       for(int i=2;i<=a;i++){
           if(a%i==0&&b%i==0){
               return true;
           }
       }
       return false;
    }

    /**
     * 892. 三维形体的表面积
     * 在 N * N 的网格上，我们放置一些 1 * 1 * 1  的立方体。
     * 每个值 v = grid[i][j] 表示 v 个正方体叠放在对应单元格 (i, j) 上。
     * 请你返回最终形体的表面积。
     * 示例 1：
     *
     * 输入：[[2]]
     * 输出：10
     * 示例 2：
     *
     * 34
     *6 10 15  18

     *   j
     *   6
     *   5
     *   4
     *   3
     *   2
     * @ 1
     * @ 0 1 2 3 4 5 6 7  i
     * 输入：[[1,2],[3,4]]
     * 输出：34
     * 示例 3：
     *
     * 输入：[[1,0],[0,2]]
     * 输出：16
     * 示例 4：
     *
     * 输入：[[1,1,1],[1,0,1],[1,1,1]]
     * 输出：32
     * 示例 5：
     *
     * 输入：[[2,2,2],[2,1,2],[2,2,2]]
     * 输出：46
     *
     * @param grid
     * @return
     */
    public int surfaceArea(int[][] grid) {
        int a=0;
        for(int i=0;i<grid.length;i++){
            int [] x=grid[i];
            for(int y=0;y<x.length;y++){
                int count=x[y];
                if(count==0){
                    continue;
                }
                int sum=(count<<2)+2;
                if(y+1<x.length){
                    int q=x[y+1];
                    sum-=q<count?q:count;
                }
                if(i+1<grid.length){
                    int q=grid[i+1][y];
                    sum-=q<count?q:count;
                }
                if(i-1>-1){
                    int q=grid[i-1][y];
                    sum-=q<count?q:count;
                }
                if(y-1>-1){
                    int q=x[y-1];
                    sum-=q<count?q:count;
                }
                a+=sum;
            }
        }
        return a;
    }

    /**
     * 1 1
     * 1 2
     * 2 3
     *
     *
     * 876. 链表的中间结点
     *
     * 给定一个带有头结点 head 的非空单链表，返回链表的中间结点。
     *
     * 如果有两个中间结点，则返回第二个中间结点。*
     * 示例 1：
     *
     * 输入：[1,2,3,4,5]
     * 输出：此列表中的结点 3 (序列化形式：[3,4,5])
     * 返回的结点值为 3 。 (测评系统对该结点序列化表述是 [3,4,5])。
     * 注意，我们返回了一个 ListNode 类型的对象 ans，这样：
     * ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, 以及 ans.next.next.next = NULL.
     *
     * 示例 2：
     *
     * 输入：[1,2,3,4,5,6]
     * 输出：此列表中的结点 4 (序列化形式：[4,5,6])
     * 由于该列表有两个中间结点，值分别为 3 和 4，我们返回第二个结点。
     * 提示：
     *
     *     给定链表的结点数介于 1 和 100 之间。
     *
     * 通过次数46,036
     * 提交次数67,540
     * @param head
     * @return
     */
    public ListNode middleNode(ListNode head) {
        if(head==null){
            return null;
        }
        return getListNode(head,head,0);
    }

    public ListNode getListNode(ListNode main,ListNode now,int count){
        if(now.next!=null){
           return getListNode(count==1?main.next:main,now.next,count==1?0:++count);
        }
        return count==1?main.next:main;
    }

    /**
     * 945. 使数组唯一的最小增量
     * 给定整数数组 A，每次 move 操作将会选择任意 A[i]，并将其递增 1。
     * 返回使 A 中的每个值都是唯一的最少操作次数。
     * 示例 1:
     * 输入：[1,2,2]
     * 输出：1
     * 解释：经过一次 move 操作，数组将变为 [1, 2, 3]。
     * 示例 2:
     * 输入：[3,2,1,2,1,7]
     * 输出：6
     * 解释：经过 6 次 move 操作，数组将变为 [3, 4, 1, 2, 5, 7]。
     * 可以看出 5 次或 5 次以下的 move 操作是不能让数组的每个值唯一的。
     * @param A
     * @return
     */
    public int minIncrementForUnique(int[] A) {
        Arrays.sort(A);
        int count=0;
        for(int i=0,length=A.length-1;i<length;i++){
            int a=A[i],b=A[i+1];
            if(a==b){
                A[i+1]=++b;
                count++;
            }else if(a>b){
                A[i+1]=a+1;
                count+=A[i+1]-b;
            }
        }
        return count;
    }

    /**
     * 面试题40. 最小的k个数
     * 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
     * 示例 1：
     * 输入：arr = [3,2,1], k = 2
     * 输出：[1,2] 或者 [2,1]
     * 示例 2：
     * 输入：arr = [0,1,2,1], k = 1
     * 输出：[0]
     * 限制：
     * 0 <= k <= arr.length <= 10000
     * 0 <= arr[i] <= 10000
     * @param arr
     * @param k
     * @return
     */
    public int[] getLeastNumbers(int[] arr,int k) {
        int [] x=new int[k];
        Arrays.sort(arr);
        for(int i=0;i<k;i++){
            x[i]=arr[i];
        }
        return x;
    }

    /**
     * 836. 矩形重叠
     * 矩形以列表 [x1, y1, x2, y2] 的形式表示，其中 (x1, y1) 为左下角的坐标，(x2, y2) 是右上角的坐标。
     *
     * 如果相交的面积为正，则称两矩形重叠。需要明确的是，只在角或边接触的两个矩形不构成重叠。
     *
     * 给出两个矩形，判断它们是否重叠并返回结果。
     * 示例 1：
     *
     * 输入：rec1 = [0,0,2,2], rec2 = [1,1,3,3]
     * 输出：true
     * 示例 2：
     *
     * 输入：rec1 = [0,0,1,1], rec2 = [1,0,2,1]
     * 输1出：false
     *
     * [-5,8,0,8]
     * [-5,4,5,5]
     * 提示：   y
     *               4
     *               3
     *               2
     *               1
     *-5 -4 -3 -2 -1 0 1 2 3 4 5 x
     *              -1
     *              -2
     *              -3
     *              -4
     * 两个矩形 rec1 和 rec2 都以含有四个整数的列表的形式给出。
     * 矩形中的所有坐标都处于 -10^9 和 10^9 之间。
     * x 轴默认指向右，y 轴默认指向上。
     * 你可以仅考虑矩形是正放的情况。
     * @param rec1
     * @param rec2
     * @return
     */
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        if(rec2[0]>=rec1[0]&&is(rec2[0],rec2[2],rec1[0],rec1[2])&&is(rec2[1],rec2[3],rec1[1],rec1[3])){
           return  true;
        }else if(rec1[0]>rec2[0]&&is(rec1[0],rec1[2],rec2[0],rec2[2])&&is(rec1[1],rec1[3],rec2[1],rec2[3])){
            return true;
        }
       return false;
    }
    public Boolean is(int start,int end,int start1,int end1){
        for(int i=start;i<end;i++){
            if(i>=start1&&i<end1){
                return true;
            }
        }
        return  false;
    }


    /**
     * 面试题 01.06. 字符串压缩
     * 字符串压缩。利用字符重复出现的次数，编写一种方法，实现基本的字符串压缩功能。比如，字符串aabcccccaaa会变为a2b1c5a3。若“压缩”后的字符串没有变短，则返回原先的字符串。你可以假设字符串中只包含大小写英文字母（a至z）。
     * 示例1:
     *
     *  输入："aabcccccaaa"
     *  输出："a2b1c5a3"
     * 示例2:
     *
     *  输入："abbccd"
     *  输出："abbccd"
     *  解释："abbccd"压缩后为"a1b2c2d1"，比原字符串长度更长。
     * @param S
     * @return
     */
    public String compressString(String S) {
        char x[]=S.toCharArray();
        if(x.length==0){
            return S;
        }
        StringBuilder sbd=new StringBuilder();
        char a=x[0];
        int count=1;
        for(int i=1;i<x.length;i++){
            if(x[i]==a){
                count++;
            }else{
                sbd.append(a+""+count);
                count=1;
                a=x[i];
            }
        }
        sbd.append(x[x.length-1]+""+count);
        return sbd.length()>=x.length?S:sbd.toString();
    }
    /**
     * 977. 有序数组的平方
     * 给定一个按非递减顺序排序的整数数组 A，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。
     * 示例 1：
     *
     * 输入：[-4,-1,0,3,10]
     * 输出：[0,1,9,16,100]
     * 示例 2：
     *
     * 输入：[-7,-3,2,3,11]
     * 输出：[4,9,9,49,121]
     * @param A
     * @return
     */
    public int[] sortedSquares(int[] A) {
        for(int i=0;i<A.length;i++){
            int x=A[i];
            A[i]=x*x;
        }
        for(int i=0;i<A.length-1;i++){
            for (int y=0;y<A.length-1-i;y++){
                int a=A[y];
                int x=A[y+1];
                if(a>x){
                    A[y]=x;
                    A[y+1]=a;
                }
            }
        }
        System.out.println(JSON.toJSONString(A));
        return null;
    }

    /**
     * 104. 二叉树的最大深度
     * 给定一个二叉树，找出其最大深度。
     *
     * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
     *
     * 说明: 叶子节点是指没有子节点的节点。
     *
     * 示例：
     * 给定二叉树 [3,9,20,null,null,15,7]，
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回它的最大深度 3 。
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
       
        return 0;
    }
    /**
     * 121. 买卖股票的最佳时机
     * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
     *
     * 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
     *
     * 注意你不能在买入股票前卖出股票。
     *
     * 示例 1:
     *
     * 输入: [7,1,5,3,6,4]
     * 输出: 5
     * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
     * 示例 2:
     *
     * 输入: [7,6,4,3,1]
     * 输出: 0
     * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
          if(prices.length<2){
              return 0;
          }
          int min=prices[0];
          int max=0;
         for(int i=1;i<prices.length;i++){
             int a=prices[i];
             if(a<min){
                 min=a;
             }
             max=a-min>max?a-min:max;
         }
        return max;
    }

    /**
     * 66. 加一
     * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
     *
     * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
     *
     * 你可以假设除了整数 0 之外，这个整数不会以零开头。
     *
     * 示例 1:
     *
     * 输入: [1,2,3]
     * 输出: [1,2,4]
     * 解释: 输入数组表示数字 123。
     * 示例 2:
     *
     * 输入: [4,3,2,1]
     * 输出: [4,3,2,2]
     * 解释: 输入数组表示数字 4321。
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {
        for(int i=digits.length-1;i>-1;i--){
            int a=digits[i];
            if(a!=9){
                digits[i]=a+1;
                return digits;
            }else {
                digits[i]=0;
            }
        }
        int [] a=new int[digits.length+1];
        a[0]=1;
        for(int i=1;i<a.length;i++){
            a[i]=0;
        }
        return a;
    }

    /**
     * 169. 多数元素
     * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
     *
     * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
     *
     * 示例 1:
     *
     * 输入: [3,2,3]
     * 输出: 3
     * 示例 2:
     *
     * 输入: [2,2,1,1,1,2,2]
     * 输出: 2
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        int count = 1;
        int lty = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (lty == nums[i]) {
                count++;
            }else {
                count--;
                if (count == 0) {
                    lty = nums[i + 1];
                }
            }
        }
        return lty;
    }

    /**
     * 35. 搜索插入位置
     * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
     *
     * 你可以假设数组中无重复元素。
     *
     * 示例 1:
     *
     * 输入: [1,3,5,6], 5
     * 输出: 2
     * 示例 2:
     *
     * 输入: [1,3,5,6], 2
     * 输出: 1
     * 示例 3:
     *
     * 输入: [1,3,5,6], 7
     * 输出: 4
     * 示例 4:
     *
     * 输入: [1,3,5,6], 0
     * 输出: 0
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        int index=0,count=0;
        for(int i=0;i<nums.length;i++){
            if(count==0&&target<nums[i]){
                count++;
                index=i;
            }
            if(target==nums[i]){
                return i;
            }
        }
        return target>nums[nums.length-1]?nums.length:index;
    }

    /**
     * 27. 移除元素
     * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
     *
     * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
     *
     * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
     * 示例 1:
     *
     * 给定 nums = [3,2,2,3], val = 3,
     *
     * 函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。
     *
     * 你不需要考虑数组中超出新长度后面的元素。
     * 示例 2:
     *
     * 给定 nums = [0,1,2,2,3,0,4,2], val = 2,
     *
     * 函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。
     *
     * 注意这五个元素可为任意顺序。
     *
     * 你不需要考虑数组中超出新长度后面的元素。
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        int count=0;
        for(int i=0;i<nums.length-count;i++){
            if(nums[i]==val){
                count++;
                for(int y=i+1;y<nums.length;y++){
                    nums[y-1]=nums[y];
                }
                i--;
            }
        }
        return nums.length-count;
    }

    /**
     * 1071. 字符串的最大公因子
     * 对于字符串 S 和 T，只有在 S = T + ... + T（T 与自身连接 1 次或多次）时，我们才认定 “T 能除尽 S”。
     * 返回最长字符串 X，要求满足 X 能除尽 str1 且 X 能除尽 str2。
     * 示例 1：
     *
     * 输入：str1 = "ABCABC", str2 = "ABC"
     * 输出："ABC"
     * 示例 2：
     *
     * 输入：str1 = "ABABAB", str2 = "ABAB"
     * 输出："AB"
     * 示例 3：
     *
     * 输入：str1 = "LEET", str2 = "CODE"
     * 输出：""
     *
     *
     * 提示：
     *
     * 1 <= str1.length <= 1000
     * 1 <= str2.length <= 1000
     * str1[i] 和 str2[i] 为大写英文字母
     * @param str1
     * @param str2
     * @return
     */
    public String gcdOfStrings(String str1, String str2) {
       StringBuilder max=null;
       StringBuilder min=null;
       if(str1.length()>str2.length()){
           max=new StringBuilder(str1);
           min=new StringBuilder(str2);
       }else{
           max=new StringBuilder(str2);
           min=new StringBuilder(str1);
       }
        int maxLen=max.length();
        int minLen=min.length();
        lty:for(int i=min.length();i>0;i--){
           if(maxLen%i==0&&minLen%i==0){
               String x=max.substring(0,i);
               String y=min.substring(0,i);
               if (x.equals(y)){
                   for (int c=i;c<maxLen;c+=i){
                       if(!y.equals(max.substring(c,i+c))){
                           continue lty;
                       }
                   }
                   return x;
               }
           }
        }
        return "";
    }
// for(int i=min.length;i>=0;i--){
//        a1.append(min,0,i);
//        for(int y=0;y<max.length-min.length;y++){
//            a2.append(max,y,i);
//            if(a1.toString().equals(a2.toString())){
//                return a1.toString();
//            }else{
//                a2.delete(0,i);
//            }
//        }
//        a1.delete(0,i);
//    }
    @Test
    public void test123(){
        TreeNode a1=new TreeNode(1);
        TreeNode a2=new TreeNode(2);
        TreeNode a3=new TreeNode(3);
        TreeNode a4=new TreeNode(4);
        TreeNode a5=new TreeNode(5);
        a2.right=a5;
        a2.left=a4;
        a1.left=a2;
        a1.right=a3;
       // a3.right=a5;

        a5.right=new TreeNode(6);
        System.out.println(diameterOfBinaryTree(a1));

    }

    /**
     *543. 二叉树的直径
     * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过根结点。
     *
     * 示例 :
     * 给定二叉树
     *
     *           1
     *          / \
     *         2   3
     *        / \
     *       4   5
     * 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
     *
     * 注意：两结点之间的路径长度是以它们之间边的数目表示。
     * @param root
     * @return
     */
    public int diameterOfBinaryTree(TreeNode root) {
        if(root==null){
            return 0;
        }
        int left=getMaxTreeNode(root.left,0);
        int right=getMaxTreeNode(root.right,0);
        System.out.println("left："+left+" right:"+right);
        return left+right;
    }

    public int getMaxTreeNode(TreeNode now,int count){
        int left=count;
        int right=count;
        if(now.left!=null){
            left=getMaxTreeNode(now.left,++count);
        }
        if(now.right!=null){
            right=getMaxTreeNode(now.right,++count);
        }
        return right>left?right:left;
    }



    /**
     * 面试题57 - II. 和为s的连续正数序列
     * 输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
     * 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
     * 示例 1：
     * 输入：target = 9
     * 输出：[[2,3,4],[4,5]]
     *
     *
     * 示例 2：
     *
     * 输入：target = 15
     * 输出：[[1,2,3,4,5],[4,5,6],[7,8]]
     * @param target
     * @return
     */
    public int[][] findContinuousSequence(int target) {
          int sum=0,max=target/2;
          if(target%2==1){
              max++;
          }
          java.util.List<int[]> list=new java.util.ArrayList<int[]>();
          for(int i=1;i<max;i++,sum=0){
              for(int y=i;y<=max;y++){
                  sum+=y;
                  if(sum>target){
                      break;
                  }
                  if(sum==target){
                      int []n=new int[y-i+1];
                     for(int x=0;x<n.length;x++){
                         n[x]=i+x;
                     }
                     list.add(n);
                     break;
                  }
              }
          }
          int [][]a=new int[list.size()][];
          for (int i=0;i<a.length;i++){
              a[i]=list.get(i);
          }
          return a;
    }

    /**
     * 辅助方法创建ListNode类
     * @param lty
     * @return
     */
    public static ListNode createListNode(long lty){
        String a=lty+"";
        ListNode init=new ListNode(0);
        ListNode x=null;
        char [] charArray=a.toCharArray();
        for (int i=charArray.length-1;i>=0;i--){
            init=new ListNode(charArray[i]-'0');
            init.next=x;
            x=init;
        }
        return x;
    }

    /**
     * 自定义辅助方法
     * 控制台输出ListNode对象节点
     * @param lty
     */
    public static void outListNode(ListNode lty){
        StringBuilder sbd=new StringBuilder();
        while (lty!=null){
            sbd.append(lty.val);
            lty=lty.next;
        }
        System.out.println(sbd.toString());
    }

    public static void main(String[] args) {
       // outListNode(createListNode(123l));
       //outListNode(addTwoNumbers(createListNode(0),createListNode(567)));
        System.out.println(maxArea(new int[]{1,8,6,2,5,4,8,3,7}));
    }


    /**
     * 11. 盛最多水的容器 难度中等
     * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     *
     * 说明：你不能倾斜容器，且 n 的值至少为 2。
     *
     *
     *
     *
     *
     * 图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
     *
     *
     *
     * 示例：
     *
     * 输入：[1,8,6,2,5,4,8,3,7]
     * 输出：49
     * @param height
     * @return
     *
     * 2分计算法
     */
    public static int maxArea(int[] height) {
        int sum=0;
        for(int i=0,y=height.length-1;i<y;){
            int mix=height[i]<height[y]?height[i++]:height[y--];
            sum=Math.max(sum,mix*(y-i+1));
        }
        return sum;
    }

    /**
     * 12. 整数转罗马数字 难度中等
     * 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
     *
     * 字符          数值
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
     *
     * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
     *
     * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
     * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
     * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
     * 给定一个整数，将其转为罗马数字。输入确保在 1 到 3999 的范围内。
     *
     * 示例 1:
     *
     * 输入: 3
     * 输出: "III"
     * 示例 2:
     *
     * 输入: 4
     * 输出: "IV"
     * 示例 3:
     *
     * 输入: 9
     * 输出: "IX"
     * 示例 4:
     *
     * 输入: 58
     * 输出: "LVIII"
     * 解释: L = 50, V = 5, III = 3.
     * 示例 5:
     *
     * 输入: 1994
     * 输出: "MCMXCIV"
     * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
     * @param num
     * @return
     */

    public static String intToRoman(int num) {
        String numStr=String.valueOf(num);
        StringBuilder sbd=new StringBuilder();
        for(int i=0;i<numStr.length();i++){
            int count=Integer.parseInt(String.valueOf(numStr.charAt(i)));
            switch (numStr.length()-i){
                case 4:
                    addStr(count,"M",sbd);
                    break;
                case 3:
                    toStr("C","D","M",count,sbd);
                    break;
                case 2:
                    toStr("X","L","C",count,sbd);
                    break;
                default:
                    toStr("I","V","X",count,sbd);
            }
        }
        return sbd.toString();
    }

    public static void toStr(String one,String five,String ten,int num,StringBuilder sbd){
        if(num<4){
            addStr(num,one,sbd);
        }else if(num==4){
            sbd.append(one+five);
        }else if(num<9){
            sbd.append(five);
            addStr(num-5,one,sbd);
        }else{
            sbd.append(one+ten);
        }
    }

    public static void addStr(int count,String s,StringBuilder sbd){
        for(int i=0;i<count;i++){
            sbd.append(s);
        }
    }
    /**
     * 8. 字符串转换整数 (atoi)
     * 请你来实现一个 atoi 函数，使其能将字符串转换成整数。
     *
     * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。接下来的转化规则如下：
     *
     * 如果第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字字符组合起来，形成一个有符号整数。
     * 假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成一个整数。
     * 该字符串在有效的整数部分之后也可能会存在多余的字符，那么这些字符可以被忽略，它们对函数不应该造成影响。
     * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换，即无法进行有效转换。
     *
     * 在任何情况下，若函数不能进行有效的转换时，请返回 0 。
     *
     * 提示：
     *
     * 本题中的空白字符只包括空格字符 ' ' 。
     * 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。如果数值超过这个范围，请返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。
     *
     *
     * 示例 1:
     *
     * 输入: "42"
     * 输出: 42
     * 示例 2:
     *
     * 输入: "   -42"
     * 输出: -42
     * 解释: 第一个非空白字符为 '-', 它是一个负号。
     *      我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
     * 示例 3:
     *
     * 输入: "4193 with words"
     * 输出: 4193
     * 解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
     * 示例 4:
     *
     * 输入: "words and 987"
     * 输出: 0
     * 解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
     *      因此无法执行有效的转换。
     * 示例 5:
     *
     * 输入: "-91283472332"
     * 输出: -2147483648
     * 解释: 数字 "-91283472332" 超过 32 位有符号整数范围。
     *      因此返回 INT_MIN (−231) 。
     * @param str
     * @return
     */
    public static int myAtoi(String str) {
        str=str.trim();
        StringBuilder sbd=new StringBuilder();
        int count=0;
        char s=0;
       for(int i=0;i<str.length();i++){
           if(!Character.isDigit(str.charAt(i))){
               if(sbd.length()>0){
                   break;
               }
               if((str.charAt(i)=='-'||str.charAt(i)=='+')&&count==0){
                  count++;
                  s=str.charAt(i);
                  continue;
               }
             break;
           }
           sbd.append(str.charAt(i));
       }
       if(sbd.length()>0){
           for(int i=0;i<sbd.length();i++){
               if(sbd.charAt(i)!='0'){
                  break;
               }
               sbd.deleteCharAt(i);
               i--;
           }
       }
       if(count>0){
           sbd.insert(0,s);
       }
       if(sbd.length()==0||(sbd.length()==1&&count==1)){
           return 0;
       }
       Long lty=Long.parseLong(sbd.length()>11?sbd.substring(0,12):sbd.toString());
        return lty>Integer.MAX_VALUE?Integer.MAX_VALUE:lty<Integer.MIN_VALUE?Integer.MIN_VALUE:lty.intValue();
    }

    /**
     * 6. Z 字形变换
     * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
     *
     * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
     *
     * L   C   I   R
     * E T O E S I I G
     * E   D   H   N
     * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
     * LCIRETOESIIGEDHN
     * LCIRETOESIIGEDHN
     * 请你实现这个将字符串进行指定行数变换的函数：
     *
     * string convert(string s, int numRows);
     * 示例 1:
     *
     * 输入: s = "LEETCODEISHIRING", numRows = 3
     * 输出: "LCIRETOESIIGEDHN"
     *
     * 示例 2:
     *
     * 输入: s = "LEETCODEISHIRING", numRows = 4
     * 输出: "LDREOEIIECIHNTSG"
     *       LDREOEII
     * 解释:
     *
     * L     D     R
     * E   O E   I I
     * E C   I H   N
     * T     S     G
     * @param s
     * @param numRows
     * @return
     */
    public static String convert(String s, int numRows) {
        StringBuilder sbd=new StringBuilder(s.length());
        int a=numRows+numRows-2;
        int count=s.length()/a;
            count+=s.length()%a!=0?1:0;
        Boolean isTwo=false;
            one:
            for(int i=0;i<numRows;i++){
                isTwo=i!=0&&i!=numRows-1;
                for(int y=0;y<count;y++){
                    int index=y*a+i;
                    if(index>s.length()-1){
                        break one;
                    }
                    sbd.append(s.charAt(index));
                    if(isTwo){
                        if(index+a-i*2>s.length()-1){
                            continue one;
                        }
                        sbd.append(s.charAt(index+a-i*2));
                    }
                }
            }
        return sbd.toString();
    }

    /**
     * 10000000000000000000000000000000001
     * [1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1]
     * [5,6,4]
     *
     * 2. 两数相加
     * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
     *
     * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
     *
     * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     *
     * 示例：
     * 342
     * 765
     * 1107
     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 0 -> 8
     * 原因：342 + 465 = 807
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        char[] c1=getListNode(l1);
        char[] c2=getListNode(l2);
        char [] big=c1.length>=c2.length?c1:c2;
        char [] less=c1.length<c2.length?c1:c2;
        int [] s=new int[big.length];
        ListNode init=null;
        ListNode x=null;
        Boolean carryBit=false;
        for (int i=0,y=0;i<big.length;i++,y++){
            int lty=big[i]-'0'+(y<less.length?less[y]-'0':0);
            lty+=carryBit?1:0;
            carryBit=false;
            if(lty>9){
                lty=lty%10;
                carryBit=true;
            }
            s[i]=lty;
        }
        if(carryBit){
            x=new ListNode(1);
        }
        for(int i=s.length-1;i>=0;i--){
            init=new ListNode(s[i]);
            init.next=x;
            x=init;
        }

        return x;
    }

    public static char[] getListNode(ListNode lty){
        StringBuilder sbd=new StringBuilder();
        while (lty!=null){
            sbd.append(lty.val);
            lty=lty.next;
        }
        return sbd.toString().toCharArray();
    }

    /**
     * 到30算输bai：要依次抢占这du几个数：2.5.8.11.14.17.20.23.26.29
     * 到30算胜zhi：要依次抢占这几个数：dao3.6.9.12.15.18.21.24.27.30
     * 数3个数的：
     * 到30算输：要依次抢占这几个数： 1.5.9.13.17.21.25.29
     * 到30算输：要依次抢占这几个数：2.6.10.14.18.22.26.30
     */

    /**
     * 剑指 Offer 67. 把字符串转换成整数
     * 写一个函数 StrToInt，实现把字符串转换成整数这个功能。不能使用 atoi 或者其他类似的库函数。

     * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。
     *
     * 当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。
     *
     * 该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。
     *
     * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。
     *
     * 在任何情况下，若函数不能进行有效的转换时，请返回 0。
     *
     * 说明：
     *
     * 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。如果数值超过这个范围，请返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。
     *
     * 示例 1:
     *
     * 输入: "42"
     * 输出: 42
     * 示例 2:
     *
     * 输入: "   -42"
     * 输出: -42
     * 解释: 第一个非空白字符为 '-', 它是一个负号。
     *      我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
     * 示例 3:
     *
     * 输入: "4193 with words"
     * 输出: 4193
     * 解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
     * 示例 4:
     *
     * 输入: "words and 987"
     * 输出: 0
     * 解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
     *      因此无法执行有效的转换。
     * 示例 5:
     *"20000000000000000000"
     * 输入: "-91283472332"
     * 输出: -2147483648
     * 解释: 数字 "-91283472332" 超过 32 位有符号整数范围。
     *      因此返回 INT_MIN (−231) 。
     * @param str
     * @return
     */
    public static int strToInt(String str) {
        if(str==null){
            return 0;
        }
        str=str.trim();
        if(str.length()==0){
            return 0;
        }
        char[] intArray=str.toCharArray();
        if (intArray[0]!=43&&intArray[0]!=45&&(intArray[0]>57||intArray[0]<48)){
           return 0;
        }
        int i=1;
        Boolean isNegative=intArray[0]==45;
        int start=0;
        for (;i<intArray.length;i++){
            char a=intArray[i];
            if(a>57||a<48){
              break;
            }
        }
        if(i==1&&(intArray[0]==45||intArray[0]==43)){
            return 0;
        }
        String Longstr=str.substring(start,i>11?12:i);
        Long returnInt=Long.parseLong(Longstr);
        return returnInt>2147483647?2147483647:returnInt<-2147483648?-2147483648:Integer.parseInt(Longstr);
    }

    /**
     * 58. 最后一个单词的长度
     * 给定一个仅包含大小写字母和空格 ' ' 的字符串 s，返回其最后一个单词的长度。如果字符串从左向右滚动显示，那么最后一个单词就是最后出现的单词。
     *
     * 如果不存在最后一个单词，请返回 0 。
     *
     * 说明：一个单词是指仅由字母组成、不包含任何空格字符的 最大子字符串。
     *
     *
     *
     * 示例:
     *
     * 输入: "Hello World"
     * 输出: 5
     * @param s
     * @return
     */
    public static int lengthOfLastWord(String s) {
        int lty=0;
        s=s.trim();
        int len=s.length();
        for(int i=len-1;i>=0;i--){
            if(s.charAt(i)==' '){
                return lty;
            }
            lty++;
        }
        return len;
    }


    /**
     * 226. 翻转二叉树
     * 翻转一棵二叉树。
     *
     * 示例：
     *
     * 输入：
     *
     *      4
     *    /   \
     *   2     7
     *  / \   / \
     * 1   3 6   9
     * 输出：
     *
     *      4
     *    /   \
     *   7     2
     *  / \   / \
     * 9   6 3   1
     * 备注:
     * 这个问题是受到 Max Howell 的 原问题 启发的 ：
     *
     * 谷歌：我们90％的工程师使用您编写的软件(Homebrew)，但是您却无法在面试时在白板上写出翻转二叉树这道题，这太糟糕了。
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        return null;
    }

    /**
     * 面试题68 - I. 二叉搜索树的最近公共祖先
     * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
     * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
     * 例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]
     * 示例 1:
     * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
     * 输出: 6
     * 解释: 节点 2 和节点 8 的最近公共祖先是 6。
     * 示例 2:
     * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
     * 输出: 2
     * 解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。
     * 说明:
     * 所有节点的值都是唯一的。
     * p、q 为不同节点且均存在于给定的二叉搜索树中。
     * @param root
     * @param p
     * @param q
     * @return
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        return null;
    }

    /**
     * "BRBB"
     * "RBGY"
     *
     * "YBBY"
     * "GYYB"
     * 面试题 16.15. 珠玑妙算
     * 珠玑妙算游戏（the game of master mind）的玩法如下。
     *
     * 计算机有4个槽，每个槽放一个球，颜色可能是红色（R）、黄色（Y）、绿色（G）或蓝色（B）。例如，计算机可能有RGGB 4种（槽1为红色，槽2、3为绿色，槽4为蓝色）。作为用户，你试图猜出颜色组合。打个比方，你可能会猜YRGB。要是猜对某个槽的颜色，则算一次“猜中”；要是只猜对颜色但槽位猜错了，则算一次“伪猜中”。注意，“猜中”不能算入“伪猜中”。
     *
     * 给定一种颜色组合solution和一个猜测guess，编写一个方法，返回猜中和伪猜中的次数answer，其中answer[0]为猜中的次数，answer[1]为伪猜中的次数。
     *
     * 示例：
     *
     * 输入： solution="RGBY",guess="GGRR"
     * 输出： [1,1]
     * 解释： 猜中1次，伪猜中1次。
     * 提示：
     *
     * len(solution) = len(guess) = 4
     * solution和guess仅包含"R","G","B","Y"这4种字符
     * @param solution
     * @param guess
     * @return
     */
    public static int[] masterMind(String solution, String guess) {
        int isTrue=0;
        int fake=0;
        char [] a=solution.toCharArray();
        char [] b=guess.toCharArray();
        char [] x=new char[]{'R','G','B','Y'};
        for (char i:x){
            int c=getArrayIndexCount(b,i);
            int v=getArrayIndexCount(a,i);
            fake+=c>=v?v:c;
        }
        for(int i=0;i<a.length;i++){
            if(a[i]==b[i]){
                isTrue++;
                fake--;
            }
        }
        return new int[]{isTrue,fake};
    }

    public static int getArrayIndexCount(char [] array,char index){
        int count=0;
        for(int i=0;i<array.length;i++){
            count=index==array[i]?++count:count;
        }
        return count;
    }

    /**
     * 283. 移动零
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     *
     * 示例:
     *
     * 输入: [0,1,0,3,12]
     * 输出: [1,3,12,0,0]
     * 必须在原数组上操作，不能拷贝额外的数组。
     * 尽量减少操作次数。
     * @param nums
     */
    public static void moveZeroes(int[] nums) {
        int index=nums.length-1;
        for (int i=0;i<nums.length;i++){
            if(index<=i){
                break;
            }
            if (nums[i]==0){
                for(int a=i;a<index;a++){
                    nums[a]=nums[a]+nums[a+1];
                    nums[a+1]=nums[a]-nums[a+1];
                    nums[a]=nums[a]-nums[a+1];
                }
                index--;
                i=nums[i]==0?--i:i;
            }
        }
    }


    /**
     *
     * @param n
     * @return
     */
    public static int trailingZeroes(int n) {
        int lty = 0;
        while(n >= 5) {
            lty += n / 5;
            n /= 5;
        }
        return lty;
    }

    /**
     * 1351. 统计有序矩阵中的负数
     * 给你一个 m * n 的矩阵 grid，矩阵中的元素无论是按行还是按列，都以非递增顺序排列。
     * 请你统计并返回 grid 中 负数 的数目。
     * 示例 1：
     *
     * 输入：grid = [[4,3,2,-1],[3,2,1,-1],[1,1,-1,-2],[-1,-1,-2,-3]]
     * 输出：8
     * 解释：矩阵中共有 8 个负数。
     * 示例 2：
     *
     * 输入：grid = [[3,2],[1,0]]
     * 输出：0
     * 示例 3：
     *
     * 输入：grid = [[1,-1],[-1,-1]]
     * 输出：3
     * 示例 4：
     *
     * 输入：grid = [[-1]]
     * 输出：1
     * @param grid
     * @return
     */
    public static int countNegatives(int[][] grid) {
        int count=0;
        for(int [] lty:grid){
           for(int i=0;i<lty.length;i++){
               if(lty[i]<0){
                   count+=lty.length-i;
                   break;
               }
           }
        }
        return count;
    }

    /**
     * 面试题29. 顺时针打印矩阵
     * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。

     * 示例 1：
     *[1,2,3],
     *[4,5,6],
     *[7,8,9]
     * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
     * 输出：[1,2,3,6,9,8,7,4,5]
     * 示例 2：
     *
     * [1,2,5,2,3,4,7,8,9],
     * [1,2,5,2,3,4,7,8,9],
     * [1,2,5,2,3,4,7,8,9],
     * [1,2,5,2,3,4,7,8,9],
     * [1,2,5,2,3,4,7,8,9],
     * [1,2,5,2,3,4,7,8,9],
     * [1,2,5,2,3,4,7,8,9],
     * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
     * [1,2,3,4],
     * [5,6,7,8],
     * [9,10,11,12]
     输出：[1,2,3,4,8,12,11,10,9,5,6,7]

     *[1,2,3,4],
     *[5,6,7,8],
     *[9,10,11,12]
     * @param matrix
     * @return
     */
    public static int[] spiralOrder(int[][] matrix) {
        if(matrix.length==0){
            return new int[]{};
        }
        int[] lty=new int[matrix.length*matrix[0].length];
        int tier=(matrix.length+1)/2;
        int ltyIndex=0;
        int twoLength=matrix[0].length;
        for (int t=1;t<=tier;t++){
            int xCount=twoLength-t;
            int yCount=matrix.length-t;
             for(int i=t-1;i<xCount&&ltyIndex<lty.length;i++,ltyIndex++){
                 lty[ltyIndex]=matrix[t-1][i];
             }
             for(int i=t-1;i<yCount&&ltyIndex<lty.length;i++,ltyIndex++){
                    System.out.println(ltyIndex+"|"+i+"|"+(twoLength-t)+"|"+matrix[i][twoLength-t]);
                    lty[ltyIndex]=matrix[i][twoLength-t];
             }
             for(int i=xCount;i>0+t-1&&ltyIndex<lty.length;i--,ltyIndex++){
                lty[ltyIndex]=matrix[matrix.length-t][i];
             }
             for(int i=yCount;i>0+t-1&&ltyIndex<lty.length;i--,ltyIndex++){
                    lty[ltyIndex]=matrix[i][t-1];
             }
        }
        if(twoLength==matrix.length&&twoLength%2==1){
            lty[lty.length-1]=matrix[tier-1][tier-1];
        }

        return lty;
    }

//      for (int c=t==0?twoLength:twoLength-t*2,index=t;c>0&&ltyIndex<lty.length;c--,ltyIndex++,index++){
//        lty[ltyIndex]=matrix[t][index];
//    }
//               for (int c=t+1;c<matrix.length-t&&ltyIndex<lty.length;c++,ltyIndex++){
//        lty[ltyIndex]=matrix[c][twoLength-(t+1)];
//    }
//               for (int c=t==0?twoLength-1:twoLength-1-t*2,index=twoLength-(t+2);c>0&&ltyIndex<lty.length;c--,ltyIndex++,index--){
//        lty[ltyIndex]=matrix[matrix.length-t-1][index];
//    }
//               for (int c=matrix.length-(t+1)*2;c>0&&ltyIndex<lty.length;c--,ltyIndex++){
//        lty[ltyIndex]=matrix[c][t];
//    }

    /**
     * 面试题 17.04. 消失的数字
     * 数组nums包含从0到n的所有整数，但其中缺了一个。请编写代码找出那个缺失的整数。你有办法在O(n)时间内完成吗？
     *
     * 注意：本题相对书上原题稍作改动
     *
     * 示例 1：
     *
     * 输入：[3,0,1]
     * 输出：2
     *      [1,2,3,4,5]
     *
     * 示例 2：
     *
     * 输入：[9,6,4,2,3,5,7,0,1]
     * 输出：8
     * @param nums
     * @return
     */
    public static int missingNumber(int[] nums) {
        int sum=0;
        int numsSum=0;
        for(int i=0;i<nums.length;i++){
            numsSum+=nums[i];
            sum+=i+1;
        }
        return sum-numsSum==0?0:sum-numsSum;
    }
//    public static int sum(int max){
//
//    }




    public static int minList(List<Integer> list){
        System.out.println(JSON.toJSONString(list));
        List<Integer> a=new ArrayList<Integer>();
        for(int i=0;i<list.size();i++){
            if((i+1)%2==1){
                a.add(list.get(i));
            }
        }
        list.removeAll(a);
        if (list.size()!=1){
           return minList(list);
        }else {
            return list.get(0);
        }

    }

    /**
     * 21. 合并两个有序链表
     * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     *     示例：
     *     输入：1->2->4, 1->3->4
     *     输出：1->1->2->3->4->4
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val <= l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }
    static int i=0;
    public static ListNode nodeSort(ListNode init,ListNode l1, ListNode l2){
        i++;
        if(l2==null){
            return l1;
        }
        if (l1==null){
            return nodeSort(init,init,l2.next);
        }else if(l2.val<l1.val){
            l2.val=l1.val+l2.val;
            l1.val=l2.val-l1.val;
            l2.val=l2.val-l1.val;
            return nodeSort(init,l1.next,l2);
        }else{
            return nodeSort(init,l1.next,l2);
        }
    }

    public static ListNode getLostNode(ListNode node){
        return node.next!=null?getLostNode(node.next) :node;
    }

    public static ListNode createListNode(int[] intArray){
        ListNode a=null;
        for(int i=intArray.length-1;i>=0;i--){
            if(a!=null){
                ListNode x=new ListNode(intArray[i]);
                x.next=a;
                a=x;
            }else{
                a=new ListNode(intArray[i]);
            }
        }
        return a;
    }



    public static void showListNode(ListNode node){
        if (node.next!=null){
            System.out.print(node.val+",");
            showListNode(node.next);
        }else{
            System.out.print(node.val+"\n");
        }
    }




    
}
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
