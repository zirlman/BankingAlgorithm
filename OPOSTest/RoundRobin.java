package org.unibl.etf.OPOSTest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.TreeSet;

import static java.lang.Thread.sleep;

public class RoundRobin {
    private static class Process {
        private int arrivalTime;
        private int remainingTime;
        private int workingTime;
        private int waitingTime;
        private int id;
        private static int idMaker;

        public Process(int arrivalTime, int remainingTime) {
            this.arrivalTime = arrivalTime;
            this.remainingTime = remainingTime;
            id = ++idMaker;
        }

        public int getArrivalTime() {
            return arrivalTime;
        }

        public int getRemainingTime() {
            return remainingTime;
        }

        public int getWorkingTime() {
            return workingTime;
        }

        public int getWaitingTime() {
            return waitingTime;
        }

        public int getId() {
            return id;
        }

        @Override
        public String toString() {
            return "P" + id + ", remaining time: " + remainingTime;
        }

        void updateWaitingTime() {
            if (passedTime > arrivalTime)
                ++waitingTime;
        }

        void decreaseRemainingTime() {
            --remainingTime;
        }

        void increaseWorkingTime() {
            ++workingTime;
        }
    }

    private static int passedTime;
    private static TreeSet<Process> jobQueue = new TreeSet<>(Comparator
            .comparingInt(Process::getArrivalTime)
            .thenComparing(Process::getId));
    private static ArrayList<Process> activeJobs = new ArrayList<>();
    public static final int QUANTUM = 1;

    private static void loadJobs(String[] arrivalTime, String[] jobs) {
        for (int i = 0; i < arrivalTime.length; i++)
            jobQueue.add(new Process(Integer.valueOf(arrivalTime[i]), Integer.valueOf(jobs[i])));
        while (!jobQueue.isEmpty()) {
            while (passedTime < jobQueue.first().getArrivalTime()) {
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ++passedTime;
            }
            updateActiveJobs();
            while (!activeJobs.isEmpty()) {
                Process running = activeJobs.remove(0);
                while (running.getRemainingTime() > 0) {
                    try {
                        sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ++passedTime;
                    running.decreaseRemainingTime();
                    running.increaseWorkingTime();
                    System.out.println(running);
                    updateActiveJobs();
                    activeJobs.forEach(Process::updateWaitingTime);
                    if (running.getRemainingTime() > 0 && running.getWorkingTime() % QUANTUM == 0) {
                        activeJobs.add(running);
                        running = activeJobs.remove(0);
                    }
                }
                System.out.println("Process P" + running.getId() + " finished, total waiting time: " + running.getWaitingTime());
            }
        }
    }

    private static void updateActiveJobs() {
        while (!jobQueue.isEmpty() && passedTime >= jobQueue.first().getArrivalTime())
            activeJobs.add(jobQueue.pollFirst());
    }

    // 10,1,2,1,5
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter arrival time (1,2,3,...): ");
        String[] arrivalTime = scanner.next().split(",");
        System.out.print("Enter burst time (1,2,3,...): ");
        String[] jobs = scanner.next().split(",");

        if (arrivalTime.length != jobs.length)
            System.out.println("Invalid input!");
        else
            loadJobs(arrivalTime, jobs);
    }


}
