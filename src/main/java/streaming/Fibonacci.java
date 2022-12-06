package streaming;

import java.math.BigInteger;
import java.util.function.Supplier;

public class Fibonacci implements Supplier<BigInteger> {
    private BigInteger current = BigInteger.ONE;
    private BigInteger previous = BigInteger.ONE;
    private int zaehler = 1;
    @Override
    public BigInteger get() {
        BigInteger result = current;
        current = previous.add(current);
        previous = result;
        zaehler++;
        return result;
    }

    public int getCount() {
        return zaehler;
    }
}
