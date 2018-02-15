package org.unibl.etf.OPOSTest;

import java.util.ArrayList;
import java.util.Scanner;

public class BankingAlgorithm {
    private static int R, P;
    private static int[][] maxMatrix, allocationMatrix;
    private static int[] availableVector;
    private static int[][] needMatrix;
    private static boolean[] visit;
    private static ArrayList<Integer> safeSeq = new ArrayList<>();
    private static int safeSeqCounter;

    private static void load(int r, int p, String[] mm, String[] am, String[] av) {
        R = r;
        P = p;
        maxMatrix = new int[P][];
        allocationMatrix = new int[P][];
        needMatrix = new int[P][];
        availableVector = new int[R];
        visit = new boolean[P];

        int k = 0;
        for (int i = 0; i < P; i++) {
            maxMatrix[i] = new int[R];
            allocationMatrix[i] = new int[R];
            needMatrix[i] = new int[R];
            for (int j = 0; j < R; j++) {
                maxMatrix[i][j] = Integer.valueOf(mm[k]);
                allocationMatrix[i][j] = Integer.valueOf(am[k++]);
                needMatrix[i][j] = maxMatrix[i][j] - allocationMatrix[i][j];
            }
        }
        for (int i = 0; i < R; i++) {
            availableVector[i] = Integer.valueOf(av[i]);
        }
    }

    private static void safeSequence() {
        for (int i = 0; i < P; i++)
            if (!visit[i] && isAvailable(i)) {
                visit[i] = true;
                for (int j = 0; j < R; j++)
                    availableVector[j] += maxMatrix[i][j];
                safeSeq.add(i);
                safeSequence();
                safeSeq.remove(safeSeq.size() - 1);
                for (int j = 0; j < R; j++)
                    availableVector[j] -= maxMatrix[i][j];
                visit[i] = false;
            }
        if (safeSeq.size() == P) {
            ++safeSeqCounter;
            for (int i = 0; i < P; ++i)
                if (i != (P - 1))
                    System.out.print("P" + safeSeq.get(i) + "-->");
                else
                    System.out.println("P" + safeSeq.get(i));
        }
    }

    private static boolean isAvailable(int i) {
        for (int j = 0; j < availableVector.length; j++) {
            if (availableVector[j] < needMatrix[i][j])
                return false;
        }
        return true;
    }

    // input: 3 5 7,5,3,3,2,2,9,0,2,2,2,2,4,3,3 0,1,0,2,0,0,3,0,2,2,1,1,0,0,2 3,3,2
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of resources: ");
        int r = scanner.nextInt();
        System.out.print("Enter number of processes: ");
        int p = scanner.nextInt();
        System.out.print("Enter " + r * p + " resources for max matrix: ");
        String[] maxMatrix = scanner.next().split(",");
        System.out.print("Enter " + r * p + " resources for allocation matrix: ");
        String[] alloactionMatrix = scanner.next().split(",");
        System.out.print("Enter " + r + " resources for available vector: ");
        String[] availableVector = scanner.next().split(",");

        load(r, p, maxMatrix, alloactionMatrix, availableVector);
        safeSequence();
        if (safeSeqCounter == 0)
            System.out.println("Couldn't allocate resources :(");
    }
}
