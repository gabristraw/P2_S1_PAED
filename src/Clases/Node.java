package Clases;

import java.util.ArrayList;

public class Node implements Comparable<Node> {


    public ArrayList<Centre> configuracio;
    public int level;
    public int bound;

    public Node(ArrayList<Centre> configuracio, int level, int bound) {
        this.configuracio = new ArrayList<>(configuracio);
        this.level = level;
        this.bound = bound;
    }

    @Override
    public int compareTo(Node other) {
        return Integer.compare(other.bound, this.bound);
    }
}
