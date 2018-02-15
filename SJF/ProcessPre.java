package org.unibl.etf.SJF;

import java.util.Objects;

import static java.lang.Thread.sleep;

public class ProcessPre {
    private final int arrivalTime;
    private final int burstTime;
    private final String name;
    private int remainingTime;
    private int waitingTime;
    MonitorPre monitor;

    ProcessPre(int arrivalTime, int burstTime, String name, MonitorPre monitor) {
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.name = name;
        this.monitor = monitor;
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

    public int getRemainingTime() {
        return remainingTime;
    }

    public void run() {
        if (remainingTime > 0) {
            System.out.println(this);
            try {
                sleep(500);
                --remainingTime;
                monitor.getProcesses().stream().skip(1).forEach(p -> p.addWaitingTime(1));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (remainingTime == 0) {
                monitor.getProcesses().remove(this);
                System.out.println("Process: " + name + ", total waiting time: " + waitingTime + "\n"
                );
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProcessPre process = (ProcessPre) o;
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

