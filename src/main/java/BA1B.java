/*
Frequent Words Problem

Find the most frequent k-mers in a string.

We say that Pattern is a most frequent k-mer in Text
if it maximizes Count(Text, Pattern) among all k-mers.
For example, "ACTAT" is a most frequent 5-mer in
"ACAACTATGCATCACTATCGGGAACTATCCT",
and "ATA" is a most frequent 3-mer of "CGATATATCCATAG".

Given: A DNA string Text and an integer k.

Return: All most frequent k-mers in Text (in any order).
*/

import java.util.ArrayList;
import java.util.List;

public class BA1B {

    /**
     * Find the most frequent k-mers in a Sequence string.
     * @param dnaString   Sequence string
     * @param kMerSize     k-mer length
     * @return      All most frequent k-mers in Text
     */
    public static List<Sequence> process(String dnaString, byte kMerSize) {

        List<Sequence> ret = new ArrayList<>();

        Sequence sequence = new Sequence(dnaString);

        Trie trie = new Trie(Sequence.NUCLES_COUNT);

        int kMerCounts = dnaString.length() - kMerSize + 1;
        for (int i = 0; i < kMerCounts; i++) {
            byte[] kMer = sequence.getKMer(i, kMerSize);
            trie.add(kMer);
        }

        List<byte[]> winners = trie.getMostFrequentElements();

        winners.stream().sequential().forEach(w -> ret.add(new Sequence(w)));

        return ret;
    }

    public static void main(String[] args) {

        List<Sequence> winners = process(
                "AATCCTCTAATCCTCTGTGGTAAAGTGGCCAATATGGCCAATAGTGGTAAAGTGCTAATGTGACATCTGGCCAATAGTGGTAAAGGTGGTAAAGTGACATCTGCTAATGAATCCTCTGTGGTAAAGTGCTAATGAATCCTCTAATCCTCTTGCTAATGAATCCTCTTGCTAATGAATCCTCTTGGCCAATAAATCCTCTAATCCTCTTGCTAATGTGCTAATGGTGGTAAAGGTGGTAAAGAATCCTCTGTGGTAAAGAATCCTCTAATCCTCTTGACATCGTGGTAAAGGTGGTAAAGTGACATCTGGCCAATATGCTAATGTGACATCAATCCTCTTGGCCAATATGACATCTGGCCAATATGACATCTGACATCTGGCCAATATGGCCAATAGTGGTAAAGTGACATCTGCTAATGGTGGTAAAGTGACATCGTGGTAAAGAATCCTCTTGCTAATGTGACATCTGCTAATGTGCTAATGAATCCTCTGTGGTAAAGTGACATCAATCCTCTGTGGTAAAGGTGGTAAAGAATCCTCTTGCTAATGGTGGTAAAGTGACATCGTGGTAAAGTGGCCAATATGCTAATGTGGCCAATATGGCCAATAGTGGTAAAGTGCTAATGTGGCCAATATGACATCAATCCTCTTGACATCTGCTAATGTGACATCTGACATCGTGGTAAAGTGCTAATGTGGCCAATATGCTAATGTGACATCAATCCTCTTGACATCGTGGTAAAGGTGGTAAAGTGACATCTGGCCAATATGCTAATGGTGGTAAAGAATCCTCTGTGGTAAAGTGGCCAATATGCTAATGAATCCTCTAATCCTCTTGCTAATGTGCTAATG",
                (byte) 13);
        winners.stream().map(Sequence::getAsString).forEach(System.out::println);
    }
}
