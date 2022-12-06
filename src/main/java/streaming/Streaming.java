package streaming;

import java.math.BigInteger;
import java.util.Optional;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Streaming {

    public static int[] a() {
        IntStream intStream = IntStream
                .range(1, 20)
                .filter(i -> i % 2 != 0)
                .map(i -> i * i);
        return intStream.toArray();
    }

    public static double b() {
        return IntStream
                .range(1, 101)
                .mapToDouble(i -> 1 / (((double) i + 1) * ((double) i + 2)))
                .sum();
    }

    public static int[] c() {
        return new Random()
                .ints(1, 46)
                .limit(6)
                .sorted()
                .toArray();
    }

    public static long d() {
        return LongStream
                .range(1, 21)
                .reduce(1, (result, number) -> result * number);
    }

    public static long e() {
        return IntStream
                .range(1, 1001)
                .flatMap(i -> String.valueOf(i).chars())
                .filter(c -> c == '1')
                .count();
    }

    public static BigInteger f() {
        FactorialSupplier factorialSupplier = new FactorialSupplier();
        Optional<BigInteger> optional = Stream
                .generate(factorialSupplier)
                .map(String::valueOf)
                .filter(i -> i.length() > 20)
                .map(BigInteger::new)
                .findFirst();
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new IllegalArgumentException();
    }

    public static int g() {
        FactorialSupplier factorialSupplier = new FactorialSupplier();
        Stream
                .generate(factorialSupplier)
                .map(String::valueOf)
                .filter(i -> i.length() > 10000)
                .map(BigInteger::new)
                .findFirst();

        return factorialSupplier.getI();
    }

    public static int h() {
        Fibonacci factorialSupplier = new Fibonacci();
        Stream
                .generate(factorialSupplier)
                .map(String::valueOf)
                .filter(i -> i.length() >= 1000)
                .map(BigInteger::new)
                .findFirst();

        return factorialSupplier.getCount();

    }
}
