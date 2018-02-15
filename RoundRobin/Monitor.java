package org.unibl.etf.RoundRobin;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class Monitor {
    private final int QUANTUM = 20;
    private ArrayList<org.unibl.etf.RoundRobin.Process> processes;
    private int passedTime;
    private int runningTime;

    Monitor() {
        //byPriority = Comparator.comparingInt(Process::getPriority);
        processes = new ArrayList<>();
        passedTime = 0;
    }

    void startSimulation() {
        org.unibl.etf.RoundRobin.Process running;
        while (processes.size() > 0) {
            while (processes.get(0).getArrivalTime() > passedTime) {
                try {
                    sleep(50);
                    ++passedTime;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            running = processes.remove(0);
            runningTime = 0;
            while (running.getRemainingTime() > 0) {
                try {
                    sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                running.decereaseRemainingTime();
                ++passedTime;
                ++runningTime;
                processes.forEach(p -> {
                    if (passedTime > p.getArrivalTime())
                        p.increaseWaitingTime();
                });
                //System.out.println(running);
                if (runningTime % QUANTUM == 0)
                    break;
            }
            if (running.getRemainingTime() > 0) {
                System.out.println(running);
                processes.add(running);
            } else
                System.out.println("Process: " + running.getName() + " finishd, total waiting time: " + running.getWaitingTime());
        }
    }

    void put(org.unibl.etf.RoundRobin.Process process) {
        processes.add(process);
    }

}
