package other;

public class TestMatch {
    public static void main(String[] args) {
        Solution testMatch = new Solution();

        boolean result = testMatch.isPalindrome(1512);

        System.out.println(result);
    }

}

//class other.Solution {  //reverse
//    public int reverse(int x) {
//
//        if (x == 0) return 0;
//
//        long re = 0;
//
//        while (x != 0) {
//            re = re * 10 + x % 10;
//
//            x = x / 10;
//        }
//
//        if (re > Integer.MAX_VALUE || re < Integer.MIN_VALUE) {
//            return 0;
//        }
//
//        return (int) re;
//    }
//}
class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0) {

            return false;

        }

        if (x < 10) {

            return true;

        }
        StringBuffer stringBuffer = new StringBuffer(x+"");

        if (stringBuffer.toString().equals(stringBuffer.reverse().toString())) {

            return true;

        } else {

            return false;

        }
    }
}