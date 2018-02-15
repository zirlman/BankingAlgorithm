package org.unibl.etf.SJF;

public class Main {
    public static void main(String[] args) {
        // Monitor monitor = new Monitor();
        MonitorPre monitor = new MonitorPre();
        monitor.put(new ProcessPre(3, 2, "P1", monitor));
        monitor.put(new ProcessPre(3, 5, "P2", monitor));
        monitor.put(new ProcessPre(4, 3, "P3", monitor));
        monitor.put(new ProcessPre(8, 2, "P4", monitor));
        monitor.put(new ProcessPre(9, 1, "P5", monitor));

        monitor.startSmulation();
    }
}
