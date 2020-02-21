public class Main {
    public static void main(String args[]) {
        String sa = "1,0000,0000";
        String sb = "1";
        int[] a = Transformer.intoArray(sa);
        int[] b = Transformer.intoArray(sb);
        int[] c;

        if(a.length > b.length) {
            c = new int[a.length];
            System.arraycopy(b, 0, c, 0, b.length);
            b = c;
        }
        else if (a.length < b.length) {
            c = new int[b.length];
            System.arraycopy(a, 0, c, 0, a.length);
            a = c;
        }

        int[] d = Calculation.div(a,b);
        //int[] d = {0,0,10};
        System.out.println(Transformer.intoSring(d));
    }
}
