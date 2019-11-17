package leetcode.L;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class L279PerfectSquares {

    // 练习DP的好题目，不能看答案，自己想
    // 首先是暴力的破解方案,首先自己梳理对应的逻辑处理过程
    // n 会被分解成 Math.sqrt(n) 小的平方和，所以按照最贪婪的方式来写，首先从大的开始遍历
    // 暴力破解以后，在加上cache缓存，然后再去尝试DP的方案，也就是先去求Cache的值，然后再去求问题的解
    public Map<Integer,Integer> r = new HashMap<Integer,Integer>();
    public int numSquares(int n) {
        if(r.containsKey(n)) return r.get(n);
        if(n == 0) return 0;
        int i = (int)Math.sqrt(n);
        int result = Integer.MAX_VALUE;
        for (;i >= 1; i--){
            if(i*i <= n){
                int re = numSquares(n-i*i);
                if(re != -1) result = Math.min(re+1,result);
            }
        }
       int vr =  result == Integer.MAX_VALUE? -1:result ;
        r.put(n,vr);
        return vr;
    }


    // 在实现DP的时候，肯定会有一个DP的数组，这个时候，
    // 我们需要仔细的想一想 哪个Cache是如何生成的
    //
    public int numSquares_DP(int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp,n+1);
        dp[1] = 1;
        // 填充这个DP数组
        for (int i = 2 ; i <= n ; i++){
            int k = (int)Math.sqrt(i);
            if(k*k == i){
                dp[i] = 1;
            }else{
                //怎么得到dp[i] 这个比较的关键，因为这个是状态转移方程
                for(int j=1; j <= i/2; j++ )
                    dp[i] = Math.min(dp[i-j]+dp[j],dp[i]);
            }
        }
        return dp[n];
    }

    /**
     * 这个实现，才吃最经典的实现，但是我们的那个实现，就是为了凑数了，为什么尼，因为我们没有能够
     * 推导出来，正确的状态转移方程
     *
     dp[0] = 0
     dp[1] = dp[0]+1 = 1
     dp[2] = dp[1]+1 = 2
     dp[3] = dp[2]+1 = 3
     dp[4] = Min{ dp[4-1*1]+1, dp[4-2*2]+1 }
     = Min{ dp[3]+1, dp[0]+1 }
     = 1
     dp[5] = Min{ dp[5-1*1]+1, dp[5-2*2]+1 }
     = Min{ dp[4]+1, dp[1]+1 }
     = 2
     .
     .
     .
     dp[13] = Min{ dp[13-1*1]+1, dp[13-2*2]+1, dp[13-3*3]+1 }
     = Min{ dp[12]+1, dp[9]+1, dp[4]+1 }
     = 2
     .
     .
     .
     dp[n] = Min{ dp[n - i*i] + 1 },  n - i*i >=0 && i >= 1

     * */
    public int numSquares_DP_simple(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i = 1; i <= n; ++i) {
            int min = Integer.MAX_VALUE;
            int j = 1;
            while(i - j*j >= 0) {
                min = Math.min(min, dp[i - j*j] + 1);
                ++j;
            }
            dp[i] = min;
        }
        return dp[n];
    }

    public static void main(String[] args) {
        L279PerfectSquares test = new L279PerfectSquares();
        System.out.println(test.numSquares(12));
        System.out.println(test.numSquares(13));
        System.out.println(test.numSquares(11));
        System.out.println(test.numSquares(10));
        System.out.println(test.numSquares(52));

        for (int i = 1 ; i < 100; i++){
            System.out.println(test.numSquares(i) + " " + test.numSquares_DP(i));
        }
    }
}
