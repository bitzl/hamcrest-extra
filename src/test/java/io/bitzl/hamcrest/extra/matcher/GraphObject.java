package io.bitzl.hamcrest.extra.matcher;


public class GraphObject {

    private String label;

    private GraphObject next;

    public GraphObject() {}

    public GraphObject(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public GraphObject getNext() {
        return next;
    }

    public void setNext(GraphObject next) {
        this.next = next;
    }

    @Override
    public String toString() {
        String nextString = "null";
        if (next != null) {
            nextString = next.toString();
        }
        return "GraphObject(label='" + label + "', next=" + nextString + ")";
    }
}
