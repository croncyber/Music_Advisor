import java.math.BigInteger;

class DoubleFactorial {
    public static BigInteger calcDoubleFactorial(int n) {
        BigInteger f = BigInteger.valueOf(n);

        if (n == 0 || n == 1)
            return BigInteger.ONE;

        return f.multiply(calcDoubleFactorial(f.subtract(BigInteger.TWO).intValue()));


    }
}