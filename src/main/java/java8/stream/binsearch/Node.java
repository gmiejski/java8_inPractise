package java8.stream.binsearch;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Created by Grzegorz Miejski (SG0221133) on 1/6/2015.
 */
public class Node {

    int value;
    Node left, right;

    public Node(int value) {
        this.value = value;
    }

    public void addChild(Node child) {
        if (child.value < this.value) {
            if (left == null) {
                left = child;
            } else {
                left.addChild(child);
            }
        } else {
            if (right == null) {
                right = child;
            } else {
                right.addChild(child);
            }
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", left=" + left +
                ", right=" + right +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        if (value != node.value) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return value;
    }

    public static void main(String[] args) {

        Node head = new Node(100);

        Random random = new Random();

        List<Node> nodes = Stream.generate(() -> new Node(random.nextInt(35))).distinct().limit(30).collect(toList());

        List<Integer> values = nodes.stream().map(x -> x.value).sorted(Integer::compare).collect(toList());
        System.out.println(values);

        int randomValue = nodes.get(random.nextInt(nodes.size() - 1)).value;
        System.out.println(randomValue);
        int binsearch = binsearch(values, randomValue);
        System.out.println(binsearch);
        assert values.get(binsearch).equals(randomValue);

        nodes.forEach(head::addChild);

        System.out.println(nodes);

    }

    static int binsearch(List<Integer> values, int value) {

        int min = 0;
        int max = values.size() - 1;
        int mid;

        while (min <= max) {
            mid = (max + min) / 2;
            int midValue = values.get(mid);
            if (value == midValue) {
                return mid;
            } else if (value < midValue) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }

        return -1;
    }


}
