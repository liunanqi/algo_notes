public class CountAndSay {
    public static void main(String[] args){
        int n = 15;
        for(int i = 0; i < n; i++){
            System.out.println(countAndSay(i + 1));
        }
    }

    public static String  countAndSay(int n){
        StringBuilder curr = new StringBuilder("1");
        StringBuilder prev;
        int count;
        char say;
        for(int i = 1; i < n; i++){
            prev = curr;
            count = 1;
            say = prev.charAt(0);
            curr = new StringBuilder();
            for(int j = 1; j < prev.length(); j++){
                if(prev.charAt(j) != say){
                    curr.append(count).append(say);
                    count = 1;
                    say = prev.charAt(j);
                }else{
                    count++;
                }
            }
            curr.append(count).append(say);
        }
        return curr.toString();
    }
}