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
                if (a[i] < 10)
                    s.append("000");
                else if (a[i] < 100)
                    s.append("00");
                else if (a[i] < 1000)
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
        //for(int i = 0; i < b.length && i < a.length; i++)
            //b[i] = a[i];
        System.arraycopy(a,0,b,0,a.length > b.length?b.length:a.length);
        return b;
    }

    //去除先导0
    public static int[] removePreZero(int[] a) {
        while (true) {
            if (a.length > 2 && a[a.length-1] == 0)
                a = setLength(a,a.length-1);
            else
                return a;
        }
    }

    //把int整数变为数组
    public static int[] intoArray(int i) {
        int[] i1 = {0,i};
        return i1;
    }
}
