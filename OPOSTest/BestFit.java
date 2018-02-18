package org.unibl.etf.OPOSTest;

import java.util.ArrayList;
import java.util.Scanner;

public class BestFit {
    private static class MemoryBlock {
        private int size;
        private int free;
        private int id;
        private static int idMaker;

        public MemoryBlock(int size) {
            this.size = free = size;
            id = ++idMaker;
        }

        public int getId() {
            return id;
        }

        public int getSize() {
            return size;
        }

        public int getFree() {
            return free;
        }

        public void setFree(int used) {
            this.free = used;
        }

        @Override
        public String toString() {
            return "Block " + id + ", size: " + size + ", free: " + free;
        }
    }

    private static ArrayList<MemoryBlock> memory = new ArrayList<>();

    private static void loadBlocks(String[] blocks, String[] used) {
        for (int i = 0; i < blocks.length; i++)
            memory.add(new MemoryBlock(Integer.valueOf(blocks[i])));
        for (int i = 0; i < used.length; ++i) {
            int tmp = Integer.MAX_VALUE;
            int index = -1;
            for (int j = 0; j < memory.size(); j++) {
                int FreeSpace = memory.get(j).getFree() - Integer.valueOf(used[i]);
                if (FreeSpace >= 0 && FreeSpace < tmp) {
                    tmp = FreeSpace;
                    index = j;
                }
            }
            if (index != -1)
                memory.get(index).setFree(tmp);
            else
                System.out.println("Block size: " + used[i] + " is too big!");
        }
        memory.forEach(System.out::println);
    }

    // 512,128,256,1028,64,32,2048 500,78,1028,600,400,10,99999
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter block size (128,256,512,...): ");
        String[] blocks = scanner.next().split(",");
        System.out.print("Enter used block size (128,256,512,...): ");
        String[] used = scanner.next().split(",");
        loadBlocks(blocks, used);
    }
}
