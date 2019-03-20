public class MyAtoi {
    public static void main(String[] args){
        String str = "2547856210";
        System.out.println(String.valueOf(atoi(str)));
    }
    public static int atoi(String str){
        str = str.trim();
        if(str.length() == 0) return 0;
        int sign = 1;
        long sum = 0;
        int i = 0;
        char first = str.charAt(i);
        if(first == '-'){
            sign = -1;
            i++;
        }else if(first == '+'){
            sign = 1;
            i++;
        }
        for(; i < str.length(); i++){
            if(!Character.isDigit(str.charAt(i))){
                return (int)sum*sign;
            }
            sum = sum*10 + str.charAt(i) - '0';
            if (sign == 1 && sum > Integer.MAX_VALUE)
                return Integer.MAX_VALUE;
            if (sign == -1 && (-1) * sum < Integer.MIN_VALUE)
                return Integer.MIN_VALUE;
        }
        return (int)sum*sign;
    }
}