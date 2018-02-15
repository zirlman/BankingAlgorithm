package org.unibl.etf.PriorityAging;

import java.util.Comparator;
import java.util.TreeSet;

import static java.lang.Thread.sleep;

public class Monitor {
    private TreeSet<org.unibl.etf.PriorityAging.Process> processes;
    private Comparator<org.unibl.etf.PriorityAging.Process> byPriority;
    private Comparator<org.unibl.etf.PriorityAging.Process> byName;
    private int passedTime;

    Monitor() {
        byName = Comparator.comparing(org.unibl.etf.PriorityAging.Process::getName);
        byPriority = Comparator.comparingInt(org.unibl.etf.PriorityAging.Process::getPriority);
        processes = new TreeSet<>(byPriority.thenComparing(byName));
        passedTime = 0;
    }

    public void startSimulation() {
        int MAX_TIME = 24;
        while (processes.size() > 0 && passedTime < MAX_TIME) {
            org.unibl.etf.PriorityAging.Process running = processes.pollFirst();
            while (running.getRemainingTime() > 0 && running.getPriority() < processes.first().getPriority()) {
                System.out.println(running);
                try {
                    sleep(500);
                    running.decreaseRemainingTime();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ++passedTime;
                switch (passedTime % 6) {
                    case 0:
                        put(new org.unibl.etf.PriorityAging.Process(0, 2, "A"));
                        break;
                    case 1:
                        put(new org.unibl.etf.PriorityAging.Process(4, 2, "B"));
                        break;
                    case 3:
                        put(new org.unibl.etf.PriorityAging.Process(7, 4, "C"));
                        break;
                    case 4:
                        put(new org.unibl.etf.PriorityAging.Process(10, 2, "D"));
                        break;
                    case 5:
                        break;
                }
            }
            processes.stream().skip(1).forEach(p -> {
                if (p.getPriority() > 0)
                    p.incresePriority();
            });

        }
    }

    void put(org.unibl.etf.PriorityAging.Process process) {
        processes.add(process);
    }
}
