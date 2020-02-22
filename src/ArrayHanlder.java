public class ArrayHanlder {
    public static int[] add(int[] a,int[] b) {
        a = ensureLength(a,b);
        b = ensureLength(b,a);

        int[] c = new int[a.length+1];

        for (int i = 0; i < a.length; i++) {
            c[i] += a[i] + b[i];
            c[i + 1] += c[i] / 10000;
            c[i] %= 10000;
        }

        return removePreZero(c);
    }

    public static int[] sub(int[] m,int[] b) {
        m = ensureLength(m,b);
        b = ensureLength(b,m);

        for (int i = m.length-1; i >=0 ; i--) {
            if (m[i] < b[i]) {
                int[] temp = b;
                b = m;
                m = temp;
                break;
            }
            else if (m[i] > b[i])
                break;
        }

        int[] a = m;
        for(int i = 0; i < a.length; i++) {
            if(a[i] < b[i]) {
                a[i] += 10000;
                a[i + 1] -= 1;
            }
            a[i] -= b[i];
        }
        return a;
    }

    public static int[] mul(int[] a, int[] b) {
        a = removePreZero(a);
        b = removePreZero(b);
        //模拟手算
        int[] c = new int[a.length + b.length];
        long temp;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                temp = a[i] * b[j];
                c[i+j] += (int)temp%10000;
                c[i+j+1] += (int)temp/10000 + c[i+j-1]/10000;
                c[i+j] %= 10000;
            }
        }
        return c;
    }

    public static int[] shiftR(int[] i) {
        int[] j = new int[i.length+1];
        System.arraycopy(i,0,j,1,i.length);
        return j;
    }

    public static int[] shiftL(int[] i) {
        int[] j = new int[i.length-1];
        System.arraycopy(i,1,j,0,j.length);
        return j;
    }

    public static int[] ensureLength(int[] source, int[] aim) {
        if (source.length < aim.length) {
            return setLength(source,aim.length);
        }
        else
            return source;
    }

    public static int[] removePreZero(int[] a) {
        while (true) {
            if (a.length > 1 && a[a.length-1] == 0)
                a = setLength(a,a.length-1);
            else
                return a;
        }
    }

    public static int[] setLength(int[] a, int length) {
        int[] b = new int[length];
        System.arraycopy(a,0,b,0,a.length > b.length?b.length:a.length);
        return b;
    }

}
