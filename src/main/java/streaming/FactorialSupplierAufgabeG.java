package streaming;

import java.math.BigInteger;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

public class FactorialSupplierAufgabeG implements IntSupplier {

    int current = 1;
    int previous = 0;

    @Override
    public int getAsInt() {
        int result = current;
        current = previous + current;
        previous = result;
        return result;
    }
}
