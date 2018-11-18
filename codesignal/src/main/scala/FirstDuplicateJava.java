public class FirstDuplicateJava {

    public static int firstDuplicate(int[] a) {
        int[] seen = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            if (seen[a[i]] != 0) {
                return a[i];
            } else
                seen[a[i]] = a[i];
        }
        return -1;
    }
}
