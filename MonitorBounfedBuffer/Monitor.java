package org.unibl.etf.MonitorBounfedBuffer;

import java.util.concurrent.Semaphore;

class Monitor<E> {
    private E[] buffer;
    private int size;
    private int top;
    private int bottom;
    private int noInBuffer;
    private static Semaphore spaceAvailable = new Semaphore(0);
    private static Semaphore itemAvailable = new Semaphore(0);

    private Monitor(int size) {
        this.size = size > 0 ? size : 0;
        buffer = (E[]) new Object[this.size];
        top = bottom = noInBuffer = 0;
    }

    private void append(E i) throws InterruptedException {
        while (noInBuffer == size)
            spaceAvailable.acquire();
        buffer[top] = i;
        top = (top + 1) % size;
        ++noInBuffer;
        itemAvailable.release();
    }

    private E take() throws InterruptedException {
        while (noInBuffer == 0)
            itemAvailable.acquire();
        E retVal = buffer[bottom];
        bottom = (bottom + 1) % size;
        --noInBuffer;
        spaceAvailable.release();
        return retVal;
    }


    public static void main(String arg[]) {
        Monitor<Character> m = new Monitor<>(100);
        for (int i = 0x30; i < 0x80; ++i) {
            try {
                m.append((char) i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0x30; i < 0x80; ++i) {
            try {
                System.out.print(m.take() + " ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (i % 10 == 0)
                System.out.println();
        }
    }
}
