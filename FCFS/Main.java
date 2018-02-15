package org.unibl.etf.FCFS;

public class Main {
    public static void main(String[] args) {
        Monitor monitor = new Monitor();
        monitor.put(new org.unibl.etf.FCFS.Process(4, 2, "P1"));
        monitor.put(new org.unibl.etf.FCFS.Process(3, 5, "P2"));
        monitor.put(new org.unibl.etf.FCFS.Process(2, 3, "P3"));
        monitor.put(new org.unibl.etf.FCFS.Process(1, 2, "P4"));
        monitor.put(new org.unibl.etf.FCFS.Process(1, 1, "P5"));

        monitor.startSimulation();
    }
}
