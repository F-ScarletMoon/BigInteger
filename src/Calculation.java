public class Calculation {

    private final static int[] one = {0,1};

    //处理正负号
    public static int[] add(int[] a, int[] b) {
        if (a.length > b.length)
            b = Transformer.setLength(b,a.length);
        else
            a = Transformer.setLength(a,b.length);

        int[] m,n,answer;
        if (a[a.length-1] > b[b.length-1]) {
            m = a;
            n = b;
        }
        else {
            m = b;
            n = a;
        }
        if (m[0] == 0 && n[0] == 0)
            return Calculation.addT(m,n);
        else if (m[0] == 0 && n[0] == 1)
            return Calculation.subT(m,n);
        else if (m[0] == 1 && n[0] == 0) {
            answer = Calculation.subT(m,n);
            answer[0] = 1;
            return answer;
        }
        else {
            answer = Calculation.addT(m,n);
            answer[0] = 1;
            return answer;
        }
    }

    public static int[] sub(int[] a,int[] b) {
        if (a.length > b.length)
            b = Transformer.setLength(b,a.length);
        else
            a = Transformer.setLength(a,b.length);

        int[] m,n,answer;
        if (a[a.length-1] > b[b.length-1]) {
            m = a;
            n = b;
        }
        else {
            m = b;
            n = a;
        }

        if (m[0] == 0 && n[0] == 0)
            return Calculation.subT(m,n);
        else if (m[0] == 0 && n[0] == 1)
            return Calculation.addT(m,n);
        else if (m[0] == 1 && n[0] == 0) {
            answer = Calculation.addT(m,n);
            answer[0] = 1;
            return answer;
        }
        else {
            answer = Calculation.subT(m,n);
            answer[0] = 1;
            return answer;
        }
    }

    public static int[] mul(int[] a,int[] b) {
        int[] answer;
        if (a[0] == 0 && b[0] == 0 || a[0] == 1 && b[0] == 1)
            return Calculation.mulT(a,b);
        else {
            answer = Calculation.mulT(a,b);
            answer[0] = 1;
            return answer;
        }
    }

    public static int[] div(int[] a,int[] b) {
        int[] answer;
        if (a[0] == 0 && b[0] == 0 || a[0] == 1 && b[0] == 1)
            return Calculation.divT(a,b);
        else {
            answer = Calculation.div(a,b);
            answer[0] = 1;
            return answer;
        }
    }

    //标准的加减乘除

    private static int[] addT(int[] a,int[] b) {
        int[] c = new int[a.length + 1];

        for (int i = 1; i < a.length; i++) {
            c[i] += a[i] + b[i];
            c[i + 1] += c[i] / 10000;
            c[i] %= 10000;
        }

        if (c[c.length-1] == 0)
            c = Transformer.setLength(c,c.length-1);
        return c;
    }

    private static int[] subT(int[] a,int[] b) {
        for(int i = 1; i < a.length; i++) {
            if(a[i] < b[i]) {
                a[i] += 10000;
                a[i + 1] -= 1;
            }
            a[i] -= b[i];
        }
        return a;
    }

    private static int[] mulT(int[] a, int[] b) {
        //模拟手算
        int[] c = new int[a.length + b.length];
        long temp;
        for (int i = 1; i < a.length; i++) {
            for (int j = 1; j < b.length; j++) {
                temp = a[i] * b[j];
                c[i+j-1] += (int)temp%10000;
                c[i+j] += (int)temp/10000 + c[i+j-1]/10000;
                c[i+j-1] %= 10000;
            }
        }
        return c;
    }

    private static int[] divT(int[] a, int[] b) {
        int[] answer = {0,0};
        //控制循环
        boolean flag = true;
        while (flag) {
            //
            if (a[a.length-1] > b[b.length-1]) {
                a = Calculation.subT(a, b);
                //商加1
                answer = Calculation.add(answer,Calculation.one);
                System.out.println(Transformer.intoSring(answer));
            }
            else if (a[a.length-1] == b[b.length-1]) {
                if (b[b.length-1] == 0) {
                    a = Transformer.setLength(a,a.length-1);
                    b = Transformer.setLength(b,b.length-1);
                }
                else {
                    answer = Calculation.add(answer,Calculation.one);
                    flag = false;
                }
            }
            else
                flag = false;
        }
        return answer;
    }


}
