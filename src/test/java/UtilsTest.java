import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.LinkedList;

public class UtilsTest {

    @Test
    public void testInstance() {
        Utils utils = new Utils();
        Assert.assertNotNull(utils);
    }

    @Test
    void testConvert() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        byte[] bytes = Utils.Convert(list);
        Assert.assertEquals(bytes, new byte[]{1,2,3,4});
    }
}
