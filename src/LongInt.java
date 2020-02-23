import java.util.Arrays;

public class LongInt {
    private int bit = 0;
    private int[] value = {0};
    private boolean isMinus = false;

    private static final LongInt tenK = new LongInt(10000);

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
        this.bit = value.length;
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

    public boolean absBiggerThan(LongInt longInt) {
        if (bit > longInt.bit)
            return true;
        else if (bit < longInt.bit)
            return false;
        else {
            for (int i = bit-1; i >= 0; i--) {
                if (value[i] > longInt.value[i])
                    return true;
                else if (value[i] < longInt.value[i])
                    return false;
            }
            return false;
        }
    }

    public boolean biggerThan(LongInt longInt) {
        if(isMinus && !longInt.isMinus)
            return false;
        else if(!isMinus && longInt.isMinus)
            return true;
        else {
            if (bit > longInt.bit)
                return true;
            else if (bit < longInt.bit)
                return false;
            else {
                for (int i = bit-1; i >= 0; i--) {
                    if (value[i] > longInt.value[i])
                        return true;
                    else if (value[i] < longInt.value[i])
                        return false;
                }
                return false;
            }
        }
    }

    public boolean EqualTo(LongInt longInt) {
        return Arrays.equals(value,longInt.value);
    }

    public void setBit(int length) {
        int[] i = new int[length];
        System.arraycopy(value,0,i,0,bit > i.length?i.length:bit);
        bit = length;
        value = i;
    }

    public void removePreZero() {
        while (true) {
            if (bit > 1 && value[bit-1] == 0) {
                setBit(bit - 1);
            }
            else
                break;
        }
    }

    public void equal(LongInt longInt) {
        this.setValue(longInt.value);
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

    public LongInt add(LongInt longInt) {
        boolean flag = this.absBiggerThan(longInt);

        if (isMinus ^ !longInt.isMinus)
            setValue(ArrayHanlder.add(this.value,longInt.value));
        else {
            setValue(ArrayHanlder.sub(this.value,longInt.value));
            isMinus = flag == this.isMinus;
        }

        removePreZero();
        longInt.removePreZero();
        return this;
    }

    public LongInt sub(LongInt longInt) {
        boolean flag = this.absBiggerThan(longInt);

        if (isMinus ^ longInt.isMinus)
            setValue(ArrayHanlder.add(this.value,longInt.value));
        else {
            setValue(ArrayHanlder.sub(this.value,longInt.value));
            isMinus = flag == isMinus;
        }

        removePreZero();
        longInt.removePreZero();
        return this;
    }


    public LongInt mul(LongInt longInt) {
        //模拟手算
        int[] c = new int[bit + longInt.bit];
        long temp;
        for (int i = 0; i < bit; i++) {
            for (int j = 0; j < longInt.bit; j++) {
                temp = value[i] * longInt.value[j];
                c[i+j] += (int)temp%10000;
                c[i+j+1] += (int)temp/10000 + c[i+j]/10000;
                c[i+j] %= 10000;
            }
        }
        setValue(c);
        isMinus = isMinus ^ longInt.isMinus;
        return this;
    }

    public int[] divAsSub(LongInt longInt) {
        int answer = 0;
        int[] remain;

        while (true) {
            if (absBiggerThan(longInt)) {
                sub(longInt);
                answer++;
            }
            else if (EqualTo(longInt)) {
                answer++;
                remain = new int[]{0};
                break;
            }
            else {
                remain = value;
                break;
            }
        }
        setValue(new LongInt(answer).value);
        setMinus(isMinus ^ longInt.isMinus);

        return remain;
    }

    public int[] divAsHand(LongInt longInt) {
        int[] quo = {0,0};
        int[] temp;
        int[] singleNumber = new int[1];
        int[] remain = new int[longInt.bit-1];
        LongInt tempLongInt = new LongInt();

        System.arraycopy(value,bit-longInt.bit+1,remain,0,longInt.bit-1);
        //减法代替除法
        for(int i = bit-longInt.bit; i>=0; i--) {
            singleNumber[0] = value[i];
            temp = ArrayHanlder.add(singleNumber, ArrayHanlder.shiftR(remain));
            tempLongInt.setValue(temp);
            remain = tempLongInt.divAsSub(longInt);
            quo = ArrayHanlder.shiftR(quo);
            quo = ArrayHanlder.add(quo,tempLongInt.value);
        }

        this.setValue(quo);
        this.setMinus(isMinus^longInt.isMinus);

        return remain;
    }
}
