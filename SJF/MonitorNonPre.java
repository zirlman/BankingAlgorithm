package org.unibl.etf.SJF;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

class MonitorNonPre {
    ArrayList<ProcessNonPre> processes;
    Comparator<ProcessNonPre> byArrivalTime = Comparator.comparingInt(ProcessNonPre::getArrivalTime);
    Comparator<ProcessNonPre> byBurstTime = Comparator.comparingInt(ProcessNonPre::getBurstTime);

    MonitorNonPre() {
        processes = new ArrayList<>();
    }

    synchronized void startSmulation() {
        while (processes.size() > 0) {
            ProcessNonPre running = processes.remove(0);
            Thread t = new Thread(running);
            t.start();
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(running.getName() + " total waiting time: " + running.getWaitingTime());
            processes.forEach(p -> p.addWaitingTime(running.getBurstTime()));
        }
    }

    void put(ProcessNonPre process) {
        processes.add(process);
        processes = processes.stream().sorted(byArrivalTime.thenComparing(byBurstTime)).collect(Collectors.toCollection(ArrayList::new));
    }

    ProcessNonPre get() {
        return processes.get(0);
    }
}
