
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

public class SingleTest {

    @Test
    public void testN() {
        assertThat(Experiments.calculationInitial(100000)).isEqualTo(10);
//        await().atMost(5, SECONDS).untilAsserted(() -> assertThat(Main.performOperations(3)).isEqualTo(10));

    }

}