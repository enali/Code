package cn.enali.utils;

import java.util.ArrayList;
import java.util.List;

public class GraphNode<T> {
    public T label;
    public List<GraphNode<T>> neighbors;

    public GraphNode(T label) {
        this.label = label;
        this.neighbors = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "GraphNode[ " + label + " ]";
    }
}
