import java.util.Arrays;

/**
 * Sequence can be represented as a String and as array of bytes
 */
public class Sequence {

    private String asString;

    private byte[] asBytes;

    public static final byte NUCLES_COUNT = 4;

    public static byte toIndex(char ch) {
        switch (ch) {
            case 'A': return 0;
            case 'C': return 1;
            case 'G': return 2;
            case 'T': return 3;
            default: throw new IllegalArgumentException("Unknown base character: " + ch);
        }
    }

    public static char toChar(byte index) {
        switch (index) {
            case 0: return 'A';
            case 1: return 'C';
            case 2: return 'G';
            case 3: return 'T';
            default: throw new IllegalArgumentException("Unknown base index: " + index);
        }
    }

    public Sequence(String string) {
        this.asString = string;
        asBytes = new byte[string.length()];
        for (int i = 0; i < string.length(); i++) {
            asBytes[i] = Sequence.toIndex(string.charAt(i));
        }
    }

    public Sequence(byte[] bytes) {
        this.asBytes = Arrays.copyOf(bytes, bytes.length);
        StringBuilder sb = new StringBuilder(bytes.length);
        for (int i = 0; i < bytes.length; i++) {
            sb.append(toChar(bytes[i]));
        }
        asString = new String(sb);
    }

    public String getAsString() {
        return asString;
    }

    public byte[] getAsBytes() {
        return asBytes;
    }

    public byte[] getKMer(int from, int k) {
        return Arrays.copyOfRange(asBytes, from, from + k);
    }

}
