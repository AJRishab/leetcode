import java.util.ArrayList;
import java.util.List;

public class Palindrome_Partitioning {

    public class Solution_dp {

        public List<List<String>> partition(String s) {

            int n = s.length();
            List<List<String>> res = new ArrayList<>();
            List<String> out = new ArrayList<>();
            boolean[][] dp = new boolean[n][n];
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j <= i; ++j) {
                    if (s.charAt(i) == s.charAt(j) && (i - j <= 2 || dp[j + 1][i - 1])) {
                        dp[j][i] = true;
                    }
                }
            }

            helper(s, 0, dp, out, res);
            return res;
        }

        void helper(String s, int start, boolean[][] dp, List<String> out, List<List<String>> res) {
            if (start == s.length()) {
                res.add(new ArrayList<>(out));
                return;
            }
            for (int i = start; i < s.length(); ++i) {
                if (!dp[start][i]) continue;

                out.add(s.substring(start, i + 1));
                helper(s, i + 1, dp, out, res);
                out.remove(out.size() - 1);
            }
        }
    }

    // based on part-I, just count each partition to find min...
    public class Solution_over_time {
        List<List<String>> list = new ArrayList<>();

        public List<List<String>> partition(String s) {

            if (s == null || s.length() == 0) {
                return list;
            }

            find(s, new ArrayList<String>());

            return list;
        }

        private void find(String s, ArrayList<String> currentList) {

            if (s.length() == 0) {
                list.add(currentList);

                return;
            }

            // idea is, scan from index=0, to find each palindrome, then rest substring to next recursion
            for (int i = 0; i < s.length(); i++) {

                String sub = s.substring(0, i + 1); // @note: substring 0-s[i]
                // System.out.println("substring is: " + sub);

                if (isPal(sub)) {

                    ArrayList<String> nextList = new ArrayList<>(currentList); // deep copy
                    nextList.add(sub);
                    find(s.substring(i + 1), nextList);
                }
            }

        }

        private boolean isPal(String s) {
            int i = 0;
            int j = s.length() - 1;

            while (i <= j) {

                // @note: better in if (s.charAt(i++) != s.charAt(j--)) { // @note: 忘了这里的++和--。。。
                if (s.charAt(i) != s.charAt(j)) {
                    return false;
                }

                i++;
                j--;

            }

            return true;
        }
    }

}

class Solution {
    private boolean[][] dp;
    private List<List<String>> ans;
    private int n;

    public List<List<String>> partition(String s) {
        ans = new ArrayList<>();
        n = s.length();
        dp = new boolean[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(dp[i], true);
        }
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1];
            }
        }
        dfs(s, 0, new ArrayList<>());
        return ans;
    }

    private void dfs(String s, int i, List<String> t) {
        if (i == n) {
            ans.add(new ArrayList<>(t));
            return;
        }
        for (int j = i; j < n; ++j) {
            if (dp[i][j]) {
                t.add(s.substring(i, j + 1));
                dfs(s, j + 1, t);
                t.remove(t.size() - 1);
            }
        }
    }
}