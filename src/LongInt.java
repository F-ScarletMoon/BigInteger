public class LongInt {
    private int bit = 0;
    private int[] value = {0};
    private boolean isMinus = false;

    LongInt(){}

    LongInt(int i) {
        int abs = Math.abs(i);
        this.value = abs<10000?new int[]{abs}:new int[]{abs%10000,abs/10000};
        this.isMinus = i<0?true:false;
        this.bit = value.length;
    }

    LongInt(int[] value,boolean isMinus) {
        this.value = value;
        this.bit = value.length;
        this.isMinus = isMinus;
    }

    LongInt(String string) {
        String[] s = string.split(",");
        this.value = new int[s.length];
        this.bit = value.length;
        if (s[0].charAt(0) == '-') {
            this.isMinus = true;
            s[0] = s[0].replace("-","");
        }
        for(int i = 0; i < s.length; i++) {
            this.value[s.length - i -1] = Integer.parseInt(s[i]);
        }
    }

    public void setValue(int[] value) {
        this.value = value;
    }

    public int[] getValue() {
        return this.value;
    }

    public void setMinus(boolean isMinus) {
        this.isMinus = isMinus;
    }

    public boolean isMinus() {
        return this.isMinus;
    }

    public boolean compareTo(LongInt longInt) {
        if (bit > longInt.bit)
            return true;
        else if (bit < longInt.bit)
            return false;
        else {
            if(value[bit-1] > longInt.value[bit-1])
                return true;
            else 
                return false;
        }
    }

    public void setBit(int length) {
        int[] i = new int[length];
        System.arraycopy(value,0,i,0,bit > i.length?i.length:bit);
        bit = length;
        value = i;
    }

    public void removePreZero() {
        int flag = 0;
        for(int i = bit-1; i > 0; i--)
            if (value[i] == 0)
                flag++;
        setBit(bit-flag);
    }

    public void equal(LongInt longInt) {
        this.value = longInt.value;
        this.bit = longInt.bit;
        this.isMinus = longInt.isMinus;
    }

    @Override
    public String toString(){
        removePreZero();
        StringBuilder s = new StringBuilder();
        if (isMinus)
            s.append("-");
        for (int i = bit - 1; i >= 0; i--) {
            if (i != bit - 1) {
                if (value[i] < 10)
                    s.append("000");
                else if (value[i] < 100)
                    s.append("00");
                else if (value[i] < 1000)
                    s.append("0");
            }
            s.append(value[i]);
            if (i > 0)
                s.append(",");
        }
            return s.toString();
    }

    public void add(LongInt longInt) {
        LongInt a,b;
        boolean flag = compareTo(longInt);
        if(flag) {
            longInt.setBit(bit);
            a = this;
            b = longInt;
        }
        else {
            setBit(longInt.bit);
            a = longInt;
            b = this;
        }

        if (!isMinus && !longInt.isMinus || isMinus && longInt.isMinus)
            value = absOfAdd(this,longInt);
        else {
            value = absOfSub(a,b);
            isMinus = flag ? false : true;
        }

        removePreZero();
        longInt.removePreZero();
    }

    private static int[] absOfAdd(LongInt a,LongInt b) {
        int[] c = new int[a.bit+1];

        for (int i = 0; i < a.bit; i++) {
            c[i] += a.value[i] + b.value[i];
            c[i + 1] += c[i] / 10000;
            c[i] %= 10000;
        }

        return c;
    }

    private static int[] absOfSub(LongInt m,LongInt b) {
        int[] a = m.value;
        for(int i = 0; i < a.length; i++) {
            if(a[i] < b.value[i]) {
                a[i] += 10000;
                a[i + 1] -= 1;
            }
            a[i] -= b.value[i];
        }
        return a;
    }
}
