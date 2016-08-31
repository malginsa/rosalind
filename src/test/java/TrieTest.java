import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TrieTest {

    @Test
    public void testGetMostFrequentElements() throws Exception {
        String dnaString = "ACA";
        Sequence sequence = new Sequence(dnaString);
        int kMerSize = 2;
        Trie trie = new Trie(Sequence.NUCLES_COUNT);
        int kMerCounts = dnaString.length() - kMerSize + 1;
        for (int i = 0; i < kMerCounts; i++) {
            byte[] kMer = sequence.getKMer(i, kMerSize);
            trie.add(kMer);
        }
        List<byte[]> winners = trie.getMostFrequentElements();
        Assert.assertEquals( winners.get(0), new byte[]{0,1} );
        Assert.assertEquals( winners.get(1), new byte[]{1,0} );
    }

    @Test
    public void testGetMostFrequentElements2() throws Exception {
        String dnaString = "CGGAAGCGAGATTCGCGTGGCGTGATTCCGGCGGGCGTGGAGAAGC" +
                "GAGATTCATTCAAGCCGGGAGGCGTGGCGTGGCGTGGCGTGCGGATTCAAGCCGGCG" +
                "GGCGTGATTCGAGCGGCGGATTCGAGATTCCGGGCGTGCGGGCGTGAAGCGCGTGGA" +
                "GGAGGCGTGGCGTGCGGGAGGAGAAGCGAGAAGCCGGATTCAAGCAAGCATTCCGGC" +
                "GGGAGATTCGCGTGGAGGCGTGGAGGCGTGGAGGCGTGCGGCGGGAGATTCAAGCCG" +
                "GATTCGCGTGGAGAAGCGAGAAGCGCGTGCGGAAGCGAGGAGGAGAAGCATTCGCGT" +
                "GATTCCGGGAGATTCAAGCATTCGCGTGCGGCGGGAGATTCAAGCGAGGAGGCGTGA" +
                "AGCAAGCAAGCAAGCGCGTGGCGTGCGGCGGGAGAAGCAAGCGCGTGATTCGAGCGG" +
                "GCGTGCGGAAGCGAGCGG";
        Sequence sequence = new Sequence(dnaString);
        int kMerSize = 12;
        Trie trie = new Trie(Sequence.NUCLES_COUNT);
        int kMerCounts = dnaString.length() - kMerSize + 1;
        for (int i = 0; i < kMerCounts; i++) {
            byte[] kMer = sequence.getKMer(i, kMerSize);
            trie.add(kMer);
        }
        List<byte[]> winnersAsBytes = trie.getMostFrequentElements();

        String[] winnersAsString = new String[winnersAsBytes.size()];
        int index = 0;
        for (byte[] winner : winnersAsBytes) {
            winnersAsString[index++] = new Sequence(winner).getAsString();
        }

        String[] winnersReference = new String[]{"CGGCGGGAGATT", "CGGGAGATTCAA",
                "CGTGCGGCGGGA", "CGTGGAGGCGTG", "CGTGGCGTGCGG", "GCGTGCGGCGGG",
                "GCGTGGAGGCGT", "GCGTGGCGTGCG", "GGAGAAGCGAGA", "GGAGATTCAAGC",
                "GGCGGGAGATTC", "GGGAGATTCAAG", "GTGCGGCGGGAG", "TGCGGCGGGAGA"};

        Assert.assertEquals(winnersAsString, winnersReference);
    }

    @Test
    void testAdd() {
        Trie trie = new Trie((byte)4);
        trie.add(new byte[]{0,1});
        trie.add(new byte[]{0,1});
        Assert.assertEquals( trie.root.children[0].children[1].counter, 2 );
    }

    @Test
    void testGetMaxCounter() {
        Trie trie = new Trie((byte) 4);
        trie.add(new byte[]{0,1});
        trie.add(new byte[]{1,0});
        trie.add(new byte[]{1,0});
        trie.add(new byte[]{0,1});
        Assert.assertEquals( trie.getMaxCounter(), 2 );
        trie.add(new byte[]{1,0});
        Assert.assertEquals( trie.getMaxCounter(), 3 );
    }


}