package org.unibl.etf.RoundRobin;

public class Main {
    public static void main(String[] args) {
        Monitor monitor = new Monitor();
        monitor.put(new org.unibl.etf.RoundRobin.Process(0, 1, 53, "P1"));
        monitor.put(new org.unibl.etf.RoundRobin.Process(0, 2, 17, "P2"));
        monitor.put(new org.unibl.etf.RoundRobin.Process(0, 3, 68, "P3"));
        monitor.put(new org.unibl.etf.RoundRobin.Process(0, 4, 24, "P4"));
//        monitor.put(new Process(12, 5, 13, "P5"));

        monitor.startSimulation();
    }
}

