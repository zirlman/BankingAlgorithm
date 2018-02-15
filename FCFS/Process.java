package org.unibl.etf.FCFS;

public class Process {
    private final int arrivingTime;
    private final int burstTime;
    private final String name;
    private int remainingTime;
    private int waitingTime;

    Process(int arrivingTime, int burstTime, String name) {
        this.arrivingTime = arrivingTime;
        this.burstTime = burstTime;
        this.name = name;
        remainingTime = burstTime;
    }

    public int getArrivingTime() {
        return arrivingTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public String getName() {
        return name;
    }

    public void decreaseRemainingTime() {
        --remainingTime;
    }

    @Override
    public String toString() {
        return "Process: " + name + ", remaining time " + remainingTime;
    }
}
