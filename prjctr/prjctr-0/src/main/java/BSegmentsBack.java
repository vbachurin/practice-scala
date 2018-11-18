import java.util.*;
import java.util.stream.Stream;

public class BSegmentsBack {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String[] nm = s.nextLine().split(" ");
        int m = Integer.valueOf(nm[1]);


        Stream<int[]> operations = constructOperationsArrays(s, m);
        int max = performOperations(operations);

        System.out.println(max);


    }

    private static Stream<int[]> constructOperationsArrays(Scanner s, int m) {
        return Stream
                .iterate(0, i -> i++)
                .limit(m)
                .map(j ->
                        Stream.of(s.nextLine()
                                .split(" "))
                                .mapToInt(Integer::valueOf)
                                .toArray()
                );
    }

    public static int performOperations(Stream<int[]> operationsArrays) {

        Map<Integer, Integer> vals = new HashMap<>();
        operationsArrays
                .map(a -> new Operation(a[0], a[1], a[2]))
                .forEach(
                        op -> {
                            for (int i = op.from; i <= op.to; i++)
                                if (vals.containsKey(i)) {
                                    Integer prev = vals.get(i);
                                    vals.put(i, prev + op.number);
                                } else
                                    vals.put(i, op.number);
                        }
                );

        return vals
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


