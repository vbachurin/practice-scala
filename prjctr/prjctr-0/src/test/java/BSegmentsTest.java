import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BSegmentsTest {

    private final int n;
    private final Stream<int[]> operations;
    private final int[] expected;
//    private final long expected;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Supplier<int[]> operations = () -> new int[]{1, 2, 100};

        return Arrays.asList(new Object[][]{
//                {1,
//                        Stream.of(new int[]{2, 2, 100}),
//                        new int[]{}
//                },
                {1,
                        Stream.of(new int[]{1, 1, 100}),
                        new int[]{100}
//                        100
                },
                {
                    5,
                        Stream.of(
                                new int[]{1, 2, 100},
                                new int[]{2, 5, 100},
                                new int[]{3, 4, 100}
                                ),
                        new int[]{100, 200, 200, 200, 100}
//                        200
                    },
                {
                    5,
                        Stream.generate(operations).limit(200000),
//                        100*200000
                        new int[]{100*200000, 100*200000}
                }
        });
    }

//    @Test
//    public void mostPopularIndex() {
//        assertThat(BSegments.mostPopularIndex(operations)).isEqualTo(expected);
//    }

    @Test
    public void performOperations() {
        assertThat(BSegmentsBack.performOperations(operations)).isEqualTo(expected);
    }

    public BSegmentsTest(int n, Stream<int[]> operations, int[] expected) {
        this.n = n;
        this.operations = operations;
        this.expected = expected;
    }
}