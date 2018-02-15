package org.unibl.etf.PriorityAging;

public class Main {
    public static void main(String[] args) {
        Monitor monitor = new Monitor();
        monitor.put(new Process(0, 2, "A"));
        monitor.put(new Process(4, 2, "B"));
        monitor.put(new Process(7, 4, "C"));
        monitor.put(new Process(10, 2, "D"));

        monitor.startSimulation();
    }
}
