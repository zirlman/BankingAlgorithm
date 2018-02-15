package org.unibl.etf.SJF;

import java.util.Comparator;
import java.util.TreeSet;

public class MonitorPre {
    private TreeSet<ProcessPre> processes;
    private Comparator<ProcessPre> byArrivalTime;
    private Comparator<ProcessPre> byBurstTime;
    private Comparator<ProcessPre> byRemainingTime;
    private Comparator<ProcessPre> byName;
    private TreeSet<ProcessPre> arivalQueue;
    private int timePassed;

    MonitorPre() {
        byArrivalTime = Comparator.comparingInt(ProcessPre::getArrivalTime);
        byBurstTime = Comparator.comparingInt(ProcessPre::getBurstTime);
        byRemainingTime = Comparator.comparingInt(ProcessPre::getRemainingTime);
        byName = Comparator.comparing(ProcessPre::getName);
        processes = new TreeSet<>(byRemainingTime.thenComparing(byName));
        arivalQueue = new TreeSet<>(byArrivalTime.thenComparing(byBurstTime));
    }

    void startSmulation() {
        while (processes.size() > 0 || arivalQueue.size() > 0) {
            checkProcesses();
        }
    }

    private void checkProcesses() {
        int counter = (int) arivalQueue.stream().filter(p -> p.getArrivalTime() == timePassed).count();
        while (counter > 0) {
            processes.add(arivalQueue.pollFirst());
            --counter;
        }
        if (processes.size() > 0) {
            processes.first().run();
        }
        ++timePassed;
    }

    void put(ProcessPre process) {
        arivalQueue.add(process);
    }


    TreeSet<ProcessPre> getProcesses() {
        return processes;
    }

    void incrementTime() {
        ++timePassed;
    }
}
