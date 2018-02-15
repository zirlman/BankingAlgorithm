package org.unibl.etf.PriorityAging;

public class Process {
    private final String name;
    private int remainingTime;
    private int priority;

    Process(int priority, int burstTime, String name) {
        this.priority = priority;
        this.name = name;
        remainingTime = burstTime;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public int getPriority() {
        return priority;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Process: " + name + ", remaining time " + remainingTime;
    }

    void incresePriority() {
        --priority;
    }

    void decreaseRemainingTime() {
        --remainingTime;
    }
}
