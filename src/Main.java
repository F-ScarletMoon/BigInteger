public class Main {
    public static void main(String args[]) {
        String sa = "-9,0500,0004";
        String sb = "-2,0015";
        //int[] a = Transformer.intoArray(sa);
        //int[] b = Transformer.intoArray(sb);
        //int[] c;

        /*if(a.length > b.length) {
            c = new int[a.length];
            System.arraycopy(b, 0, c, 0, b.length);
            b = c;
        }
        else if (a.length < b.length) {
            c = new int[b.length];
            System.arraycopy(a, 0, c, 0, a.length);
            a = c;
        }*/

        LongInt a = new LongInt(sa);
        LongInt b = new LongInt(sb);
        a.add(b);
        System.out.println(a.toString());

        //int[] d = Calculation.divAsHand(a,b);
        //int[] d = {0,9,10};
        //System.out.println(Transformer.intoSring(d));

    }
}
