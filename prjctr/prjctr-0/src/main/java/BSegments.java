import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;
import static java.util.stream.Collectors.summingInt;

public class BSegments {

    public static void main(String[] args) {

        Stream<int[]> operations = constructOperationsArrays();
        long max = mostPopularIndex(operations);

        System.out.println(max);
    }

    private static Stream<int[]> constructOperationsArrays() {
        Scanner s = new Scanner(System.in);

        return Arrays.stream(s.nextLine().split(" "))
                .skip(1) // N isn't used in this implementation
                .mapToInt(Integer::valueOf)
                .boxed()
                .flatMap(m ->
                        IntStream.range(0, m).boxed())
                .map(__ ->
                        Stream.of(s.nextLine()
                                .split(" "))
                                .mapToInt(Integer::valueOf)
                                .toArray()
                );
    }

    public static int mostPopularIndex(Stream<int[]> operationsArrays) {
        return operationsArrays
                // array elements to Operation
                .map(a -> new Operation(a[0], a[1], a[2]))
                // stream of pairs (array_index, value_to_add)
                .flatMap(op -> IntStream.rangeClosed(op.from, op.to).boxed()
                        .map(pos -> new SimpleEntry<Integer, Integer>(pos, op.number))
                )
                // [SLOOOOOW] grouping by index and accumulating the sum for each index
                .collect(groupingBy(SimpleEntry::getKey, summingInt(SimpleEntry::getValue)))
                // finding maximum sum
                .values()
                .stream()
                .max(Comparator.naturalOrder())
                .get();
    }


    static class Operation {
        int from;
        int to;
        int number;

        public Operation(int from, int to, int number) {
            this.from = from;
            this.to = to;
            this.number = number;
        }

        @Override
        public String toString() {
            return String.format("from: %d, to: %d, add: %d", from, to, number);
        }
    }
}
