import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class BSegmentsPerformance {

//    int n = 10000;
            int n = 10000000;
//        int n = 1000000000;
//    int m = 200000;
    int m = 5000;

    Random random = new Random();
    Supplier<int[]> opsSupllier = () -> {

        int to = random.nextInt(n);
        int from = random.nextInt(++to);
//            return new int[]{1, to, random.nextInt(1000)};
        return new int[]{++from, to, random.nextInt(1000)};
    };

    @Test
    public void performOperations() {


        Stream<int[]> operations = Stream.generate(opsSupllier).limit(m);
        assertThat(BSegmentsBack.performOperations(operations)).isNotNegative();
//        assertThat(BSegments.mostPopularIndex(operations)).isNotZero();
    }

    @Test
    public void mostPopularIndex() {
//        Supplier<int[]> opsSupllier = () -> new int[]{1, 3, 100};
//        BSegments.mostPopularIndex(Stream.generate(opsSupllier).limit(2));
        long max = BSegments.mostPopularIndex(Stream.of(
                new int[]{1, 2, 100},
                new int[]{2, 5, 100},
                new int[]{3, 4, 100}
        ));
        assertThat(max).isEqualTo(200);
    }
}
