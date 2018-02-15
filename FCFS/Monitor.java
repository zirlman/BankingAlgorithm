package org.unibl.etf.FCFS;

import java.util.Comparator;
import java.util.TreeSet;

import static java.lang.Thread.sleep;

public class Monitor {
    TreeSet<org.unibl.etf.FCFS.Process> processes;
    Comparator<org.unibl.etf.FCFS.Process> byArrivingTime;
    Comparator<org.unibl.etf.FCFS.Process> byName;
    private int passedTime;

    Monitor() {
        byArrivingTime = Comparator.comparingInt(org.unibl.etf.FCFS.Process::getArrivingTime);
        byName = Comparator.comparing(org.unibl.etf.FCFS.Process::getName);
        processes = new TreeSet<>(byArrivingTime.thenComparing(byName));
        passedTime = 0;
    }

    void startSimulation() {
        while (processes.size() > 0) {
            while (processes.first().getArrivingTime() != passedTime) {
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ++passedTime;
            }
            org.unibl.etf.FCFS.Process running = processes.pollFirst();
            while (running.getRemainingTime() > 0) {
                System.out.println(running);
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                running.decreaseRemainingTime();
            }
            System.out.println("Process " + running.getName() + " total waiting time: " + running.getWaitingTime() + "\n");
        }
    }

    void put(org.unibl.etf.FCFS.Process p) {
        processes.add(p);
    }
}
