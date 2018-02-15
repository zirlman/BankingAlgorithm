package org.unibl.etf.RoundRobin;

public class Process {
    private final int priority;
    private final int arrivalTime;
    private final int burstTime;
    private final String name;
    private int remainingTime;
    private int waitingTime;

    public Process(int arrivalTime, int priority, int burstTime, String name) {
        this.priority = priority;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.name = name;
        remainingTime = burstTime;
        waitingTime = 0;
    }

    public int getPriority() {
        return priority;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public String getName() {
        return name;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    @Override
    public String toString() {
        return "Process: " + name + " remaining time: " + remainingTime;
    }

    void decereaseRemainingTime() {
        --remainingTime;
    }

    void increaseWaitingTime() {
        ++waitingTime;
    }
}
