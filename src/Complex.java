public class Complex {
    double r;
    double i;

    Complex() {}
    Complex(double r,double i) {
        this.r = r;
        this.i = i;
    }

    public Complex add(Complex complex) {
        return  new Complex(r + complex.r,i + complex.i);
    }

    public Complex sub(Complex complex) {
        return new Complex(r - complex.r,i - complex.i);
    }

    public Complex mul(Complex complex) {
        return new Complex(r * complex.r - i * complex.i,r * complex.i + i * complex.r);
    }

    public Complex mul(double d) {
        r *= d;
        i *= d;
        return new Complex(r * d, i * d);
    }


    public Complex div(Complex complex) {
        return new Complex((r*complex.r + i*complex.i)/(i*i + complex.i*complex.i),(i*complex.r - r*complex.i)/(i*i + complex.i*complex.i));
    }

    public Complex div(double d) {
        return new Complex(r / d, i / d);
    }

    public Complex conj() {
        return new Complex(r,-i);
    }

    public static Complex omega(int n, int k) {
        return new Complex(Math.cos(2 * Math.PI * k / n),Math.sin(2 * Math.PI * k / n));
    }
}
