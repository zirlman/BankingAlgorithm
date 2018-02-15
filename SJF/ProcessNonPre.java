package org.unibl.etf.SJF;

import java.util.Objects;

import static java.lang.Thread.sleep;

public class ProcessNonPre implements Runnable {
    private final int arrivalTime;
    private final int burstTime;
    private final String name;
    private int remainingTime;
    private int waitingTime;


    ProcessNonPre(int arrivalTime, int burstTime, String name) {
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.name = name;
        remainingTime = burstTime;
    }

    public String getName() {
        return name;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public void addWaitingTime(int time) {
        waitingTime += time;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    @Override
    public void run() {
        while (remainingTime > 0) {
            System.out.println(this);
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            --remainingTime;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProcessNonPre process = (ProcessNonPre) o;
        return arrivalTime == process.arrivalTime &&
                burstTime == process.burstTime;
    }

    @Override
    public int hashCode() {
        return Objects.hash(arrivalTime, burstTime);
    }

    @Override
    public String toString() {
        return "Process: " + name + ", remaining time " + remainingTime;
    }
}
