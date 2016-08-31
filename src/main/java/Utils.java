import java.util.LinkedList;

public class Utils {

    /**
     * Convert LinkedList of Integers into bytes
     * @param from
     * @return
     */
    public static byte[] Convert(LinkedList<Integer> from) {
        byte[] ret = new byte[from.size()];
        int index = 0;
        for (Integer element : from) {
            ret[index++] = (byte)(int) element;
        }
        return ret;
    }

}
