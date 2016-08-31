import org.testng.Assert;
import org.testng.annotations.Test;

public class SequenceTest {

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void toIndexTest() {
        Assert.assertEquals(Sequence.toIndex('A'), 0);
        Assert.assertEquals(Sequence.toIndex('C'), 1);
        Assert.assertEquals(Sequence.toIndex('G'), 2);
        Assert.assertEquals(Sequence.toIndex('T'), 3);
        Sequence.toIndex('Q');
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void toCharTest() {
        Assert.assertEquals(Sequence.toChar((byte)0), 'A');
        Assert.assertEquals(Sequence.toChar((byte)1), 'C');
        Assert.assertEquals(Sequence.toChar((byte)2), 'G');
        Assert.assertEquals(Sequence.toChar((byte)3), 'T');
        Sequence.toChar((byte)4);
    }

    @Test
    void stringToBytesTest() {
        byte[] acgtBytes = new Sequence("ACGT").getAsBytes();
        Assert.assertEquals(acgtBytes, new byte[]{0,1,2,3});
        Assert.assertNotEquals(acgtBytes, new byte[]{0,1,2,4});
    }

    @Test
    void InstanceWithBytes() {
        String sequence = new Sequence(new byte[]{0, 1, 2, 3}).getAsString();
        Assert.assertTrue(sequence.equals("ACGT"));
        Assert.assertFalse(sequence.equals("ACG"));
        Assert.assertFalse(sequence.equals("TTTT"));
    }

    @Test
    void KMerTest() {
        Sequence sequence = new Sequence("TACACATAC");
        Assert.assertEquals(sequence.getKMer(0, 3), sequence.getKMer(6, 3));
        Assert.assertNotEquals(sequence.getKMer(0, 3), sequence.getKMer(6, 2));
        Assert.assertNotEquals(sequence.getKMer(0, 3), sequence.getKMer(1, 3));
    }
}