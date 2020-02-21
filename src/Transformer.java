public class Transformer {
    /*public static int[] intoArray(String string) {
        int[] a = new int[string.length()/ 4 + 2];
        if (string.charAt(0) == '-')
            a[0] = 1;
        string = string.replaceAll("-","");
        for (int i = string.length() - 1,j = 1; i >= 0; i-=4,j++) {
            if(i - 3 >= 0)
                a[j] = Integer.parseInt(string.substring(i-3,i));
            else
                a[j] = Integer.parseInt(string.substring(0,i));
        }
        return a;
    } */


    public static int[] intoArray(String string) {
        String[] s = string.split(",");
        int[] a = new int[s.length+1];
        if (s[0].charAt(0) == '-') {
            a[0] = 1;
            s[0] = s[0].replaceAll("-","");
        }
        for(int i = 0; i < s.length; i++) {
            a[s.length - i] = Integer.parseInt(s[i]);
        }
        return  a;
    }

    public static String intoSring(int[] a) {
        StringBuilder s = new StringBuilder();
        if (a[0] == 1)
            s.append("-");
        for (int i = a.length - 1; i > 0; i--) {
            if (i != a.length - 1) {
                for (int j = (a[i]!=0?a[i]:a[i]+1); j < 1000; j *= 10)
                    s.append("0");
            }
            s.append(a[i]);
            if (i > 1)
                s.append(",");
        }
        if(s.charAt(0) == '0')
            return s.toString().replaceFirst("0,(0000,)*0*","");
        else
            return s.toString();
    }

    //将数组长度设为指定值
    public static int[] setLength(int[] a, int length) {
        int[] b = new int[length];
        for(int i = 0; i < b.length && i < a.length; i++)
            b[i] = a[i];
        return b;
    }
}
