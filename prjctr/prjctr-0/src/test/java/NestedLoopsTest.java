
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;

import java.time.Instant;
import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

@RunWith(Parameterized.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NestedLoopsTest {

    private long executeAndTime(int n) {
        long lStartTime = Instant.now().toEpochMilli();

        Main.calculation(n);

        long lEndTime = Instant.now().toEpochMilli();

        long output = lEndTime - lStartTime;

        System.out.println("Elapsed time in milliseconds: " + output);

        return output;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {1, 1},
                {2, 4},
                {3, 10},
                {4, 20},
                {5, 35},
                {6, 56},
                {1825, 14731018},
                {5000, 845834860},
                {10000, 716668838},
                {20000, 533330669},
                {100000, 665533303}, // 665533303 (calc using oldCube)
                {1000000, 500329845},
                {235302625, 500329845},
                {500329845, 500329845},
                {1000000000, 442358244},
        });
    }

    private int n;
    private int res;

    public NestedLoopsTest(int n, int res) {
        this.n = n;
        this.res = res;
    }

//    @Test()
//    public void testNew() {
//        await().atMost(5, SECONDS).untilAsserted(() -> assertThat(Main.performOperations(n)).isEqualTo(res));
//
//    }

    @Test
    public void testNewNoTime() {
        System.out.println(n);
        assertThat(Main.calculation(n)).isEqualTo(res);

    }

//    @Test
//    public void testOld() {
//        assertThat(Experiments.calculationInitial(n)).isEqualTo(res);
//    }

//    @Test
//    public void testOldCube() {
//        assertThat(Experiments.calculationCube(n)).isEqualTo(res);
//    }

}