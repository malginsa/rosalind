import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Simple digital trie implementation
 * Witch stores counts of elements on trie's leaves
 * Nodes are represented by bytes
 */
public class Trie {

    class Node {

        Node[] children;
        int counter;

        public Node() {
            children = new Node[CHILDRENS_COUNT];
        }

//        public Node getChild(byte index) {
//            return children[index];
//        }

        public Node createChild(byte index) {
            if (null == children[index]) {
                children[index] = new Node();
            }
            return children[index];
        }

        public int incCounter() {
            return ++counter;
        }

        public int getCounter() {
            return counter;
        }
    }

    private final byte CHILDRENS_COUNT;

    Node root;

    private int maxCounter;

    public Trie(byte childrenCounts) {
        this.CHILDRENS_COUNT = childrenCounts;
        root = new Node();
        maxCounter = 0;
    }

    public int getMaxCounter() {
        return maxCounter;
    }

    void MostFrequentElements(Node node, LinkedList<Integer> currentSequence,
                              List<byte[]> winners) {
        if (node.getCounter() == maxCounter) {
            winners.add(Utils.Convert(currentSequence));
        }
        for (int i = 0; i < CHILDRENS_COUNT; i++) {
            if (null != node.children[i]) {
                currentSequence.add(i);
                MostFrequentElements(node.children[i], currentSequence, winners);
                currentSequence.pollLast();
            }
        }
    }

    public void add(byte[] bytes) {
        Node current = root;
        for (int i = 0; i < bytes.length; i++) {
            current = current.createChild(bytes[i]);
        }
        int newCounter = current.incCounter();
        maxCounter = newCounter > maxCounter ? newCounter : maxCounter;
    }

    /**
     * @return elements witch counter is a maximum
     */
    public List<byte[]> getMostFrequentElements() {
        List<byte[]> ret = new ArrayList<>();
        MostFrequentElements(root, new LinkedList<>(), ret);
        return ret;
    }

}
