import java.util.HashSet;
import java.util.Set;

public class SubString {
    public int lengthOfLongestSubstring(String s) {
        //set + sliding window
        int len = 0;
        int n = s.length();
        int l = 0, r = 0;
        Set<Character> set = new HashSet<>();
        while(l < n && r < n){
            if(!set.contains(s.charAt(r))){
                set.add(s.charAt(r++));
                len = Math.max(len, r - l);
            }else{
                set.remove(s.charAt(l++));
            }
        }
        return len;
    }

    public int longestPalindrome(String s) {
        if(s == null || s.length() == 0) return 0;
        Set<Character> set = new HashSet<>();
        int count = 0;
        for(int i = 0; i < s.length(); i++){
            if(set.contains(s.charAt(i))){
                set.remove(s.charAt(i));
                count++;
            }else{
                set.add(s.charAt(i));
            }
        }
        return set.isEmpty() ? count * 2 : count * 2 + 1;
    }
}