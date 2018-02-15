package org.unibl.etf.MonitorSemaphore;

public class Monitor {
    int val;

    Monitor(int n) {
        val = n;
    }

    synchronized void waitt() {
        try {
            if (--val < 0)
                wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized void signal() {
        if (val++ <= 0)
            notify();
    }
}

